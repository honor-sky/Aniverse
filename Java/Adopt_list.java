package org.gyeongsoton.gyeongsoton_jelly;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.MotionEvent;
import android.widget.ImageButton;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;


public class Adopt_list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopt_list);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_adopt_list);


        ImageButton homeButton = (ImageButton) findViewById(R.id.imageButton);
        homeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        Button btn1 = (Button)findViewById(R.id.button);
        Button btn2 = (Button)findViewById(R.id.button2);
        ImageButton btn3 = (ImageButton)findViewById(R.id.imageButton6);

        btn1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                btn1.setPressed(true);
                btn2.setPressed(false);
                btn3.setVisibility(View.INVISIBLE);


                return true;
            }
        });



        btn2.setOnTouchListener(new View.OnTouchListener() { //입양완료 버튼 누르면 데이터도 바뀌고 검색창도 뜸
           @Override
            public boolean onTouch(View v, MotionEvent event) {
                btn2.setPressed(true);
                btn1.setPressed(false);

               btn3.setVisibility(View.VISIBLE);
               SearchView searchView;
               searchView = findViewById(R.id.searchView);
               searchView.setQueryHint("아이디로 검색하세요");


  /*             searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                   @Override
                   public boolean onQueryTextSubmit(String query) { //검색어 입력시
                       return false;
                   }

                   @Override
                   public boolean onQueryTextChange(String newText) { //검색어 완료시 //데이터베이스에서 비교 후 결과에 따라 데이터 보여줌
                       return false;
                   }
               });*/


                return true;
            }
        });





    }



}