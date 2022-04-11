package com.example.cars;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class API {
    private   final String URL = "http://10.59.164.16:8011/cars_api/api/";
    private   final String ACCOUNT = URL+"account/";
    private   final String ATTEMPT = URL+"attempt/";
    private   final String ATTEMPT_LINE = URL+"attempt_line/";
    private   final String QUIZ = URL+"/quiz/";
    private   final String IMAGES = URL;

    private  Context context;
    public  API(Context context){
        this.context = context;
    }

    public String getImageURL() {
        return IMAGES;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////
    //account

    public  void login(String email, String password, APIResponseListener apiResponseListener){
        String URL = ACCOUNT+"login.php";

        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("email", email);
            jsonObject.put("password", password);
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
                        Gson gson = new Gson();
                        Type type = new TypeToken<AccountResponseDTO>(){}.getType();
                        AccountResponseDTO accountResponseDTO = gson.fromJson(response.toString(),type);
                        apiResponseListener.onResponse(accountResponseDTO);
                    }

                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        apiResponseListener.onError(error);

                    }
                });

        MySingleton.getInstances(context).addToRequestQueue(jsonObjReq);
    }


    public  void register(User user, APIResponseListener apiResponseListener){
        String URL = ACCOUNT+"register.php";

        JSONObject jsonObject = new JSONObject();
        try{

            jsonObject.put("firstName", user.getFirstName());
            jsonObject.put("lastName", user.getLastName());
            jsonObject.put("dob", user.getDob());
            jsonObject.put("phoneNumber", user.getPhoneNumber());
            jsonObject.put("email", user.getEmail());
            jsonObject.put("password", user.getPassword());
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
                        Gson gson = new Gson();
                        Type type = new TypeToken<AccountResponseDTO>(){}.getType();
                        AccountResponseDTO accountResponseDTO = gson.fromJson(response.toString(),type);
                        apiResponseListener.onResponse(accountResponseDTO);
                    }

                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        apiResponseListener.onError(error);

                    }
                });

        MySingleton.getInstances(context).addToRequestQueue(jsonObjReq);
    }




    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////
    //Attempts


    //Update the given attempt line.
    public  void createAttempt(Integer UserID, APIResponseListener apiResponseListener){
        String URL = ATTEMPT+"create.php";
        Integer isSubmitted =0;
        Integer totalQuestions =3;
        Integer totalCorrectAnswer =0;
        String startedAt = this.currentTime();
        String finishedAt ="";
        Integer userId = UserID;

        JSONObject jsonObject = new JSONObject();
        try{

            jsonObject.put("isSubmitted", isSubmitted);
            jsonObject.put("totalQuestions", totalQuestions);
            jsonObject.put("totalCorrectAnswer", totalCorrectAnswer);
            jsonObject.put("startedAt", startedAt);
            jsonObject.put("finishedAt", finishedAt);
            jsonObject.put("userId", userId);
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
                        Gson gson = new Gson();
                        Type type = new TypeToken<Attempt>(){}.getType();
                        Attempt attempt = gson.fromJson(response.toString(),type);
                        apiResponseListener.onResponse(attempt);
                    }

                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        apiResponseListener.onError(error);

                    }
                });

        MySingleton.getInstances(context).addToRequestQueue(jsonObjReq);

    }



    public void  GetAttmpts (Integer userId, APIResponseListener apiResponseListener){
        String URL = ATTEMPT+"readbyuser.php?userid=" +userId;
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
                        Type type = new TypeToken<ArrayList<Attempt>>(){}.getType();
                        ArrayList<Attempt> attempts = gson.fromJson(response.toString(),type);
                        apiResponseListener.onResponse(attempts);
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


    //Update the given attempt line.
    public  void submitAttempt(Integer attemptId, Integer isSubmitted, Integer totalCorrectAnswer, String finishedAt, Integer UserID){
        String URL = ATTEMPT+"update.php";

        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("id", attemptId);
            jsonObject.put("isSubmitted", isSubmitted);
            jsonObject.put("totalCorrectAnswer", totalCorrectAnswer);
            jsonObject.put("finishedAt", finishedAt);
            jsonObject.put("userId", UserID);
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











    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////
    //attemptLine

    //Return the list of attemptLine including quizzes and its options
    public void  readAttemptLines (Integer attemptId, APIResponseListener apiResponseListener){
        String URL = ATTEMPT_LINE+"read.php?attemptid="+ attemptId;
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

    //Update the given attempt line.
    public  void updateAttemptLine(AttemptLine line){
        String URL = ATTEMPT_LINE+"update.php";

        JSONObject jsonObject = new JSONObject();
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






    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////
    //Quiz

    //Read all question and its options
    public void  readAllQuestions(APIResponseListener apiResponseListener){
        String URL = QUIZ+"read.php";
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




    private String  currentTime(){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }
}
