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

    public Adopt_uploadRequest(String animalIdx, String userIdx, String Species,String Sex,String Neutering,String Age, String Inoculation,String Disease,String Deadline,String Finding_spot,String Personality,
                               String Center_name,String Center_address,String QA_number,String Condition,Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null); //서버에 요청

        map = new HashMap<>();
        map.put("animalIdx",animalIdx);
        map.put("userIdx",userIdx);
        map.put("animalSpecies", Species);
        map.put("animalGender", Sex);
        map.put("animalNeutralization", Neutering);
        map.put("animalAge", Age);
        map.put("animalVaccinated", Inoculation);
        map.put("animalDiseases", Disease);
        map.put("Deadline", Deadline);
        map.put("animalFind", Finding_spot);
        map.put("animalIntro", Personality);
        map.put("Center_name", Center_name);
        map.put("Center_address", Center_address);
        map.put("QA_number", QA_number);
        map.put("adoptCondition", Condition);

    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}