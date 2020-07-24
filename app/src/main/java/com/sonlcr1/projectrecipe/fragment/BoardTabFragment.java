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

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BoardEdit.class);
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

}
