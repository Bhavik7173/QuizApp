package com.gi.application2.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gi.application2.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RuleActivity extends AppCompatActivity {
    FloatingActionButton fab, addlogout;
    TextView logout;
    Boolean isAllFabsVisible;

    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule);
        next = findViewById(R.id.next);
        addlogout = findViewById(R.id.logout_btn);
        fab = findViewById(R.id.fab);
        logout = findViewById(R.id.logout);

        addlogout.setVisibility(View.GONE);
        logout.setVisibility(View.GONE);

        isAllFabsVisible = false;
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RuleActivity.this, QuizActivity.class);
                startActivity(i);
                finish();
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isAllFabsVisible) {
                    addlogout.show();
                    logout.setVisibility(View.VISIBLE);
                    isAllFabsVisible = true;
                } else {
                    addlogout.hide();
                    logout.setVisibility(View.GONE);
                    isAllFabsVisible = false;
                }
            }
        });
        addlogout.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SharedPreferences sharedPreferences = getSharedPreferences("Login File", MODE_PRIVATE);
                        SharedPreferences.Editor edit = sharedPreferences.edit();
                        edit.putString("status", "logout");
                        edit.commit();
                        Toast.makeText(RuleActivity.this, "Successfully Logout.", Toast.LENGTH_SHORT).show();
                        finishAffinity();
                    }
                });

    }

}