package com.sonlcr1.projectrecipe.fragment;

import android.content.Context;
import android.content.res.AssetManager;
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
import com.sonlcr1.projectrecipe.adapter.ThemaIconAdapter;
import com.sonlcr1.projectrecipe.member.VORecipe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ThemaTabFragment extends Fragment {

    ArrayList<VORecipe> datas = new ArrayList<>();

    ThemaIconAdapter iconAdapter;
    RecyclerView recyclerView;
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thematab, container, false);

        context = getContext();
        recyclerView = view.findViewById(R.id.recycle);
        recyclerView.setNestedScrollingEnabled(false);

        getdata();


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        datas.removeAll(datas);
        iconAdapter.notifyDataSetChanged();
    }

    void getdata() {
        AssetManager assetManager = context.getAssets();
        StringBuffer buffer = new StringBuffer();
        try {
            InputStream is = assetManager.open("thema_list_recipe.json");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);

            while (true) {
                String line = reader.readLine();
                if (line == null) break;
                buffer.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        VORecipe[] VORecipe = gson.fromJson(buffer.toString(), VORecipe[].class);
        for (VORecipe e: VORecipe){
            datas.add(e);
        }

        iconAdapter = new ThemaIconAdapter(context, datas,getResources());
        iconAdapter.notifyDataSetChanged();

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 2);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(iconAdapter);
    }
}
