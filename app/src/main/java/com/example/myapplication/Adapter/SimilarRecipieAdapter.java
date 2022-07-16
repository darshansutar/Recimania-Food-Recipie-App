package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Listeners.RecipieClickListener;
import com.example.myapplication.Models.SimilarRecipieResponse;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SimilarRecipieAdapter extends RecyclerView.Adapter<SimilarRecipieViewHolder> {
    Context context;
    List<SimilarRecipieResponse> list;
    RecipieClickListener listener;

    public SimilarRecipieAdapter(Context context, List<SimilarRecipieResponse> list, RecipieClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SimilarRecipieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SimilarRecipieViewHolder(LayoutInflater.from(context).inflate(R.layout.list_similar_recipie,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SimilarRecipieViewHolder holder, int position) {
            holder.textView_similar_title.setText(list.get(position).title);
            holder.textView_similar_title.setSelected(true);
            holder.getTextView_similar_serving.setText(list.get(position).servings+" Persons");
        Picasso.get().load(" https://spoonacular.com/recipeImages/"+list.get(position).id+"-556x370."+list.get(position).imageType).into(holder.imageView_similar);

        holder.similar_recipie_holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRecipieClicked(String.valueOf(list.get(holder.getAdapterPosition()).id));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class SimilarRecipieViewHolder extends RecyclerView.ViewHolder{

    CardView similar_recipie_holder;
    TextView textView_similar_title,getTextView_similar_serving;
    ImageView imageView_similar;

    public SimilarRecipieViewHolder(@NonNull View itemView) {
        super(itemView);
        similar_recipie_holder = itemView.findViewById(R.id.similar_recipie_holder);
        textView_similar_title = itemView.findViewById(R.id.textView_similar_title);
        getTextView_similar_serving = itemView.findViewById(R.id.textView_similar_serving);
        imageView_similar = itemView.findViewById(R.id.imageView_similar);
    }
}
