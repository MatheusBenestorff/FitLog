package com.example.fitlog.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.example.fitlog.R;
import com.example.fitlog.adapter.WorkoutAdapter;
import com.example.fitlog.models.Workout;
import com.example.fitlog.activities.NewWorkoutActivity;
import com.example.fitlog.network.WorkoutApi;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.util.Log;
import com.example.fitlog.network.RetrofitService;



public class WorkoutsFragment extends Fragment {

    private RecyclerView recyclerView;
    private WorkoutAdapter adapter;
    private List<Workout> workoutList = new ArrayList<>();
    private LinearLayout btnNovoWorkout;

    private WorkoutApi workoutApi;

    private Long userId = 1L;

    public WorkoutsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workouts, container, false);

        recyclerView = view.findViewById(R.id.recyclerRotinas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new WorkoutAdapter(workoutList);
        recyclerView.setAdapter(adapter);

        workoutApi = RetrofitService.getRetrofitInstance().create(WorkoutApi.class);
        getWorkouts();

        btnNovoWorkout = view.findViewById(R.id.btnNovoWorkout);
        btnNovoWorkout.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), NewWorkoutActivity.class);
            startActivity(intent);
        });

        return view;
    }

    private void getWorkouts() {
        Call<List<Workout>> call = workoutApi.getWorkoutsByUser(userId);
        call.enqueue(new Callback<List<Workout>>() {
            @Override
            public void onResponse(Call<List<Workout>> call, Response<List<Workout>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    workoutList.clear();
                    workoutList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Workout>> call, Throwable t) {
                Log.e("WorkoutsFragment", "Erro ao buscar workouts", t);
            }
        });
    }

}
