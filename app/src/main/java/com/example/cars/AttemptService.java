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
import java.util.ArrayList;
import java.util.List;

public class AttemptService {

    private Context context;
    private Integer userId;
    public AttemptService(Context context, Integer userId) {
        this.context = context;
        this.userId = userId;
    }
/*
    public void  GetAttmpts(APIResponseListener apiResponseListener){
        String URL = API.ATTEMPT+"readbyuser.php?userid=" +userId;
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

 */
}
