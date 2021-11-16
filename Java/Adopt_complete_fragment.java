package org.gyeongsoton.gyeongsoton_jelly;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class Adopt_complete_fragment extends Fragment {

    private ImageView ani_1;
    private TextView textView1_1,textView1_2;
    String animalImage,animalSpecies,animalAge,adoptDate,userIdx,userAuth; //입양완료 띄우는거
    int animalIdx;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        ViewGroup view= (ViewGroup) inflater.inflate(R.layout.fragment_adopt_complete, container, false);

        //Bundle bundle = getArguments();
        //userIdx = bundle.getString("userIdx");
        //userAuth = bundle.getString("userAuth");

        ani_1=view.findViewById(R.id.ani_1);
        textView1_1=view.findViewById(R.id.textView1_1);
        textView1_2=view.findViewById(R.id.textView1_2);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonResponse = new JSONObject(response); //서버 응답 받아 json 파일 받아옴
                    boolean success = jsonResponse.getBoolean("isSuccess");
                    if (success) {
                        System.out.println("성공");

                        animalIdx = jsonResponse.getInt("animalIdx");  //세부화면으로 넘어갈 때 필요
                        animalImage = jsonResponse.getString("animalImage");
                        animalSpecies=jsonResponse.getString("animalSpecies");
                        animalAge=jsonResponse.getString("animalAge");
                        adoptDate=jsonResponse.getString("adoptDate");

                        //첫번째 동물 정보 셋팅
                        textView1_1.setText(animalSpecies+" "+animalAge+"세");
                        textView1_2.setText(adoptDate);
                        Glide.with(Adopt_complete_fragment.this).load(animalImage).into(ani_1);
                        ani_1.setClipToOutline(true);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        ainimalList_Request ainimallist_request = new ainimalList_Request("S", responseListener);
        RequestQueue queue = Volley.newRequestQueue(container.getContext());
        queue.add(ainimallist_request);

        ani_1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), Adopt_monitor.class);
                //intent.putExtra( "animalIdx", Integer.toString(animalIdx));
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}