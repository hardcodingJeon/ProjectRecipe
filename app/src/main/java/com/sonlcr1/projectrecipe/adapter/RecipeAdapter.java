package com.sonlcr1.projectrecipe.adapter;

import android.content.Context;
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
import com.sonlcr1.projectrecipe.member.Recipe;
import com.sonlcr1.projectrecipe.member.Recipe2;
import com.sonlcr1.projectrecipe.member.ThemaIcon2;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<ThemaIcon2.Candy> datas;
    Resources resources;

    public RecipeAdapter() {
    }

    public RecipeAdapter(Context context, ArrayList<ThemaIcon2.Candy> datas, Resources resources) {
        this.context = context;
        this.datas = datas;
        this.resources = resources;
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
        ThemaIcon2.Candy data = datas.get(position);

        vh.tv.setText(data.recipemsg);
        vh.tv_num.setText(data.num);
        int resId = resources.getIdentifier(data.recipeimg,"drawable","com.sonlcr1.projectrecipe");
        Glide.with(context).load(resId).into(vh.iv);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class VH extends RecyclerView.ViewHolder{

        ImageView iv;
        TextView tv;
        TextView tv_num;

        public VH(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
            tv_num = itemView.findViewById(R.id.tv_num);
        }
    }
}
