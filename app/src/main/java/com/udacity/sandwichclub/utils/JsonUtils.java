package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich;
        String  mainName = null,
                placeOfOrigin = null,
                description = null,
                image = null;
        ArrayList<String> ListAlsoKnownAs = new ArrayList<>();
        ArrayList<String> ListIngredients = new ArrayList<>();

        try {
            JSONObject sandwichJson = new JSONObject(json);
            JSONObject sandwichUsed = sandwichJson.getJSONObject("name");

            mainName        = sandwichUsed.getString("mainName");

            placeOfOrigin   = sandwichJson.getString("placeOfOrigin");
            description     = sandwichJson.getString("description");
            image           = sandwichJson.getString("image");

            JSONArray alsoKnowsAsJson = sandwichUsed.getJSONArray("alsoKnownAs");
            for (int i = 0; i < alsoKnowsAsJson.length(); i++) {
                ListAlsoKnownAs.add(alsoKnowsAsJson.getString(i));
            }

            JSONArray ingredientsJson = sandwichJson.getJSONArray("ingredients");
            for (int i = 0; i < ingredientsJson.length(); i++) {
                ListIngredients.add(ingredientsJson.getString(i));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        sandwich = new Sandwich(
                mainName,
                ListAlsoKnownAs,
                placeOfOrigin,
                description,
                image,
                ListIngredients);

        return sandwich;
    }
}
