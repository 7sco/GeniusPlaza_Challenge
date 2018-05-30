package com.example.franciscoandrade.geniusplazachallenge.data.api;

import com.example.franciscoandrade.geniusplazachallenge.data.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UsersApi {
    @GET("users")
    Call<UserResponse> getUsersList(@Query("per_page") int perPage, @Query("page") int page);

}
