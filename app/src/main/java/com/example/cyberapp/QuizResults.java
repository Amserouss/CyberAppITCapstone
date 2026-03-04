package com.example.cyberapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;


public class QuizResults extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_results);

        TextView resultText = findViewById(R.id.resultText);

        int score = getIntent().getIntExtra("SCORE", 0);
        int total = getIntent().getIntExtra("TOTAL", 0);
        String quizTitle = getIntent().getStringExtra("QUIZ_TITLE");

        resultText.setText(
                quizTitle + "\n\nScore: " + score + " / " + total
        );
    }
    public void buttonClick(View v) {
        Intent intent = null;

        int id = v.getId();
        if (id == R.id.returnButtonId) {
            intent = new Intent(this, MainActivity.class);

        } else {
            intent = new Intent(this, ErrorPage.class);
        }

        startActivity(intent);
    }

}
