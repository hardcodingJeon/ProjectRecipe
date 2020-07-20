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

public class HomeChoiceAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<VORecipe> datas = new ArrayList<>();
    Resources resources;

    public HomeChoiceAdapter() {
    }

    public HomeChoiceAdapter(Context context, ArrayList<VORecipe> datas, Resources resources) {
        this.context = context;
        this.datas = datas;
        this.resources = resources;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(context).inflate(R.layout.recycler_choice,parent,false);
        VH holder = new VH(itemview);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH)holder;
        VORecipe items = datas.get(position);

        vh.tvSub.setText(items.list.get(0).sub);
        vh.tvTitle.setText(items.list.get(0).title);
        int resId = resources.getIdentifier(items.list.get(0).img,"drawable","com.sonlcr1.projectrecipe");
        Glide.with(context).load(resId).into(vh.imgMain);



    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class VH extends RecyclerView.ViewHolder{
        TextView tvEx;
        TextView tvTitle;
        TextView tvSub;
        ImageView imgMain;


        public VH(@NonNull View itemView) {
            super(itemView);
            tvEx = itemView.findViewById(R.id.tv_ex);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvSub = itemView.findViewById(R.id.tv_sub);
            imgMain = itemView.findViewById(R.id.iv_main);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    VORecipe data = datas.get(getLayoutPosition());

                    Intent intent = new Intent(context, RecipeActivity.class);
                    intent.putExtra("list",data.list.get(0));

                    context.startActivity(intent);

                }
            });
        }
    }
}
