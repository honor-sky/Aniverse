package org.gyeongsoton.gyeongsoton_jelly;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class protectInfo_Request extends StringRequest {

    //서버 url 설정(php파일 연동)
    final static  private String URL="http://3.36.175.200/app/protect/postImgClick";
    private Map<String, String> map;

    public protectInfo_Request(String animalIdx, Response.Listener<String> listener){
        super(Method.POST, URL, listener,null);

        map = new HashMap<>();
        map.put("animalIdx", animalIdx);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

}
