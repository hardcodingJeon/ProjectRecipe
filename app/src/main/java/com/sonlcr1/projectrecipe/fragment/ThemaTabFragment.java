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
import com.sonlcr1.projectrecipe.adapter.ThemaIconAdapter;
import com.sonlcr1.projectrecipe.member.ThemaIcon;

import java.util.ArrayList;

public class ThemaTabFragment extends Fragment {

    ArrayList<ThemaIcon> datas = new ArrayList<>();

    ThemaIconAdapter iconAdapter;
    RecyclerView recyclerView;
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thematab,container,false);

        context = getContext();
        recyclerView = view.findViewById(R.id.recycle);

        datas.add(new ThemaIcon(R.string.icontitle_01,R.drawable.a01juice));
        datas.add(new ThemaIcon(R.string.icontitle_02,R.drawable.a02desert));
        datas.add(new ThemaIcon(R.string.icontitle_03,R.drawable.a03beer));
        datas.add(new ThemaIcon(R.string.icontitle_04,R.drawable.a04rice));
        datas.add(new ThemaIcon(R.string.icontitle_05,R.drawable.a05ggigae));
        datas.add(new ThemaIcon(R.string.icontitle_06,R.drawable.a06guk));
        datas.add(new ThemaIcon(R.string.icontitle_07,R.drawable.a07banchan));
        datas.add(new ThemaIcon(R.string.icontitle_08,R.drawable.a08kimchijangacci));
        datas.add(new ThemaIcon(R.string.icontitle_09,R.drawable.a09bokkem));
        datas.add(new ThemaIcon(R.string.icontitle_10,R.drawable.a10nuddle));

        iconAdapter = new ThemaIconAdapter(context,datas);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context,2);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(iconAdapter);


        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        datas.removeAll(datas);
        iconAdapter.notifyDataSetChanged();
    }
}
