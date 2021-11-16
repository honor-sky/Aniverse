package org.gyeongsoton.gyeongsoton_jelly;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class protectList_Request extends StringRequest {
    //서버 url 설정(php파일 연동)
    final static private String URL = "http://3.36.175.200/protect/list";
    private Map<String, String> map;

    public protectList_Request(Response.Listener<String> listener) {
        super(Method.GET, URL, listener, null);

    }
}
