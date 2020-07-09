package com.sonlcr1.projectrecipe.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.sonlcr1.projectrecipe.R;
import com.sonlcr1.projectrecipe.RetrofitHelper;
import com.sonlcr1.projectrecipe.RetrofitService;
import com.sonlcr1.projectrecipe.adapter.HomeChoiceAdapter;
import com.sonlcr1.projectrecipe.adapter.HomeNormalAdapter;
import com.sonlcr1.projectrecipe.member.HomeChoice;
import com.sonlcr1.projectrecipe.member.HomeNormal;
import com.sonlcr1.projectrecipe.member.HomeSummer;
import com.squareup.picasso.Picasso;

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

    Context context;
    FragmentActivity fragmentActivity;

    RecyclerView recyclerView;
    RecyclerView recyclergrid;

    ArrayList<HomeChoice> datas = new ArrayList<>();
    ArrayList<HomeSummer> datasSummer = new ArrayList<>();
    ArrayList<HomeNormal> datasNormal = new ArrayList<>();


    HomeChoiceAdapter recyclerAdapter;
    HomeNormalAdapter normalAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hometab,container,false);

        //fragmentActivity = getActivity();
        context = getContext();

        // Choice recyclerview
        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<ArrayList<HomeChoice>> call = retrofitService.getChoiceArray();
        call.enqueue(new Callback<ArrayList<HomeChoice>>() {
            @Override
            public void onResponse(Call<ArrayList<HomeChoice>> call, Response<ArrayList<HomeChoice>> response) {
                if (response != null) {
                    ArrayList<HomeChoice> items = response.body();
                    datas.addAll(items);

                    Log.e("length",datas.get(0).img+", "+datas.get(1).img+", "+datas.get(2).img);

                    recyclerView = view.findViewById(R.id.recycle01);
                    recyclerAdapter = new HomeChoiceAdapter(context,datas);
                    recyclerView.setAdapter(recyclerAdapter);

                    SnapHelper snapHelper = new PagerSnapHelper();
                    snapHelper.attachToRecyclerView(recyclerView);

                }

            }

            @Override
            public void onFailure(Call<ArrayList<HomeChoice>> call, Throwable t) {
                Toast.makeText(context, "트래픽 사용량 초과", Toast.LENGTH_SHORT).show();
            }
        }); //choice part...

        //summer part
        Call<ArrayList<HomeSummer>> call2 = retrofitService.getSummerArray();
        call2.enqueue(new Callback<ArrayList<HomeSummer>>() {
            @Override
            public void onResponse(Call<ArrayList<HomeSummer>> call, Response<ArrayList<HomeSummer>> response) {
                if (response != null) {
                    ArrayList<HomeSummer> item = response.body();
                    datasSummer.addAll(item);

                    tvSub = getActivity().findViewById(R.id.tv_sub);
                    tvSub.setText(datasSummer.size()+"개의 레시피");

                    for (int i=0 ; i<3 ; i++){
                        iv = view.findViewById(R.id.iv_01+i);
                        //Glide.with(context).load(imgUrl+"/recipeSummer/"+datasSummer.get(i).img).into(iv);
                        //Glide.with(context).load(R.drawable.moana).into(iv);
                        Picasso.get().load(imgUrl+"/recipeSummer/"+datasSummer.get(i).img).into(iv);


                        Log.e("url",imgUrl+"/recipeSummer/"+datasSummer.get(i).img);

                        tv = view.findViewById(R.id.tv_01+i);
                        tv.setText(datasSummer.get(i).title);
                    }

                }
            }

            @Override
            public void onFailure(Call<ArrayList<HomeSummer>> call, Throwable t) {

            }
        }); //summer part....

        //normal part
        recyclergrid = view.findViewById(R.id.grid);
        if (recyclergrid != null){
            datasNormal.add(new HomeNormal(R.string.normal_01_title,R.string.normal_01_msg,R.drawable.normal01));
            datasNormal.add(new HomeNormal(R.string.normal_02_title,R.string.normal_02_msg,R.drawable.normal02));
            datasNormal.add(new HomeNormal(R.string.normal_03_title,R.string.normal_03_msg,R.drawable.normal03));
            datasNormal.add(new HomeNormal(R.string.normal_04_title,R.string.normal_04_msg,R.drawable.normal04));
            datasNormal.add(new HomeNormal(R.string.normal_05_title,R.string.normal_05_msg,R.drawable.normal05));
            datasNormal.add(new HomeNormal(R.string.normal_06_title,R.string.normal_06_msg,R.drawable.normal06));
            datasNormal.add(new HomeNormal(R.string.normal_07_title,R.string.normal_07_msg,R.drawable.normal07));
            datasNormal.add(new HomeNormal(R.string.normal_08_title,R.string.normal_08_msg,R.drawable.normal08));

            normalAdapter = new HomeNormalAdapter(context,datasNormal);

            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context,2);
            recyclergrid.setLayoutManager(layoutManager);

            recyclergrid.setAdapter(normalAdapter);
        }else Toast.makeText(context, "recyclergrid null point!!", Toast.LENGTH_SHORT).show();







        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        datas.removeAll(datas);
        recyclerAdapter.notifyDataSetChanged();

        datasNormal.removeAll(datasNormal);
        normalAdapter.notifyDataSetChanged();
    }
}
