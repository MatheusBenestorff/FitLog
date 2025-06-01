package com.example.fitlog.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitlog.R;
import com.example.fitlog.adapter.ExerciseAdapter;
import com.example.fitlog.models.Exercise;
import com.example.fitlog.network.ExerciseApi;
import com.example.fitlog.network.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddExerciseInWorkoutActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ExerciseAdapter adapter;
    private final ExerciseApi exerciseApi = RetrofitService.getRetrofitInstance().create(ExerciseApi.class);
    private Button btnCriar, btnAdicionarExercicio;
    private TextView btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise_in_workout);

        recyclerView = findViewById(R.id.recyclerExercicios);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnCriar = findViewById(R.id.btnCriar);
        btnAdicionarExercicio = findViewById(R.id.btnAdicionarExercicio);
        btnCancelar = findViewById(R.id.btnCancelar);

        btnAdicionarExercicio.setVisibility(View.GONE);

        btnAdicionarExercicio.setOnClickListener(v -> {
            if (!adapter.getSelectedExercises().isEmpty()) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("selectedExercises", new ArrayList<>(adapter.getSelectedExercises()));
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        btnCriar.setOnClickListener(v -> {
            Intent intent = new Intent(this, CreateExerciseActivity.class);
            startActivity(intent);
        });

        btnCancelar.setOnClickListener(v -> finish());

        carregarExercicios();
    }

    private void carregarExercicios() {
        Call<List<Exercise>> call = exerciseApi.getAllExercises();
        call.enqueue(new Callback<List<Exercise>>() {
            @Override
            public void onResponse(Call<List<Exercise>> call, Response<List<Exercise>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter = new ExerciseAdapter(response.body(), () -> {
                        int count = adapter.getSelectedExercises().size();

                        btnAdicionarExercicio.setText("Adicionar exercício" + (count != 1 ? "s" : ""));
                        btnAdicionarExercicio.setEnabled(count > 0);
                        btnAdicionarExercicio.setVisibility(count > 0 ? View.VISIBLE : View.GONE);
                    });

                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(AddExerciseInWorkoutActivity.this, "Erro ao carregar exercícios", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Exercise>> call, Throwable t) {
                Toast.makeText(AddExerciseInWorkoutActivity.this, "Falha: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
