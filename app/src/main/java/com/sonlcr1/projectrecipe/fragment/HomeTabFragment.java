package com.sonlcr1.projectrecipe.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.sonlcr1.projectrecipe.R;
import com.sonlcr1.projectrecipe.adapter.HomeChoiceAdapter;
import com.sonlcr1.projectrecipe.adapter.HomeNormalAdapter;
import com.sonlcr1.projectrecipe.member.Recipe;
import com.sonlcr1.projectrecipe.member.Recipe2;
import com.sonlcr1.projectrecipe.member.ThemaIcon2;
import com.sonlcr1.projectrecipe.recipeActivity.RecipeActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class HomeTabFragment extends Fragment {
    //hotfixthird

    String imgUrl = "http://jeondh9971.dothome.co.kr/Recipe";  //뒤에 주소 덧 붙여야함

    TextView tv, tvSub;
    ImageView iv1,iv2,iv3;

    Context mContext;
    FragmentActivity fragmentActivity;

    RecyclerView recyclerView;
    RecyclerView recyclergrid;
    RecyclerView recyclerView2;

    //ArrayList<Recipe> datas = new ArrayList<>();
    ArrayList<ThemaIcon2> datasChoice = new ArrayList<>();
    ArrayList<ThemaIcon2> datasSummer = new ArrayList<>();

    //ArrayList<HomeSummer> datasSummer = new ArrayList<>();
    ArrayList<ThemaIcon2> datasNormal = new ArrayList<>();


    HomeChoiceAdapter recyclerAdapter;
    HomeNormalAdapter normalAdapter;
    HomeNormalAdapter normalAdapter2;

    SwipeRefreshLayout refreshLayout;
    View view;

    ProgressDialog dialog;
    Resources resources;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_hometab,container,false);
        refreshLayout = view.findViewById(R.id.refresh);
        mContext = getContext();
        recyclergrid = view.findViewById(R.id.recy_grid);
        recyclerView = view.findViewById(R.id.recycle01);
        recyclerView2 = view.findViewById(R.id.recycle02);
        resources = getResources();

        getdata();

        //리프레쉬
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                datasChoice.removeAll(datasChoice);
                datasNormal.removeAll(datasNormal);
                datasSummer.removeAll(datasSummer);

                getdata();
                if (recyclerAdapter != null) {
                    recyclerAdapter.notifyDataSetChanged();
                }
                if (normalAdapter != null) {
                    normalAdapter.notifyDataSetChanged();
                }


                refreshLayout.setRefreshing(false);
            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    void getdata(){
        AssetManager assetManager = mContext.getAssets();
        StringBuffer buffer = new StringBuffer();
        try {
            InputStream inputStream = assetManager.open("home_tab_recipe2.json");
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(isr);

            while (true){
                String line = reader.readLine();
                if (line == null) break;
                buffer.append(line+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        ThemaIcon2[] recipes = gson.fromJson(buffer.toString(), ThemaIcon2[].class);
        for (int i=0;i<3;i++){
            datasChoice.add(recipes[i]);
        }
        for (int i=3;i<6;i++){
            datasSummer.add(recipes[i]);
        }
        for (int i=6;i<recipes.length;i++){
            datasNormal.add(recipes[i]);
        }

        //choice
        recyclerAdapter = new HomeChoiceAdapter(mContext,datasChoice,resources);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setOnFlingListener(null);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        //summer
        normalAdapter2 = new HomeNormalAdapter(mContext,datasSummer,resources);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(mContext,3);
        recyclerView2.setLayoutManager(layoutManager);
        recyclerView2.setAdapter(normalAdapter2);

        //normal
        normalAdapter = new HomeNormalAdapter(mContext,datasNormal,resources);
        RecyclerView.LayoutManager layoutManager2 = new GridLayoutManager(mContext,2);
        recyclergrid.setLayoutManager(layoutManager2);
        recyclergrid.setAdapter(normalAdapter);


    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        datasChoice.removeAll(datasChoice);
        recyclerAdapter.notifyDataSetChanged();

        datasSummer.removeAll(datasSummer);
        normalAdapter2.notifyDataSetChanged();

        datasNormal.removeAll(datasNormal);
        normalAdapter.notifyDataSetChanged();


    }

}
