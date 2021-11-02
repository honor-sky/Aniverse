package org.tecttown.aniverse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.MotionEvent;
import android.widget.ImageButton;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class Market_list extends AppCompatActivity {

    private final int Fragment_1 = 1;
    private final int Fragment_2 = 2;
    private final int Fragment_3 = 3;
    private final int Fragment_4 = 4;
    private final int Fragment_5 = 5;

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

        ImageButton mypage_btn = (ImageButton)findViewById(R.id.mypage_btn);
        mypage_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Mypage.class);
                startActivity(intent);
            }
        });

        Button btn1 = (Button)findViewById(R.id.category_all);
        Button btn2 = (Button)findViewById(R.id.category_hy_product);
        Button btn3 = (Button)findViewById(R.id.category_clothe);
        Button btn4 = (Button)findViewById(R.id.category_snack);
        Button btn5 = (Button)findViewById(R.id.category_toy);

        btn1.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                FragmentView(Fragment_1);
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
                FragmentView(Fragment_2);
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
                FragmentView(Fragment_3);
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
                FragmentView(Fragment_4);
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
                FragmentView(Fragment_5);
                btn5.setPressed(true);
                btn1.setPressed(false);
                btn2.setPressed(false);
                btn3.setPressed(false);
                btn4.setPressed(false);

                return true;
            }
        });

        FragmentView(Fragment_1);
        btn1.setPressed(true);
        btn2.setPressed(false);
        btn3.setPressed(false);
        btn4.setPressed(false);
        btn5.setPressed(false);

        Button add_btn = (Button)findViewById(R.id.item_add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Market_item_upload.class);
                startActivity(intent);
            }
        });
    }

    private void FragmentView(int fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (fragment) {
            case 1:
                // 첫번째 프래그먼트 호출
                Fragment fragment1 = new Market_category_all();
                transaction.replace(R.id.item_list_container, fragment1);
                transaction.commit();
                break;

            case 2:
                // 두번째 프래그먼트 호출
                Fragment fragment2 = new Market_category_hy();
                transaction.replace(R.id.item_list_container, fragment2);
                transaction.commit();
                break;

            case 3:
                // 세번째 프래그먼트 호출
                Fragment fragment3 = new Market_category_clothe();
                transaction.replace(R.id.item_list_container, fragment3);
                transaction.commit();
                break;

            case 4:
                // 세번째 프래그먼트 호출
                Fragment fragment4 = new Market_category_snack();
                transaction.replace(R.id.item_list_container, fragment4);
                transaction.commit();
                break;

            case 5:
                // 세번째 프래그먼트 호출
                Fragment fragment5 = new Market_category_toy();
                transaction.replace(R.id.item_list_container, fragment5);
                transaction.commit();
                break;
        }
    }


}