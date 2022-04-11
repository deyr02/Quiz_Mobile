package com.example.cars;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.android.volley.VolleyError;
import com.google.android.material.navigation.NavigationView;

public class HomeFragment extends Fragment {
    private  Integer userId;
    private  View rootview;
    private Button btn_start_quiz;
    private  API api;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_home, container, false);
        userId = getArguments().getInt("userId");
        api = new API(rootview.getContext());
        btn_start_quiz = rootview.findViewById(R.id.btn_start_quiz);



        btn_start_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                api.createAttempt(userId, new APIResponseListener<Attempt>() {
                    @Override
                    public void onError(VolleyError error) {
                        Log.e("CreateAttempt", error.getMessage());
                    }

                    @Override
                    public void onResponse(Attempt response) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("attemptId", response.getID());
                        bundle.putInt("isSubmitted", response.getIsSubmitted());
                        bundle.putInt("totalQuestions", response.getTotalQuestions());
                        bundle.putInt("totalCorrectAnswer", response.getTotalCorrectAnswer());
                        bundle.putString("startedAt", response.getStartedAt());
                        bundle.putString("finishedAt", response.getFinishedAT());
                        bundle.putInt("userId", response.getUserId());
                        StartQuizFragment startQuizFragment = new StartQuizFragment();
                        startQuizFragment.setArguments(bundle);
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                       fragmentManager.beginTransaction().replace(R.id.fragment_container,
                                startQuizFragment).commit();

                    }
                });
            }
        });
        return rootview;
    }
}
