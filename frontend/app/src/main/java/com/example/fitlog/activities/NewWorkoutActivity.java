package com.example.fitlog.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fitlog.R;
import com.example.fitlog.models.Exercise;
import com.example.fitlog.models.Workout;
import com.example.fitlog.network.RetrofitService;
import com.example.fitlog.network.WorkoutApi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewWorkoutActivity extends AppCompatActivity {

    private EditText editNomeWorkout;
    private Button btnSalvar, btnAdicionarExercicio;
    private TextView btnCancelar;

    private List<Exercise> selectedExercises = new ArrayList<>();

    private final WorkoutApi workoutApi = RetrofitService.getRetrofitInstance().create(WorkoutApi.class);

    private final ActivityResultLauncher<Intent> addExerciseLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    List<Exercise> exercises = (List<Exercise>) result.getData().getSerializableExtra("selectedExercises");
                    if (exercises != null) {
                        selectedExercises.addAll(exercises);
                        Toast.makeText(this, exercises.size() + " exercício(s) adicionado(s)", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_workout);

        editNomeWorkout = findViewById(R.id.editNomeWorkout);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnAdicionarExercicio = findViewById(R.id.btnAdicionarExercicio);
        btnCancelar = findViewById(R.id.btnCancelar);

        btnCancelar.setOnClickListener(v -> finish());

        btnAdicionarExercicio.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddExerciseInWorkoutActivity.class);
            addExerciseLauncher.launch(intent);
        });

        btnSalvar.setOnClickListener(v -> salvarWorkout());
    }

    private void salvarWorkout() {
        String nome = editNomeWorkout.getText().toString().trim();

        if (nome.isEmpty()) {
            Toast.makeText(this, "Por favor, insira um título.", Toast.LENGTH_SHORT).show();
            return;
        }

        Workout workout = new Workout();
        workout.setName(nome);
        workout.setDescription("Workout criado via app");
        workout.setExercises(selectedExercises);
        workout.setDate(LocalDate.now().toString());
        Long userId = 1L;

        Call<Workout> call = workoutApi.createWorkout(userId, workout);
        call.enqueue(new Callback<Workout>() {
            @Override
            public void onResponse(Call<Workout> call, Response<Workout> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(NewWorkoutActivity.this, "Workout salvo com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(NewWorkoutActivity.this, "Erro ao salvar Workout.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Workout> call, Throwable t) {
                Toast.makeText(NewWorkoutActivity.this, "Falha na conexão: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
