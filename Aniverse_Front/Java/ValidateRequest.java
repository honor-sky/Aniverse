package org.gyeongsoton.gyeongsoton_jelly;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

public class ValidateRequest extends StringRequest{
    //서버 url 설정(php파일 연동)
    final static  private String URL="http://3.36.175.200/user/checkid";
    private Map<String, String> map;

    public ValidateRequest(String UserID, Response.Listener<String> listener){
        super(Method.POST, URL, listener,null);

        map = new HashMap<>();
        map.put("userId", UserID);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
