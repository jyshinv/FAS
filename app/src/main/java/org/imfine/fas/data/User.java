package org.imfine.fas.data;

import androidx.annotation.Keep;

import java.util.ArrayList;
import java.util.List;

@Keep
public class User {
    String uid;
    String userID;
    List<String> oldman_list =new ArrayList<>();
    //Intent intent = new Intent (getApplicationContext(), LoginPageActivity.class);
    //startActivity(intent); //다음화면으로 넘어감
    //finish();


    public User() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public List<String> getOldman_list() {
        return oldman_list;
    }

    public void setOldman_list(List<String> oldman_list) {
        this.oldman_list = oldman_list;
    }
}
