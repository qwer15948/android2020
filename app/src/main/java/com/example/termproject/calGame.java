package com.example.termproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class calGame extends AppCompatActivity {
    TextView quiz;
    TextView time;
    EditText answer;
    Button enter;

    Random random = new Random();

    int mul = random.nextInt(2);
    int num1, num2, result;

    long myTime;
    String  myResultTime; // 성공 시 저장될 시간

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quiz = findViewById(R.id.textView);
        answer = findViewById(R.id.editText2);
        enter = findViewById(R.id.button);
        time = findViewById(R.id.textView3);

        myTime = SystemClock.elapsedRealtime();
        myTimer.sendEmptyMessage(0);

        if(mul == 0) {
            num1 = random.nextInt(90) + 10; // 두 자리수 0~89+10 = 10~99
            num2 = random.nextInt(90) + 10;
            result = num1 + num2;
            quiz.setText(num1 + " + " + num2);
        } else {
            num1 = random.nextInt(90) + 10;
            num2 = random.nextInt(9) + 1;
            result = num1 * num2;
            quiz.setText(num1 + " x " + num2);
        }
    }

    public void ButtonClicked(View v) {
        if(Integer.parseInt(answer.getText().toString()) == result) {
            Toast.makeText(this, "Good!", Toast.LENGTH_LONG).show();
            myResultTime = getTime(); // 성공 시 시간 저장되게
        } else {
            Toast.makeText(this, "Try again", Toast.LENGTH_LONG).show();
        }
    }

    @SuppressLint("HandlerLeak")
    Handler myTimer = new Handler(){
        public void handleMessage(Message msg){
            time.setText(getTime());
            myTimer.sendEmptyMessage(0);
        }
    };

    @SuppressLint("DefaultLocale")
    String getTime(){
        long now = SystemClock.elapsedRealtime();
        long outTime = now - myTime;
        return String.format("%02d : %02d : %02d", outTime/1000/3600,
                outTime/1000/60, (outTime/1000)%60);
    }

}
