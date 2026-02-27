package com.example.cyberapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ErrorPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.error_page);

    }

    public void buttonClick(View v) {
        Intent intent = null;

        int id = v.getId();

        if (id == R.id.returnButtonId) {
            intent = new Intent(this, ErrorPage.class);
        }
    }
}
