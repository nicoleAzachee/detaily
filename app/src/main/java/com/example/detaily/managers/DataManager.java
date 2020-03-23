package com.example.detaily.managers;

import android.app.Activity;
import android.util.Log;

import com.example.detaily.data.EnvironmentVariables;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DataManager {

    public static final String TAG = DataManager.class.getSimpleName();


//    void run() throws IOException {
//
//        OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                call.cancel();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//
//                final String myResponse = response.body().string();
//
//                MainActivity.this.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        txtString.setText(myResponse);
//                    }
//                });
//
//            }
//        });
//    }



}

