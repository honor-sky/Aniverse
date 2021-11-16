package org.gyeongsoton.gyeongsoton_jelly;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class marketInfoRequest extends StringRequest {

    //서버 URL 설정(php 파일 연동)
    final static private String URL = "";
    private Map<String, String> map;


    public marketInfoRequest(String productIdx,Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null); //서버에 요청

        map = new HashMap<>();
        map.put("productIdx", productIdx);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

}