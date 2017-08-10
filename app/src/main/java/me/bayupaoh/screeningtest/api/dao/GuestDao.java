package me.bayupaoh.screeningtest.api.dao;

import java.io.Serializable;

/**
 * Created by dsantren on 09/08/2017.
 */

public class GuestDao implements Serializable{

    /**
     * id : 1
     * name : Andi
     * birthdate : 2014-01-01
     */

    private int id;
    private String name;
    private String birthdate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}
