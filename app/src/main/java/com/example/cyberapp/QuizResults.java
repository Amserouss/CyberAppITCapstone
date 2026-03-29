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
        TextView difficultyText = findViewById(R.id.difficultyText);


        int score = getIntent().getIntExtra("SCORE", 0);
        int total = getIntent().getIntExtra("TOTAL", 0);
        String quizTitle = getIntent().getStringExtra("QUIZ_TITLE");

        resultText.setText(
                quizTitle + "\n\nScore: " + score + " / " + total

        );
        String difficulty = getIntent().getStringExtra("DIFFICULTY");

        if (difficulty != null && !difficulty.isEmpty()) {
            difficultyText.setVisibility(View.VISIBLE);
            difficultyText.setText("Difficulty Level: " + difficulty);
        } else {
            difficultyText.setVisibility(View.GONE);
        }



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
