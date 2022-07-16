package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.IngridientsAdapter;
import com.example.myapplication.Adapter.InstructionsAdapter;
import com.example.myapplication.Adapter.SimilarRecipieAdapter;
import com.example.myapplication.Listeners.InstructionsListener;
import com.example.myapplication.Listeners.RecipieClickListener;
import com.example.myapplication.Listeners.RecipieDetailsListener;
import com.example.myapplication.Listeners.SimilarRecipiesListener;
import com.example.myapplication.Models.InstructionsResponse;
import com.example.myapplication.Models.RecpieDetailsResponse;
import com.example.myapplication.Models.SimilarRecipieResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipieDetailsActivity extends AppCompatActivity {

    int id;
    TextView textView_meal_name,getTextView_meal_source,getTextView_meal_summary;
    ImageView imageView_meal_image;
    RecyclerView recycler_meal_ingredients,recycler_meal_similar,recycler_meal_instructions;
    RequestManger manger;
    CustomProgressDialog dialog;
    IngridientsAdapter ingridientsAdapter;
    SimilarRecipieAdapter similarRecipieAdapter;
    InstructionsAdapter instructionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipie_details);

        findViews();

        id =Integer.parseInt(getIntent().getStringExtra("id"));
        manger = new RequestManger(this);
        manger.getRecipieDetails(recipieDetailslistener,id);
        manger.getSimilarRecipie(similarRecipiesListener,id);
        manger.getInstructions(instructionsListener,id);
        dialog = new CustomProgressDialog(this);
        dialog.setTitle("Loading Details....");
        dialog.show();


    }

    private void findViews() {
        textView_meal_name = findViewById(R.id.textView_meal_name);
        getTextView_meal_source = findViewById(R.id.textView_meal_source);
        getTextView_meal_summary = findViewById(R.id.textView_meal_summary);
        imageView_meal_image = findViewById(R.id.imageView_meal_image);
        recycler_meal_ingredients = findViewById(R.id.recycler_meal_ingredients);
        recycler_meal_similar = findViewById(R.id.recycler_meal_similar);
        recycler_meal_instructions = findViewById(R.id.recycler_meal_instructions);

    }

    private final RecipieDetailsListener recipieDetailslistener = new RecipieDetailsListener() {
        @Override
        public void didFetch(RecpieDetailsResponse response, String message) {
            dialog.dismiss();
            textView_meal_name.setText(response.title);
            getTextView_meal_source.setText(response.sourceName);
            getTextView_meal_summary.setText(response.summary);
            Picasso.get().load(response.image).into(imageView_meal_image);

            recycler_meal_ingredients.setHasFixedSize(true);
            recycler_meal_ingredients.setLayoutManager(new LinearLayoutManager(RecipieDetailsActivity.this,LinearLayoutManager.HORIZONTAL,false));
            ingridientsAdapter = new IngridientsAdapter(RecipieDetailsActivity.this,response.extendedIngredients);
            recycler_meal_ingredients.setAdapter(ingridientsAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipieDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private final SimilarRecipiesListener similarRecipiesListener = new SimilarRecipiesListener() {
        @Override
        public void didFetch(List<SimilarRecipieResponse> response, String message) {
                recycler_meal_similar.setHasFixedSize(true);
                recycler_meal_similar.setLayoutManager(new LinearLayoutManager(RecipieDetailsActivity.this,LinearLayoutManager.HORIZONTAL,false));
                similarRecipieAdapter =new SimilarRecipieAdapter(RecipieDetailsActivity.this,response,recipieClickListener);
                recycler_meal_similar.setAdapter(similarRecipieAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipieDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private final RecipieClickListener recipieClickListener = new RecipieClickListener() {
        @Override
        public void onRecipieClicked(String id) {
            startActivity(new Intent(RecipieDetailsActivity.this,RecipieDetailsActivity.class)
                    .putExtra("id",id));

        }
    };

    private final InstructionsListener instructionsListener = new InstructionsListener() {
        @Override
        public void didFetch(List<InstructionsResponse> response, String message) {
            recycler_meal_instructions.setHasFixedSize(true);
            recycler_meal_instructions.setLayoutManager(new LinearLayoutManager(RecipieDetailsActivity.this,LinearLayoutManager.VERTICAL,false));
            instructionsAdapter = new InstructionsAdapter(RecipieDetailsActivity.this,response);
            recycler_meal_instructions.setAdapter(instructionsAdapter);
        }

        @Override
        public void didError(String message) {

        }
    };
}