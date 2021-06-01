package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplication.Common.Common;
import com.example.myapplication.model.QuestionScore;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Done extends AppCompatActivity {
    Button btnNextPart;
    TextView txtResultScore,getTxtResultScore;
    ProgressBar progressBar;

    FirebaseDatabase database;
    DatabaseReference question_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);

        database = FirebaseDatabase.getInstance();
        question_score = database.getReference("Question_Score");

        txtResultScore = findViewById(R.id.txtTotalScore);
        getTxtResultScore = findViewById(R.id.txtTotalQuestion);
        progressBar = findViewById(R.id.doneProgressBar);
        btnNextPart = findViewById(R.id.btnNextPart);

        btnNextPart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Done.this,Home.class);
                startActivity(intent);
                finish();
            }
        });

        Bundle extra = getIntent().getExtras();
        if (extra != null)
        {
            int score = extra.getInt("SCORE");
            int totalQuestion = extra.getInt("TOTAL");
            int correctAnswer = extra.getInt("CORRECT");

            txtResultScore.setText(String.format("SCORE : %d",score));
            getTxtResultScore.setText(String.format("PASSED : %d / %d",correctAnswer,totalQuestion));

            progressBar.setMax(totalQuestion);
            progressBar.setProgress(correctAnswer);

            question_score.child(String.format("%s_%s", Common.currentUser.getUsername(),
                                                 Common.categoryId1))
                    .setValue(new QuestionScore(String.format("%s_%s", Common.currentUser.getUsername(),
                    Common.categoryId1),
                            Common.currentUser.getUsername(),
                            String.valueOf(score),
                            Common.categoryId1,
                            Common.categoryName1));
            question_score.child(String.format("%s_%s", Common.currentUser.getUsername(),
                    Common.categoryId2))
                    .setValue(new QuestionScore(String.format("%s_%s", Common.currentUser.getUsername(),
                            Common.categoryId2),
                            Common.currentUser.getUsername(),
                            String.valueOf(score),
                            Common.categoryId2,
                            Common.categoryName2));

        }
    }
}