package com.example.fitlog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private TextView welcomeMessage;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        welcomeMessage = findViewById(R.id.welcomeMessage);
        logoutButton = findViewById(R.id.logoutButton);

        welcomeMessage.setText("Bem-vindo ao FitLog!");

        logoutButton.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, MainActivity.class));
            finish();
        });
    }
}
