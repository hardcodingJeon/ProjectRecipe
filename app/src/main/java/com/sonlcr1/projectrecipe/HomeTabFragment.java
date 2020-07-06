package com.sonlcr1.projectrecipe;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.sonlcr1.projectrecipe.adapter.RecyclerAdapter;
import com.sonlcr1.projectrecipe.member.Choice;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeTabFragment extends Fragment {

    //HomeAdapter1 homeAdapter1;
    RecyclerView recyclerView;
    ArrayList<Choice> datas = new ArrayList<>();
    Context context;
    RecyclerAdapter recyclerAdapter;
    FragmentActivity fragmentActivity;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hometab,container,false);

        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<ArrayList<Choice>> call = retrofitService.getChoiceArray();
        call.enqueue(new Callback<ArrayList<Choice>>() {
            @Override
            public void onResponse(Call<ArrayList<Choice>> call, Response<ArrayList<Choice>> response) {
                ArrayList<Choice> items = response.body();
                for (Choice e : items){
                    datas.add(e);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Choice>> call, Throwable t) {

            }
        });

        fragmentActivity = getActivity();
        context = getContext();
        recyclerView = fragmentActivity.findViewById(R.id.recycle01);
        recyclerAdapter = new RecyclerAdapter(context,datas);
        recyclerView.setAdapter(recyclerAdapter);


        return view;
    }
}
