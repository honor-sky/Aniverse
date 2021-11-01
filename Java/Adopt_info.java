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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Adopt_info extends AppCompatActivity {

    //Adopt_list에서 클릭한 동물 정보의 서버 인덱스 전달
    Intent intent = getIntent(); /*데이터 수신*/
    String imgePath = intent.getExtras().getString("1");

    private static String URL = "http://13.209.98.213/";

    String Species;
    TextView species_text = (TextView) findViewById(R.id.species_text);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //동물 사진 설정
        ImageView petImage = (ImageView)findViewById(R.id.petImage);
        Glide.with(this).load(imgePath).into(petImage);
        //동물 정보 설정


        //'신청하기' 버튼 누르면 신청자 정보 입력 화면으로 넘어감
        Button request_btn = (Button) findViewById(R.id.request_btn);
        request_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Adopt_request.class);
                startActivity(intent);
            }
        });

        //뒤로가기 버튼
        ImageButton back_btn = (ImageButton) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Adopt_list.class);
                startActivity(intent);
            }
        });

    }
}