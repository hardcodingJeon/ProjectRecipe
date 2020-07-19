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
import com.sonlcr1.projectrecipe.member.HomeNormal;
import com.sonlcr1.projectrecipe.member.Recipe;
import com.sonlcr1.projectrecipe.recipeActivity.HomeChoiceRecipe;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class HomeNormalAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<Recipe> datas;
    Resources resources;

    public HomeNormalAdapter() {
    }

    public HomeNormalAdapter(Context context, ArrayList<Recipe> datas, Resources resources) {
        this.context = context;
        this.datas = datas;
        this.resources = resources;
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
        Recipe item = datas.get(position);
        //String imgUrl = "http://jeondh9971.dothome.co.kr/Recipe/recipeData/" + datas.get(position).firstimg;

        vh.msg.setText(item.firstsub);
        vh.title.setText(item.firsttitle);
        int resId = resources.getIdentifier(item.firstimg,"drawable","com.sonlcr1.projectrecipe");
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
                    Recipe data = datas.get(getLayoutPosition());

                    Intent intent = new Intent(context, HomeChoiceRecipe.class);
                    intent.putExtra("firstsub",data.firstsub);
                    intent.putExtra("firsttile",data.firsttitle);
                    intent.putExtra("firstimg",data.firstimg);

                    intent.putExtra("secondessential",data.secondessential);
                    intent.putExtra("secondessentialmsg",data.secondessentialmsg);
                    intent.putExtra("secondchoice",data.secondchoice);
                    intent.putExtra("secondchoicemsg",data.secondchoicemsg);
                    intent.putExtra("secondsource",data.secondsource);
                    intent.putExtra("secondsourcemsg",data.secondsourcemsg);

                    intent.putExtra("thirdimg",data.thirdimg);
                    intent.putExtra("thirdmsg",data.thirdmsg);

                    intent.putExtra("fourthimg",data.fourthimg);
                    intent.putExtra("fourthmsg",data.fourthmsg);

                    intent.putExtra("fifthimg",data.fifthimg);
                    intent.putExtra("fifthmsg",data.fifthmsg);

                    intent.putExtra("sixthimg",data.sixthimg);
                    intent.putExtra("sixthmsg",data.sixthmsg);

                    intent.putExtra("seventhimg",data.seventhimg);
                    intent.putExtra("seventhmsg",data.seventhmsg);

                    intent.putExtra("eighthimg",data.eighthimg);
                    intent.putExtra("eighthmsg",data.eighthmsg);

                    context.startActivity(intent);
                }
            });
        }
    }
}
