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

public class Funding_upload extends AppCompatActivity {

    private final int Fragment_1 = 1;
    private final int Fragment_2 = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funding_upload);

        Button btn1 = (Button)findViewById(R.id.animal_btn);
        Button btn2 = (Button)findViewById(R.id.center_btn);

        btn1.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                FragmentView(Fragment_1);
                btn1.setPressed(true);
                btn2.setPressed(false);


                return true;
            }
        });


        btn2.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                FragmentView(Fragment_2);
                btn2.setPressed(true);
                btn1.setPressed(false);

                return true;
            }
        });

        FragmentView(Fragment_1);
        btn1.setPressed(true);
        btn2.setPressed(false);


    }

    private void FragmentView(int fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (fragment) {
            case 1:
                // 첫번 째 프래그먼트 호출
                Fragment fragment1 = new Funding_animal_upload_fragment();
                transaction.replace(R.id.funding_info_container, fragment1);
                transaction.commit();
                break;

            case 2:
                // 두번 째 프래그먼트 호출
                Fragment fragment2 = new Funding_center_upload_fragment();
                transaction.replace(R.id.funding_info_container, fragment2);
                transaction.commit();
                break;
        }
    }
}