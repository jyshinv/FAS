package org.imfine.fas.data;

import androidx.annotation.Keep;

import java.util.ArrayList;
import java.util.List;

@Keep
public class Oldman {
    String id;
   String addr;
   String age;
   String cam;
   String history;
   String name;
   String phone;
   String sex;
   String wb;
    List<ShortAccident> accident_list = new ArrayList<>();


    @Keep
    private class ShortAccident {
        String accident_id;
        String datetime;
        String location;
        String injuries;
        String thumbnail_img_url;

        public ShortAccident() {
        }

        public String getAccident_id() {
            return accident_id;
        }

        public void setAccident_id(String accident_id) {
            this.accident_id = accident_id;
        }

        public String getDatetime() {
            return datetime;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getInjuries() {
            return injuries;
        }

        public void setInjuries(String injuries) {
            this.injuries = injuries;
        }

        public String getThumbnail_img_url() {
            return thumbnail_img_url;
        }

        public void setThumbnail_img_url(String thumbnail_img_url) {
            this.thumbnail_img_url = thumbnail_img_url;
        }
    }


    public Oldman() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCam() {
        return cam;
    }

    public void setCam(String cam) {
        this.cam = cam;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getWb() {
        return wb;
    }

    public void setWb(String wb) {
        this.wb = wb;
    }

    public List<ShortAccident> getAccident_list() {
        return accident_list;
    }

    public void setAccident_list(List<ShortAccident> accident_list) {
        this.accident_list = accident_list;
    }
}
