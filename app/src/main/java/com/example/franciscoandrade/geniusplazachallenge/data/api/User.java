package com.example.franciscoandrade.geniusplazachallenge.data.api;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("id")
    private String id;
    @SerializedName("job")
    private String job;
    @SerializedName("name")
    private String name;

    public String getCreatedAt() {
        return createdAt;
    }

    public String getId() {
        return id;
    }

    public String getJob() {
        return job;
    }

    public String getName() {
        return name;
    }
}
