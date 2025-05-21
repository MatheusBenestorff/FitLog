package com.example.fitlog.network;

import com.example.fitlog.models.User;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.*;

public interface UserApi {

    // GET /api/users
    @GET("/api/users")
    Call<List<User>> getAllUsers();

    // GET /api/users/{id}
    @GET("/api/users/{id}")
    Call<User> getUserById(@Path("id") Long id);

    // POST /api/users
    @POST("/api/users")
    Call<User> createUser(@Body User user);

    // DELETE /api/users/{id}
    @DELETE("/api/users/{id}")
    Call<Void> deleteUser(@Path("id") Long id);
}
