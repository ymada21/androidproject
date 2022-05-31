package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegeisterationActivity extends AppCompatActivity {
    private TextView signin;
    FirebaseAuth firebaseAuth;
    private Button signup;
    private EditText fullname, email, password;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regeisteration);
        initViews();
        firebaseAuth = FirebaseAuth.getInstance();
        progressBar.setVisibility(View.GONE);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegeisterationActivity.this, LoginActivitiy.class);
                startActivity(intent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
                progressBar.setVisibility(View.VISIBLE);


            }
        });
    }

    private void initViews() {
        signin = findViewById(R.id.signin);
        fullname = findViewById(R.id.etname);
        email = findViewById(R.id.etemail);
        password = findViewById(R.id.etpwd);
        signup = findViewById(R.id.btnsignup);
        progressBar = findViewById(R.id.progressbar);
    }

    private void signUp() {
        String Fullname = fullname.getText().toString();
        String Email = email.getText().toString();
        String Password = password.getText().toString();
        if (Fullname.isEmpty()) {
            Toast.makeText(this, "Fullname can't be empty", Toast.LENGTH_SHORT).show();
        } else if (Email.isEmpty()) {
            Toast.makeText(this, "Email can't be empty", Toast.LENGTH_SHORT).show();

        } else if (Password.length() < 6) {
            Toast.makeText(this, "Password can't be less than 6 digits", Toast.LENGTH_SHORT).show();

        } else {
            //send request to firebase Auth then check if status succses
            //send all data to firestore else show  error massage
            firebaseAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    //send data to firestore
                    if (task.isSuccessful()) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(RegeisterationActivity.this, "Account created succsessfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegeisterationActivity.this, MainActivity.class);
                        startActivity(intent);
                        RegeisterationActivity.this.finish();

                    }
                     else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(RegeisterationActivity.this, "Error while create account", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }

    }
}
