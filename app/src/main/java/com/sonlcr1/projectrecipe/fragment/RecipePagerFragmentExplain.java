package com.sonlcr1.projectrecipe.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sonlcr1.projectrecipe.R;

public class RecipePagerFragmentExplain extends Fragment {




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //여기에 리싸이클러뷰 들어갈것
        View view = inflater.inflate(R.layout.fragment_recipe_explain,container,false);


        return view;

    }
}
