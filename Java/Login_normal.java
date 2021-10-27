package org.gyeongsoton.gyeongsoton_jelly;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class Login_normal extends AppCompatActivity {

    private EditText login_ID, login_pass;
    private Button login_button,join_button,normalbtn,centerbtn,sellerbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_normal);

        normalbtn = findViewById( R.id.normalbtn );
        normalbtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Login_normal.this, Login_normal.class );
                startActivity( intent );
            }
        });

        centerbtn=  findViewById( R.id.centerbtn );
        centerbtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Login_normal.this, Login_center.class );
                startActivity( intent );
            }
        });

        sellerbtn=findViewById( R.id.sellerbtn );
        sellerbtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Login_normal.this, Login_seller.class );
                startActivity( intent );
            }
        });


        login_ID = findViewById( R.id.login_ID );
        login_pass = findViewById( R.id.login_pass );

        join_button = findViewById( R.id.join_button );
        join_button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Login_normal.this, Signup.class );
                startActivity( intent );
            }
        });

        login_button = findViewById( R.id.login_button );
        login_button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserID = login_ID.getText().toString();
                String UserPass = login_pass.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject( response );
                            boolean success = jsonObject.getBoolean( "success" );

                            if(success) {//로그인 성공시

                                String UserEmail = jsonObject.getString( "UserID" );
                                String UserPwd = jsonObject.getString( "UserPass" );
                                String UserName = jsonObject.getString( "UserName" );

                                Toast.makeText( getApplicationContext(), String.format("%s님 환영합니다.", UserName), Toast.LENGTH_SHORT ).show();
                                Intent intent = new Intent( Login_normal.this, MainActivity.class );

                                intent.putExtra( "UserID", UserID );
                                intent.putExtra( "UserPass", UserPass );
                                intent.putExtra( "UserName", UserName );

                                startActivity( intent );

                            } else {//로그인 실패시
                                Toast.makeText( getApplicationContext(), "로그인에 실패하셨습니다.", Toast.LENGTH_SHORT ).show();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest( UserID, UserPass, responseListener );
                RequestQueue queue = Volley.newRequestQueue( Login_normal.this );
                queue.add( loginRequest );

            }
        });
    }
}