package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private TextView originL;
    private TextView originTV;
    private TextView alsoKnowAsL;
    private TextView alsoKnownAsTV;
    private TextView ingredientsTV;
    private TextView descriptionTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        originL         = findViewById(R.id.origin_label);
        originTV        = findViewById(R.id.origin_tv);
        alsoKnowAsL     = findViewById(R.id.also_known_tv);
        alsoKnownAsTV   = findViewById(R.id.also_known_tv);
        ingredientsTV   = findViewById(R.id.ingredients_tv);
        descriptionTV   = findViewById(R.id.description_tv);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
//        if (sandwich.getPlaceOfOrigin().isEmpty()) {
//            originL.setVisibility(View.GONE);
//            originTV.setVisibility(View.GONE);
//        } else {
            originTV.setText(sandwich.getPlaceOfOrigin());
//        }
//
//        if (sandwich.getAlsoKnownAs().isEmpty()) {
//            alsoKnowAsL.setVisibility(View.GONE);
//            alsoKnownAsTV.setVisibility(View.GONE);
//        } else {
            alsoKnownAsTV.setText(TextUtils.join(", ", sandwich.getAlsoKnownAs()));
//        }
//
        ingredientsTV.setText(TextUtils.join(", ", sandwich.getIngredients()));
        descriptionTV.setText(sandwich.getDescription());
    }
}
