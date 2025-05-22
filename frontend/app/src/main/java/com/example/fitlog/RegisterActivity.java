package com.example.fitlog;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fitlog.models.User;
import com.example.fitlog.network.RetrofitService;
import com.example.fitlog.network.UserApi;

import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameInput, emailInput, passwordInput, birthDateInput, genderInput, heightInput, weightInput;
    private Button registerButton;
    private TextView loginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameInput = findViewById(R.id.nameInput);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        birthDateInput = findViewById(R.id.birthDateInput);
        genderInput = findViewById(R.id.genderInput);
        heightInput = findViewById(R.id.heightInput);
        weightInput = findViewById(R.id.weightInput);
        registerButton = findViewById(R.id.createAccountButton);
        loginLink = findViewById(R.id.loginLink);
        birthDateInput.setFocusable(false);
        birthDateInput.setClickable(true);
        birthDateInput.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    RegisterActivity.this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        String formattedDate = String.format(Locale.getDefault(), "%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay);
                        birthDateInput.setText(formattedDate);
                    },
                    year, month, day
            );
            datePickerDialog.show();
        });



        registerButton.setOnClickListener(v -> attemptRegister());
        loginLink.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }

    private void attemptRegister() {
        String name = nameInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();
        String birthDate = birthDateInput.getText().toString().trim();
        String gender = genderInput.getText().toString().trim();
        String heightStr = heightInput.getText().toString().trim();
        String weightStr = weightInput.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || birthDate.isEmpty()
                || gender.isEmpty() || heightStr.isEmpty() || weightStr.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        Double height = Double.parseDouble(heightStr);
        Double weight = Double.parseDouble(weightStr);

        User user = new User(name, email, password, birthDate, gender, height, weight, null);

        UserApi userApi = RetrofitService.getRetrofitInstance().create(UserApi.class);
        userApi.createUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Conta criada com sucesso!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Erro ao criar conta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("REGISTER", "Erro: ", t);
                Toast.makeText(RegisterActivity.this, "Erro de conexão", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
