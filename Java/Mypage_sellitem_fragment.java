package org.gyeongsoton.gyeongsoton_jelly;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Mypage_sellitem_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Mypage_sellitem_fragment extends Fragment {

    String productName,productPrice,productImage;
    ImageView s_item;
    TextView item_name,item_price;
    String userIdx,userAuth;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Mypage_sellitem_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_mypage_seller_sellitem.
     */
    // TODO: Rename and change types and number of parameters
    public static Mypage_sellitem_fragment newInstance(String param1, String param2) {
        Mypage_sellitem_fragment fragment = new Mypage_sellitem_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup view= (ViewGroup)inflater.inflate(R.layout.fragment_mypage_seller_sellitem, container, false);

        Bundle bundle = getArguments();
         userIdx = bundle.getString("userIdx");
         userAuth = bundle.getString("userAuth");

        s_item=view.findViewById(R.id.s_item);
        item_name=view.findViewById(R.id.item_name);
        item_price=view.findViewById(R.id.item_price);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonResponse = new JSONObject(response); //서버 응답 받아 json 파일 받아옴
                    boolean success = jsonResponse.getBoolean("isSuccess");
                    if (success) {
                        System.out.println("성공");

                        productName = jsonResponse.getString("productName");
                        productPrice = Integer.toString(jsonResponse.getInt("productPrice"));
                        productImage = jsonResponse.getString("productImage");

                        //첫번째 동물 정보 셋팅
                        item_name.setText("상품명:  "+productName);
                        item_price.setText("가격:   "+productPrice);
                        Glide.with(Mypage_sellitem_fragment.this).load(productImage).into(s_item);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        };

        n_itemCheck_Request n_itemcheck_Request = new n_itemCheck_Request(userIdx, responseListener);
        RequestQueue queue = Volley.newRequestQueue(container.getContext());
        queue.add(n_itemcheck_Request);

        return view;
    }
}