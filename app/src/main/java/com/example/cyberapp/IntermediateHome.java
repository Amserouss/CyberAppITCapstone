package com.example.cyberapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class IntermediateHome extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.intermediate_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.intermediateHomeId), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void buttonClick(View v) {
        Intent intent = null;

        int id = v.getId();

        if (id == R.id.webQuizInterId) {
            intent = new Intent(this, BegWebQuiz.class);

        } else if (id == R.id.devQuizInterId) {
            intent = new Intent(this, BegDevQuiz.class);

        } else if (id == R.id.cyberQuizInterId) {
            intent = new Intent(this, BegCyberQuiz.class);

        } else if (id == R.id.returnButtonId) {
            intent = new Intent(this, MainActivity.class);

        } else {
            intent = new Intent(this, ErrorPage.class);
        }

        startActivity(intent);
    }
}
