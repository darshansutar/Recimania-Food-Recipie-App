package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Models.ExtendedIngredient;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class IngridientsAdapter extends RecyclerView.Adapter<IngridientsViewHolder> {

    Context context;
    List<ExtendedIngredient> list;

    public IngridientsAdapter(Context context, List<ExtendedIngredient> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public IngridientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngridientsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_meal_ingredients,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull IngridientsViewHolder holder, int position) {
            holder.getTextView_ingridients_name.setText(list.get(position).name);
            holder.getTextView_ingridients_name.setSelected(true);
            holder.textView_ingridients_quantity.setText(list.get(position).original);
            holder.textView_ingridients_quantity.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/" + list.get(position).image).into(holder.imageView_ingridients);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class IngridientsViewHolder extends RecyclerView.ViewHolder {

    TextView textView_ingridients_quantity,getTextView_ingridients_name;
    ImageView imageView_ingridients;
    public IngridientsViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_ingridients_quantity = itemView.findViewById(R.id.textView_ingridients_quantity);
        getTextView_ingridients_name = itemView.findViewById(R.id.textView_ingredients_name);
        imageView_ingridients = itemView.findViewById(R.id.imageView_ingridients);
    }
}
