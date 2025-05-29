package com.example.fitlog.network;

import com.example.fitlog.models.Workout;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface WorkoutApi {

    @GET("/api/workouts/user/{userId}")
    Call<List<Workout>> getWorkoutsByUser(@Path("userId") Long userId);

    @POST("/api/workouts/user/{userId}")
    Call<Workout> createWorkout(@Path("userId") Long userId, @Body Workout workout);
}
