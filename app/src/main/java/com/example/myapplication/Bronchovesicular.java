package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;



import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.VideoView;

public class Bronchovesicular extends AppCompatActivity implements View.OnClickListener {
    ProgressDialog mDialog;
    VideoView videoView;
    TextView txt1,txt2,txt3,txtBroncho;
    ImageButton btnPlay,imageButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bronchovesicular);

        videoView = findViewById(R.id.vdoBroncho);
        btnPlay = findViewById(R.id.btnPlay);
        imageButton = findViewById(R.id.imageButton);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        txtBroncho = findViewById(R.id.txtBroncho);
        btnPlay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String videoURL= "android.resource://" + getPackageName() + "/"+ R.raw.broncho;
        mDialog = new ProgressDialog(Bronchovesicular.this);
        mDialog.setCanceledOnTouchOutside(false);

        try {
            if (!videoView.isPlaying()){
                Uri uri = Uri.parse(videoURL);
                videoView.setVideoURI(uri);
                videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        btnPlay.setImageResource(R.drawable.ic_play);
                    }
                });

            }else {
                videoView.pause();
                btnPlay.setImageResource(R.drawable.ic_play);
            }
        }
        catch (Exception ex){

        }
        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mDialog.dismiss();
                mp.setLooping(true);

                videoView.start();
                btnPlay.setImageResource(R.drawable.ic_pause);
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