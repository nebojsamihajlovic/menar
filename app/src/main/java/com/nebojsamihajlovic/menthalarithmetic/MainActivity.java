package com.nebojsamihajlovic.menthalarithmetic;

import android.content.Intent;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int DIGITS_NUMBER = 15;
    private static final int SPEED = 1500;
    private final long startTime = DIGITS_NUMBER * 1000;
    private final long interval = SPEED;
    public TextView text;
    short[] digitsArray;
    int sum = 0;
    private CountDownTimer countDownTimer;
    // private boolean timerHasStarted = false;
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button) this.findViewById(R.id.btnStart);
        btnStart.setOnClickListener(this);

        text = (TextView) this.findViewById(R.id.number);

        countDownTimer = new MyCountDownTimer(startTime, interval);

        text.setText("M e n a r");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.settings_id:
                // Intent intent = new Intent(HomePage.this, Setting.class);
                // startActivity(intent);
                // finish();

                Toast.makeText(this, "You pressed the Setting!", Toast.LENGTH_LONG).show();

                break;


            case R.id.about_us_id:

                Toast.makeText(this, "Author - Mihajlovic Nebojsa", Toast.LENGTH_LONG).show();
                break;


        }

        return super.onOptionsItemSelected(item);
    }


    private void createDigitsArray() {
        digitsArray = new short[DIGITS_NUMBER];

        Random random = new Random();
        int randomNumber;
        short counter = 0;

        while (counter < DIGITS_NUMBER) {
            randomNumber = random.nextInt(19) - 9;

            if ((randomNumber == 0) || (sum + randomNumber <= 0) || (sum + randomNumber >= 10)) {
            } else {
                digitsArray[counter++] = (short) randomNumber;
                sum += randomNumber;
            }
        }
    }

    @Override
    public void onClick(View v) {
        createDigitsArray();
        countDownTimer.start();
        btnStart.setEnabled(false);
    }

    public class MyCountDownTimer extends CountDownTimer {

        int counter = 0;
        ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);


        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onFinish() {

            toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP, 1500);

            text.setText("Result: " + sum);
            btnStart.setEnabled(true);

            counter = 0;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            text.setText(String.valueOf(digitsArray[counter++]));

            toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP, 150);
        }
    }
}