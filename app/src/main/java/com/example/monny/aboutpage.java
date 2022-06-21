package com.example.monny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class aboutpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutpage);

    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    public void gitlink(View view) {
        gotoUrl("https://github.com/fairunnasuha/Monny-Daily-Expenses-Tracker");
    }

    public void ytubelink(View view) {
        gotoUrl("https://www.youtube.com/channel/UCtviIetLp-9CrEGigjrV3fw");
    }

    public void emellink(View view) {
        gotoUrl("2020489584@student.uitm.edu.my");
    }
}