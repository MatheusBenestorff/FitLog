package com.example.fitlog.network;

import com.example.fitlog.models.Exercise;
import retrofit2.Call;
import retrofit2.http.*;
import java.util.List;

public interface ExerciseApi {
    @GET("/api/exercises")
    Call<List<Exercise>> getAllExercises();
}
