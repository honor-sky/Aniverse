package org.gyeongsoton.gyeongsoton_jelly;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

/*    ImageView adopt_img[]=new ImageView[4];
    ImageView funding_img[]=new ImageView[4];
    ImageView market_img[]=new ImageView[4];
    int[] imgId = {R.id.imageView1,R.id.imageView2,R.id.imageView3,R.id.imageView5,R.id.imageView6,R.id.imageView7,R.id.imageView9,
            R.id.imageView10,R.id.imageView11};*/

    private ImageView imageView1,imageView2,imageView3,imageView5,imageView6,imageView7,imageView9,imageView10,imageView11;
    String adopt1,adopt2,adopt3,adopt1Idx, adopt2Idx,adopt3Idx,funding1,funding2,funding3,funding1Idx,funding2Idx,funding3Idx,
            market1,market2,market3,market1Idx,market2Idx,market3Idx;

 /*   String [] adopt = {"adopt1","adopt2","adopt3"};
    String []adoptIdx= {"adopt1Idx", "adopt2Idx","adopt3Idx"};
    String []funding= {"funding1","funding2","funding3"};
    String []fundingIdx= {"funding1Idx","funding2Idx","funding3Idx"};
    String []market= {"market1","market2","market3"};
    String []marketIdx= {"market1Idx","market2Idx","market3Idx"};*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

       /* for(int i=1; i<4; i++) {
            adopt_img[i-1] = (ImageView) findViewById(imgId[i]);
        }
        for(int i=1; i<4; i++) {
            funding_img[i-1] = (ImageView) findViewById(imgId[i+4]);
        }
        for(int i=1; i<4; i++) {
            market_img[i-1] = (ImageView) findViewById(imgId[i+8]);
        }
*/
        imageView1=findViewById(R.id.imageView1);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView9=findViewById(R.id.imageView9);
        imageView10=findViewById(R.id.imageView10);
        imageView11=findViewById(R.id.imageView11);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonResponse = new JSONObject(response); //서버 응답 받아 json 파일 받아옴
                    boolean success = jsonResponse.getBoolean("isSuccess");
                    if (success) {
                        System.out.println("성공");

                    /*    //준거 개수만큼 여기 출력
                        JSONArray movieArray = jsonObject.getJSONArray("Movies");
                        for(int i=0; i<movieArray.length(); i++)
                        {
                            JSONObject movieObject = movieArray.getJSONObject(i);
                            adopt[i] = jsonResponse.getString(adopt[i]);
                            adoptIdx[i]= jsonResponse.getString(adoptIdx[i]);

                        }*/


                       adopt1 = jsonResponse.getString("adopt1");
                        adopt2= jsonResponse.getString("adopt2");
                        adopt3= jsonResponse.getString("adopt3");
                        adopt1Idx= jsonResponse.getString("adopt1Idx");
                        adopt2Idx= jsonResponse.getString("adopt2Idx");
                        adopt3Idx= jsonResponse.getString("adopt3Idx");

                        funding1= jsonResponse.getString("funding1");
                        funding2= jsonResponse.getString("funding2");
                        funding3= jsonResponse.getString("funding3");
                        funding1Idx = jsonResponse.getString("funding1Idx");
                        funding2Idx = jsonResponse.getString("funding2Idx");
                        funding3Idx = jsonResponse.getString("funding3Idx");

                        market1= jsonResponse.getString("market1");
                        market2= jsonResponse.getString("market2");
                        market3= jsonResponse.getString("market3");
                        market1Idx=jsonResponse.getString("market1Idx");
                        market2Idx=jsonResponse.getString("market2Idx");
                        market3Idx=jsonResponse.getString("market3Idx");

                        //입양 동물 셋팅
                        Glide.with(MainActivity.this).load(adopt1).into(imageView1);
                        Glide.with(MainActivity.this).load(adopt2).into(imageView2);
                        Glide.with(MainActivity.this).load(adopt3).into(imageView3);
                        Glide.with(MainActivity.this).load(funding1).into(imageView5);
                        Glide.with(MainActivity.this).load(funding2).into(imageView6);
                        Glide.with(MainActivity.this).load(funding3).into(imageView7);
                        Glide.with(MainActivity.this).load(market1).into(imageView9);
                        Glide.with(MainActivity.this).load(market2).into(imageView10);
                        Glide.with(MainActivity.this).load(market3).into(imageView11);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        mainRequest mainrequest = new mainRequest(responseListener); //임시보호 진행중
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(mainrequest);

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Adopt_info.class);
                intent.putExtra( "animalIdx", adopt1Idx );
                startActivity(intent);
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Adopt_info.class);
                intent.putExtra( "animalIdx", adopt2Idx );
                startActivity(intent);
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Adopt_info.class);
                intent.putExtra( "animalIdx", adopt2Idx );
                startActivity(intent);
            }
        });

        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Funding_info.class);
                intent.putExtra( "fundingIdx", funding1Idx );
                startActivity(intent);
            }
        });
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Funding_info.class);
                intent.putExtra( "fundingIdx", funding2Idx );
                startActivity(intent);
            }
        });
        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Funding_info.class);
                intent.putExtra( "fundingIdx", funding3Idx );
                startActivity(intent);
            }
        });
        imageView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Market_item_info.class);
                intent.putExtra( "marketIdx", market1Idx );
                startActivity(intent);
            }
        });
        imageView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Market_item_info.class);
                intent.putExtra( "marketIdx", market2Idx );
                startActivity(intent);
            }
        });
        imageView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Market_item_info.class);
                intent.putExtra( "marketIdx", market3Idx );
                startActivity(intent);
            }
        });


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
        market_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Market_list.class);
                startActivity(intent);
            }
        });

        ImageButton mypage_btn = (ImageButton)findViewById(R.id.mypage_btn);
        mypage_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Mypage.class);
                startActivity(intent);
            }
        });

    }
}