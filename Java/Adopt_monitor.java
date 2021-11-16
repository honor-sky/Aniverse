package org.gyeongsoton.gyeongsoton_jelly;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class Adopt_monitor extends AppCompatActivity {

    private final int Fragment_1 = 1;
    private final int Fragment_2 = 2;
    String userIdx,userAuth=null;
    String animalImage,animalIdx,adoptDate,adoptReviewFile1,adoptReviewFile2,adoptReviewText;
    Button date_square;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopt_monitor);

        Intent intent= getIntent();
        userIdx= intent.getStringExtra("userIdx");
        userAuth= intent.getStringExtra("userAuth");

        date_square=findViewById(R.id.date_square);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonResponse = new JSONObject(response); //서버 응답 받아 json 파일 받아옴
                    boolean success = jsonResponse.getBoolean("isSuccess");
                    if (success) {

                        animalIdx = Integer.toString(jsonResponse.getInt("animalIdx"));
                        animalImage = jsonResponse.getString("animalImage");
                        adoptDate=jsonResponse.getString("adoptDate");
                        adoptReviewFile1=jsonResponse.getString("adoptReviewFile1"); //진단서 사진
                        adoptReviewFile2=jsonResponse.getString("adoptReviewFile2");//후기 사진
                        adoptReviewText=jsonResponse.getString("adoptReviewText");//후기


                        Glide.with(Adopt_monitor.this).load(animalImage).into(image);
                        image.setClipToOutline(true);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        adoptMonitor_Request adoptmonitor_request = new adoptMonitor_Request(animalIdx,userIdx,responseListener);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(adoptmonitor_request);


        Button diagno_btn = (Button)findViewById(R.id.diagno_btn);
        Button review_btn = (Button)findViewById(R.id.review_btn);

        diagno_btn.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                FragmentView(Fragment_1);
                diagno_btn.setPressed(true);
                review_btn.setPressed(false);

                return true;
            }
        });

        review_btn.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                FragmentView(Fragment_2);
                diagno_btn.setPressed(false);
                review_btn.setPressed(true);

                return true;
            }
        });

        FragmentView(Fragment_1);
        diagno_btn.setPressed(true);
        review_btn.setPressed(false);

        Button add_btn = (Button)findViewById(R.id.news_add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Adopt_monitoring_upload.class);
                startActivity(intent);
            }
        });

        ImageButton back_btn = (ImageButton) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Adopt_list.class);
                intent.putExtra( "userIdx", userIdx);
                intent.putExtra( "userAuth", userAuth);
                startActivity(intent);
            }
        });

    }

    private void FragmentView(int fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (fragment) {
            case 1:
                // 첫번째 프래그먼트 호출 //진단서 사진 전달
                Fragment fragment1 = new Adopt_monitor_diagno_fragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.news_container,fragment1).commit();

                Bundle bundle = new Bundle(); // 파라미터의 숫자는 전달하려는 값의 갯수
                bundle.putString("adoptReviewFile1",adoptReviewFile1);
                fragment1.setArguments(bundle);
                break;

            case 2:
                // 두번째 프래그먼트 호출 //후기 사진과 후기글 전달
                Fragment fragment2 = new Adopt_monitor_review_fragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.news_container,fragment2).commit();

                Bundle bundle2 = new Bundle(2); // 파라미터의 숫자는 전달하려는 값의 갯수
                bundle2.putString("adoptReviewFile1",adoptReviewFile2);
                bundle2.putString("adoptReviewText",adoptReviewText);
                fragment2.setArguments(bundle2);
                break;
        }
    }
}
