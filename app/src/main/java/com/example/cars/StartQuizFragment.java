package com.example.cars;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;

public class StartQuizFragment extends Fragment {
    private  View rootView;
    private QuizTestAdapter quizTestAdapter;
    RecyclerView recyclerView_test_quiz;
    Button btn_submit;
    ArrayList<AttemptLine> attemptLines;



    ConstraintLayout starting_block, score_block;
    TextView lbl_startedAt, txt_date, txt_time, lbl_score, txt_score, txt_completed;
    ScrollView scrollView;

    private Integer attemptID;
    private Integer IsSubmitted;
    private Integer totalQuestion;
    private Integer totalCorrectAnswer;
    private String startedAt ;
    private String finishedAt ;
    private Integer userId ;
    private Attempt attempt ;
    @SuppressLint("ResourceAsColor")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_start_quiz, container, false);
        recyclerView_test_quiz = rootView.findViewById(R.id.recyclerView_test_quiz);

        ///Detials////////////////////////////////////////////////////////

        starting_block = rootView.findViewById(R.id.starting_block);
        score_block = rootView.findViewById(R.id.score_block);
        lbl_startedAt = rootView.findViewById(R.id.lbl_startedAt);
        lbl_score = rootView.findViewById(R.id.lbl_Score);
        txt_date = rootView.findViewById(R.id.txt_date);
        txt_time = rootView.findViewById(R.id.txt_time);
        txt_score = rootView.findViewById(R.id.txt_score);
        txt_completed = rootView.findViewById(R.id.txt_quiz_completed);
        scrollView = rootView.findViewById(R.id.scrollView2);

        attemptID = getArguments().getInt("attemptId");
        IsSubmitted= getArguments().getInt("isSubmitted");
        totalQuestion = getArguments().getInt("totalQuestions");
        totalCorrectAnswer = getArguments().getInt("totalCorrectAnswer");
        startedAt = getArguments().getString("startedAt");
        finishedAt = getArguments().getString("finishedAt");
        userId = getArguments().getInt("userId");
        attempt = new Attempt(attemptID, IsSubmitted, totalQuestion, totalCorrectAnswer, startedAt, finishedAt, userId);



        API api= new API(rootView.getContext());
        attemptLines = new ArrayList<AttemptLine>();
        quizTestAdapter = new QuizTestAdapter(rootView.getContext(), attemptLines, attempt);
        recyclerView_test_quiz.setAdapter(quizTestAdapter);
        btn_submit = rootView.findViewById(R.id.btn_submit);

        recyclerView_test_quiz.setLayoutManager(new LinearLayoutManager((rootView.getContext())));

        api.readAttemptLines(attemptID, new APIResponseListener<ArrayList<AttemptLine>>() {
            @Override
            public void onError(VolleyError error) {
                Toast.makeText(rootView.getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(ArrayList<AttemptLine> response) {
                attemptLines = response;
                quizTestAdapter = new QuizTestAdapter(rootView.getContext(), attemptLines, attempt);
                recyclerView_test_quiz.setAdapter(quizTestAdapter);
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalCorrectAnswer = calculateCorrectAnswer(attemptLines);
                SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(System.currentTimeMillis());
                finishedAt = formatter.format(date);
                IsSubmitted =1;
                api.submitAttempt(attemptID, IsSubmitted, totalCorrectAnswer, finishedAt, userId);
                attempt = new Attempt(attemptID, IsSubmitted, totalQuestion, totalCorrectAnswer, startedAt, finishedAt, userId);
                bindData();
                quizTestAdapter = new QuizTestAdapter(rootView.getContext(), attemptLines, attempt);
                recyclerView_test_quiz.setAdapter(quizTestAdapter);
                scrollView.scrollTo(0, 0);

            }
        });

        //binding
        bindData();




        return rootView;
    }

    @SuppressLint("ResourceAsColor")
    private  void  bindData(){
        if (IsSubmitted ==0){
            if(startedAt == ""){
                starting_block.setVisibility(View.GONE);
            }
            else {
                String[] Date  = startedAt.split(" ");
                txt_date.setText("Date: "+ Date[0]);
                txt_time.setText("Time: " +Date[1]);
            }

            lbl_score.setText("Number of Questions: ");
            txt_score.setText(""+totalQuestion);



        }
        else {
            btn_submit.setVisibility(View.GONE);
            starting_block.setBackgroundColor(getResources().getColor(R.color.yellow));
            score_block.setBackgroundColor(getResources().getColor(R.color.yellow));
            lbl_startedAt.setText("Time Taken: ");

            String diff = DurationCalculator.findDifference(startedAt, finishedAt);
            txt_date.setText(diff);
            txt_time.setVisibility(View.GONE);
            lbl_score.setText("Your Score:");
            txt_score.setText(totalCorrectAnswer +"/"+ totalQuestion);
            txt_completed.setText("Completed");
        }
    }


    private int calculateCorrectAnswer(ArrayList<AttemptLine> lines){
        Integer correctAnswer=0;
        for (AttemptLine line:lines)
        {
            if(line.getQuiz().getAnswers().equals(line.getUserSelection())){
                correctAnswer = correctAnswer +1;
            }
        }
        return  correctAnswer;
    }
}
