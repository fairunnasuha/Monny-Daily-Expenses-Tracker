package com.example.monny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginpage extends AppCompatActivity {
    EditText inputEmail, inputPassword;
    String email, password;
    Button btnLogin;
    private FirebaseAuth mAuth;
    boolean passwordVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        inputEmail = findViewById(R.id.emaillogin);
        inputPassword = findViewById(R.id.passlog);
        btnLogin = findViewById(R.id.nextlog);
        mAuth = FirebaseAuth.getInstance();

        inputPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(event.getRawX()>=inputPassword.getRight()-inputPassword.getCompoundDrawables()[Right].getBounds().width()){
                        int selection=inputPassword.getSelectionEnd();
                        if(passwordVisible){
                            inputPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_baseline_visibility_off_24,0);
                            inputPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible=false;
                        }else {
                            inputPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_baseline_visibility_24,0);
                          inputPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible=true;

                        }
                        inputPassword.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });
    }

    private void checkLogin() {

        email = inputEmail.getText().toString();
        password = inputPassword.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(loginpage.this, "email or password cannot empty", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(loginpage.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            finish();
                            Intent i = new Intent(getApplicationContext(), homemyexpense.class);
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(loginpage.this, "Login Unsuccessful, Email or Password ", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    public void backtomain(View view) {
        Intent intent = new Intent(loginpage.this, welcomepage.class);
        startActivity(intent);
    }

    public void tohome(View view) {
        Intent intent = new Intent(loginpage.this, homemyexpense.class);
        startActivity(intent);
    }
}
