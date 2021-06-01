package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



import android.content.Intent;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.myapplication.Common.Common;
import com.squareup.picasso.Picasso;


public class Playing extends AppCompatActivity implements View.OnClickListener {
   final static long INTERVAL = 3000;
   final static long TIMEOUT = 30000;
   int progressValue = 0;

   CountDownTimer mCountDown;
   int index=0,score=0,thisQuestion,totalQuestion,correctAnswer;


   ProgressBar progressBar;
   ImageView question_image;
   Button btnA,btnB,btnC;
   TextView txtScore,txtQuestionNum,question_text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);


        txtScore = findViewById(R.id.txtScore);
        txtQuestionNum = findViewById(R.id.txtTotalQuestion);
        question_text = findViewById(R.id.question_text);
        question_image = findViewById(R.id.question_image);

        progressBar = findViewById(R.id.progressBar);

        btnA = (Button) findViewById(R.id.btnAnswerA);
        btnB = (Button) findViewById(R.id.btnAnswerB);
        btnC = (Button) findViewById(R.id.btnAnswerC);

        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
    }
        @Override
        public void onClick(View view) {

                mCountDown.cancel();
                if (index < totalQuestion)
                {
                    Button  clickedButton = (Button)view;
                    if (clickedButton.getText().equals(Common.questionList.get(index).getCorrectAnswer()))
                    {
                        score+=10;
                        correctAnswer++;
                        showQuestion(++index);
                    }
                    else{
                        Intent intent = new Intent(this,Done.class);
                        Bundle dataSend = new Bundle();
                        dataSend.putInt("SCORE",score);
                        dataSend.putInt("TOTAL",totalQuestion);
                        dataSend.putInt("CORRECT",correctAnswer);

                        intent.putExtras(dataSend);
                        startActivity(intent);
                        finish();
                    }
                }
            txtScore.setText(String.format(String.format("%d",score)));

            }




        private void showQuestion(int index) {
            if (index < totalQuestion)
            {

                thisQuestion++;
                txtQuestionNum.setText(String.format("%d / %d",thisQuestion,totalQuestion));
                progressBar.setProgress(0);
                progressValue=0;
                if (Common.questionList.get(index).getIsImageQuestion().equals("true"))
                {
                    Picasso.with(getBaseContext())
                            .load(Common.questionList.get(index).getQuestion())
                            .into(question_image);
                    question_image.setVisibility(View.INVISIBLE);
                    question_text.setText(View.VISIBLE);

                }else {
                    question_text.setText(Common.questionList.get(index).getQuestion());

                    question_text.setVisibility(View.VISIBLE);
                    question_image.setVisibility(View.INVISIBLE);
                }
                btnA.setText(Common.questionList.get(index).getAnswerA());
                btnB.setText(Common.questionList.get(index).getAnswerB());
                btnC.setText(Common.questionList.get(index).getAnswerC());

                mCountDown.start();

            }else {
                Intent intent = new Intent(this,Done.class);
                Bundle dataSend = new Bundle();
                dataSend.putInt("SCORE",score);
                dataSend.putInt("TOTAL",totalQuestion);
                dataSend.putInt("CORRECT",correctAnswer);
                intent.putExtras(dataSend);
                startActivity(intent);
                finish();

            }

        }

        @Override
        protected void onResume() {
            super.onResume();

            totalQuestion = Common.questionList.size();

            mCountDown = new CountDownTimer(TIMEOUT,INTERVAL) {
                @Override
                public void onTick(long minisec) {
                    progressBar.setProgress(progressValue);
                    progressValue++;


                }

                @Override
                public void onFinish() {
                    mCountDown.cancel();
                    showQuestion(++index);
                }
            };
            showQuestion(index);
        }

    }
