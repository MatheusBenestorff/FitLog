package com.example.fitlog.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitlog.R;
import com.example.fitlog.models.Exercise;
import com.example.fitlog.network.ExerciseApi;
import com.example.fitlog.network.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateExerciseActivity extends AppCompatActivity {

    private EditText editNome, editGrupo;
    private Button btnSalvar;

    private final ExerciseApi exerciseApi = RetrofitService.getRetrofitInstance().create(ExerciseApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exercise);

        editNome = findViewById(R.id.editNomeExercicio);
        editGrupo = findViewById(R.id.editGrupoMuscular);
        btnSalvar = findViewById(R.id.btnSalvarExercicio);

        btnSalvar.setOnClickListener(v -> {
            String nome = editNome.getText().toString().trim();
            String grupo = editGrupo.getText().toString().trim();

            if (nome.isEmpty() || grupo.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            Exercise exercise = new Exercise();
            exercise.setName(nome);
            exercise.setMuscleGroup(grupo);

            exerciseApi.createExercise(exercise).enqueue(new Callback<Exercise>() {
                @Override
                public void onResponse(Call<Exercise> call, Response<Exercise> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(CreateExerciseActivity.this, "Exercício criado!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        String erroMsg = "Erro: " + response.code();
                        try {
                            erroMsg += " - " + response.errorBody().string();
                        } catch (Exception e) {
                            erroMsg += " (sem detalhes)";
                        }
                        Toast.makeText(CreateExerciseActivity.this, erroMsg, Toast.LENGTH_LONG).show();
                    }
                }


                @Override
                public void onFailure(Call<Exercise> call, Throwable t) {
                    Toast.makeText(CreateExerciseActivity.this, "Falha: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
