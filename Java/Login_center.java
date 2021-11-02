package org.gyeongsoton.gyeongsoton_jelly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class Login_center extends AppCompatActivity {

    private EditText login_ID;
    private Button signup_btn,normalbtn,centerbtn,sellerbtn;
    private ImageButton back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginin_center);

        normalbtn = findViewById( R.id.normalbtn );
        normalbtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Login_center.this, Login_normal.class );
                startActivity( intent );
            }
        });

        centerbtn=  findViewById( R.id.centerbtn );
        centerbtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Login_center.this, Login_center.class );
                startActivity( intent );
            }
        });

        sellerbtn = findViewById( R.id.sellerbtn );
        sellerbtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Login_center.this, Login_seller.class );
                startActivity( intent );
            }
        });

        signup_btn = findViewById( R.id.signup_btn );
        signup_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Login_center.this, Signup.class );
                startActivity( intent );
            }
        });

        back_btn = findViewById( R.id.back_btn );
        back_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Login_center.this, Mypage.class );
                startActivity( intent );
            }
        });

        /*login_button 클릭시 center 데이터베이스 정보와 연결*/
    }

}