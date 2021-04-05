package org.imfine.fas.data;

import java.util.HashMap;
import java.util.Map;

public class FirebasePostFallAlert {

    public String name;
    public String value;
    public String time;


    public FirebasePostFallAlert(){
        // Default constructor required for calls to DataSnapshot.getValue(FirebasePost.class)
    }

    public FirebasePostFallAlert(String time, String name,String value) {
        this.name = name;
        this.value = value;
        this.time = time;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("value", value);
        result.put("time",time);
        return result;
    }
}
