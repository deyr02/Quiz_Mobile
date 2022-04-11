package com.example.cars;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AttemptAdapter extends RecyclerView.Adapter<AttemptAdapter.MyViewHolder>  {

    private  Context context;
    private  List<Attempt> attemptList;


    public  AttemptAdapter(Context context, ArrayList<Attempt> attemptList){
        this.context = context;
        this.attemptList = attemptList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.attempt_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        initializeComponents(holder, holder.getAdapterPosition());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Bundle bundle = new Bundle();
                bundle.putInt("attemptId", attemptList.get(holder.getAdapterPosition()).getID());
                bundle.putInt("isSubmitted", attemptList.get(holder.getAdapterPosition()).getIsSubmitted());
                bundle.putInt("totalQuestions", attemptList.get(holder.getAdapterPosition()).getTotalQuestions());
                bundle.putInt("totalCorrectAnswer", attemptList.get(holder.getAdapterPosition()).getTotalCorrectAnswer());
                bundle.putString("startedAt", attemptList.get(holder.getAdapterPosition()).getStartedAt());
                bundle.putString("finishedAt", attemptList.get(holder.getAdapterPosition()).getFinishedAT());
                bundle.putInt("userId", attemptList.get(holder.getAdapterPosition()).getUserId());
                StartQuizFragment startQuizFragment = new StartQuizFragment();
                startQuizFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        startQuizFragment).commit();
            }
        });




    }

    private void initializeComponents(MyViewHolder holder, int adapterPosition) {
        holder.txt_heading.setText("Attempt - " + (adapterPosition+1) );

        if (attemptList.get(adapterPosition).getIsSubmitted() ==1) {
            holder.img_view_quiz_status_icon.setImageResource(R.drawable.ic_submit);
            String time = DurationCalculator.findDifference(attemptList.get(adapterPosition).getStartedAt(), attemptList.get(adapterPosition).getFinishedAT());
            holder.txt_completion.setText("Time Taken: " + time );
            holder.txt_view_quiz_status_text.setText("Completed");

            holder.txt_score.setText(
                    "Score:" + attemptList.get(adapterPosition).getTotalCorrectAnswer() +"/" + attemptList.get(adapterPosition).getTotalQuestions()
            );
        } else {
            holder.img_view_quiz_status_icon.setImageResource(R.drawable.ic_watiing);
            holder.txt_view_quiz_status_text.setText("Pending...");
            holder.txt_score.setText(
                    "Score: 0/" + attemptList.get(adapterPosition).getTotalQuestions()
            );
            holder.txt_completion.setText("Started At: " + attemptList.get(adapterPosition).getStartedAt());

        }

    }

    @Override
    public int getItemCount() {
        return attemptList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt_heading, txt_view_quiz_status_text, txt_completion, txt_score;

        ImageView img_view_quiz_status_icon ;
        ConstraintLayout attempt_row;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_heading = itemView.findViewById(R.id.txt_heading);
            txt_view_quiz_status_text = itemView.findViewById((R.id.txt_view_quiz_status_text));
            txt_completion = itemView.findViewById((R.id.txt_completion));
            txt_score = itemView.findViewById(R.id.txt_score);

            img_view_quiz_status_icon = itemView.findViewById(R.id.img_view_quiz_status_icon);
            attempt_row = itemView.findViewById(R.id.attempt_row);
        }
    }
}
