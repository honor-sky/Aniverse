package org.tecttown.aniverse;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.MotionEvent;
import android.widget.ImageButton;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Market_list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_market_list);

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
        funding_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Market_list.class);
                startActivity(intent);
            }
        });

        Button btn1 = (Button)findViewById(R.id.button);
        Button btn2 = (Button)findViewById(R.id.button2);
        Button btn3 = (Button)findViewById(R.id.button3);
        Button btn4 = (Button)findViewById(R.id.button4);
        Button btn5 = (Button)findViewById(R.id.button5);

        btn1.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                btn1.setPressed(true);
                btn2.setPressed(false);
                btn3.setPressed(false);
                btn4.setPressed(false);
                btn5.setPressed(false);


                return true;
            }
        });


        btn2.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                btn2.setPressed(true);
                btn1.setPressed(false);
                btn3.setPressed(false);
                btn4.setPressed(false);
                btn5.setPressed(false);

                return true;
            }
        });

        btn3.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                btn3.setPressed(true);
                btn1.setPressed(false);
                btn2.setPressed(false);
                btn4.setPressed(false);
                btn5.setPressed(false);

                return true;
            }
        });

        btn4.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                btn4.setPressed(true);
                btn1.setPressed(false);
                btn2.setPressed(false);
                btn3.setPressed(false);
                btn5.setPressed(false);

                return true;
            }
        });

        btn5.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                btn5.setPressed(true);
                btn1.setPressed(false);
                btn2.setPressed(false);
                btn3.setPressed(false);
                btn4.setPressed(false);

                return true;
            }
        });

        Button add_btn = (Button)findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Market_item_upload.class);
                startActivity(intent);
            }
        });

        ImageView image1 = (ImageView)findViewById(R.id.imageView);
        image1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Market_item_info.class);
                startActivity(intent);
            }
        });
    }


}