package com.sonlcr1.projectrecipe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sonlcr1.projectrecipe.R;
import com.sonlcr1.projectrecipe.member.Board;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class BoardAdapter extends RecyclerView.Adapter {

    String imgUrl = "http://jeondh9971.dothome.co.kr/Recipe/recipeBoardEdit/";

    Context context;
    ArrayList<Board> datas;
    RelativeLayout relativeLayout;

    public BoardAdapter() {
    }

    public BoardAdapter(Context context, ArrayList<Board> datas) {
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(context).inflate(R.layout.recycler_board,parent,false);
        relativeLayout = itemview.findViewById(R.id.relative01);
        return new VH(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH)holder;
        Board item = datas.get(position);

        String userimg = imgUrl + item.userimg;
        String imgmain = imgUrl + item.imgmain;

        if(item!=null&&item.userimg==null&&item.userid==null) relativeLayout.setVisibility(View.GONE);

        Glide.with(context).load(userimg).into(vh.circleimg);
        Glide.with(context).load(imgmain).into(vh.ivmain);
        vh.tvuser.setText(item.userid);
        vh.tvday.setText(item.date);
        vh.tvmsg.setText(item.msg.substring(1,item.msg.length()-1));
        vh.tvfavor.setText(""+item.favornum);
        //vh.tvchat.setText(item.chatnum);
        vh.tbfavor.setChecked(item.favorstate==1?true:false);

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class VH extends RecyclerView.ViewHolder{

        CircleImageView circleimg;
        ImageView ivmain;
        TextView tvuser,tvday,tvmsg,tvfavor,tvchat;
        ToggleButton tbfavor;

        public VH(@NonNull View itemView) {
            super(itemView);
            circleimg = itemView.findViewById(R.id.circleimg);
            ivmain = itemView.findViewById(R.id.iv_main);
            tvuser = itemView.findViewById(R.id.tv_user);
            tvday = itemView.findViewById(R.id.tv_day);
            tvmsg = itemView.findViewById(R.id.tv_msg);
            tvfavor = itemView.findViewById(R.id.tv_favor);
            //tvchat = itemView.findViewById(R.id.tv_chat);
            tbfavor = itemView.findViewById(R.id.tb_favor);

            tbfavor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked == true) {
                        tvfavor.setText(""+(datas.get(getLayoutPosition()).favornum+1));
                    }else tvfavor.setText(""+(datas.get(getLayoutPosition()).favornum));
                }
            });
        }
    }
}
