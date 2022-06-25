package com.example.monny;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.setting_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.profilee)
        {
            Intent intent = new Intent(homemyexpense.this,Profilepage.class);
            startActivity(intent);
            return true;

        }
        else
            if (id == R.id.about)
        {
            Intent intent = new Intent(homemyexpense.this,aboutpage.class);
            startActivity(intent);
            return true;

        }
        else
            if (id==R.id.notifi)
            {
                Intent intent = new Intent(homemyexpense.this,notificationpage.class);
                startActivity(intent);
                return true;

            }
            else
                if(id==R.id.weeklyy)
                {
                    Intent intent = new Intent(homemyexpense.this,weeklyspend.class);
                    startActivity(intent);
                    return true;
                }
                else
                if(id==R.id.monthlyy)
                {
                    Intent intent = new Intent(homemyexpense.this,monthlypage.class);
                    startActivity(intent);
                    return true;
                }
                else
                if(id==R.id.sdcategory)
                {
                    Intent intent = new Intent(homemyexpense.this,spendbycategory.class);
                    startActivity(intent);
                    return true;
                }
        return super.onOptionsItemSelected(item);

    }
}