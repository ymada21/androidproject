package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivitiy extends AppCompatActivity {
    private EditText email ,password;
    private Button signin;
    private TextView register;
    FirebaseAuth firebaseAuth ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activitiy);
        initViews();
        firebaseAuth= FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivitiy.this, RegeisterationActivity.class);
                startActivity(intent);
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();


            }
        });

    }
    private void initViews(){
        email = findViewById(R.id.etemail);
        password = findViewById(R.id.etpwd);
        signin = findViewById(R.id.btnsignin);
        register= findViewById(R.id.signup);

    }
    private void signIn(){
        String Email = email.getText().toString();
        String Password = password.getText().toString();
        if(Email.isEmpty()){
            Toast.makeText(this, "Email required", Toast.LENGTH_SHORT).show();
        }else if (Password.isEmpty()) {
            Toast.makeText(this, "Password required", Toast.LENGTH_SHORT).show();
        }else{
            firebaseAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(LoginActivitiy.this, "Login succsessfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivitiy.this, MainActivity.class);
                        startActivity(intent);
                        LoginActivitiy.this.finish();

                    }
                    else{
                        Toast.makeText(LoginActivitiy.this, "Something error check your email and passsword", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }

    }
}