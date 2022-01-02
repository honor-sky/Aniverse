package org.gyeongsoton.gyeongsoton_jelly;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class Market_item_info extends AppCompatActivity {

    String userIdx,userAuth,productIdx,productName,productPrice,productIntro,productImage;
    ImageView img;
    TextView productname,productprice,productintro;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_item_info);

        Intent intent= getIntent();
        productIdx= intent.getStringExtra("productIdx");
        userIdx= intent.getStringExtra("userIdx");
        userAuth= intent.getStringExtra("userAuth");

        productname=findViewById(R.id.productname);
        productprice=findViewById(R.id.productprice);
        productintro=findViewById(R.id.productintro);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonResponse = new JSONObject(response); //서버 응답 받아 json 파일 받아옴
                    boolean success = jsonResponse.getBoolean("isSuccess");
                    if (success) {

                        productIdx = Integer.toString(jsonResponse.getInt("productIdx"));
                        productImage = jsonResponse.getString("productImage");
                        productName=jsonResponse.getString("productName");
                        productPrice=Integer.toString(jsonResponse.getInt("productPrice"));
                        productIntro=jsonResponse.getString("productIntro");


                        //첫번째 동물 정보 셋팅
                        productname.setText(productName);
                        Glide.with(Market_item_info.this).load(productImage).into(img);
                        img.setClipToOutline(true);
                        productprice.setText(productPrice);
                        productintro.setText(productIntro);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        marketInfoRequest marketinfoRequest = new marketInfoRequest(productIdx,responseListener);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(marketinfoRequest);



        ImageButton back_btn = (ImageButton)findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Market_list.class);
                intent.putExtra( "userIdx", userIdx);
                intent.putExtra("userAuth",userAuth);
                startActivity(intent);
            }
        });

        Button buy_btn = (Button)findViewById(R.id.buy_btn);
        final Market_bottom_sheet bottomSheetFragment = new Market_bottom_sheet();

        buy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            { bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
                Bundle bundle= new Bundle(); // 파라미터의 숫자는 전달하려는 값의 갯수
                bundle.putString("userIdx",userIdx);
                bundle.putString("userAuth",userAuth);
                bottomSheetFragment.setArguments(bundle);

            }
        });

    }

    public void back(View v){
        Intent intent = new Intent(Market_item_info.this, Market_list.class);
        startActivity(intent);
    }

}