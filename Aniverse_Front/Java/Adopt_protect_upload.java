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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Adopt_protect_upload extends AppCompatActivity {

    private EditText species, sex, age, inoculation, disease, startDate, endDate,
            center_name, center_address, center_customer_number;
    private final int GET_GALLERY_IMAGE=200;
    private ImageButton img_b;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopt_protect_upload);

        species = findViewById(R.id.species);
        sex = findViewById(R.id.sex);
        age = findViewById(R.id.age);
        inoculation = findViewById(R.id.inoculation);
        disease = findViewById(R.id.disease);
        startDate = findViewById(R.id.startDate);
        endDate = findViewById(R.id.endDate);
        center_name = findViewById(R.id.center_name);

        Button upload_button = (Button) findViewById(R.id.upload_button);
        upload_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Species = species.getText().toString();
                final String Sex = sex.getText().toString();
                final String Age = age.getText().toString();
                final String Inoculation = inoculation.getText().toString();
                final String Disease = disease.getText().toString();
                final String StartDate = startDate.getText().toString();
                final String EndDate = endDate.getText().toString();
                final String Center_name = center_name.getText().toString();
                final String Center_address = center_address.getText().toString();
                final String QA_number = center_customer_number.getText().toString();


                //??? ???????????? ?????? ????????? ??????
                if (Species.equals("")  || Age.equals("") || Inoculation.equals("") || Disease.equals("") || StartDate.equals("") || EndDate.equals("") ||
                        Sex.equals("") || Center_name.equals("") ) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Adopt_protect_upload.this);
                    dialog = builder.setMessage("?????? ??????????????????.").setNegativeButton("??????", null).create();
                    dialog.show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject( response );
                            boolean success = jsonObject.getBoolean( "success" );

                            //????????? ?????????
                            if (success) {

                                Toast.makeText(getApplicationContext(), String.format("????????? ??????"), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Adopt_protect_upload.this, Adopt_confirm.class);
                                startActivity(intent);

                                //????????? ?????????
                            } else {
                                Toast.makeText(getApplicationContext(), "????????? ??????", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                //????????? Volley??? ???????????? ??????
                Adopt_protect_uploadRequest uploadRequest = new Adopt_protect_uploadRequest("1","1",Species, Age, Sex,Inoculation,
                        Disease,StartDate,EndDate, Center_name,responseListener);
                RequestQueue queue = Volley.newRequestQueue( Adopt_protect_upload.this );
                queue.add(uploadRequest);
            }
        });



        ImageButton back_btn = (ImageButton) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Adopt_list.class);
                startActivity(intent);
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