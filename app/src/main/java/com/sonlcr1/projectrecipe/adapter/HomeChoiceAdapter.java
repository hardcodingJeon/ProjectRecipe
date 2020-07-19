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
import com.sonlcr1.projectrecipe.member.Recipe;
import com.sonlcr1.projectrecipe.recipeActivity.RecipeActivity;

import java.util.ArrayList;

public class HomeChoiceAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<Recipe> datas = new ArrayList<>();
    Resources resources;

    public HomeChoiceAdapter() {
    }

    public HomeChoiceAdapter(Context context, ArrayList<Recipe> datas, Resources resources) {
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
        Recipe items = datas.get(position);

        //String imgUrl = "http://jeondh9971.dothome.co.kr/Recipe/recipeData/" + items.firstimg;


        vh.tvSub.setText(items.firstsub);
        vh.tvTitle.setText(items.firsttitle);
        int resId = resources.getIdentifier(items.firstimg,"drawable","com.sonlcr1.projectrecipe");
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
                    Recipe data = datas.get(getLayoutPosition());

                    Intent intent = new Intent(context, RecipeActivity.class);
                    //intent.putExtra("Recipe",data.getClass());

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
