package org.gyeongsoton.gyeongsoton_jelly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Login_center extends AppCompatActivity {

    private EditText login_ID;
    private Button signup_btn, normalbtn, centerbtn, sellerbtn, login_btn;
    private ImageButton back_btn;
    String userIdx, userAuth = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginin_center);

        normalbtn = findViewById(R.id.normalbtn);
        normalbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_center.this, Login_normal.class);
                startActivity(intent);
            }
        });

        centerbtn = findViewById(R.id.centerbtn);
        centerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_center.this, Login_center.class);
                startActivity(intent);
            }
        });

        sellerbtn = findViewById(R.id.sellerbtn);
        sellerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_center.this, Login_seller.class);
                startActivity(intent);
            }
        });

        signup_btn = findViewById(R.id.signup_btn);
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_center.this, Signup.class);
                startActivity(intent);
            }
        });

        back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_center.this, Mypage.class);
                startActivity(intent);
            }
        });


        /*login_button 클릭시 center 데이터베이스 정보와 연결*/
        login_btn = findViewById(R.id.login_button);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserID = login_ID.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("isSuccess");

                            if (success) {//로그인 성공시

                                userIdx = Integer.toString(jsonObject.getInt("userIdx"));
                                userAuth = jsonObject.getString("userAuth"); //유저의 유형

                                Toast.makeText(getApplicationContext(), String.format("%s님 환영합니다.", UserID), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Login_center.this, Mypage.class); //로그인 성공시 마이페이지로 화면 전환
                                //화면 전환 시 전달되는 값들
                                intent.putExtra("userIdx", userIdx);
                                intent.putExtra("userAuth", userAuth);
                                startActivity(intent);

                            } else {//로그인 실패시
                                Toast.makeText(getApplicationContext(), "로그인에 실패하셨습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
              /*  LoginRequest loginRequest = new LoginRequest( UserID,responseListener );
                RequestQueue queue = Volley.newRequestQueue( Login_center.this );
                queue.add( loginRequest );*/

            }
        });

    }
}