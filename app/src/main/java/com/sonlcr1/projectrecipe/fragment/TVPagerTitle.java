package com.sonlcr1.projectrecipe.fragment;

import android.content.Intent;
import android.net.Uri;
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
import com.sonlcr1.projectrecipe.member.VORecipe;

public class TVPagerTitle extends Fragment {

    ImageView iv;
    TextView tvsub,tvtitle,tvVideo;
    FragmentActivity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv_title,container,false);
        iv = view.findViewById(R.id.iv_main);
        tvsub = view.findViewById(R.id.tv_sub);
        tvtitle = view.findViewById(R.id.tv_title);
        tvVideo = view.findViewById(R.id.tv_video);

        activity = getActivity();


        VORecipe.Apple list = (VORecipe.Apple)activity.getIntent().getSerializableExtra("list");

        String title = list.title;
        String sub = list.sub;
        String img = list.img;
        tvsub.setText(sub);
        tvtitle.setText(title);
        int resId = getResources().getIdentifier(img,"drawable","com.sonlcr1.projectrecipe");
        Glide.with(view).load(resId).into(iv);

        tvVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(list.uri));
                startActivity(intent);
            }
        });


        return view;
    }
}
