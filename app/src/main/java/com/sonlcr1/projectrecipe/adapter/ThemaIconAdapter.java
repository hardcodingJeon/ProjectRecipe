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
import com.sonlcr1.projectrecipe.member.ThemaIcon;
import com.sonlcr1.projectrecipe.member.ThemaIcon2;
import com.sonlcr1.projectrecipe.recipeActivity.ThemaActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ThemaIconAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<ThemaIcon2> datas;
    Resources resources;

    public ThemaIconAdapter() {
    }

    public ThemaIconAdapter(Context context, ArrayList<ThemaIcon2> datas, Resources resources) {
        this.context = context;
        this.datas = datas;
        this.resources = resources;
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
        vh.tv.setText(datas.get(position).maintitle);
        int resId = resources.getIdentifier(datas.get(position).mainimg,"drawable","com.sonlcr1.projectrecipe");
        Glide.with(context).load(resId).into(vh.iv);
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ThemaIcon2 data = datas.get(getLayoutPosition());
                    Intent intent = new Intent(context, ThemaActivity.class);
                    intent.putExtra("title",data.maintitle);
                    intent.putExtra("list",data.list);
                    context.startActivity(intent);
                }
            });
        }
    }
}
