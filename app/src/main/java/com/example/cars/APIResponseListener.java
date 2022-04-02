package com.example.cars;

import com.android.volley.VolleyError;

interface APIResponseListener<T> {

    void onError(VolleyError error);
    void onResponse( T response);
}
