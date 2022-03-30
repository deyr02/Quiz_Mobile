package com.example.cars;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.List;

interface VolleyResponseListener{
    void onError(VolleyError error);
    void onResponse(List<Attempt> response);
}

public class AttemptService {

    private Context context;
    private  final String URL = "http://10.59.164.16:8064/api/attempt/7ff31d0d-cfe9-461b-aca8-6bff24ae5e79";

    public AttemptService(Context context) {
        this.context = context;
    }

    public void  GetAttmpts(String Guid, VolleyResponseListener volleyResponseListener){
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
                        Type type = new TypeToken<List<Attempt>>(){}.getType();
                        List<Attempt> attempts = gson.fromJson(response.toString(),type);

                        volleyResponseListener.onResponse(attempts);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        volleyResponseListener.onError(error);
                    }
                });
        //  requestQueue.add(jsonArrayRequest);

        MySingleton.getInstances(context).addToRequestQueue(jsonArrayRequest);
    }
}
