package com.example.termproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

@SuppressLint("Registered")
public class alcoholTest extends AppCompatActivity {
    TextView timeOut;
    TextView numSoju;
    TextView numBeer;
    Button half_soju;
    Button full_soju;
    Button half_beer;
    Button full_beer;
    Button start_end;
    Button home;

    String year = "";
    String month = "";
    String day = "";
    String date = "";
    SQLiteDatabase db;

    Handler handler = new Handler();
    final static int Wait = 0;
    final static int Run = 1;
    final static int ACT_EDIT = 0;

    int randomValue = 0;
    int cur_Status;
    double soju_count = 0;
    double beer_count = 0;
    int myResultTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alcoholtest);

        home = findViewById(R.id.home_button);
        numBeer = findViewById(R.id.num_beer);
        numSoju = findViewById(R.id.num_soju);
        timeOut = findViewById(R.id.timer_text);
        half_soju = findViewById(R.id.soju_half);
        full_soju = findViewById(R.id.soju_full);
        half_beer = findViewById(R.id.beer_half);
        full_beer = findViewById(R.id.beer_full);
        start_end = findViewById(R.id.end_button);
        start_end.setText("start");
        numSoju.setEnabled(false);
        numBeer.setEnabled(false);
        half_beer.setEnabled(false);
        half_soju.setEnabled(false);
        full_soju.setEnabled(false);
        full_beer.setEnabled(false);

        cur_Status = Wait;
        Random rand = new Random();

        half_soju.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                myOnClick(half_soju);
            }
        });
        full_soju.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myOnClick(full_soju);
            }
        });
        half_beer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myOnClick(half_beer);
            }
        });
        full_beer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myOnClick(full_beer);
            }
        });
        start_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myOnClick(start_end);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu.class);
                startActivity(intent);
            }
        });

        if((soju_count%5 == 0 && soju_count !=0) ||
                (beer_count%5 == 0 && beer_count !=0)){
            randomValue = rand.nextInt(2);
            switch (randomValue){
                case 1:
                    Intent textgame = new Intent(getApplicationContext(), TextGame.class);
                    startActivity(textgame);
                case 2:
                    Intent calgame = new Intent(getApplicationContext(), calGame.class);
                    startActivity(calgame);
            }
        }
    }

    public void myOnClick(View v){
        timeThread thread = new timeThread();
        switch (v.getId()) {
            case R.id.home_button:
                Intent backIntent = new Intent(getApplicationContext(), MainActivity.class);
                backIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(backIntent);
            case R.id.soju_full:
                soju_count = soju_count + 1.0;
                numSoju.setText("소주 " + soju_count + "잔");
                break;
            case R.id.soju_half:
                soju_count += 0.5;
                numSoju.setText("소주 " + soju_count + "잔");
                break;
            case R.id.beer_full:
                beer_count = beer_count + 1.0;
                numBeer.setText("맥주 " + beer_count + "잔");
                break;
            case R.id.beer_half:
                beer_count += 0.5;
                numBeer.setText("맥주 " + beer_count + "잔");
                break;
            case R.id.end_button:
                switch (cur_Status) {
                    case Wait:
                        Date currentTime = Calendar.getInstance().getTime();
                        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
                        SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
                        SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());
                        year = yearFormat.format(currentTime);
                        month = monthFormat.format(currentTime);
                        day = dayFormat.format(currentTime);
                        date = year + month + day;

                        thread.start();
                        start_end.setText("end");
                        cur_Status = Run;

                        numBeer.setEnabled(true);
                        numSoju.setEnabled(true);
                        half_soju.setEnabled(true);
                        full_soju.setEnabled(true);
                        half_beer.setEnabled(true);
                        full_beer.setEnabled(true);

                        break;
                    case Run:
                        Intent result = new Intent(alcoholTest.this,
                                resultPage.class);
                        thread.stopForever();
                        result.putExtra("timeText", myResultTime);
                        result.putExtra("numberOfSoju", soju_count);
                        result.putExtra("numberOfBeer", beer_count);
                        result.putExtra("date", date);
                        startActivityForResult(result, ACT_EDIT);
                        break;
                }
                break;
        }
    }

    class timeThread extends Thread{
        int value = 0;
        boolean isRun = true;
        public void stopForever(){
            synchronized (this){
                this.isRun = false;
            }
        }
        public void run(){
            while(isRun){
                try{
                    Thread.sleep(1000);

                }catch (Exception e){}
                value++;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        timeOut.setText(String.format("%02d : %02d : %02d",
                                value/3600, value/60%60, value%60));
                        myResultTime = value;
                    }
                });
            }
        }
    }
}


