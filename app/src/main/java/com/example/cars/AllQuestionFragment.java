package com.example.cars;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;

import java.util.ArrayList;

public class AllQuestionFragment extends Fragment {
    private  View rootView;
    private QuizPractiseAdapter quizAdapter;
    RecyclerView recyclerView_all_question;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_all_question, container, false);
        recyclerView_all_question = rootView.findViewById(R.id.recycler_view_all_questions);
        API api = new API(rootView.getContext());

        ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
        quizAdapter = new QuizPractiseAdapter(rootView.getContext(), quizzes);
        recyclerView_all_question.setAdapter(quizAdapter);
        recyclerView_all_question.setLayoutManager(new LinearLayoutManager((rootView.getContext())));


        api.readAllQuestions(new APIResponseListener<ArrayList<Quiz>>() {
            @Override
            public void onError(VolleyError error) {
                Toast.makeText(rootView.getContext(), error.toString(), Toast.LENGTH_SHORT).show();;
            }

            @Override
            public void onResponse(ArrayList<Quiz> response) {
                quizAdapter = new QuizPractiseAdapter(rootView.getContext(), response);
                recyclerView_all_question.setAdapter(quizAdapter);
            }
        });

        return rootView;
    }
}
