package org.gyeongsoton.gyeongsoton_jelly;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

public class marketListRequest  extends StringRequest {

    //서버 URL 설정(php 파일 연동)
    final static private String URL = "";

    public marketListRequest(Response.Listener<String> listener) {
        super(Method.GET, URL, listener, null); //서버에 요청

    }

}