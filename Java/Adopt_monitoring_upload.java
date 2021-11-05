package org.tecttown.aniverse;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Adopt_monitoring_upload extends AppCompatActivity {

    private final int GET_GALLERY_IMAGE=200;
    private ImageButton img_diagno, img_review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopt_monitoring_upload);

        ImageButton back_btn = (ImageButton) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Adopt_monitor.class);
                startActivity(intent);
            }
        });

        img_diagno = (ImageButton) findViewById(R.id.diagnosis_img);
        img_diagno.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            launcher1.launch(intent);
        });

        img_review = (ImageButton) findViewById(R.id.review_img);
        img_review.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            launcher2.launch(intent);
        });

    }
    ActivityResultLauncher<Intent> launcher1 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK){
                        Intent intent = result.getData();
                        Uri uri = intent.getData();
                        img_diagno.setImageURI(uri);
                    }
                }
            });
    ActivityResultLauncher<Intent> launcher2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK){
                        Intent intent = result.getData();
                        Uri uri = intent.getData();
                        img_review.setImageURI(uri);
                    }
                }
            });

}