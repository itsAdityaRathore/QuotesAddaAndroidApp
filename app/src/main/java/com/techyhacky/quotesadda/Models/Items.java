package com.techyhacky.quotesadda.Models;

public class Items {
    private String Name;
    private String Image;
    private String Url;

    public Items(String name, String image, String url) {
        Name = name;
        Image = image;
        Url = url;
    }

    public Items() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
