package org.gyeongsoton.gyeongsoton_jelly;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Adopt_protect_uploadRequest extends StringRequest {

    final static private String URL = "";
    private Map<String, String> map;

    public Adopt_protect_uploadRequest(String animalIdx, String userIdx, String Species, String Age, String gender,
                                       String Inoculation, String Disease, String startDate, String EndDate,
                                       String Center_name, Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null); //서버에 요청

        map = new HashMap<>();
        map.put("animalIdx", animalIdx);
        map.put("userIdx", userIdx);
        map.put("Age", Age);
        map.put("protectDateStart", startDate);
        map.put("protectDateEnd", EndDate);
        map.put("animalSpecies", Species);
        map.put("animalAge", Age);
        map.put("animalGender", gender);
        map.put("animalVaccinated", Inoculation);
        map.put("animalDiseases", Disease);
        map.put("centerName", Center_name);


    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}