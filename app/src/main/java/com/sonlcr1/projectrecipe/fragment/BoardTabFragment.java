package com.sonlcr1.projectrecipe.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sonlcr1.projectrecipe.recipeActivity.BoardEdit;
import com.sonlcr1.projectrecipe.R;
import com.sonlcr1.projectrecipe.RetrofitHelper;
import com.sonlcr1.projectrecipe.RetrofitService;
import com.sonlcr1.projectrecipe.adapter.BoardAdapter;
import com.sonlcr1.projectrecipe.member.Board;
import com.sonlcr1.projectrecipe.recipeActivity.KakaoLoginActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BoardTabFragment extends Fragment {


    ArrayList<Board> datas = new ArrayList<>();

    RecyclerView recyclerView;
    BoardAdapter boardAdapter;
    Context context;

    FloatingActionButton fab;

    SwipeRefreshLayout refreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_boardtab, container, false);

        fab = view.findViewById(R.id.fabBtn);
        refreshLayout = view.findViewById(R.id.refresh);

        recyclerView = view.findViewById(R.id.recycle);
        context = getContext();

        //서버에서 데이터 얻어 와서 recyclerview에 띄우기
        //activity();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KakaoLoginActivity.class);
                startActivity(intent);
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                datas.removeAll(datas);
                refreshLayout.setRefreshing(false);
            }
        });
    }

    void activity(){
        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<ArrayList<Board>> call = retrofitService.loadData();
        call.enqueue(new Callback<ArrayList<Board>>() {
            @Override
            public void onResponse(Call<ArrayList<Board>> call, Response<ArrayList<Board>> response) {
                if (response.isSuccessful()) {
                    ArrayList<Board> items = response.body();
                    for (Board e : items) {

                        datas.add(0, e);

                        boardAdapter = new BoardAdapter(context, datas);
                        boardAdapter.notifyDataSetChanged();

                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(boardAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Board>> call, Throwable t) {

            }
        });

//        TextView tv = view.findViewById(R.id.tv);
//        ImageView iv = view.findViewById(R.id.iv);
//        tv.setText(datas.get(0).msg);
//        Glide.with(context).load(imgUrl+datas.get(0).imgmain).into(iv);
//
//        boardAdapter = new BoardAdapter(context,datas);
//        boardAdapter.notifyDataSetChanged();
//
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context,RecyclerView.VERTICAL,false);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(boardAdapter);
    }

}
