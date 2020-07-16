package com.sonlcr1.projectrecipe.fragment;

import android.content.Context;
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
import com.sonlcr1.projectrecipe.member.Recipe;

import java.util.ArrayList;

public class RecipePagerFragmentExplain extends Fragment {

    FragmentActivity activity;
    ArrayList<Recipe> datas = new ArrayList<>();
    RecyclerView recyclerView;
    RecipeAdapter adapter;
    Context context;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //여기에 리싸이클러뷰 들어갈것
        View view = inflater.inflate(R.layout.fragment_recipe_explain,container,false);
        activity = getActivity();
        recyclerView = view.findViewById(R.id.recycle);
        context = getContext();

        String thirdimg = activity.getIntent().getStringExtra("thirdimg");
        String thirdmsg = activity.getIntent().getStringExtra("thirdmsg");
        String fourthimg = activity.getIntent().getStringExtra("fourthimg");
        String fourthmsg = activity.getIntent().getStringExtra("fourthmsg");
        String fifthimg = activity.getIntent().getStringExtra("fifthimg");
        String fifthmsg = activity.getIntent().getStringExtra("fifthmsg");
        String sixthimg = activity.getIntent().getStringExtra("sixthimg");
        String sixthmsg = activity.getIntent().getStringExtra("sixthmsg");
        String seventhimg = activity.getIntent().getStringExtra("seventhimg");
        String seventhmsg = activity.getIntent().getStringExtra("seventhmsg");
        String eighthimg = activity.getIntent().getStringExtra("eighthimg");
        String eighthmsg = activity.getIntent().getStringExtra("eighthmsg");

        //third에 데이터 add하기
        datas.add(new Recipe(null,null,null,null,null,null,null,null,null,thirdimg,thirdmsg,null,null,null,null,null,null,null,null,null,null));
        datas.add(new Recipe(null,null,null,null,null,null,null,null,null,fourthimg,fourthmsg,null,null,null,null,null,null,null,null,null,null));
        datas.add(new Recipe(null,null,null,null,null,null,null,null,null,fifthimg,fifthmsg,null,null,null,null,null,null,null,null,null,null));
        datas.add(new Recipe(null,null,null,null,null,null,null,null,null,sixthimg,sixthmsg,null,null,null,null,null,null,null,null,null,null));
        datas.add(new Recipe(null,null,null,null,null,null,null,null,null,seventhimg,seventhmsg,null,null,null,null,null,null,null,null,null,null));
        datas.add(new Recipe(null,null,null,null,null,null,null,null,null,eighthimg,eighthmsg,null,null,null,null,null,null,null,null,null,null));

        if (!thirdimg.equals("")) {
            adapter = new RecipeAdapter(context,datas);
            recyclerView.setAdapter(adapter);
            SnapHelper snapHelper = new PagerSnapHelper();
            snapHelper.attachToRecyclerView(recyclerView);
        }
        //todo : 탈취제 3번째 프래그먼트 나오는거 수정, 김찌개 recyclerview 생성만 되고 파괴는 되지 않아서 데이터중첩됨

        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        datas.removeAll(datas);
        adapter.notifyDataSetChanged();

    }
}
