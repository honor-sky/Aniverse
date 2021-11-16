package org.gyeongsoton.gyeongsoton_jelly;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class mainRequest extends StringRequest {
    final static  private String URL="http://3.36.175.200/main";

    public mainRequest( Response.Listener<String> listener){
        super(Method.GET, URL, listener,null);

    }

}
