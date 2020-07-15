package com.sonlcr1.projectrecipe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sonlcr1.projectrecipe.R;
import com.sonlcr1.projectrecipe.member.Recipe;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<Recipe> datas;

    public RecipeAdapter() {
    }

    public RecipeAdapter(Context context, ArrayList<Recipe> datas) {
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.recycler_recipe,parent,false);
        VH holder = new VH(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH)holder;
        Recipe data = datas.get(position);

        String imgUrl = "http://jeondh9971.dothome.co.kr/Recipe/recipeData/";
        vh.tv.setText(data.thirdmsg);
        Glide.with(context).load(imgUrl+data.thirdimg).into(vh.iv);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class VH extends RecyclerView.ViewHolder{

        ImageView iv;
        TextView tv;

        public VH(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
