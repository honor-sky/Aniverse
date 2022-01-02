package org.gyeongsoton.gyeongsoton_jelly;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    private EditText species, sex, age, neutering ,inoculation, disease, deadline,
            finding_spot, personality, center_name, center_address, center_customer_number, condition;
    private AlertDialog dialog;
    private final int GET_GALLERY_IMAGE=200;
    private ImageButton img_b;

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
        neutering = findViewById(R.id.neutering);
        age = findViewById(R.id.age);
        inoculation = findViewById(R.id.inoculation);
        disease = findViewById(R.id.disease);
        deadline = findViewById(R.id.deadline);
        finding_spot = findViewById(R.id.finding_spot);
        personality = findViewById(R.id.personality);
        center_name = findViewById(R.id.center_name);
        condition = findViewById(R.id.condition);


        Button upload_button = (Button) findViewById(R.id.upload_button);
        upload_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Species = species.getText().toString();
                final String Sex = sex.getText().toString();
                final String Neutering = neutering.getText().toString();
                final String Age = age.getText().toString();
                final String Inoculation = inoculation.getText().toString();
                final String Disease = disease.getText().toString();
                final String Deadline = deadline.getText().toString();
                final String Finding_spot = finding_spot.getText().toString();
                final String Personality = personality.getText().toString();
                final String Center_name = center_name.getText().toString();
                final String Center_address = center_address.getText().toString();
                final String QA_number = center_customer_number.getText().toString();
                final String Condition = condition.getText().toString();


                //한 칸이라도 입력 안했을 경우
                if (Species.equals("")  || Sex.equals("") ||Neutering.equals("") ||Age.equals("") || Inoculation.equals("") || Disease.equals("") || Deadline.equals("") || Finding_spot.equals("") ||
                        Personality.equals("") || Center_name.equals("") || Center_address.equals("") || QA_number.equals("") || Condition.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Adopt_upload.this);
                    dialog = builder.setMessage("모두 입력해주세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject( response );
                            boolean success = jsonObject.getBoolean( "success" );

                            //업로드 성공시
                            if (success) {

                                Toast.makeText(getApplicationContext(), String.format("업로드 성공"), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Adopt_upload.this, Adopt_confirm.class);
                                startActivity(intent);

                                //업로드 실패시
                            } else {
                                Toast.makeText(getApplicationContext(), "업로드 실패", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                //서버로 Volley를 이용해서 요청
                Adopt_uploadRequest uploadRequest = new Adopt_uploadRequest("1","1",Species,Sex,Neutering,Age,
                        Inoculation,Disease,Deadline,Finding_spot,Personality,
                        Center_name,Center_address,QA_number,Condition,responseListener);
                RequestQueue queue = Volley.newRequestQueue( Adopt_upload.this );
                queue.add( uploadRequest );
            }
        });


        img_b = (ImageButton) findViewById(R.id.animal_img);
        img_b.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            launcher.launch(intent);
        });

    }
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK){
                        Intent intent = result.getData();
                        Uri uri = intent.getData();
                        img_b.setImageURI(uri);
                    }
                }
            });


}