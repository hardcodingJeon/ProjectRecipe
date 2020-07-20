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
import com.sonlcr1.projectrecipe.member.Recipe2;
import com.sonlcr1.projectrecipe.member.ThemaIcon2;

import java.util.ArrayList;

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


        ThemaIcon2.Apple list = (ThemaIcon2.Apple)activity.getIntent().getSerializableExtra("list");

        String title = list.title;
        String sub = list.sub;
        String img = list.img;
        tvsub.setText(sub);
        tvtitle.setText(title);
        int resId = getResources().getIdentifier(img,"drawable","com.sonlcr1.projectrecipe");
        Glide.with(view).load(resId).into(iv);


        return view;
    }
}
