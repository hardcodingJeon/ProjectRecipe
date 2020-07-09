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
import com.sonlcr1.projectrecipe.member.ThemaIcon;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ThemaIconAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<ThemaIcon> datas;

    public ThemaIconAdapter() {
    }

    public ThemaIconAdapter(Context context, ArrayList<ThemaIcon> datas) {
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemveiw = LayoutInflater.from(context).inflate(R.layout.recyclergrid_icon,parent,false);
        VH holder = new VH(itemveiw);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH)holder;
        vh.tv.setText(datas.get(position).title);
        Glide.with(context).load(datas.get(position).img).into(vh.iv);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class VH extends RecyclerView.ViewHolder{

        TextView tv;
        ImageView iv;

        public VH(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_icon_title);
            iv = itemView.findViewById(R.id.iv_icon);
        }
    }
}
