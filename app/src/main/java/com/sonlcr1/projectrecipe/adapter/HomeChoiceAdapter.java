package com.sonlcr1.projectrecipe.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sonlcr1.projectrecipe.R;
import com.sonlcr1.projectrecipe.member.HomeChoice;
import com.sonlcr1.projectrecipe.recipeActivity.HomeChoiceRecipe;

import java.util.ArrayList;

public class HomeChoiceAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<HomeChoice> datas = new ArrayList<>();

    public HomeChoiceAdapter() {
    }

    public HomeChoiceAdapter(Context context, ArrayList<HomeChoice> datas) {
        this.context = context;
        this.datas = datas;
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
        HomeChoice items = datas.get(position);

        String imgUrl = "http://jeondh9971.dothome.co.kr/Recipe/recipeChoice/" + items.img;

        vh.tvSubject.setText(items.subject);
        vh.tvTitle.setText(items.title);
        Glide.with(context).load(imgUrl).into(vh.imgMain);



    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class VH extends RecyclerView.ViewHolder{
        TextView tvSubject;
        TextView tvTitle;
        ImageView imgMain;


        public VH(@NonNull View itemView) {
            super(itemView);
            tvSubject = itemView.findViewById(R.id.tv_subject);
            tvTitle = itemView.findViewById(R.id.tv_title);
            imgMain = itemView.findViewById(R.id.iv_main);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (getLayoutPosition()){
                        case 0:
                            Intent intent = new Intent(context, HomeChoiceRecipe.class);
                            //여기서 put 데이터로 레시피 제목 넣을것
                            context.startActivity(intent);
                            break;
                        case 1:
                            break;
                        case 2:
                            break;
                    }

                }
            });
        }
    }
}
