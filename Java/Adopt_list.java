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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class Adopt_list extends AppCompatActivity {

    ArrayList<Adopt_list_data> items= new ArrayList<>();
    ImageView image1;
    //ItemAdapter adapter;

    String URL="http://3.34.166.156/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_adopt_list);

        /*JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, URL, null, new Response.Listener<JSONObject>() {
            //post 방식이 갱신에도 적합
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(Adopt_list.this, response.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, response.toString());

                //파라미터로 응답받은 결과 JsonArray를 분석
                items.clear();
                try {

                        int no= Integer.parseInt(response.getString("no")); //no가 문자열이라서 바꿔야함.
                        String imgPath=response.getString("imgPath");
                        imgPath = URL+imgPath;

                        items.add(0,new Adopt_list_data(no,imgPath)); // 첫 번째 매개변수는 몇번째에 추가 될지, 제일 위에 오도록
                    } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Adopt_list.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });

        image1.findViewById(R.id.imageView);
        Glide.with(this).load(items.get(0).getImgPath()).into(image1);*/


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

        adopt_tab.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                adopt_tab.setPressed(true);
                protect_tab.setPressed(false);
                complete_tab.setPressed(false);

                return true;
            }
        });

        protect_tab.setOnTouchListener(new View.OnTouchListener() { //입양완료 버튼 누르면 데이터도 바뀌고 검색창도 뜸
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                protect_tab.setPressed(true);
                adopt_tab.setPressed(false);
                complete_tab.setPressed(false);
                return true;
            }
        });

        complete_tab.setOnTouchListener(new View.OnTouchListener() { //입양완료 버튼 누르면 데이터도 바뀌고 검색창도 뜸
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                protect_tab.setPressed(false);
                adopt_tab.setPressed(false);
                complete_tab.setPressed(true);
                return true;
            }
        });

        Button add_btn = (Button)findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Adopt_upload.class);
                startActivity(intent);
            }
        });

        ImageView image1 = (ImageView)findViewById(R.id.imageView);
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Adopt_info.class);
                intent.putExtra("1",items.get(0).getImgPath()); //items 리스트를 넘겨주는 방법은 없나?...
                startActivity(intent);
            }
        });

      /*  //실제 요청 작업을 수행해주는 요청큐 객체 생성
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        //요청큐에 요청 객체 생성
        requestQueue.add(jsonObjReq);*/


    }

}