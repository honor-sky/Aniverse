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

public class Market_category_all extends Fragment {

    String userIdx,userAuth,productIdx,productImage,productName;
    ImageView item_1;
    TextView textView1;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        ViewGroup view= (ViewGroup) inflater.inflate(R.layout.fragment_market_category_all, container, false);

        Bundle bundle = getArguments();
        userIdx = bundle.getString("userIdx");
        userAuth = bundle.getString("userAuth");

        item_1=view.findViewById(R.id.item_1);
        textView1=view.findViewById(R.id.textView1);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonResponse = new JSONObject(response); //서버 응답 받아 json 파일 받아옴
                    boolean success = jsonResponse.getBoolean("isSuccess");
                    if (success) {

                        productIdx = Integer.toString(jsonResponse.getInt("productIdx"));
                        productImage = jsonResponse.getString("productImage");
                        productName=jsonResponse.getString("productName");

                        //첫번째 동물 정보 셋팅
                        textView1.setText(productName);
                        Glide.with(Market_category_all.this).load(productImage).into(item_1);
                        item_1.setClipToOutline(true);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        marketListRequest marketlist_request = new marketListRequest( responseListener);
        RequestQueue queue = Volley.newRequestQueue(container.getContext());
        queue.add(marketlist_request);


        item_1=view.findViewById(R.id.item_1);
        item_1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), Market_item_info.class);
                intent.putExtra( "productIdx", productIdx);
                intent.putExtra( "userIdx", userIdx);
                intent.putExtra( "userAuth", userAuth);
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