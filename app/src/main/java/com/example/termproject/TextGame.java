package com.example.termproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class TextGame extends AppCompatActivity {

    TextView tv1;
    TextView tv2;
    EditText mEdit;
    Button enter;

    Random random = new Random();
    int rand = random.nextInt(10); // 문자열 수만큼 범위 조절

    String arr[] = {
            "No one can earn a million dollars honestly.",
            "Early morning hath gold in its mouth",
            "The truth is rarely pure and never simple",
            "A profession is the backbone of life",
            "A day without laughter is a day wasted.",
            "People find life entirely too time consuming",
            "The future is much like the present, only longer",
            "Fashion is made to become unfashionable",
            "Love looks not with the eyes, but with the mind",
            "One must desire something to be alive"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textgame);

        mEdit = (EditText)findViewById(R.id.editText);
        tv1 = (TextView)findViewById(R.id.textView);
        tv1.setText(arr[rand]);
        tv2 = (TextView)findViewById(R.id.textView2);

        mEdit.addTextChangedListener(mWatcher);
    }


    TextWatcher mWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    public void onEnterButtonClicked(View v) {
        if(mEdit.getText().toString().equalsIgnoreCase(tv1.getText().toString())) {
            // 성공했을 때 여기에 코드 넣으면 됨
            tv2.setText("O");
        } else {
            tv2.setText("Try again");
        }
    }
}
