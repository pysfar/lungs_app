package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Crackles extends AppCompatActivity {
    Button btnCoarse,btnFines;
    ImageButton imageButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crackles);
        btnCoarse = findViewById(R.id.buttonCoarse);
        btnFines = findViewById(R.id.buttonFines);
        imageButton3 = findViewById(R.id.imageButton3);

        btnCoarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent11 = new Intent(getApplicationContext(),Coarse.class);
                startActivity(intent11);
            }
        });
        btnFines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent12 = new Intent(getApplicationContext(), Fines.class);
                startActivity(intent12);
            }
        });
        imageButton3.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                System.out.println("Exit the program");
                System.exit(0);
            }


        });

    }
}