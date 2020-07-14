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

import com.bumptech.glide.Glide;
import com.sonlcr1.projectrecipe.R;
import com.sonlcr1.projectrecipe.member.Recipe;

public class RecipePagerFragmentTitle extends Fragment {

    String imgUrl = "http://jeondh9971.dothome.co.kr/Recipe";

    ImageView iv;
    TextView tvsub,tvtitle;
    Recipe data;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_title,container,false);
        iv = view.findViewById(R.id.iv);
        tvsub = view.findViewById(R.id.tv_sub);
        tvtitle = view.findViewById(R.id.tv_title);

//        tvsub.setText(data.firstsub);
//        tvtitle.setText(data.firsttitle);
//        Glide.with(view).load(imgUrl+"/recipeData/"+data.firstimg).into(iv);


        return view;
    }
}
