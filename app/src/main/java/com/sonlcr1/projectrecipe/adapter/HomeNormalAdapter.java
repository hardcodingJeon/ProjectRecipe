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
import com.sonlcr1.projectrecipe.member.HomeNormal;
import com.sonlcr1.projectrecipe.member.Recipe;

import java.util.ArrayList;

public class HomeNormalAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<Recipe> datas;

    public HomeNormalAdapter() {
    }

    public HomeNormalAdapter(Context context, ArrayList<Recipe> datas) {
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(context).inflate(R.layout.recyclergrid_normal,parent,false);
        VH holder = new VH(itemview);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH)holder;

        String imgUrl = "http://jeondh9971.dothome.co.kr/Recipe/recipeData/" + datas.get(position).firstimg;

        vh.msg.setText(datas.get(position).firstsub);
        vh.title.setText(datas.get(position).firsttitle);
        Glide.with(context).load(imgUrl).into(vh.iv);
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

                }
            });
        }
    }
}
