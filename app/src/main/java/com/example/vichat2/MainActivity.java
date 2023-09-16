package com.example.vichat2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        username = findViewById(R.id.username);
        new Handler().postDelayed(new Runnable() {
            @Override

            public void run() {
                FirebaseUser user = auth.getCurrentUser();
                if(user!=null){
                    startActivity(new Intent(MainActivity.this, Dashboard.class));
                    finish();

                }else {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }

            }

        },2000);

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//    }
}