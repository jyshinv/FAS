package org.imfine.fas.data;

import java.util.HashMap;
import java.util.Map;

public class Storage {

    public String video;

    public Storage(){
        // Default constructor required for calls to DataSnapshot.getValue(FirebasePost.class)
    }

    public Storage(String video) {
        this.video = video;

    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("video", video);
        return result;
    }
}