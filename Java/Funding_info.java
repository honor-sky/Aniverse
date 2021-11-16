package org.gyeongsoton.gyeongsoton_jelly;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class Funding_info extends AppCompatActivity {

    String fundingPurpose,fundingName,fundingPeriod,fundingImage,fundingIdx,userIdx,userAuth,jellyNum;
    int fundingAmount;
    private TextView funding_amount,funding_purpose,funding_name,funding_period,center_name;
    private ImageView img;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funding_info);

        img = findViewById(R.id.img);
        funding_amount = findViewById(R.id.fundingamount);
        funding_purpose = findViewById(R.id.fundingpurpose);
        funding_name = findViewById(R.id.fundingname);
        funding_period = findViewById(R.id.fundingperiod);
        center_name = findViewById(R.id.centername);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonResponse = new JSONObject(response); //서버 응답 받아 json 파일 받아옴
                    boolean success = jsonResponse.getBoolean("isSuccess");
                    if (success) {

                        fundingAmount=jsonResponse.getInt("fundingAmount");
                        fundingPurpose=jsonResponse.getString("fundingPurpose");
                        fundingName=jsonResponse.getString("fundingName");
                        fundingImage=jsonResponse.getString("fundingImage");
                        fundingPeriod = jsonResponse.getString("fundingPeriod");
                        //centerName = jsonResponse.getString("centerName");
                        System.out.println(fundingImage);

                        //동물정보 셋팅
                        funding_name.setText(fundingName); //펀딩명
                        Glide.with(Funding_info.this).load(fundingImage).into(img);
                        funding_purpose.setText("펀딩 내용: "+fundingPurpose);//펀딩내용
                        funding_amount.setText("펀딩 목표 금액: "+fundingAmount+"원");//펀딩 금액
                        funding_period.setText("펀딩기간: "+fundingPeriod);//펀딩기간
                        //center_name.setText("주관 보호소명: "+centerName);//주관 보호소명

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        FundingInfo_Request fund_info_Request = new FundingInfo_Request("16",responseListener); //입양 진행중
        RequestQueue queue = Volley.newRequestQueue(Funding_info.this );
        queue.add(fund_info_Request);


        Button d_btn = (Button)findViewById(R.id.fund_btn);
        d_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), String.format("젤리 기부 감사합니다!!"), Toast.LENGTH_SHORT).show();

                /*Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject( response );
                            boolean success = jsonObject.getBoolean( "isSuccess" );

                            //업로드 성공시
                            if (success) {
                                Toast.makeText(getApplicationContext(), String.format("젤리 "+jellyNum+"개를 기부했습니다."), Toast.LENGTH_SHORT).show();

                                //업로드 실패시
                            } else {
                                Toast.makeText(getApplicationContext(), "젤리 기부를 하지 못했습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }*/


                //서버로 Volley를 이용해서 요청
               // Funding_request fundRequest = new Funding_request("1","2","18",jellyNum,responseListener);
               // RequestQueue queue = Volley.newRequestQueue( Funding_info.this );
               // queue.add( fundRequest );
            }
        });


        SeekBar seekbar = findViewById(R.id.jellyBar);
        TextView textView = findViewById(R.id.countJelly);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress < 10){
                    progress = 10;
                }else if(progress>100){
                    progress = 100;
                }else{
                    //NOP
                }
                textView.setText(progress+"개");
            }

            ProgressBar progressBar = findViewById(R.id.progressBar);
            TextView textView2 = findViewById(R.id.percent);

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch( SeekBar seekBar) {
            }

        });

    }

}