package com.example.monny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class homemyexpense extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homemyexpense);
    }

    public void calendarfc(View view) {
        Intent intent = new Intent(homemyexpense.this, MainActivity.class);
        startActivity(intent);
    }
}