package org.gyeongsoton.gyeongsoton_jelly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Protect_info extends AppCompatActivity {

    String protectListIdx,protectFile,animalSpecies,animalAge,animalGender,animalNeutralization,animalWeight,animalVaccinated,animalDiseases,
            animalFind,animalIntro,protectDateStart,protectDateEnd,centerName,centerAddress,centerPhoneNum,adoptCondition,animalIdx,userIdx,
            userAuth;
    private ImageView img;
    private TextView species_gender_neu,age,weight,Vacci,dises,animalfind,animalintro,protect_term,adoptcondition,centername,centeraddress,centerphoneNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Adopt_list에서 클릭한 동물의 인덱스 전달
        //Intent intent = getIntent(); /*데이터 수신*/
        //animalIdx = intent.getExtras().getString("animalIdx");
        //userIdx= intent.getStringExtra("userIdx");
        //userAuth= intent.getStringExtra("userAuth");

        img = findViewById(R.id.img);
        species_gender_neu = findViewById(R.id.species_gender_neu);
        age = findViewById(R.id.age);
        weight = findViewById(R.id.weight);
        Vacci = findViewById(R.id.Vacci);
        dises = findViewById(R.id.dises);
        animalfind = findViewById(R.id.animalfind);
        animalintro = findViewById(R.id.animalintro);
        protect_term = findViewById(R.id.protect_term);
        adoptcondition = findViewById(R.id.adoptcondition);
        centername = findViewById(R.id.centername);
        centeraddress = findViewById(R.id.centeraddress);
        centerphoneNum = findViewById(R.id.centerphoneNum);


        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonResponse = new JSONObject(response); //서버 응답 받아 json 파일 받아옴
                    boolean success = jsonResponse.getBoolean("isSuccess");
                    if (success) {

                        protectListIdx=jsonResponse.getString("protectListIdx");
                        animalSpecies=jsonResponse.getString("animalSpecies");
                        protectFile=jsonResponse.getString("protectFile");
                        animalAge=jsonResponse.getString("animalAge");
                        animalGender = jsonResponse.getString("animalGender");
                        animalNeutralization = jsonResponse.getString("animalNeutralization");
                        animalWeight = jsonResponse.getString("animalWeight");
                        animalVaccinated = jsonResponse.getString("animalVaccinated");
                        animalDiseases = jsonResponse.getString("animalDiseases");
                        animalFind = jsonResponse.getString("animalFind");
                        animalIntro = jsonResponse.getString("animalIntro");
                        protectDateStart = jsonResponse.getString("protectDateStart");
                        protectDateEnd = jsonResponse.getString("protectDateEnd");
                        centerName = jsonResponse.getString("centerName");
                        centerAddress = jsonResponse.getString("centerAddress");
                        centerPhoneNum = jsonResponse.getString("centerPhoneNum");
                        adoptCondition = jsonResponse.getString("adoptCondition");

                        //동물정보 셋팅
                        Glide.with(Protect_info.this).load(protectFile).into(img); //사진
                        species_gender_neu.setText("품종: "+animalSpecies+"   성별: "+ animalGender+"   중성화 여부:  "+animalNeutralization); //종성별중성화
                        age.setText("추정 나이: "+animalAge);//나이
                        Vacci.setText("품종: "+animalVaccinated);//접종여부
                        dises.setText("병력: "+animalDiseases);//병력
                        weight.setText("무게: "+animalWeight);//무게
                        animalfind.setText("발견장소: "+animalFind);//발견장소
                        animalintro.setText("특성 및 성격: "+animalIntro);//특성 및 성격
                        protect_term.setText("임시보호 기간: "+protectDateStart+"~"+protectDateEnd);//입양마감일
                        centername.setText("보호소명: "+centerName);//센터이름
                        centeraddress.setText("보호소 주소지: "+centerAddress);//센터주소
                        centerphoneNum.setText("문의 번호: "+centerPhoneNum);//센터 문의 번호
                        adoptcondition.setText("임시보호 조건 및 기타사항: "+adoptCondition);//입양조건 및 기타사항

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        protectInfo_Request protectinfo_Request = new protectInfo_Request("4", responseListener); //임시보호 진행중
        RequestQueue queue = Volley.newRequestQueue(Protect_info.this );
        queue.add(protectinfo_Request);


        //'신청하기' 버튼 누르면 신청자 정보 입력 화면으로 넘어감
        Button request_btn = (Button) findViewById(R.id.request_btn);
        request_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Adopt_request.class);
                //intent.putExtra( "protectListIdx", protectListIdx );
                //intent.putExtra( "userIdx", userIdx);
                //intent.putExtra( "userAuth", userAuth);
                startActivity(intent);
            }
        });

        //뒤로가기 버튼
        ImageButton back_btn = (ImageButton) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Adopt_list.class);
                //intent.putExtra( "userIdx", userIdx);
                //intent.putExtra( "userAuth", userAuth);
                startActivity(intent);
            }
        });

    }
}
