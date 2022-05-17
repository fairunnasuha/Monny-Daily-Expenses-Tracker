package com.example.monny;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class welcomepage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);
    }

    public void logintonextpage(View view) {
        Intent intent = new Intent(welcomepage.this,loginpage.class);
        startActivity(intent);
    }

    public void signuptonextpage(View view) {
        Intent intent = new Intent(welcomepage.this,signuppage.class);
        startActivity(intent);
    }
}