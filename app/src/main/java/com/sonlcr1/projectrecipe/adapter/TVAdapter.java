package com.sonlcr1.projectrecipe.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sonlcr1.projectrecipe.R;
import com.sonlcr1.projectrecipe.member.VORecipe;
import com.sonlcr1.projectrecipe.recipeActivity.RecipeActivity;

import java.util.ArrayList;

public class TVAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<VORecipe> datas;
    Resources resources;

    public TVAdapter() {
    }

    public TVAdapter(Context context, ArrayList<VORecipe> datas, Resources resources) {
        this.context = context;
        this.datas = datas;
        this.resources = resources;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(context).inflate(R.layout.recycler_tv,parent,false);
        VH holder = new VH(itemview);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH)holder;
        VORecipe.Apple data = datas.get(position).list.get(0);

        vh.msg.setText(data.sub);
        vh.title.setText(data.title);
        int resId = resources.getIdentifier(data.img,"drawable","com.sonlcr1.projectrecipe");
        Glide.with(context).load(resId).into(vh.iv);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class VH extends RecyclerView.ViewHolder{

        TextView msg,title;
        ImageView iv;

        public VH(@NonNull View itemView) {
            super(itemView);
            msg = itemView.findViewById(R.id.tv_msg);
            title = itemView.findViewById(R.id.tv_title);
            iv = itemView.findViewById(R.id.iv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    VORecipe.Apple list = datas.get(getLayoutPosition()).list.get(0);
                    Intent intent = new Intent(context, RecipeActivity.class);
                    intent.putExtra("list",list);
                    context.startActivity(intent);
                }
            });
        }
    }
}
