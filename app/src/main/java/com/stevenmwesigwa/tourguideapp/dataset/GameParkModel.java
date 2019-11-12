package com.stevenmwesigwa.tourguideapp.dataset;

import android.content.Context;

import com.stevenmwesigwa.tourguideapp.GamePark;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

public class GameParkModel {
    private Context context;

    public GameParkModel(Context context) {
        this.context = context;
    }

    public ArrayList<GamePark> get() {
        ArrayList<GamePark> gameParkArrayList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(Objects.requireNonNull(loadJSONFromAsset()));
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject row = jsonArray.getJSONObject(i);
                int id = row.getInt("id");
                String title = row.getString("title");
                String description = row.getString("description");
                String image = row.getString("image");

                GamePark gamePark = new GamePark(id, title, description, image);
                gameParkArrayList.add(gamePark);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return gameParkArrayList;
    }

    private String loadJSONFromAsset() {
        //function to load the JSON from the Asset and return the object
        String json = null;
        final Charset charset = StandardCharsets.UTF_8;
        try {
            InputStream inputStream = context.getAssets().open("gameParksList.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, charset);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
