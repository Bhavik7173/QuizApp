package com.gi.application2.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gi.application2.R;
import com.gi.application2.adapter.HistoryAdapter;
import com.gi.application2.model.QuestionPojo;
import com.gi.application2.model.ResultPojo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    FloatingActionButton fab, addquiz, addlogout;
    TextView logout, addquiz1;
    ArrayList<ResultPojo> listViewModels;
    HistoryAdapter adapter;
    RecyclerView recycleView;
    ArrayList<ResultPojo> resultPojos;
    Boolean isAllFabsVisible;
    ArrayList<QuestionPojo> questionPojos;
    TextView result, setTitle, nonAttemptText, attemptText;
    String count, title, totalQuestion, nonAttempt, Attempt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        fab = findViewById(R.id.fab);
        addquiz = findViewById(R.id.quiz);
        addlogout = findViewById(R.id.logout_btn);
        logout = findViewById(R.id.logout);
        addquiz1 = findViewById(R.id.addquiz);

        addquiz.setVisibility(View.GONE);
        addlogout.setVisibility(View.GONE);
        logout.setVisibility(View.GONE);
        addquiz1.setVisibility(View.GONE);
        isAllFabsVisible = false;
        recycleView = findViewById(R.id.recycleView);

        questionPojos = getIntent().getParcelableArrayListExtra("list");
        result = findViewById(R.id.result);
        nonAttemptText = findViewById(R.id.nonAttemptText);
        attemptText = findViewById(R.id.attemptText);
        setTitle = findViewById(R.id.setTitle);
        resultPojos = new ArrayList<>();
        count = getIntent().getStringExtra("count");
        Log.d("gilog", count + "");

        title = getIntent().getStringExtra("title");
        Log.d("gilog", title + "");
        totalQuestion = getIntent().getStringExtra("totalQuestion");
        Log.d("gilog", totalQuestion + "");
        nonAttempt = getIntent().getStringExtra("nonAttempt");
        Log.d("gilog", nonAttempt + "");
        Attempt = Integer.parseInt(totalQuestion) - Integer.parseInt(nonAttempt) + "";
        Log.d("gilog", Attempt + "");

        result.setText(count + "/" + totalQuestion);
        setTitle.setText(title);
        nonAttemptText.setText(nonAttempt);
        attemptText.setText(Attempt);

        adapter = new HistoryAdapter(HistoryActivity.this, resultPojos);
        loadData();
        buildRecyclerView();

//
        resultPojos.add(new ResultPojo(title,count, nonAttempt,Attempt));
        adapter.notifyItemInserted(resultPojos.size());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isAllFabsVisible) {


                    addquiz.show();
                    addlogout.show();
                    logout.setVisibility(View.VISIBLE);
                    addquiz1.setVisibility(View.VISIBLE);

                    isAllFabsVisible = true;
                } else {


                    addquiz.hide();
                    addlogout.hide();
                    logout.setVisibility(View.GONE);
                    addquiz1.setVisibility(View.GONE);


                    isAllFabsVisible = false;
                }
            }
        });
        addlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("Login File", MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString("status", "logout");
                edit.commit();
                Toast.makeText(HistoryActivity.this, "Successfully Logout.", Toast.LENGTH_SHORT).show();
                finishAffinity();
            }
        });
        addquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HistoryActivity.this, RuleActivity.class);
                startActivity(intent);
            }
        });

    }

    private void buildRecyclerView() {
//        adapter = new HistoryAdapter(HistoryActivity.this, resultPojos);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(manager);

        recycleView.setAdapter(adapter);
    }

    private void loadData() {

        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("courses", null);
        Type type = new TypeToken<ArrayList<ResultPojo>>() {
        }.getType();
        resultPojos = gson.fromJson(json, type);
        if (resultPojos == null) {
            resultPojos = new ArrayList<>();
        }
    }
}