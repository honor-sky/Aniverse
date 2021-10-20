package org.gyeongsoton.gyeongsoton_jelly;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class Login_page extends AppCompatActivity {

    private EditText login_ID, login_pass;
    private Button login_button,join_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);


        login_ID = findViewById( R.id.login_ID );
        login_pass = findViewById( R.id.login_pass );

        join_button = findViewById( R.id.join_button );
        join_button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Login_page.this, Signup_page.class );
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
                                Intent intent = new Intent( Login_page.this, MainActivity.class );

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
                RequestQueue queue = Volley.newRequestQueue( Login_page.this );
                queue.add( loginRequest );

            }
        });
    }
}