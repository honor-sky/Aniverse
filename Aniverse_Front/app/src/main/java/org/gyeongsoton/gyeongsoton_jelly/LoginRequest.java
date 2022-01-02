package org.gyeongsoton.gyeongsoton_jelly;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest{

    //서버 URL 설정(php 파일 연동)
    final static private String URL = "http://3.36.175.200/signin";
    private Map<String, String> map;

    public LoginRequest(String UserID, String UserPass, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userId", UserID);
        map.put("userAmmo", UserPass);
    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}
