package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Ingredient;
import com.example.myapplication.Models.Equipment;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class InstructionsIngridentsAdapter extends RecyclerView.Adapter<InstructionIngridentsViewHolder> {

    Context context;
    List<Ingredient> list;

    public InstructionsIngridentsAdapter(Context context, ArrayList<Ingredient> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionIngridentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionIngridentsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instructions_steps_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionIngridentsViewHolder holder, int position) {
                holder.textView_instrutions_step_item.setText(list.get(position).name);
                holder.textView_instrutions_step_item.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/"+list.get(position).image).into(holder.imageView_instructions_step_items);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class InstructionIngridentsViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView_instructions_step_items;
    TextView textView_instrutions_step_item;

    public InstructionIngridentsViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView_instructions_step_items=itemView.findViewById(R.id.imageView_instruction_step_item);
        textView_instrutions_step_item = itemView.findViewById(R.id.textView_instructions_step_item);
    }
}
