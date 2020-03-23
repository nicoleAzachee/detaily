package com.example.detaily.managers;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.detaily.DetailyApplication;
import com.example.detaily.data.Songs;
import com.google.gson.Gson;

public class SharedPreferenceManager {

    public static final String TAG = SharedPreferenceManager.class.getSimpleName();
    public static void saveStringSP(String key, String value){
        Log.d(TAG, "== 2 == saveStringSP NICOLE: ");

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(DetailyApplication.getAppContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getStringSP(String key){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(DetailyApplication.getAppContext());
        String name = preferences.getString(key, "");
        return name;
    }

    public static void saveSongSP(String key, Songs song){
        Gson gson = new Gson();
        String json = gson.toJson(song);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(DetailyApplication.getAppContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, json);
        editor.apply();
    }

    public static Songs getSongSP(String key){
        Gson gson = new Gson();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(DetailyApplication.getAppContext());
        String json = preferences.getString(key, "");
        Songs song = gson.fromJson(json, Songs.class);
        return song;
    }
}
