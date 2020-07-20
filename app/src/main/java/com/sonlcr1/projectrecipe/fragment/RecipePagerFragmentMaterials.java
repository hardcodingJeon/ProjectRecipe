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
import com.sonlcr1.projectrecipe.member.Recipe2;
import com.sonlcr1.projectrecipe.member.ThemaIcon2;

import java.util.ArrayList;

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

        ThemaIcon2.Apple list = (ThemaIcon2.Apple)activity.getIntent().getSerializableExtra("list");

        tvEss.setText(list.materials.essential);
        tvEssMsg.setText(list.materials.essentialmsg);
        tvChoi.setText(list.materials.choice);
        tvChoiMsg.setText(list.materials.choicemsg);
        tvSour.setText(list.materials.source);
        tvSourMsg.setText(list.materials.sourcemsg);

        return view;
    }
}
