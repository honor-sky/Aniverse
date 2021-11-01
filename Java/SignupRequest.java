package org.gyeongsoton.gyeongsoton_jelly;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class SignupRequest extends StringRequest{

    //서버 URL 설정(php 파일 연동)
    final static private String URL = "http://13.209.98.213/";
    private Map<String, String> map;
    //private Map<String, String>parameters;

    public SignupRequest(String UserID, String UserPass, String UserName, String UserNum,Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null); //서버에 요청

        map = new HashMap<>();
        map.put("UserID", UserID);
        map.put("UserPass", UserPass);
        map.put("UserName", UserName);
        map.put("UserNum", UserNum);
    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}
