package com.example.monny;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class welcomepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    public void logintonextpage(View view) {
        Intent intent = new Intent(welcomepage.this,loginpage.class);
        startActivity(intent);
    }

    public void signuptonextpage(View view) {
        Intent intent = new Intent(welcomepage.this,signuppage.class);
        startActivity(intent);
    }

    public void githublink(View view) {
        gotoUrl("https://github.com/fairunnasuha/Monny-Daily-Expenses-Tracker");
    }

    public void utubelink(View view) {
        gotoUrl("https://www.youtube.com/channel/UCtviIetLp-9CrEGigjrV3fw");
    }

    public void emaillink(View view) {
        gotoUrl("2020489584@student.uitm.edu.my");
    }
}