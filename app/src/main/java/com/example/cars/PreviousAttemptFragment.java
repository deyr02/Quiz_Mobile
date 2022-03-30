package com.example.cars;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.VolleyError;

import java.util.List;

public class PreviousAttemptFragment extends Fragment {
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_previous_attempt, container, false);

        AttemptService  attemptService = new AttemptService (rootView.getContext());

        String Guid = "7ff31d0d-cfe9-461b-aca8-6bff24ae5e79";

        attemptService.GetAttmpts(Guid, new VolleyResponseListener() {
            @Override
            public void onError(VolleyError error) {
                Toast.makeText(rootView.getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(List<Attempt> response) {
                Toast.makeText(rootView.getContext(),  "" + response.get(0).getAppUserId(), Toast.LENGTH_SHORT).show();
            }
        });



        return  rootView;
    }
}
