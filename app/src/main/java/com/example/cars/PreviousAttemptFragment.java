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
import java.util.List;

public class PreviousAttemptFragment extends Fragment {
    private View rootView;
    private RecyclerView  recyclerView_all_attempts;
    private  AttemptAdapter attemptAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_previous_attempt, container, false);
        recyclerView_all_attempts = rootView.findViewById(R.id.recycler_view_all_attempts);
        Integer userId =2;
        API  api = new API (rootView.getContext());

        ArrayList<Attempt> attempts = new ArrayList<>();
        attemptAdapter = new AttemptAdapter(rootView.getContext(), attempts);
        recyclerView_all_attempts.setAdapter(attemptAdapter);
        recyclerView_all_attempts.setLayoutManager(new LinearLayoutManager((rootView.getContext())));

        String Guid = "7ff31d0d-cfe9-461b-aca8-6bff24ae5e79";

        api.GetAttmpts(userId, new APIResponseListener<ArrayList<Attempt>>() {
            @Override
            public void onError(VolleyError error) {
                Toast.makeText(rootView.getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(ArrayList<Attempt> response) {
                attemptAdapter = new AttemptAdapter(rootView.getContext(), response);
                recyclerView_all_attempts.setAdapter(attemptAdapter);
            }
        });

        return  rootView;
    }
}
