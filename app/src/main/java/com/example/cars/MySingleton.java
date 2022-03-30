package com.example.cars;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton {
    private  static MySingleton instance;
    private RequestQueue queue;
    private  static Context ctx;

    private MySingleton(Context context){
        this.ctx = context;
    }

    public static synchronized MySingleton getInstances(Context context){
        if (instance == null){
            instance = new MySingleton(context);
        }
        return instance;
    }

    public  RequestQueue getRequestedQueue(){
        if(queue == null){
            queue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return queue;
    }

    public <T> void addToRequestQueue(Request<T> req){
        getRequestedQueue().add(req);
    }



}
