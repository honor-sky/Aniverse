package org.gyeongsoton.gyeongsoton_jelly;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Jelly_buyRequest extends StringRequest {

    final static private String URL = "";
    private Map<String, String> map;

    public Jelly_buyRequest(String userIdx, String purchaseJellyNum, String purchaseJellyWay,
                            Response.Listener<String> listener) {
        super(Method.POST,URL, listener, null);

        map = new HashMap<>();
        map.put("userIdx", userIdx);
        map.put("purchaseJellyNum", purchaseJellyNum);
        map.put("purchaseJellyWay", purchaseJellyWay);

    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}