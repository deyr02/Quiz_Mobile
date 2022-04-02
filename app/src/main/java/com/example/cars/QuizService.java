package com.example.cars;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class QuizService {
    private Context context;

    public QuizService(Context context){
        this.context = context;
    }

    public void  read(APIResponseListener apiResponseListener){
        String URL = API.QUIZ+"read.php";
        JsonArrayRequest
                jsonArrayRequest
                = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<ArrayList<Quiz>>(){}.getType();
                        ArrayList<Quiz> quizzes = gson.fromJson(response.toString(),type);
                        apiResponseListener.onResponse(quizzes);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        apiResponseListener.onError(error);
                    }
                });
        MySingleton.getInstances(context).addToRequestQueue(jsonArrayRequest);
    }
}
