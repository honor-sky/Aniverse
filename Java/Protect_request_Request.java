package org.gyeongsoton.gyeongsoton_jelly;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class Protect_request_Request extends StringRequest {

    //서버 URL 설정(php 파일 연동)
    final static private String URL = "http://3.36.175.200/app/protect/postUserinfo";
    private Map<String, String> map;

    public Protect_request_Request(String userIdx,String protectListIdx, String contactName, String protectComment, String contactPhoneNum, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null); //서버에 요청

        map = new HashMap<>();
        map.put("userIdx", userIdx);
        map.put("protectListIdx", protectListIdx);
        map.put("contactName", contactName);
        map.put("protectComment", protectComment);
        map.put("contactPhoneNum", contactPhoneNum);

    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}
