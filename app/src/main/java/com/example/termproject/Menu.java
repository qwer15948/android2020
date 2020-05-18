package com.example.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class Menu extends AppCompatActivity {
    public Button test;
    public Button minigame;
    public Button calendar;

    private TextView tv_profile;
    private ImageView iv_profile;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_success);

        test = findViewById(R.id.test_button);
        minigame = findViewById(R.id.game_button);
        calendar = findViewById(R.id.cal_button);
        tv_profile = findViewById(R.id.tv_profile);
        iv_profile = findViewById(R.id.iv_profile);

        Intent intent = getIntent();
        String nickName = intent.getStringExtra("nickName");
        String photoUrl = intent.getStringExtra("photoUrl");

        tv_profile.setText(nickName);
        Glide.with(this).load(photoUrl).into(iv_profile);

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent testIntent = new Intent(getApplicationContext(), alcoholTest.class);
                testIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(testIntent);

            }
        });
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calIntent = new Intent(getApplicationContext(), calendarPage.class);
                calIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(calIntent);
            }
        });
    }
}
