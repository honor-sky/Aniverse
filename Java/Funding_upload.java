package org.gyeongsoton.gyeongsoton_jelly;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.loader.content.CursorLoader;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

public class Funding_upload extends AppCompatActivity {
    private final int GET_GALLERY_IMAGE = 200;
    private EditText fund_title, fund_obj, fund_price, fund_term, center_name;
    private ImageButton img;
    private Button upload_button;
    private AlertDialog dialog;
    private String aUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funding_upload);

        ImageButton back_btn = (ImageButton) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Funding_list.class);
                startActivity(intent);
            }
        });

        img = (ImageButton) findViewById(R.id.image);
        img.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            launcher.launch(intent);
        });

        fund_title = findViewById( R.id.fund_title);
        fund_obj = findViewById( R.id.fund_obj );
        fund_price = findViewById( R.id.fund_price );
        fund_term = findViewById(R.id.fund_term);
        center_name = findViewById(R.id.center_name);


        // 업로드 버튼 눌렀을 때
        upload_button = findViewById(R.id.upload_btn);
        upload_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Fund_title = fund_title.getText().toString();
                final String Fund_obj = fund_obj.getText().toString();
                final String Fund_price = fund_price.getText().toString();
                final String Fund_term = fund_term.getText().toString();
                final String Center_name = center_name.getText().toString();


                //한 칸이라도 입력 안했을 경우
                if (Fund_title.equals("") || Fund_obj.equals("") || Fund_price.equals("")||Fund_term.equals("")||Center_name.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Funding_upload.this);
                    dialog = builder.setMessage("모두 입력해주세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject( response );
                            boolean success = jsonObject.getBoolean( "isSuccess" );

                            //업로드 성공시
                            if (success) {
                                Toast.makeText(getApplicationContext(), String.format("%s를 업로드하였습니다.", Fund_title), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Funding_upload.this, Funding_list.class);
                                startActivity(intent);

                                //업로드 실패시
                            } else {
                                Toast.makeText(getApplicationContext(), "업로드 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };


                //서버로 Volley를 이용해서 요청
                Funding_uploadRequest uploadRequest = new Funding_uploadRequest("29",Fund_title, Fund_obj, Fund_price,
                        Fund_term,"https://user-images.githubusercontent.com/77181984/141118320-c906e1e5-e07d-4dac-a4fc-9651f06e0495.png",responseListener);
                RequestQueue queue = Volley.newRequestQueue( Funding_upload.this );
                queue.add( uploadRequest );
            }
        });

    }

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK){
                        Intent intent = result.getData();
                        Uri uri = intent.getData();
                        img.setImageURI(uri);
                    }
                }
            });
/*
    private String getImageNameToUri(Uri data){
        String[] proj= {MediaStore.Images.Media.DATA};
        Cursor cursor= this.getContentResolver().query(data, proj,null,null,null);
        int column_index= cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        abUri= cursor.getString(column_index);
        cursor.close();
        System.out.println("Column_index!!!"+column_index);
        System.out.println("uri!!!"+data);
        System.out.println("abUri!!!!"+abUri);

           //2번째 코드
        String[] proj= {MediaStore.Images.Media.DATA};
        CursorLoader loader= new CursorLoader(this, Uri.parse(data.toString()), proj, null, null, null);
        Cursor cursor= loader.loadInBackground();
        assert cursor != null;
        int column_index= cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String abUri= cursor.getString(column_index);
        cursor.close();

        return abUri;
    }
*/

    /*

    public void DoFileUpload(String apiUrl, String absolutePath){
        HttpFileUpload(apiUrl,"", absolutePath);
    }

    public void HttpFileUpload(String urlString, String params, String fileName){
        try{
            FileInputStream mFileInputStream=new FileInputStream(fileName);
            URL connectUrl=new URL(urlString);
            Log.d("Test","mFileInputStream is"+mFileInputStream);

            HttpURLConnection conn= (HttpURLConnection)connectUrl.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Conncetion","Keep-Alive");
            conn.setRequestProperty("Content-Type","multipart/form-data;boundary="+boundary);

            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
            dos.writeBytes(twoHyphens+boundary+lineEnd);
            dos.writeBytes("Content-Disposition:form-data; name=\"uploadedfile\""+fileName+"\""+lineEnd);
            dos.writeBytes(lineEnd);

            int bytesAvailable= mFileInputStream.available();
            int maxBufferSize=1024;
            int bufferSize=Math.min(bytesAvailable,maxBufferSize);

            byte[] buffer = new byte[bufferSize];
            int bytesRead= mFileInputStream.read(buffer,0,bufferSize);

            Log.d("Test","image bye is "+bytesRead);

            while(bytesRead>0){
                dos.write(buffer,0,bufferSize);
                bytesAvailable=mFileInputStream.available();
                bufferSize=Math.min(bytesAvailable,maxBufferSize);
                bytesRead=mFileInputStream.read(buffer,0,bufferSize);
            }
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens+boundary+twoHyphens+lineEnd);

            Log.e("Test","File is written");
            mFileInputStream.close();
            dos.flush();

            int ch;
            InputStream is = conn.getInputStream();
            StringBuffer b= new StringBuffer();
            while((ch=is.read())!=-1){
                b.append((char)ch);
            }
            String s= b.toString();
            Log.e("Test","result= "+s);
            dos.close();
        }
        catch(Exception e){
            Log.d("Test","exception "+e.getMessage());
        }
    }

    public static String createCopyAndReturnRealPath(@NonNull Context context, @NonNull Uri uri){
        final ContentResolver contentResolver = context.getContentResolver();

        if (contentResolver==null)
            return null;

        String filePath=context.getApplicationInfo().dataDir+ File.separator+System.currentTimeMillis();

        File file = new File(filePath);
        try{
            InputStream inputStream = contentResolver.openInputStream(uri);
            if (inputStream==null)
                return null;
            OutputStream outputStream = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while((len=inputStream.read(buf))>0){
                outputStream.write(buf,0,len);
            }
            outputStream.close();
            inputStream.close();
        }catch (Exception e){
            return null;
        }
        return file.getAbsolutePath();
    }*/

}