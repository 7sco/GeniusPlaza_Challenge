package com.example.franciscoandrade.geniusplazachallenge.data.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Data implements Serializable{
    @SerializedName("avatar")
    private String avatar;
    @SerializedName("last_name")
    private String last_name;
    @SerializedName("first_name")
    private String first_name;
    @SerializedName("id")
    private int id;

    public String getAvatar() {
        return avatar;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public int getId() {
        return id;
    }
}
