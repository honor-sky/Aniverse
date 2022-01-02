package org.gyeongsoton.gyeongsoton_jelly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class Funding_monitoring extends AppCompatActivity {

    ImageView image,review_img1,review_img2;
    TextView review1,review2;
    String fundingImage,fundingReviewText,fundingReviewFile1,fundingReviewFile2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funding_monitoring);


        Intent intent= getIntent();
        String fundingIdx= intent.getStringExtra("fundingIdx");

        image=findViewById(R.id.image);
        review_img1=findViewById(R.id.review_img1);
        review_img2=findViewById(R.id.review_img2);
        review1=findViewById(R.id.review1);
        review2=findViewById(R.id.review2);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonResponse = new JSONObject(response); //서버 응답 받아 json 파일 받아옴
                    boolean success = jsonResponse.getBoolean("isSuccess");
                    if (success) {

                        //adoptListIdx=jsonResponse.getInt("adoptListIdx");
                        fundingReviewText=jsonResponse.getString("fundingReviewText");
                        fundingImage=jsonResponse.getString("fundingImage"); //큰 이미지
                        fundingReviewFile1=jsonResponse.getString("fundingReviewFile1");
                        fundingReviewFile2=jsonResponse.getString("fundingReviewFile2");

                        //동물정보 셋팅
                        Glide.with(Funding_monitoring.this).load(fundingImage).into(image); //사진
                        Glide.with(Funding_monitoring.this).load(fundingReviewFile1).into(review_img1);
                        Glide.with(Funding_monitoring.this).load(fundingReviewFile2).into(review_img2);
                        review1.setText(fundingReviewText);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        fundungMonitor_Request fundungmonitor_Request = new fundungMonitor_Request("moongchi", responseListener); //입양 진행중
        RequestQueue queue = Volley.newRequestQueue(Funding_monitoring.this );
        queue.add(fundungmonitor_Request);

        ImageButton back_btn = (ImageButton) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Mypage.class);
                startActivity(intent);
            }
        });

       Button news_add_btn=findViewById(R.id.news_add_btn);
        news_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Funding_monitoring_upload.class);
                startActivity(intent);
            }
        });
    }
}