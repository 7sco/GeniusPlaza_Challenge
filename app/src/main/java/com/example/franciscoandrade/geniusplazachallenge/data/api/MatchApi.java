package com.example.franciscoandrade.geniusplazachallenge.data.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MatchApi {
    @GET("/users")
    Call<UserResponse> getUsersList(@Query("page") int page);

}
