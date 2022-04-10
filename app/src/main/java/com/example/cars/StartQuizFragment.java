package com.example.cars;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;

import java.util.ArrayList;

public class StartQuizFragment extends Fragment {
    private  View rootView;
    private QuizTestAdapter quizTestAdapter;
    RecyclerView recyclerView_test_quiz;
    Button btn_submit;
    ArrayList<AttemptLine> attemptLines;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_start_quiz, container, false);
        recyclerView_test_quiz = rootView.findViewById(R.id.recyclerView_test_quiz);
        Integer attemptID =140;
        API api= new API(rootView.getContext());
        attemptLines = new ArrayList<AttemptLine>();
        quizTestAdapter = new QuizTestAdapter(rootView.getContext(), attemptLines);
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
                quizTestAdapter = new QuizTestAdapter(rootView.getContext(), response);
                recyclerView_test_quiz.setAdapter(quizTestAdapter);
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer correctAnser = calculateCorrectAnswer(attemptLines);
                Log.e("CorrectAnswer", correctAnser+"");
            }
        });

        return rootView;
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
