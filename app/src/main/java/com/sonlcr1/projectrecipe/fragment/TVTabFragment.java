package com.sonlcr1.projectrecipe.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sonlcr1.projectrecipe.R;
import com.sonlcr1.projectrecipe.adapter.TVAdapter;
import com.sonlcr1.projectrecipe.member.TV;

import java.util.ArrayList;

public class TVTabFragment extends Fragment {

    ArrayList<TV> datas = new ArrayList<>();

    TVAdapter tvAdapter;

    RecyclerView recyclerView;

    Context context;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tvtab,container,false);

        context = getContext();
        recyclerView = view.findViewById(R.id.recycle);

        datas.add(new TV(R.string.tvtitle_01,R.string.tvmsg_01,R.drawable.tv01));
        datas.add(new TV(R.string.tvtitle_02,R.string.tvmsg_02,R.drawable.tv02));
        datas.add(new TV(R.string.tvtitle_03,R.string.tvmsg_03,R.drawable.tv03));
        datas.add(new TV(R.string.tvtitle_04,R.string.tvmsg_04,R.drawable.tv04));
        datas.add(new TV(R.string.tvtitle_05,R.string.tvmsg_05,R.drawable.tv05));
        datas.add(new TV(R.string.tvtitle_06,R.string.tvmsg_06,R.drawable.tv06));

        tvAdapter = new TVAdapter(context,datas);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(tvAdapter);

        return view;
    }

    //viewPager.setOffscreenPageLimit(3); setofflimit로 메모리에서 삭제 하지 않고 유지시키기 때문에 ondestroyView 안해도 상관 없다.

}
