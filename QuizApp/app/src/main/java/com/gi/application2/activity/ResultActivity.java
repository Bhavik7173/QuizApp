package com.gi.application2.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gi.application2.R;
import com.gi.application2.adapter.HistoryAdapter;
import com.gi.application2.model.QuestionPojo;
import com.gi.application2.model.ResultPojo;
import com.gi.application2.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    TextView result, setTitle, nonAttemptText, attemptText;
    String count, title, totalQuestion, nonAttempt, Attempt;
    Button viewResult, saveBtn,addBtn;
    RecyclerView recycleView;
    ArrayList<QuestionPojo> questionPojos;
    ArrayList<ResultPojo> resultPojos;
    HistoryAdapter adapter ;
    User user = User.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Result</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        saveBtn = findViewById(R.id.idBtnSave);
        addBtn = findViewById(R.id.idBtnAdd);
        recycleView = findViewById(R.id.recycleView);

        questionPojos = getIntent().getParcelableArrayListExtra("list");
        result = findViewById(R.id.result);
        nonAttemptText = findViewById(R.id.nonAttemptText);
        attemptText = findViewById(R.id.attemptText);
        setTitle = findViewById(R.id.setTitle);
        viewResult = findViewById(R.id.viewResult);
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

//        loadData();
//        buildRecyclerView();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultPojos.add(new ResultPojo(setTitle.getText().toString(),result.getText().toString(), nonAttemptText.getText().toString(),attemptText.getText().toString()));
                Log.d("gilog","from rst activity "+resultPojos.size());
                adapter = new HistoryAdapter(ResultActivity.this,resultPojos);
//                adapter.notifyItemInserted(resultPojos.size());
                Intent i = new Intent(ResultActivity.this,HistoryActivity.class);
                Intent intent = new Intent(ResultActivity.this, HistoryActivity.class);
                intent.putExtra("count", String.valueOf(resultPojos.size()));
                intent.putExtra("title", title);
                intent.putExtra("totalQuestion", ""+resultPojos.size());
//                intent.putExtra("nonAttempt", String.valueOf(nonCnt));
                startActivity(i);
                finish();
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
//                resultPojos.add(new ResultPojo(setTitle.getText().toString(),result.getText().toString(), nonAttemptText.getText().toString(),attemptText.getText().toString()));
//                adapter.notifyItemInserted(resultPojos.size());

            }
        });
        viewResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, ViewSummary.class);
                intent.putExtra("title", title);
                intent.putParcelableArrayListExtra("list", questionPojos);
                startActivity(intent);
            }
        });

    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(resultPojos);
        editor.putString("result", json);
        editor.apply();
        Toast.makeText(this, "Saved Array List to Shared preferences. ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, QuizActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
    }
    private void buildRecyclerView() {
        // initializing our adapter class.
        adapter = new HistoryAdapter(ResultActivity.this, resultPojos);

        // adding layout manager to our recycler view.
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recycleView.setHasFixedSize(true);

        // setting layout manager to our recycler view.
        recycleView.setLayoutManager(manager);

        // setting adapter to our recycler view.
//        recycleView.setAdapter(adapter);
    }
    private void loadData() {
        // method to load arraylist from shared prefs
        // initializing our shared prefs with name as
        // shared preferences.
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);

        // creating a variable for gson.
        Gson gson = new Gson();

        // below line is to get to string present from our
        // shared prefs if not present setting it as null.
        String json = sharedPreferences.getString("courses", null);

        // below line is to get the type of our array list.
        Type type = new TypeToken<ArrayList<ResultPojo>>() {}.getType();

        // in below line we are getting data from gson
        // and saving it to our array list
        resultPojos = gson.fromJson(json, type);

        // checking below if the array list is empty or not
        if (resultPojos == null) {
            // if the array list is empty
            // creating a new array list.
            resultPojos = new ArrayList<>();
        }
    }
}