package com.gi.application2.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gi.application2.R;
import com.gi.application2.activity.HistoryActivity;
import com.gi.application2.model.QuestionPojo;
import com.gi.application2.model.ResultPojo;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder> {
    Context context;
    ArrayList<ResultPojo> resultArrayList = new ArrayList<>();

    public HistoryAdapter(Context context, ArrayList<ResultPojo> listViewModels) {
        this.resultArrayList = listViewModels;
        this.context = context;
//        Log.d("gilog",resultArrayList.size()+"");
    }

    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_history_design, parent, false);
        HistoryAdapter.HistoryHolder holder = new HistoryAdapter.HistoryHolder(view);
        return new HistoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder holder, int position) {
        ResultPojo modal = resultArrayList.get(position);
        holder.result.setText(modal.getResult_score());
        holder.setTitle.setText(modal.getResult_id());
        holder.attempt.setText(modal.getAttempt());
        holder.nonattempt.setText(modal.getNonattempt());
    }

    @Override
    public int getItemCount() {
        return resultArrayList.size();
    }

    public class HistoryHolder extends RecyclerView.ViewHolder {
        TextView result,attempt,nonattempt,setTitle;


        public HistoryHolder(View itemView) {
            super(itemView);

            result = itemView.findViewById(R.id.result);
            setTitle = itemView.findViewById(R.id.setTitle);
            attempt = itemView.findViewById(R.id.attemptText);
            nonattempt = itemView.findViewById(R.id.nonAttemptText);


        }
    }
}
