package org.imfine.fas;

import java.util.HashMap;
import java.util.Map;

public class FirebasePostDouriReg {

    public String name;
    public String age;
    public String sex;
    public String addr;
    public String phone;
    public String history;
    public String wb;
    public String cam;

    public FirebasePostDouriReg(){
        // Default constructor required for calls to DataSnapshot.getValue(FirebasePost.class)
    }

    public FirebasePostDouriReg(String name, String age, String sex, String addr, String phone, String history, String wb, String cam) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.addr = addr;
        this.phone = phone;
        this.history = history;
        this.wb = wb;
        this.cam = cam;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name",name);
        result.put("age", age);
        result.put("sex", sex);
        result.put("addr",addr);
        result.put("phone", phone);
        result.put("history", history);
        result.put("wb", wb);
        result.put("cam", cam);
        return result;
    }

}
