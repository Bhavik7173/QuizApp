package com.gi.application2.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gi.application2.R;
import com.gi.application2.adapter.SummaryAdapter;
import com.gi.application2.model.QuestionPojo;
import com.gi.application2.staticfunction.AppSetting;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.ArrayList;

public class ViewSummary extends AppCompatActivity {

    RecyclerView summaryRView;
    String title;
    TextView heading;
    LinearLayout pageLayout;
    Button change = null;
    boolean flag = true;
    ArrayList<QuestionPojo> questionPojos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_summary);
        questionPojos = getIntent().getParcelableArrayListExtra("list");
        getSupportActionBar().setTitle("Summary");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);

        title = getIntent().getStringExtra("title");
        //pageLayout = findViewById(R.id.pageLayout);
        summaryRView = findViewById(R.id.summaryRView);
        heading = findViewById(R.id.heading);
        summaryRView.setLayoutManager(new LinearLayoutManager(this));
        heading.setText(title);
        setSummary(0);
        System.out.println("Size:"+ questionPojos.size());
        int numpages = questionPojos.size() / AppSetting.numbers;
        int rem = questionPojos.size() % AppSetting.numbers;
        System.out.println("Size:"+ numpages);
        System.out.println("Size:"+ rem);

        if (rem != 0)
            numpages++;

        for (int i = 0; i < numpages; i++) {
            String no = String.valueOf(i + 1);
            Button buttonDes = new Button(ViewSummary.this);
            buttonDes.setText("Page " + no);
            final int temp = i;

            buttonDes.setTextColor(Color.parseColor("#FFFFFF"));
            buttonDes.setBackgroundColor(Color.parseColor("#BAB8B7"));

            if (flag) {
                change = buttonDes;
                buttonDes.setBackgroundColor(Color.parseColor("#F44336"));
                flag = false;
            }

            buttonDes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (change != null) {
                        change.setBackgroundColor(Color.parseColor("#BAB8B7"));
                    }
                    change = buttonDes;
                    buttonDes.setBackgroundColor(Color.parseColor("#F44336"));
                    setSummary(temp * 5);
                }
            });

        }

    }

    public void setSummary(int start) {
        SummaryAdapter adapter = new SummaryAdapter(this, start,questionPojos);
        summaryRView.setAdapter(adapter);
    }

     @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}