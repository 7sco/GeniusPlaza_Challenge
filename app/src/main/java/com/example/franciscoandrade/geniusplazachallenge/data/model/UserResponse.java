package com.example.franciscoandrade.geniusplazachallenge.data.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class UserResponse implements Serializable{
    @SerializedName("data")
    private List<Data> data;
    @SerializedName("total_pages")
    private int total_pages;
    @SerializedName("total")
    private int total;
    @SerializedName("per_page")
    private int per_page;
    @SerializedName("page")
    private int page;

    public List<Data> getData() {
        return data;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public int getTotal() {
        return total;
    }

    public int getPer_page() {
        return per_page;
    }

    public int getPage() {
        return page;
    }
}
