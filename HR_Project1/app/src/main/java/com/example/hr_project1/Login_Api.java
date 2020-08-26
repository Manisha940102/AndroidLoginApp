package com.example.hr_project1;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface Login_Api {

    @POST("login")
    Call<Login_Response> createPost(@Body Post post);

    @PUT("user")
    Call<Update_Response> createPut(@Body Put put);

    @POST("take")
    Call<Response_Take> createTake(@Body Take take);

    @POST("user")
    Call<Register_Add> createAddUser(@Body AddUser adduser);

}
