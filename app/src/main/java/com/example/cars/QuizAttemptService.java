package com.example.cars;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QuizAttemptService {
    private Context context;
    private  Integer attemptid;

    public QuizAttemptService(Context context, Integer attemptId){

        this.context = context;
        this.attemptid = attemptId;
    }
/*
    public void  read (APIResponseListener apiResponseListener){
        String URL = API.ATTEMPT_LINE+"read.php?attemptid="+ this.attemptid;
        JsonArrayRequest
                jsonArrayRequest
                = new JsonArrayRequest(
                Request.Method.PUT,
                URL,
                null,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<ArrayList<AttemptLine>>(){}.getType();
                        ArrayList<AttemptLine> attemptLines = gson.fromJson(response.toString(),type);
                        apiResponseListener.onResponse(attemptLines);
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

    public  void update(AttemptLine line){
        String URL = API.ATTEMPT_LINE+"update.php";

        JSONObject  jsonObject = new JSONObject();
        try{
            jsonObject.put("attemptId", line.getAttemptId());
            jsonObject.put("quizId", line.getQuizId());
            jsonObject.put("isAnswered", line.getIsAnswered());
            jsonObject.put("userSelection", line.getUserSelection());
        }catch (JSONException e){
            Log.e("Json Convert", e.getMessage());
        }
        JsonObjectRequest
                jsonObjReq
                = new JsonObjectRequest(
                Request.Method.POST,
                URL,
                jsonObject,
                new Response.Listener() {

                    @Override
                    public void onResponse(Object response) {
                        Log.d("AttemptLine Update", response.toString());
                    }

                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.e("AttemptLine Update", error.getMessage());

                    }
                });

        MySingleton.getInstances(context).addToRequestQueue(jsonObjReq);

    }

 */
}
