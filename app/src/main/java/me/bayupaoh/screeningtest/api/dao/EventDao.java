package me.bayupaoh.screeningtest.api.dao;

/**
 * Created by dsantren on 09/08/2017.
 */

public class EventDao {
    private String image;
    private String name;
    private String date;
    private Double latitude;
    private Double longitude;

    public EventDao() {
    }

    public EventDao(String image, String name, String date) {
        this.image = image;
        this.name = name;
        this.date = date;
    }

    public EventDao(String image, String name, String date, Double latitude, Double longitude) {
        this.image = image;
        this.name = name;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
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
