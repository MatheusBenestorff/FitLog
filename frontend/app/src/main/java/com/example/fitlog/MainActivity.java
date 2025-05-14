package com.example.fitlog;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.example.fitlog.models.Workout;
import com.example.fitlog.network.RetrofitService;
import com.example.fitlog.network.WorkoutApi;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private WorkoutApi workoutApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        workoutApi = RetrofitService.getRetrofitInstance().create(WorkoutApi.class);

        
        Call<List<Workout>> call = workoutApi.getAllWorkouts();
        call.enqueue(new Callback<List<Workout>>() {
            @Override
            public void onResponse(Call<List<Workout>> call, Response<List<Workout>> response) {
                if (response.isSuccessful()) {
                    List<Workout> workouts = response.body();
                    for (Workout workout : workouts) {
                        Log.d("Workout", "ID: " + workout.getId() + ", Nome: " + workout.getName());
                    }
                } else {
                    Log.e("API Error", "Código: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Workout>> call, Throwable t) {
                Log.e("API Failure", t.getMessage());
            }
        });
    }
}
