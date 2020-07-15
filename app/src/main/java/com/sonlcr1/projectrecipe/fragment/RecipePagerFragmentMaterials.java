package com.sonlcr1.projectrecipe.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.sonlcr1.projectrecipe.R;

public class RecipePagerFragmentMaterials extends Fragment {

    FragmentActivity activity;

    TextView tvEss,tvEssMsg,tvChoi,tvChoiMsg,tvSour,tvSourMsg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipe_materials,container,false);
        activity = getActivity();
        tvEss = view.findViewById(R.id.tv_ess);
        tvEssMsg = view.findViewById(R.id.tv_essMsg);
        tvChoi = view.findViewById(R.id.tv_choi);
        tvChoiMsg = view.findViewById(R.id.tv_choiMsg);
        tvSour = view.findViewById(R.id.tv_sour);
        tvSourMsg = view.findViewById(R.id.tv_sourMsg);

        tvEss.setText(activity.getIntent().getStringExtra("secondessential"));
        tvEssMsg.setText(activity.getIntent().getStringExtra("secondessentialmsg"));
        tvChoi.setText(activity.getIntent().getStringExtra("secondchoice"));
        tvChoiMsg.setText(activity.getIntent().getStringExtra("secondchoicemsg"));
        tvSour.setText(activity.getIntent().getStringExtra("secondsource"));
        tvSourMsg.setText(activity.getIntent().getStringExtra("secondsourcemsg"));

        return view;
    }
}
