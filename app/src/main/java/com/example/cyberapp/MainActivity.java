package com.example.cyberapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void buttonClick(View v) {
        Intent intent = null;

        int id = v.getId();

        if (id == R.id.AdvQuiz) {
            intent = new Intent(this, advancedHome.class);

        } else if (id == R.id.BegQuiz) {
            intent = new Intent(this, beginnerHome.class);

        } else if (id == R.id.InterQuiz) {
            intent = new Intent(this, intermediateHome.class);

        } else if (id == R.id.PreTest) {
            intent = new Intent(this, preTestHome.class);

        } else {
            intent = new Intent(this, errorPage.class);
        }

        startActivity(intent);
    }
//


}