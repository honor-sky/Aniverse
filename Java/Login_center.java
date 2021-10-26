package org.gyeongsoton.gyeongsoton_jelly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login_center extends AppCompatActivity {

    private EditText login_ID;
    private Button login_button,normalbtn,centerbtn,sellerbtn;

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

        sellerbtn=findViewById( R.id.sellerbtn );
        sellerbtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Login_center.this, Login_seller.class );
                startActivity( intent );
            }
        });

        /*login_button 클릭시 center 데이터베이스 정보와 연결*/
    }

}