package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Adventitious extends AppCompatActivity {
    Button btnCrackles,btnWheezing,btnRhonchi,btnStridor ;
    ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventitious);
        btnCrackles = findViewById(R.id.buttonCrackles);
        btnWheezing = findViewById(R.id.buttonWheezing);
        btnRhonchi = findViewById(R.id.buttonRhonchi);
        btnStridor = findViewById(R.id.buttonStridor);
        imageButton = findViewById(R.id.imageButtona);




        btnCrackles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getApplicationContext(), Crackles.class);
                startActivity(intent3);
            }
        });
        btnWheezing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(getApplicationContext(), Wheezing.class);
                startActivity(intent4);
            }
        });
        btnRhonchi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(getApplicationContext(), Rhonchi.class);
                startActivity(intent5);
            }


        });
        btnStridor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent6 = new Intent(getApplicationContext(), Stridor.class);
                startActivity(intent6);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Exit the program");
                System.exit(0);
            }


        });

    }
}