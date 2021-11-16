package org.gyeongsoton.gyeongsoton_jelly;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Jellyshop_pay extends AppCompatActivity {

    private AlertDialog dialog;
    private Button buy_button;
    private String pur_way;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jellyshop_pay);

        Intent intent= getIntent();
        String jelly= intent.getStringExtra("jelly");
        String price= intent.getStringExtra("price");

        TextView tv = (TextView) findViewById(R.id.jelly);
        TextView tv2 = (TextView) findViewById(R.id.price);
        tv.setText(jelly+"개");
        tv2.setText(price+"원");

        buy_button=findViewById(R.id.buy_btn);
        buy_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject( response );
                            boolean success = jsonObject.getBoolean( "isSuccess" );

                            if (success) {

                                Toast.makeText(getApplicationContext(), String.format("구매하였습니다."), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Jellyshop_pay.this, Mypage.class);
                                startActivity(intent);

                            } else {
                                Toast.makeText(getApplicationContext(), "구매하지 못하였습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                Jelly_buyRequest jelly_buyRequest = new Jelly_buyRequest("1" , jelly,pur_way, responseListener);
                RequestQueue queue = Volley.newRequestQueue( Jellyshop_pay.this );
                queue.add(jelly_buyRequest);
            }
        });

        RadioGroup purchaseWay=findViewById(R.id.purchaseWay);
        RadioButton card=findViewById(R.id.card);
        RadioButton account=findViewById(R.id.account);
        RadioButton normal=findViewById(R.id.normal);

        pur_way=card.getText().toString();

        purchaseWay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i){
                if (i==R.id.card){
                    pur_way=card.getText().toString();
                }
                else if(i==R.id.account){
                    pur_way=account.getText().toString();
                }
                else{
                    pur_way=normal.getText().toString();
                }
            }
        });

    }
}