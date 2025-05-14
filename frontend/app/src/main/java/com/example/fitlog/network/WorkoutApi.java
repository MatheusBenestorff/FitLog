package com.example.fitlog.network;

import com.example.fitlog.models.Workout;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.*;

public interface WorkoutApi {
    @GET("/api/workouts")
    Call<List<Workout>> getAllWorkouts();

    @POST("/api/workouts")
    Call<Workout> createWorkout(@Body Workout workout);

    @GET("/api/workouts/{id}")
    Call<Workout> getWorkoutById(@Path("id") Long id);
}
