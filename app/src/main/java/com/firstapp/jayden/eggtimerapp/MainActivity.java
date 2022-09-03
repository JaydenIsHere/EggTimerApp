package com.firstapp.jayden.eggtimerapp;

import android.media.Image;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView countdownText;//initialize
    SeekBar setTimeSeekBar;//initialize
    Button buttonPause;
    Button button;
    boolean counterIsActive = false;
    CountDownTimer countDownTimer;
    ImageView egg;

    public void restart(){
        countdownText.setText("00:30");
        setTimeSeekBar.setProgress(30);
        setTimeSeekBar.setEnabled(true);
        countDownTimer.cancel();//cancel the timer
        button.setText("Start");
        counterIsActive = false;//switch back to false
    }

    public void startButton (View view){

        if(counterIsActive){
//if counterIsActive is false we back to default
            restart();

        }else{
        counterIsActive = true;//someone click the button
        setTimeSeekBar.setEnabled(false);//once button clicked we enable the seekBar
        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.air_horn);
        button = (Button) findViewById(R.id.button);
        button.setText("Stop");

            countDownTimer = new CountDownTimer(setTimeSeekBar.getProgress() * 1000 + 100,1000){


            public void onTick(long l){

                setTimeMethod((int) l / 1000);//1 long = 100000 int

            }
            public void onFinish(){
                //play the sound here
                egg = (ImageView)findViewById(R.id.egg);
                mediaPlayer.start();
                egg.animate().rotation(3600).setDuration(1000);
                restart();
            }
        }.start();

        }
    }



    public void setTimeMethod(int secondLeft){
        int minutes = secondLeft/60;
        int second = secondLeft - (minutes * 60);
        String secondString = Integer.toString(second);
        if(second <= 9){
            secondString = "0" + secondString;
        }
        countdownText.setText(Integer.toString(minutes) + ":" + secondString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countdownText = (TextView) findViewById(R.id.countdownText);//target countdown
        setTimeSeekBar = (SeekBar) findViewById(R.id.setTimeSeekBar);
        setTimeSeekBar.setMax(600);
        setTimeSeekBar.setProgress(30);
        setTimeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //Change textView when move seekBar
                setTimeMethod(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
