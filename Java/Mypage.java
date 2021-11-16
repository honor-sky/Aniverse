package org.gyeongsoton.gyeongsoton_jelly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class Mypage extends AppCompatActivity {

    private Button buy_sell_itme,my_adopt,login_btn,signup_btn;
    private ImageButton jellt_shop_btn;
    private ImageView my_fund_img1,my_fund_img2,my_fund_img3;
    private TextView jelly_amount;
    String userId,fundingImage,fundingName,userJellyNum;
    String userIdx,userAuth=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        Intent intent= getIntent();
         userIdx= intent.getStringExtra("userIdx");
         userAuth= intent.getStringExtra("userAuth");

        login_btn = (Button)findViewById(R.id.login_btn);
        signup_btn = (Button)findViewById(R.id.signup_btn);
        jellt_shop_btn = findViewById(R.id.jellt_shop_btn);
        buy_sell_itme= findViewById(R.id.buy_sell_itme);
        my_adopt= findViewById(R.id.my_adopt);
        buy_sell_itme= findViewById(R.id.buy_sell_itme);
        my_fund_img1=findViewById(R.id.my_fund_img1);
        my_fund_img2=findViewById(R.id.my_fund_img2);
        my_fund_img3=findViewById(R.id.my_fund_img3);
        jelly_amount=findViewById(R.id.jelly_amount);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login_normal.class);
                startActivity(intent);
            }
        });

        if(!userIdx.equals("")){ //로그인 한 상태

            //로그인 버튼을 아이디로 바꿔줘야 함
            if(userAuth.equals("U")) {
                System.out.println(userId);
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("isSuccess");

                            if (success) {

                                userId = jsonObject.getString("userId");
                                fundingName = jsonObject.getString("fundingName");
                                fundingImage = jsonObject.getString("fundingImage");
                               // userJellyNum= jsonObject.getString("userJellyNum");

                                System.out.println(userId);
                                login_btn.setText(userId);

                                Glide.with(Mypage.this).load(fundingImage).into(my_fund_img1);
                            }

                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                        }
                    };

                    MypageRequest mypageRequest = new MypageRequest("28", responseListener);
                    RequestQueue queue = Volley.newRequestQueue(Mypage.this);
                    queue.add(mypageRequest );
                System.out.println(userId);
                login_btn.setText(userId);
                //jelly_amount.setText(userJellyNum);

                    //내가 산 물건
                 buy_sell_itme.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick (View view){
                        Intent intent = new Intent(getApplicationContext(), Mypage_buy_item.class);
                        intent.putExtra( "userIdx", userIdx);
                        intent.putExtra( "userAuth", userAuth);
                        startActivity(intent);
                    }
                    });

                my_fund_img1.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick (View view){
                        Intent intent = new Intent(getApplicationContext(), Funding_monitoring.class);
                        intent.putExtra( "fundingName", fundingName);
                        intent.putExtra( "userIdx", userIdx);
                        intent.putExtra( "userAuth", userAuth);
                        startActivity(intent);
                    }
                    });

                }else if(userAuth.equals("C")){
                //센터의 경우

            }else if(userAuth.equals("M")){
                //업로드한 상품 및 주문 들어온 상품
                buy_sell_itme.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick (View view){
                        Intent intent = new Intent(getApplicationContext(), Mypage_seller_item.class);
                        intent.putExtra( "userIdx", userIdx);
                        intent.putExtra( "userAuth", userAuth);
                        startActivity(intent);
                    }
                });
                login_btn.setText(userId);
            }
        }else{ //로그인 안 한 상태

        }

        //회원가입
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                startActivity(intent);
            }
        });

        //jelly_shop 연결
        jellt_shop_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Jellyshop.class);
                //intent.putExtra( "userIdx", userIdx);
                //intent.putExtra( "userAuth", userAuth);
                startActivity(intent);
            }
        });

        buy_sell_itme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Mypage_buy_item.class);
               // intent.putExtra( "userIdx", userIdx);
                //intent.putExtra( "userAuth", userAuth);
                startActivity(intent);
            }
        });

        /*하단바*/
        ImageButton home_btn = (ImageButton)findViewById(R.id.home_btn);
        home_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //intent.putExtra( "userIdx", userIdx);
                //intent.putExtra( "userAuth", userAuth);
                startActivity(intent);
            }
        });

        ImageButton adopt_btn = (ImageButton)findViewById(R.id.adopt_btn);
        adopt_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Adopt_list.class);
               // intent.putExtra( "userIdx", userIdx);
               // intent.putExtra( "userAuth", userAuth);
                startActivity(intent);
            }
        });

        ImageButton funding_btn = (ImageButton)findViewById(R.id.funding_btn);
        funding_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Funding_list.class);
               // intent.putExtra( "userIdx", userIdx);
                //intent.putExtra( "userAuth", userAuth);
                startActivity(intent);
            }
        });
        ImageButton market_btn = (ImageButton)findViewById(R.id.market_btn);
        market_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Market_list.class);
                //intent.putExtra( "userIdx", userIdx);
                //intent.putExtra( "userAuth", userAuth);
                startActivity(intent);
            }
        });
        ImageButton mypage_btn = (ImageButton)findViewById(R.id.mypage_btn);
        mypage_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Mypage.class);
                //intent.putExtra( "userIdx", userIdx);
                //intent.putExtra( "userAuth", userAuth);
                startActivity(intent);
            }
        });

    }
}
