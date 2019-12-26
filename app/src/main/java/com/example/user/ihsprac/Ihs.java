package com.example.user.ihsprac;

public class Ihs {
    private String title, desc, image, username;
    public Ihs(String title, String desc, String image, String username){
        this.title = title;
        this.desc = desc;
        this.image = image;
        this.username = username;
    }
    public Ihs(){

    }

    public String getDesc() {
        return desc;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getUsername() {
        return username;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
