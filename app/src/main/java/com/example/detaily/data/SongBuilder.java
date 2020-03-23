package com.example.detaily.data;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;


public class SongBuilder {

    public static final String TAG = SongBuilder.class.getSimpleName();
    public static final List<Songs> SONGS = new ArrayList<Songs>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
//        for (int i = 1; i <= COUNT; i++) {
//            addItem(createSongList(i));
//        }
    }

    private static void addItem(Songs item) {
        SONGS.add(item);
    }

    private static Songs createSongList(int position, JSONArray jsonArray) {
        return new Songs("", "", "", "");
    }

    private static String makeDetails(int position, Songs currentSong) {
        if(null != currentSong){
            StringBuilder builder = new StringBuilder();
            builder.append(currentSong.getTrackName())
                    .append(currentSong.getTrackPrice())
                    .append(currentSong.getPrimaryGenreName())
                    .append(position);

            return builder.toString();
        }
        return String.valueOf(position);
    }


}
