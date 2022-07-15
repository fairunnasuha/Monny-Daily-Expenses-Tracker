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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.Map;

public class homemyexpense extends AppCompatActivity {
EditText mwallet;
String mymoney,userID;
TextView walletm;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homemyexpense);

        mwallet = findViewById(R.id.wallet);
        walletm = findViewById(R.id.mywallet);



        System.out.println("TEST "+mymoney);

        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(userID).child("MyExpense");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if (map.get("currentExpense") != null) {
                        mymoney = map.get("currentExpense").toString();
                        walletm.setText("RM "+mymoney);

                    }else{
                        walletm.setText("RM 0.0");
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void toaddnote(View view) {
        Intent intent = new Intent(homemyexpense.this,ViewActivity.class);
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

    public void saveMoney(View view) {
        mymoney=mwallet.getText().toString();
        money moneys = new money(mymoney);
        System.out.println("TEST "+mymoney);
        FirebaseDatabase.getInstance().getReference("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("MyExpense")
                .setValue(moneys);

        mwallet.setText("");
    }
}