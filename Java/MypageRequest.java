package org.gyeongsoton.gyeongsoton_jelly;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MypageRequest extends StringRequest {

    //서버 URL 설정(php 파일 연동)
    final static private String URL = "http://3.36.175.200/user/mypage";
    private Map<String, String> map;

    public MypageRequest(String userIdx, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null); //서버에 요청

        map = new HashMap<>();
        map.put("userIdx", userIdx);
    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }

}
