package com.sonlcr1.projectrecipe.fragment;

import android.content.Context;
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
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.bumptech.glide.Glide;
import com.sonlcr1.projectrecipe.R;
import com.sonlcr1.projectrecipe.RetrofitHelper;
import com.sonlcr1.projectrecipe.RetrofitService;
import com.sonlcr1.projectrecipe.adapter.RecyclerAdapter;
import com.sonlcr1.projectrecipe.member.Choice;
import com.sonlcr1.projectrecipe.member.Summer;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeTabFragment extends Fragment {
    //dothome

    String imgUrl = "http://jeondh9971.dothome.co.kr/Recipe";  //뒤에 주소 덧 붙여야함

    TextView tv, tvSub;
    ImageView iv;

    //HomeAdapter1 homeAdapter1;
    RecyclerView recyclerView;
    ArrayList<Choice> datas = new ArrayList<>();
    ArrayList<Summer> datasSummer = new ArrayList<>();
    Context context;
    RecyclerAdapter recyclerAdapter;
    FragmentActivity fragmentActivity;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hometab,container,false);

        fragmentActivity = getActivity();
        context = getContext();

        // Choice recyclerview
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

            }

            @Override
            public void onFailure(Call<ArrayList<Choice>> call, Throwable t) {

            }
        });

        Call<ArrayList<Summer>> call2 = retrofitService.getSummerArray();
        call2.enqueue(new Callback<ArrayList<Summer>>() {
            @Override
            public void onResponse(Call<ArrayList<Summer>> call, Response<ArrayList<Summer>> response) {
                if (response != null) {
                    ArrayList<Summer> item = response.body();
                    datasSummer.addAll(item);

                    tvSub = getActivity().findViewById(R.id.tv_sub);
                    tvSub.setText(datasSummer.size()+"개의 레시피");

                    for (int i=0 ; i<3 ; i++){
                        iv = fragmentActivity.findViewById(R.id.iv_01+i);
                        Glide.with(context).load(imgUrl+"/recipeSummer/"+datasSummer.get(i).img).into(iv);
                        //Glide.with(context).load(R.drawable.moana).into(iv);


                        Log.e("url",imgUrl+"/recipeSummer/"+datasSummer.get(i).img);

                        tv = fragmentActivity.findViewById(R.id.tv_01+i);
                        tv.setText(datasSummer.get(i).title);
                    }

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Summer>> call, Throwable t) {

            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        datas.removeAll(datas);
        recyclerAdapter.notifyDataSetChanged();
    }
}
