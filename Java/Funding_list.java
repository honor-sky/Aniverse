package org.gyeongsoton.gyeongsoton_jelly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class Funding_list extends AppCompatActivity {

    private ImageView img1_1;
    private TextView text1_1;
    String fundingImage,fundingName;
    Button add_btn;
    int fundingIdx;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funding_list);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_funding_list);


        img1_1 = findViewById(R.id.img1_1);
        text1_1 = findViewById(R.id.text1_1);
        add_btn = findViewById(R.id.add_btn);



        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonResponse = new JSONObject(response); //서버 응답 받아 json 파일 받아옴
                    boolean success = jsonResponse.getBoolean("isSuccess");
                    if (success) {

                        fundingIdx = jsonResponse.getInt("fundingIdx");
                        fundingImage = jsonResponse.getString("fundingImage");
                        fundingName = jsonResponse.getString("fundingName");

                        System.out.println(fundingIdx);

                        //첫번째 펀딩 정보 셋팅
                        text1_1.setText(fundingName);
                        Glide.with(Funding_list.this).load(fundingImage).into(img1_1);
                        img1_1.setClipToOutline(true);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        Fundinglist_Request fundinglist_request = new Fundinglist_Request(responseListener);
        RequestQueue queue = Volley.newRequestQueue(Funding_list.this);
        queue.add(fundinglist_request);

        //이미지 누르면 세부화면으로 전환
        img1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Funding_info.class);
                startActivity(intent);
            }
        });

        Button funding_add_btn=(Button) findViewById(R.id.funding_add_btn);
        funding_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Funding_list.this, Funding_upload.class);
                startActivity(intent);
            }
        });

        //하단바
        ImageButton home_btn = (ImageButton) findViewById(R.id.home_btn);
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Funding_list.this, MainActivity.class);
                //intent.putExtra( "userIdx", userIdx);
                //intent.putExtra( "userAuth", userAuth);
                startActivity(intent);
            }
        });

        ImageButton adopt_btn = (ImageButton) findViewById(R.id.adopt_btn);
        adopt_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Adopt_list.class);
                //intent.putExtra( "userIdx", userIdx);
                //intent.putExtra( "userAuth", userAuth);
                startActivity(intent);
            }
        });

        ImageButton funding_btn = (ImageButton) findViewById(R.id.funding_btn);
        funding_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Funding_list.class);
               // intent.putExtra( "userIdx", userIdx);
               // intent.putExtra( "userAuth", userAuth);
                startActivity(intent);
            }
        });
        ImageButton market_btn = (ImageButton) findViewById(R.id.market_btn);
        market_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Market_list.class);
                //intent.putExtra( "userIdx", userIdx);
                //intent.putExtra( "userAuth", userAuth);
                startActivity(intent);
            }
        });
    }
}