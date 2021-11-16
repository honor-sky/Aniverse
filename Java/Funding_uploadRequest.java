package org.gyeongsoton.gyeongsoton_jelly;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Funding_uploadRequest extends StringRequest{

    final static private String URL = "http://3.36.175.200/funding";
    private Map<String, String> map;


    public Funding_uploadRequest(String UserIdx,String fund_title, String fund_obj, String fund_price,
                                 String fund_term,String fund_image, Response.Listener<String> listener) {
        super(Method.POST,URL, listener, null);

        map = new HashMap<>();
        map.put("userIdx", UserIdx);
        map.put("fundingName", fund_title);
        map.put("fundingPurpose", fund_obj);
        map.put("fundingAmount", fund_price);
        map.put("fundingPeriod", fund_term);
        map.put("fundingImage",fund_image);
    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}