package com.example.monny;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import io.realm.Realm;

public class AddNoteActivity extends AppCompatActivity {
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    static String currentMoney;
    String userID;
    boolean loop=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        EditText titleInput = findViewById(R.id.purchaseInput);
        EditText descriptionInput = findViewById(R.id.descriptioninput);
        EditText price = findViewById(R.id.priceInput);
        Spinner category = findViewById(R.id.categoryInput);
        MaterialButton saveBtn = findViewById(R.id.savebtn);

        spinner = (Spinner) findViewById(R.id.categoryInput);
        adapter = ArrayAdapter.createFromResource(this, R.array.names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " Selected", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleInput.getText().toString();
                String description = descriptionInput.getText().toString();
                String prices = price.getText().toString();
                String categorys = category.getSelectedItem().toString();
                long createdTime = System.currentTimeMillis();

                realm.beginTransaction();
                com.example.monny.Note note = realm.createObject(com.example.monny.Note.class);
                note.setTitle(title);
                note.setPrice(prices);
                note.setCategory(categorys);
                note.setDescription(description);
                note.setCreatedTime(createdTime);
                realm.commitTransaction();
                Toast.makeText(getApplicationContext(),"Note saved",Toast.LENGTH_SHORT).show();

                mAuth = FirebaseAuth.getInstance();
                userID = mAuth.getCurrentUser().getUid();
                databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(userID).child("MyExpense");

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                            Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();

                            if (map.get("currentExpense") != null&&loop==false) {
                                currentMoney = map.get("currentExpense").toString();
                                double dmoney = Double.parseDouble(currentMoney)-Double.parseDouble(prices);
                                money test = new money(Double.toString(dmoney));
                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("MyExpense")
                                        .setValue(test);
                                loop=true;
                            }else{

                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });

                finish();


            }
        });


    }
}