package com.example.monny;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class UserProfile extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mCustomerDatabase;
    private String userID, mName, memail, mProfileImageUrl;
    EditText ename,ephone,eemail,efull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        ename = findViewById(R.id.edittext_name);
        ephone = findViewById(R.id.edittext_phone);
        eemail = findViewById(R.id.edittext_email);
        efull = findViewById(R.id. edittext_fullname);

        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();
        mCustomerDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);

        mCustomerDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if (map.get("username") != null) {
                        mName = map.get("username").toString();
                        ename.setText(mName);

                        if (map.get("useremail") != null) {
                            memail = map.get("useremail").toString();
                            eemail.setText(memail);
                        }

                    }
                }


            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        }
        }