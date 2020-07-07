package com.sonlcr1.projectrecipe.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
import com.sonlcr1.projectrecipe.RetrofitHelper;
import com.sonlcr1.projectrecipe.RetrofitService;
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

        fragmentActivity = getActivity();
        context = getContext();

        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<ArrayList<Choice>> call = retrofitService.getChoiceArray();
        call.enqueue(new Callback<ArrayList<Choice>>() {
            @Override
            public void onResponse(Call<ArrayList<Choice>> call, Response<ArrayList<Choice>> response) {
                if (response != null) {
                    ArrayList<Choice> items = response.body();
                    datas.addAll(items);

                    Log.e("length",datas.get(0).img+", "+datas.get(1).img+", "+datas.get(2).img);

                    recyclerView = fragmentActivity.findViewById(R.id.recycle01);
                    recyclerAdapter = new RecyclerAdapter(context,datas);
                    recyclerView.setAdapter(recyclerAdapter);

                    SnapHelper snapHelper = new PagerSnapHelper();
                    snapHelper.attachToRecyclerView(recyclerView);

                }

                //Log.e("length",items.get(0).title+", "+items.get(1).title+", "+items.get(2).title);

                Log.e("length1",datas.size()+", ");

            }

            @Override
            public void onFailure(Call<ArrayList<Choice>> call, Throwable t) {

            }
        });
        //Log.e("length",datas.get(0).title+", "+datas.get(1).title+", "+datas.get(2).title);




        //Log.e("tag",datas.get(0).title+", "+datas.get(1).title+", "+datas.get(2).title);


        return view;
    }
}
