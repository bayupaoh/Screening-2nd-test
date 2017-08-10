package me.bayupaoh.screeningtest.api.dao;

/**
 * Created by dsantren on 09/08/2017.
 */

public class EventDao {
    private String image;
    private String name;
    private String date;

    public EventDao() {
    }

    public EventDao(String image, String name, String date) {
        this.image = image;
        this.name = name;
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
