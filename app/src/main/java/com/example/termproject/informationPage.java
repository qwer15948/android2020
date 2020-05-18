package com.example.termproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class informationPage extends AppCompatActivity {
    TextView soju;
    TextView beer;
    TextView ratio;
    Button back;

    DBHelper helper;
    SQLiteDatabase db;

    int info_soju;
    int info_beer;
    int info_time;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        soju = findViewById(R.id.sojuCount);
        beer = findViewById(R.id.beerCount);
        ratio = findViewById(R.id.janPerHour);
        back = findViewById(R.id.home_button);

        Intent intent = getIntent();
        String date = intent.getStringExtra("date");
/*
        helper = new DBHelper(informationPage.this, "alcohol.db",
                null, 1);
        db = helper.getReadableDatabase();
        helper.onCreate(db);
 */
        Cursor c = db.query("alcohol", null, null,
                null, null, null, null);
        try{
            if(c != null){
                while(c.getString(c.getColumnIndex("date")) == date){
                    if(c.getString(c.getColumnIndex("date")) == date){
                        String info_date = c.getString(c.getColumnIndex("date"));
                        info_soju = c.getInt(c.getColumnIndex("soju"));
                        info_beer = c.getInt(c.getColumnIndex("beer"));
                        info_time = c.getInt(c.getColumnIndex("time"));
                    }
                }
            }
        }finally {
            if(c != null){
                c.close();
            }
        }

        soju.setText(Integer.toString(info_soju));
        beer.setText(Integer.toString(info_beer));
        ratio.setText(String.format("%02d : %02d : %02d",
                info_time/3600,
                info_time/60%60,
                info_time/60));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(informationPage.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}