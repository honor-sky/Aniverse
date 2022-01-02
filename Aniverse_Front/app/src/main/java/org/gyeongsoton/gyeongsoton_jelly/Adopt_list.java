package org.gyeongsoton.gyeongsoton_jelly;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;


public class Adopt_list extends AppCompatActivity {

    private ImageView ani_1;
    private TextView textView1;
    String userIdx,userAuth;


    private final int Fragment_1 = 1;
    private final int Fragment_2 = 2;
    private final int Fragment_3 = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_adopt_list);

        Intent intent= getIntent();
        //userIdx= intent.getStringExtra("userIdx");
        //userAuth= intent.getStringExtra("userAuth");
        //userIdx="28";
        //userAuth="U";
        Button add_btn = (Button)findViewById(R.id.add_btn);


        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Adopt_protect_upload.class);
               // intent.putExtra( "userIdx", userIdx);
               // intent.putExtra( "userAuth", userAuth);
                startActivity(intent);
            }
        });

        ImageButton adopt_btn = (ImageButton)findViewById(R.id.adopt_btn);
        adopt_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Adopt_list.class);
                //intent.putExtra( "userIdx", userIdx);
                //intent.putExtra( "userAuth", userAuth);
                startActivity(intent);
            }
        });

        ImageButton home_btn = (ImageButton)findViewById(R.id.home_btn);
        home_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        ImageButton funding_btn = (ImageButton)findViewById(R.id.funding_btn);
        funding_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Funding_list.class);
                startActivity(intent);
            }
        });
        ImageButton market_btn = (ImageButton)findViewById(R.id.market_btn);
        market_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Market_list.class);
                startActivity(intent);
            }
        });
        ImageButton mypage_btn = (ImageButton)findViewById(R.id.mypage_btn);
        mypage_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Mypage.class);
                startActivity(intent);
            }
        });

        /*입양, 입양완료 버튼 전환*/
        Button adopt_tab = (Button)findViewById(R.id.adopt_tab);
        Button protect_tab = (Button)findViewById(R.id.protect_tab);
        Button complete_tab = (Button)findViewById(R.id.complete_tab);

        //FragmentView(Fragment_1);
        //adopt_tab.setPressed(true);
        //protect_tab.setPressed(false);
        //complete_tab.setPressed(false);

        adopt_tab.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                FragmentView(Fragment_1);
                adopt_tab.setPressed(true);
                protect_tab.setPressed(false);
                complete_tab.setPressed(false);
                return true;
            }
        });

        protect_tab.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                FragmentView(Fragment_2);
                protect_tab.setPressed(true);
                adopt_tab.setPressed(false);
                complete_tab.setPressed(false);
                return true;

            }
        });

        complete_tab.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                FragmentView(Fragment_3);
                complete_tab.setPressed(true);
                adopt_tab.setPressed(false);
                protect_tab.setPressed(false);
                return true;

            }
        });



    }

    private void FragmentView(int fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (fragment) {
            case 1:
                // 첫번째 프래그먼트 호출
                Fragment fragment1 = new Adopt_animal_fragment(); //미리 정의
                getSupportFragmentManager().beginTransaction().replace(R.id.adopt_list_container,fragment1).commit(); //공통으로 뺄 것
                break;

            case 2:
                // 두번째 프래그먼트 호출
                Fragment fragment2 = new Adopt_protect_fragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.adopt_list_container,fragment2).commit();

                break;

            case 3:
                // 세번째 프래그먼트 호출
                Fragment fragment3 = new Adopt_complete_fragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.adopt_list_container,fragment3).commit();

                break;
        }
    }

}