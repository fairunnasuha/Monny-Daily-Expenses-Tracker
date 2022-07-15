package com.example.monny;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signuppage extends AppCompatActivity {
    EditText inputEmail,pass1,pass2,userName;
    Button btnRegister;
    boolean passwordvisible;

    FirebaseDatabase fDatabase;
    DatabaseReference dRef;
    String password;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signuppage);
        inputEmail=findViewById(R.id.emailsign);
        pass1=findViewById(R.id.passwordsign);
        pass2=findViewById(R.id.confirmpass);
        userName=findViewById(R.id.usernamesign);
        btnRegister=findViewById(R.id.nextsign);

        pass1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(event.getRawX()>=pass1.getRight()-pass1.getCompoundDrawables()[Right].getBounds().width()){
                        int selection=pass1.getSelectionEnd();
                        if(passwordvisible){
                            pass1.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_baseline_visibility_off_24,0);
                            pass1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordvisible=false;
                        }else {
                            pass1.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_baseline_visibility_24,0);
                            pass1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordvisible=true;

                        }
                        pass1.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });

        pass2.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(event.getRawX()>=pass2.getRight()-pass2.getCompoundDrawables()[Right].getBounds().width()){
                        int selection=pass1.getSelectionEnd();
                        if(passwordvisible){
                            pass2.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_baseline_visibility_off_24,0);
                            pass2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordvisible=false;
                        }else {
                            pass2.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_baseline_visibility_24,0);
                            pass2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordvisible=true;

                        }
                        pass2.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });


        fDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        dRef = fDatabase.getReference().child("Users");


        btnRegister.setOnClickListener(v -> {

            String uemail=inputEmail.getText().toString();
            String password1=pass1.getText().toString();
            String password2=pass2.getText().toString();
            String uname = userName.getText().toString();


            if (uname.isEmpty()){
                Toast.makeText(signuppage.this, "User Name is required", Toast.LENGTH_SHORT).show();
                userName.requestFocus();
                return;
            }

            else if (uemail.isEmpty()){
                Toast.makeText(signuppage.this, "Email is required", Toast.LENGTH_SHORT).show();
                inputEmail.requestFocus();
                return;
            }
            else if (password1.isEmpty() || password2.isEmpty()) {
                Toast.makeText(signuppage.this, "Password is required", Toast.LENGTH_SHORT).show();
                pass1.requestFocus();
                pass2.requestFocus();
                return;
            }
            else if (password1.length()<6||password2.length()<6){
                Toast.makeText(signuppage.this, "Password Must Be More or Equal than 6 Characters", Toast.LENGTH_SHORT).show();
                pass1.requestFocus();
                pass2.requestFocus();
                return;
            }
            else if (!password1.equals(password2)){
                Toast.makeText(signuppage.this, "Password and confirm password must be same", Toast.LENGTH_SHORT).show();
                pass1.requestFocus();
                pass2.requestFocus();
                return;
            }
            else if(password1==password2){
                password = password1;
            }


            mAuth.createUserWithEmailAndPassword(uemail,password1)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                User user = new User(uname,uemail);

                                dRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            Toast.makeText(signuppage.this, "User created succesfully", Toast.LENGTH_SHORT).show();

                                            Intent intent = new Intent(signuppage.this, loginpage.class);
                                            startActivity(intent);
                                        }else {
                                            Toast.makeText(signuppage.this, "Fail to register! Try again!", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                    }
                                });
                            }else{
                                Toast.makeText(getApplicationContext(),"Fail to register! Try again!",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    });

            if (uname!=null ||uemail!=null){
            }

        });
    }

    public void backtomainpg(View view) {
        Intent intent = new Intent(signuppage.this, welcomepage.class);
        startActivity(intent);
    }

    public void tologin(View view) {
        Intent intent = new Intent(signuppage.this,loginpage.class);
        startActivity(intent);
    }
}