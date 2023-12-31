package com.example.vichat2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

public class SignupActivity extends AppCompatActivity {

    FirebaseAuth auth;
    EditText emailbox, passwordBox, nameBox;
    Button loginBtn, signupBtn;
    FirebaseFirestore database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();
        emailbox = findViewById(R.id.emailBox);
        passwordBox =findViewById(R.id.passwordBox);
        nameBox = findViewById(R.id.nameBox);
        loginBtn = findViewById(R.id.loginbtn);
        signupBtn = findViewById(R.id.createbtn);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, pass, name;
                email = emailbox.getText().toString();
                pass = passwordBox.getText().toString();
                name = nameBox.getText().toString();

                User user = new User();
                user.setEmail(email);
                user.setPass(pass);
                user.setName(name);
                auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                      if(task.isSuccessful()){
                          database.collection("Users")
                                  .document().set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                              @Override
                              public void onSuccess(Void unused) {
                                  startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                              }
                          });
                          Toast.makeText(SignupActivity.this, "Account is Created", Toast.LENGTH_SHORT).show();
                      }
                      else {
                          Toast.makeText(SignupActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                      }
                    }
                });

            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupActivity.this,LoginActivity.class));
            }
        });
    }
}