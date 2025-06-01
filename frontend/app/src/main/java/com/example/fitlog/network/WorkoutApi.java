package com.example.fitlog.network;

import com.example.fitlog.models.Workout;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface WorkoutApi {

    @GET("/api/users/{userId}/workouts")
    Call<List<Workout>> getWorkoutsByUser(@Path("userId") Long userId);

    @POST("/api/users/{userId}/workouts")
    Call<Workout> createWorkout(@Path("userId") Long userId, @Body Workout workout);
}
