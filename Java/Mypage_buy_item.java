package org.gyeongsoton.gyeongsoton_jelly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Mypage_buy_item extends AppCompatActivity {

    private String productName;
    private int productPrice;
    private String productImage;
    private int productAmount;
    private ImageView b_item,b_item2;
    private TextView item_name1,item_name2,amount1,amount2,price1,price2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_buy_item);

        item_name1=(TextView)findViewById(R.id.item_name1);
        item_name2=(TextView)findViewById(R.id.item_name2);
        amount1=(TextView)findViewById(R.id.amount1);
        amount2=(TextView)findViewById(R.id.amount2);
        price1=(TextView)findViewById(R.id.price1);
        price2=(TextView)findViewById(R.id.price2);
        b_item=(ImageView) findViewById(R.id.b_item);
        b_item2=(ImageView) findViewById(R.id.b_item2);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonResponse = new JSONObject(response); //서버 응답 받아 json 파일 받아옴
                    boolean success = jsonResponse.getBoolean("isSuccess");
                    if (success) {
                        System.out.println("성공");

                        productName = jsonResponse.getString("productName");
                        productAmount = Integer.parseInt(jsonResponse.getString("productAmount"));
                        productPrice = Integer.parseInt(jsonResponse.getString("productPrice"));
                        productImage = jsonResponse.getString("productImage");

                        //첫번째 동물 정보 셋팅
                        item_name1.setText("상품명:  "+productName);
                        amount1.setText("수량:  "+productAmount);                        price1.setText("금액:  "+productPrice*productAmount);
                        Glide.with(Mypage_buy_item.this).load(productImage).into(b_item);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        n_itemCheck_Request n_itemcheck_Request = new n_itemCheck_Request("4", responseListener);
        RequestQueue queue = Volley.newRequestQueue(Mypage_buy_item.this);
        queue.add(n_itemcheck_Request);


    }


}