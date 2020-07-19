package com.sonlcr1.projectrecipe.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.sonlcr1.projectrecipe.R;
import com.sonlcr1.projectrecipe.member.Recipe;

public class RecipePagerFragmentTitle extends Fragment {

    //String imgUrl = "http://jeondh9971.dothome.co.kr/Recipe/recipeData/";

    ImageView iv;
    TextView tvsub,tvtitle;
    Recipe data;
    FragmentActivity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_title,container,false);
        iv = view.findViewById(R.id.iv_main);
        tvsub = view.findViewById(R.id.tv_sub);
        tvtitle = view.findViewById(R.id.tv_title);

        activity = getActivity();


        String sub = activity.getIntent().getExtras().getString("firstsub");
        String title = activity.getIntent().getStringExtra("firsttile");
        String img = activity.getIntent().getExtras().getString("firstimg");
        tvsub.setText(sub);
        tvtitle.setText(title);
        int resId = getResources().getIdentifier(img,"drawable","com.sonlcr1.projectrecipe");
        Glide.with(view).load(resId).into(iv);
        //tvsub.setText(intent.getStringExtra("tvSub"));
//        tvtitle.setText(intent.getStringExtra("tvTitle"));
//        Glide.with(view).load(imgUrl+intent.getStringExtra("img")).into(iv);
//        tvsub.setText(data.firstsub);
//        tvtitle.setText(data.firsttitle);
//        Glide.with(view).load(imgUrl+"/recipeData/"+data.firstimg).into(iv);


        return view;
    }
}
