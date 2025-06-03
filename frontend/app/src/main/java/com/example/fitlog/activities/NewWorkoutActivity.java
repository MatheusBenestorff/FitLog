package com.example.fitlog.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitlog.R;
import com.example.fitlog.adapter.SelectedExerciseDisplayAdapter;
import com.example.fitlog.models.Exercise;
import com.example.fitlog.models.Workout;
import com.example.fitlog.network.RetrofitService;
import com.example.fitlog.network.WorkoutApi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewWorkoutActivity extends AppCompatActivity {

    private static final String TAG = "NewWorkoutActivity";

    private EditText editNomeWorkout;
    private Button btnSalvar, btnAdicionarExercicio;
    private TextView btnCancelar;
    private TextView textOrientacao;
    private RecyclerView recyclerSelectedExercises;

    private List<Exercise> selectedExercises = new ArrayList<>();
    private SelectedExerciseDisplayAdapter selectedExercisesAdapter;

    private final WorkoutApi workoutApi = RetrofitService.getRetrofitInstance().create(WorkoutApi.class);

    private final ActivityResultLauncher<Intent> addExerciseLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    ArrayList<Exercise> exercisesFromResult = (ArrayList<Exercise>) result.getData().getSerializableExtra("selectedExercises");

                    if (exercisesFromResult != null && !exercisesFromResult.isEmpty()) {
                        int countAdded = 0;
                        Set<Long> existingExerciseIds = new HashSet<>();
                        for (Exercise ex : selectedExercises) {
                            if (ex.getId() != null) {
                                existingExerciseIds.add(ex.getId());
                            }
                        }

                        for (Exercise newExercise : exercisesFromResult) {
                            if (newExercise.getId() != null && !existingExerciseIds.contains(newExercise.getId())) {
                                selectedExercises.add(newExercise);
                                existingExerciseIds.add(newExercise.getId());
                                countAdded++;
                            } else if (newExercise.getId() == null) {
                                selectedExercises.add(newExercise);
                                countAdded++;
                                Log.w(TAG, "Adicionado exercício sem ID: " + newExercise.getName());
                            }
                        }

                        if (countAdded > 0) {
                            selectedExercisesAdapter.updateExercises(selectedExercises);
                            Toast.makeText(this, countAdded + " exercício(s) novo(s) adicionado(s)", Toast.LENGTH_SHORT).show();
                        } else if (!exercisesFromResult.isEmpty()) {
                            Toast.makeText(this, "Todos os exercícios selecionados já foram adicionados.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.d(TAG, "Nenhum exercício retornado ou lista vazia.");
                    }
                } else {
                    Log.d(TAG, "Resultado não OK ou dados nulos de AddExerciseInWorkoutActivity. Código: " + result.getResultCode());
                }
                atualizarVisibilidadeListaExercicios();
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
        textOrientacao = findViewById(R.id.textOrientacao);
        recyclerSelectedExercises = findViewById(R.id.recyclerSelectedExercises);

        recyclerSelectedExercises.setLayoutManager(new LinearLayoutManager(this));
        selectedExercisesAdapter = new SelectedExerciseDisplayAdapter(selectedExercises);
        recyclerSelectedExercises.setAdapter(selectedExercisesAdapter);

        btnCancelar.setOnClickListener(v -> finish());

        btnAdicionarExercicio.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddExerciseInWorkoutActivity.class);
            addExerciseLauncher.launch(intent);
        });

        btnSalvar.setOnClickListener(v -> salvarWorkout());

        atualizarVisibilidadeListaExercicios();
    }


    private void atualizarVisibilidadeListaExercicios() {
        if (selectedExercises.isEmpty()) {
            textOrientacao.setVisibility(View.VISIBLE);
            recyclerSelectedExercises.setVisibility(View.GONE);
        } else {
            textOrientacao.setVisibility(View.GONE);
            recyclerSelectedExercises.setVisibility(View.VISIBLE);
        }
    }

    private void salvarWorkout() {
        String nome = editNomeWorkout.getText().toString().trim();

        if (nome.isEmpty()) {
            Toast.makeText(this, "Por favor, insira um nome para o workout.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedExercises.isEmpty()) {
            Toast.makeText(this, "Por favor, adicione pelo menos um exercício ao workout.", Toast.LENGTH_SHORT).show();
            return;
        }

        Workout workout = new Workout();
        workout.setName(nome);
        workout.setDescription("Workout criado via app");
        workout.setExercises(new ArrayList<>(selectedExercises));
        workout.setDate(LocalDate.now().toString());
        Long userId = 1L;

        Log.d(TAG, "Salvando workout: " + nome + " com " + selectedExercises.size() + " exercícios.");

        Call<Workout> call = workoutApi.createWorkout(userId, workout);
        call.enqueue(new Callback<Workout>() {
            @Override
            public void onResponse(Call<Workout> call, Response<Workout> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(NewWorkoutActivity.this, "Workout '" + response.body().getName() + "' salvo com sucesso!", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                } else {
                    String errorMessage = "Erro ao salvar Workout.";
                    if (response.errorBody() != null) {
                        try {
                            errorMessage += " Código: " + response.code() + " Mensagem: " + response.errorBody().string();
                        } catch (Exception e) {
                            Log.e(TAG, "Erro ao ler errorBody", e);
                            errorMessage += " Código: " + response.code();
                        }
                    } else {
                        errorMessage += " Código: " + response.code();
                    }
                    Toast.makeText(NewWorkoutActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                    Log.e(TAG, "Erro ao salvar workout: " + errorMessage);
                }
            }

            @Override
            public void onFailure(Call<Workout> call, Throwable t) {
                Toast.makeText(NewWorkoutActivity.this, "Falha na conexão: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e(TAG, "Falha na API ao salvar workout", t);
            }
        });
    }
}
