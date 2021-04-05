package org.imfine.fas.data;

import androidx.annotation.Keep;

@Keep
public class Accident {
    String key;
    String name;
    String time;
    String value;
//    String oldman_id;
//    String video_url;
//    String thumbnail_img_url;
//    String latitude;
//    String longitude;
//    String location;
//    String injuries;

    public Accident() {
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }



//    public String getOldman_id() {
//        return oldman_id;
//    }
//
//    public void setOldman_id(String oldman_id) {
//        this.oldman_id = oldman_id;
//    }
//
//    public String getVideo_url() {
//        return video_url;
//    }
//
//    public void setVideo_url(String video_url) {
//        this.video_url = video_url;
//    }
//
//    public String getThumbnail_img_url() {
//        return thumbnail_img_url;
//    }
//
//    public void setThumbnail_img_url(String thumbnail_img_url) {
//        this.thumbnail_img_url = thumbnail_img_url;
//    }
//
//    public String getLatitude() {
//        return latitude;
//    }
//
//    public void setLatitude(String latitude) {
//        this.latitude = latitude;
//    }
//
//    public String getLongitude() {
//        return longitude;
//    }
//
//    public void setLongitude(String longitude) {
//        this.longitude = longitude;
//    }
//
//    public String getLocation() {
//        return location;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
//    }
//
//    public String getInjuries() {
//        return injuries;
//    }
//
//    public void setInjuries(String injuries) {
//        this.injuries = injuries;
//    }
}
