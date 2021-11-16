package org.gyeongsoton.gyeongsoton_jelly;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class Mypage_seller_item extends AppCompatActivity {

    private final int Fragment_1 = 1;
    private final int Fragment_2 = 2;
    String userIdx,userAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_seller_item);

        Intent intent= getIntent();
         userIdx= intent.getStringExtra("userIdx");
         userAuth= intent.getStringExtra("userAuth");

        Button btn1 = (Button)findViewById(R.id.order);
        Button btn2 = (Button)findViewById(R.id.sell);


        findViewById(R.id.order).setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                FragmentView(Fragment_1);
                btn1.setPressed(true);
                btn2.setPressed(false);
                return true;
            }
        });

        findViewById(R.id.sell).setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                FragmentView(Fragment_2);
                btn2.setPressed(true);
                btn1.setPressed(false);
                return true;

            }
        });
        btn1.setPressed(true);
        btn2.setPressed(false);
        FragmentView(Fragment_1);

    }
    private void FragmentView(int fragment) {

        //FragmentTransaction를 이용해 프래그먼트를 사용합니다.
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (fragment) {
            case 1: //상인이 주문 받은 상품
                // 첫번 째 프래그먼트 호출
                Fragment fragment1 = new Mypage_orderitem_fragment();
                transaction.replace(R.id.item_container, fragment1);
                transaction.commit();

                Bundle bundle = new Bundle(); // 파라미터의 숫자는 전달하려는 값의 갯수
                bundle.putString("userIdx",userIdx);
                bundle.putString("userAuth",userAuth);
                fragment1.setArguments(bundle);
                break;

            case 2: //상인이 판매하려고 올린 상품
                // 두번 째 프래그먼트 호출
                Fragment fragment2 = new Mypage_sellitem_fragment();
                transaction.replace(R.id.item_container, fragment2);
                transaction.commit();

                Bundle bundle2 = new Bundle(); // 파라미터의 숫자는 전달하려는 값의 갯수
                bundle2.putString("userIdx",userIdx);
                bundle2.putString("userAuth",userAuth);
                fragment2.setArguments(bundle2);
                break;
        }
    }

}