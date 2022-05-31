package com.example.myapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapp.Models.PopularModel;
import com.example.myapp.R;

import java.util.List;

public class PopularAdapter  extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {
    private Context context;
    private List<PopularModel> popularModelList;

    public PopularAdapter(Context context, List<PopularModel> popularModelList) {
        this.context = context;
        this.popularModelList = popularModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide .with(context).load(popularModelList.get(position).getImg_url()).into(holder.pop_img);
        holder.name.setText(popularModelList.get(position).getName());
        holder.rating.setText(popularModelList.get(position).getName());
        holder.discount.setText(popularModelList.get(position).getDiscount());
        holder.discrption.setText(popularModelList.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return popularModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView pop_img;
        TextView discrption,discount,rating,name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pop_img = itemView.findViewById(R.id.pop_img);
            discrption = itemView.findViewById(R.id.pop_des);
            discount = itemView.findViewById(R.id.pop_dis);
            rating = itemView.findViewById(R.id.pop_rat);
            name= itemView.findViewById(R.id.pop_name);
        }
    }

}
