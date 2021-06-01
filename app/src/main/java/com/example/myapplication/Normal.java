package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Normal extends AppCompatActivity {
    Button btnVes,btnBronchial,btnBroncho ;
    ImageButton imageButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        btnVes = findViewById(R.id.buttonVes);
        btnBronchial = findViewById(R.id.buttonBron);
        btnBroncho = findViewById(R.id.buttonBroncho);
        imageButton2 = findViewById(R.id.imageButton2);

        btnVes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent8 = new Intent(getApplicationContext(), Vesicular.class);
                startActivity(intent8);
            }
        });
        btnBronchial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent9 = new Intent(getApplicationContext(), Bronchial.class);
                startActivity(intent9);
            }
        });
        btnBroncho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent10 = new Intent(getApplicationContext(), Bronchovesicular.class);
                startActivity(intent10);
            }


        });
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                System.out.println("Exit the program");
                System.exit(0);
            }


        });
    }
}