package com.example.detaily.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.example.detaily.data.EnvironmentVariables;
import com.example.detaily.data.SongBuilder;
import com.example.detaily.R;
import com.example.detaily.data.Songs;
import com.example.detaily.managers.SharedPreferenceManager;

import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ItemListActivity extends AppCompatActivity {

    private static final String TAG = ItemListActivity.class.getSimpleName();

    ArrayList<Songs> songsArray;
    JSONObject songObject;
    private static JSONArray jsonArray;
    JSONObject jsonObject;

    private static String trackName;
    private static String trackPrice;
    private static String primaryGenreName;
    private static String image;

    private static String longDescription; //in detail view
    private static String artistName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        songsArray = new ArrayList<>();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        requestUrl(EnvironmentVariables.URL);

    }

    public void requestUrl(String url) {
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                return;
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    if (!response.isSuccessful())
                        throw new IOException("IOException:  " + response);
                    String responseData = response.body().string();
                    JSONObject jsonObject = new JSONObject(responseData);
                    Log.d(TAG, "== 1 == onResponse NICOLE: " + jsonObject.toString());

                    SharedPreferenceManager.saveStringSP(EnvironmentVariables.JSONRESPONSE, jsonObject.toString());
                    setJSONArrayToModel();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static JSONObject getJSONReponseFromSP() {
        String jsonSP = SharedPreferenceManager.getStringSP(EnvironmentVariables.JSONRESPONSE);
        Log.d(TAG, "getJSONReponseFromSP NICOLE JSON SP: " + jsonSP);
        if (jsonSP != null) {
            try {
                JSONObject jsonResponse = new JSONObject(jsonSP);
                return jsonResponse;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void setJSONArrayToModel() {
        JSONObject jsonObject = getJSONReponseFromSP();
        Log.d(TAG, "== 3 == setJSONArrayToModel NICOLE JSON OBJECT SP: " + jsonObject.toString());
        if (null != jsonObject) {
            try {
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                for (int i = 0; i < jsonArray.length(); i++) {
                    songObject = jsonArray.getJSONObject(i);
                    songsArray.add(new Songs(
                            songObject.getString("trackName"),
                            songObject.getString("trackPrice"),
                            songObject.getString("primaryGenreName"),
                            songObject.getString("artworkUrl100")
                    ));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


    public void saveCurrentSongToSP(int position) {
        try {
            if (null != jsonArray) {
                Log.d(TAG, "getDataAtPosition NICOLE JSON ARRAY: " + jsonArray.toString());
                jsonArray = jsonObject.getJSONArray("results");

                songObject = jsonArray.getJSONObject(position);
                trackName = songObject.getString("trackName");
                trackPrice = songObject.getString("trackPrice");
                primaryGenreName = songObject.getString("primaryGenreName");
                image = songObject.getString("artworkUrl100");

                longDescription = songObject.getString("longDescription");
                artistName = songObject.getString("artistName");

                Songs currentSong = new Songs(trackName, trackPrice, primaryGenreName, image);
                SharedPreferenceManager.saveSongSP(EnvironmentVariables.CURRENTSONG, currentSong);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new RecyclerViewAdapter(this, SongBuilder.SONGS));
    }

}
