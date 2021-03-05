package com.example.filmflix.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.filmflix.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChooseActivity extends AppCompatActivity {

    Button chooseLogin, chooseRegister;
    private Intent popularMoviesActivity;
    private ImageView gif;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        popularMoviesActivity = new Intent(this, PopularMoviesActivity.class);
        chooseLogin = findViewById(R.id.choose_login);
        chooseRegister = findViewById(R.id.choose_register);

        gif = findViewById(R.id.gif);
        Glide.with(getApplicationContext()).load(R.drawable.pocetna2gif).into(gif);

        mAuth = FirebaseAuth.getInstance();

        chooseLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChooseActivity.this, LoginActivity.class));
            }
        });

        chooseRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChooseActivity.this, RegisterActivity.class));
            }
        });
    }

    private void updateUI() {
        startActivity(popularMoviesActivity);
        finish();
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseUser user = mAuth.getCurrentUser();
//        if(user != null) {
//            //user is already connected  so we need to redirect him to home page
//            updateUI();
//        }
//    }
}