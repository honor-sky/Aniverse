package org.gyeongsoton.gyeongsoton_jelly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

public class Protect_request extends AppCompatActivity {

    String userIdx,protectListIdx,userAuth,animalIdx;
    private TextInputEditText username, phone,message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protect_request);

        Intent intent= getIntent();
        protectListIdx= intent.getStringExtra("protectListIdx");
        animalIdx = intent.getExtras().getString("animalIdx");
        userIdx= intent.getStringExtra("userIdx");
        userAuth= intent.getStringExtra("userAuth");

        //뒤로가기 버튼
        ImageButton back_btn = (ImageButton) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Protect_info.class);
                animalIdx = intent.getExtras().getString("animalIdx");
                userIdx= intent.getStringExtra("userIdx");
                userAuth= intent.getStringExtra("userAuth");
                startActivity(intent);
            }
        });

        username = findViewById(R.id.username);
        phone = findViewById(R.id.phone);
        message = findViewById(R.id.message);
        final String contactName = username.getText().toString();
        final String protectComment = phone.getText().toString();
        final String contactPhoneNum = message.getText().toString();

        //정보 입력 후 진짜 신청 버튼
        Button request_btn = (Button)findViewById(R.id.request_btn);
        request_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean( "isSuccess" );

                            if (success) {

                                Intent intent = new Intent(getApplicationContext(), Adopt_confirm.class);
                                startActivity(intent);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                //서버로 Volley를 이용해서 요청
                Protect_request_Request Protect_rere = new Protect_request_Request(userIdx,protectListIdx,contactName,protectComment,contactPhoneNum,responseListener);
                RequestQueue queue = Volley.newRequestQueue( Protect_request.this );
                queue.add( Protect_rere );

            }
        });


    }
}
