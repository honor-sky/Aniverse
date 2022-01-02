package org.gyeongsoton.gyeongsoton_jelly;

import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Funding_request extends StringRequest {
    final static private String URL = "";
    private Map<String, String> map;


    public Funding_request(String donateJellyIDX, String fundingIDX, String userIDX,String jellyNum,Response.Listener<String> listener) {
        super(Method.POST,URL, listener, null);

        map = new HashMap<>();
        map.put("donateJellyIdx", donateJellyIDX);
        map.put("fundingIdx", fundingIDX);
        map.put("userIdx", userIDX);
        map.put("donateJellyNum", jellyNum);
    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}