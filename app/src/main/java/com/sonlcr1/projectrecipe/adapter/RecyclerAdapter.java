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
import com.sonlcr1.projectrecipe.member.Choice;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<Choice> datas = new ArrayList<>();

    public RecyclerAdapter() {
    }

    public RecyclerAdapter(Context context, ArrayList<Choice> datas) {
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
        Choice items = datas.get(position);

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
        }
    }
}
