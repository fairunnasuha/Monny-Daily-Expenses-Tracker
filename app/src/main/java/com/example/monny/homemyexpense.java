package com.example.monny;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class homemyexpense extends AppCompatActivity {
EditText mwallet;
String mymoney;
TextView walletm;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    money moneyCopy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mwallet = findViewById(R.id.wallet);
        walletm = findViewById(R.id.mywallet);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users");

        money moneys = new money(mymoney);

        FirebaseDatabase.getInstance().getReference("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(moneys).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(homemyexpense.this, "User created succesfully", Toast.LENGTH_SHORT).show();


                        }else {
                            Toast.makeText(homemyexpense.this, "Fail to register! Try again!", Toast.LENGTH_SHORT).show();

                        }
                    }
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
            Intent intent = new Intent(homemyexpense.this,UserProfile.class);
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