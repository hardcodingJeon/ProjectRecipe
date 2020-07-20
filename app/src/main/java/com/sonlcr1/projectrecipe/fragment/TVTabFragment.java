package com.sonlcr1.projectrecipe.fragment;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.sonlcr1.projectrecipe.R;
import com.sonlcr1.projectrecipe.adapter.TVAdapter;
import com.sonlcr1.projectrecipe.member.TV;
import com.sonlcr1.projectrecipe.member.VORecipe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TVTabFragment extends Fragment {

    ArrayList<VORecipe> datas = new ArrayList<>();

    TVAdapter tvAdapter;

    RecyclerView recyclerView;

    Context context;
    Resources resources;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tvtab,container,false);

        context = getContext();
        resources = getResources();
        recyclerView = view.findViewById(R.id.recycle);

        getdata();


        return view;
    }

    //viewPager.setOffscreenPageLimit(3); setofflimit로 메모리에서 삭제 하지 않고 유지시키기 때문에 ondestroyView 안해도 상관 없다.

    void getdata(){
        AssetManager assetManager = context.getAssets();
        try {
            InputStream is = assetManager.open("tv_list_recipe.json");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);

            StringBuffer buffer = new StringBuffer();
            while (true){
                String line = reader.readLine();
                if (line==null) break;
                buffer.append(line+"\n");
            }
            Gson gson = new Gson();
            VORecipe[] voRecipes = gson.fromJson(buffer.toString(), VORecipe[].class);
            for (VORecipe e : voRecipes){
                datas.add(e);
            }
            tvAdapter = new TVAdapter(context,datas,resources);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context,2);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(tvAdapter);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
