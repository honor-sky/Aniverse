package org.tecttown.aniverse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Adopt_monitor extends AppCompatActivity {

    private final int Fragment_1 = 1;
    private final int Fragment_2 = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopt_monitor);

        Button diagno_btn = (Button)findViewById(R.id.diagno_btn);
        Button review_btn = (Button)findViewById(R.id.review_btn);

        diagno_btn.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                FragmentView(Fragment_1);
                diagno_btn.setPressed(true);
                review_btn.setPressed(false);

                return true;
            }
        });

        review_btn.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                FragmentView(Fragment_2);
                diagno_btn.setPressed(false);
                review_btn.setPressed(true);

                return true;
            }
        });

        FragmentView(Fragment_1);
        diagno_btn.setPressed(true);
        review_btn.setPressed(false);

        Button add_btn = (Button)findViewById(R.id.news_add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Adopt_monitoring_upload.class);
                startActivity(intent);
            }
        });

        ImageButton back_btn = (ImageButton) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Adopt_list.class);
                startActivity(intent);
            }
        });

    }

    private void FragmentView(int fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (fragment) {
            case 1:
                // 첫번째 프래그먼트 호출
                Fragment fragment1 = new Adopt_monitor_diagno_fragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.news_container,fragment1).commit();
                break;

            case 2:
                // 두번째 프래그먼트 호출
                Fragment fragment2 = new Adopt_monitor_review_fragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.news_container,fragment2).commit();
                break;
        }
    }
}

