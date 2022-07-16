package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.Adapter.RandomRecipieAdapter;
import com.example.myapplication.Listeners.RandomRecipieResponseListener;
import com.example.myapplication.Listeners.RecipieClickListener;
import com.example.myapplication.Models.RandomRecipieApiResponse;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    CustomProgressDialog dialog;
    RequestManger manger;
    RandomRecipieAdapter randomRecipieAdapter;
    RecyclerView recyclerView;
    Spinner spinner;
    SearchView searchView;
    List<String> tags = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new CustomProgressDialog(this);
        searchView = findViewById(R.id.searchView_home);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                tags.clear();
                tags.add(query);
                manger.getRandomRecipies(randomRecipieResponseListener,tags);
                dialog.show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        spinner = findViewById(R.id.spinner_tags);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.tags,
                R.layout.spinner_text
        );


        arrayAdapter.setDropDownViewResource(R.layout.spinner_inner_text);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(spinnerSelectedListener);

        manger = new RequestManger(this);
//        manger.getRandomRecipies(randomRecipieResponseListener);
////        dialog.show();
    }

    private final RandomRecipieResponseListener randomRecipieResponseListener = new RandomRecipieResponseListener() {
        @Override
        public void didFetch(RandomRecipieApiResponse response, String message) {
            dialog.dismiss();
            recyclerView = findViewById(R.id.recycler_random);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,1));
            randomRecipieAdapter = new RandomRecipieAdapter(MainActivity.this,response.recipes,recipieClickListener);
            recyclerView.setAdapter(randomRecipieAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private final AdapterView.OnItemSelectedListener spinnerSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tags.clear();
                tags.add(adapterView.getSelectedItem().toString());
                manger.getRandomRecipies(randomRecipieResponseListener,tags);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    private final RecipieClickListener recipieClickListener = new RecipieClickListener() {
        @Override
        public void onRecipieClicked(String id) {
            startActivity(new Intent(MainActivity.this,RecipieDetailsActivity.class)
                    .putExtra("id",id));

        }
    };
}