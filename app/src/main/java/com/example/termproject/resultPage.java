package com.example.termproject;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class resultPage extends AppCompatActivity {
    double sojuCount;
    double beerCount;
    int time;
    double ratio;
    Button buttonOfCheck;
    TextView sojuResult;
    TextView beerResult;
    String date;

    DBHelper helper;
    SQLiteDatabase db;

    @SuppressLint("DefaultLocale")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        helper = new DBHelper(resultPage.this, "alcohol.db", null, 1);
        db = helper.getWritableDatabase();
        helper.onCreate(db);

        sojuResult = findViewById(R.id.soju_record);
        beerResult = findViewById(R.id.beer_record);
        buttonOfCheck = findViewById(R.id.check);
        Intent intent = getIntent();
        date = intent.getStringExtra("date");
        sojuCount = intent.getDoubleExtra("numberOfSoju", 0);
        beerCount = intent.getDoubleExtra("numberOfBeer", 0);
        time = intent.getIntExtra("timeText", 0);
        if(time/3600<1) ratio = 1;
        else ratio = time/3600;

        ContentValues values = new ContentValues();
        values.put("date", date);
        values.put("beer", beerCount);
        values.put("time", time);
        values.put("soju", sojuCount);
        db.insert("alcohol", null, values);

        sojuResult.setText(String.format("소주 %.2f j/h", sojuCount/ratio));
        beerResult.setText(String.format("맥주 %.2f j/h", beerCount/ratio));

        buttonOfCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), calendarPage.class);
                startActivity(intent);
            }
        });
    }
}
