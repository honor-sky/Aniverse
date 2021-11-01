package org.gyeongsoton.gyeongsoton_jelly;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

public class Adopt_upload extends AppCompatActivity {

    TextInputEditText species, sex, age, inoculation, disease, deadline,
            finding_spot, character, center_name, center_address, center_customer_number, condition;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopt_upload);

        ImageButton back_btn = (ImageButton) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Adopt_list.class);
                startActivity(intent);
            }
        });

        species = findViewById(R.id.species);
        sex = findViewById(R.id.sex);
        age = findViewById(R.id.age);
        inoculation = findViewById(R.id.inoculation);
        disease = findViewById(R.id.disease);
        deadline = findViewById(R.id.deadline);
        finding_spot = findViewById(R.id.finding_spot);
        character = findViewById(R.id.character);
        center_name = findViewById(R.id.center_name);
        center_address = findViewById(R.id.center_address);
        center_customer_number = findViewById(R.id.center_customer_number);
        condition = findViewById(R.id.condition);

        Button upload_button = (Button) findViewById(R.id.upload_button);
        upload_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Species = species.getText().toString();
                final String Sex = sex.getText().toString();
                final String Age = age.getText().toString();
                final String Inoculation = inoculation.getText().toString();
                final String Disease = disease.getText().toString();
                final String Deadline = deadline.getText().toString();
                final String Finding_spot = finding_spot.getText().toString();
                final String Charac = character.getText().toString();
                final String Center_name = center_name.getText().toString();
                final String Center_address = center_address.getText().toString();
                final String Center_customer_number = center_customer_number.getText().toString();
                final String Condition = condition.getText().toString();


                //한 칸이라도 입력 안했을 경우
                if (Species.equals("") || Sex.equals("") || Age.equals("") || Inoculation.equals("") || Disease.equals("") || Deadline.equals("") || Finding_spot.equals("") ||
                        Charac.equals("") || Center_name.equals("") || Center_address.equals("") || Center_customer_number.equals("") || Condition.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Adopt_upload.this);
                    dialog = builder.setMessage("모두 입력해주세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }
            }
        });

        /*//서버로 Volley를 이용해서 요청
        SignupRequest signupRequest = new SignupRequest( Species, Sex, Age, Inoculation,Disease,Deadline,Finding_spot,Charac,
                Center_name,Center_address,Center_customer_number,Condition);
        RequestQueue queue = Volley.newRequestQueue( Adopt_upload.this );
        queue.add( signupRequest );*/

    }
}