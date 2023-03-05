package com.gi.application2.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.gi.application2.R;

public class MainActivity extends AppCompatActivity implements Runnable {

    SharedPreferences sharedPreferences;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler();
        handler.postDelayed(this, 3000);


    }

    @Override
    public void run() {
        sharedPreferences = getSharedPreferences("Login File", MODE_PRIVATE);
        String a = sharedPreferences.getString("status","no data");
        if (a.equals("login")) {
            Intent intent = new Intent(this, RuleActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            Log.d("mylog", "MainActivity");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}