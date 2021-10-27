package org.tecttown.aniverse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Market_item_info extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_item_info);
    }

    public void back(View v){
        Intent intent = new Intent(Market_item_info.this, Market_list.class);
        startActivity(intent);
    }
}