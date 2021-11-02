package org.tecttown.aniverse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Funding_list extends AppCompatActivity {

    private final int Fragment_1 = 1;
    private final int Fragment_2 = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funding_list);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_funding_list);

        ImageButton home_btn = (ImageButton)findViewById(R.id.home_btn);
        home_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        ImageButton adopt_btn = (ImageButton)findViewById(R.id.adopt_btn);
        adopt_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Adopt_list.class);
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
                Intent intent = new Intent(getApplicationContext(), Market_list.class);
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


        Button add_btn = (Button)findViewById(R.id.funding_add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Funding_upload.class);
                startActivity(intent);
            }
        });

        Button btn1 = (Button)findViewById(R.id.animal_funding_btn);
        Button btn2 = (Button)findViewById(R.id.center_funding_btn);

        btn1.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                FragmentView(Fragment_1);
                btn1.setPressed(true);
                btn2.setPressed(false);


                return true;
            }
        });


        btn2.setOnTouchListener(new View.OnTouchListener() { //입양완료 버튼 누르면 데이터도 바뀌고 검색창도 뜸
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                FragmentView(Fragment_2);
                btn2.setPressed(true);
                btn1.setPressed(false);

                return true;
            }
        });

        FragmentView(Fragment_1);
        btn1.setPressed(true);
        btn2.setPressed(false);

    }

    private void FragmentView(int fragment) {

        //FragmentTransaction를 이용해 프래그먼트를 사용합니다.
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (fragment) {
            case 1:
                // 첫번 째 프래그먼트 호출
                Fragment fragment1 = new Funding_animal_list_fragment();
                transaction.replace(R.id.funding_list_container, fragment1);
                transaction.commit();
                break;

            case 2:
                // 두번 째 프래그먼트 호출
                Fragment fragment2 = new Funding_center_list_fragment();
                transaction.replace(R.id.funding_list_container, fragment2);
                transaction.commit();
                break;
        }
    }
}