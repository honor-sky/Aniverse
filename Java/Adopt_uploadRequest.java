package org.gyeongsoton.gyeongsoton_jelly;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Adopt_uploadRequest extends StringRequest{

    final static private String URL = "http://3.34.166.156/";
    private Map<String, String> map;
    //private Map<String, String>parameters;

    public Adopt_uploadRequest(String Species, String Age, String Inoculation,String Disease,String Deadline,String Finding_spot,String Personality,
                               String Center_name,String Center_address,String QA_number,String Condition,Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null); //서버에 요청

        map = new HashMap<>();
        map.put("UserID", Species);
        //map.put("UserPass", Sex);
        map.put("Age", Age);
        map.put("Inoculation", Inoculation);
        map.put("Disease", Disease);
        map.put("Deadline", Deadline);
        map.put("Finding_spot", Finding_spot);
        map.put("Personality", Personality);
        map.put("Center_name", Center_name);
        map.put("Center_address", Center_address);
        map.put("QA_number", QA_number);
        map.put("Condition", Condition);

    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}
