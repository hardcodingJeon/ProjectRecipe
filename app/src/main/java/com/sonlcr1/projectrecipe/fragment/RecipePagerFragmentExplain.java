package com.sonlcr1.projectrecipe.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.sonlcr1.projectrecipe.R;
import com.sonlcr1.projectrecipe.adapter.RecipeAdapter;
import com.sonlcr1.projectrecipe.member.VORecipe;

import java.util.ArrayList;

public class RecipePagerFragmentExplain extends Fragment {

    FragmentActivity activity;
    RecyclerView recyclerView;
    RecipeAdapter adapter;
    Context context;
    Resources resources;
    ArrayList<VORecipe.Candy> recipe;
    VORecipe.Apple list;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //여기에 리싸이클러뷰 들어갈것
        View view = inflater.inflate(R.layout.fragment_recipe_explain,container,false);
        activity = getActivity();
        recyclerView = view.findViewById(R.id.recycle);
        context = getContext();
        resources = getResources();

        list = (VORecipe.Apple)activity.getIntent().getSerializableExtra("list");


        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        recipe = list.recipe;
        if (!recipe.get(0).recipeimg.isEmpty()) {
            adapter = new RecipeAdapter(context,recipe,resources);
            recyclerView.setAdapter(adapter);
            SnapHelper snapHelper = new PagerSnapHelper();
            snapHelper.attachToRecyclerView(recyclerView);
        }
    }

}
