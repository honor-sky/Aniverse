package org.gyeongsoton.gyeongsoton_jelly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Adopt_confirm extends AppCompatActivity {

    ImageButton back_btn;
    String userIdx,userAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopt_confirm);

        Intent intent =new Intent();
        userIdx= intent.getStringExtra("userIdx");
        userAuth= intent.getStringExtra("userAuth");

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Adopt_list.class);
                intent.putExtra("userIdx", userIdx);
                intent.putExtra("userAuth", userAuth);
                startActivity(intent);
            }
        });

    }
}