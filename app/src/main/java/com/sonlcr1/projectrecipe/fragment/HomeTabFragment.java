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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.sonlcr1.projectrecipe.R;
import com.sonlcr1.projectrecipe.RetrofitHelper;
import com.sonlcr1.projectrecipe.RetrofitService;
import com.sonlcr1.projectrecipe.adapter.HomeChoiceAdapter;
import com.sonlcr1.projectrecipe.adapter.HomeNormalAdapter;
import com.sonlcr1.projectrecipe.member.HomeChoice;
import com.sonlcr1.projectrecipe.member.HomeNormal;
import com.sonlcr1.projectrecipe.member.HomeSummer;
import com.sonlcr1.projectrecipe.member.Recipe;
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

    ArrayList<Recipe> datas = new ArrayList<>();
    ArrayList<HomeSummer> datasSummer = new ArrayList<>();
    ArrayList<HomeNormal> datasNormal = new ArrayList<>();


    HomeChoiceAdapter recyclerAdapter;
    HomeNormalAdapter normalAdapter;

    SwipeRefreshLayout refreshLayout;
    View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_hometab,container,false);

        refreshLayout = view.findViewById(R.id.refresh);

        //fragmentActivity = getActivity();
        context = getContext();

        //normal part
        recyclergrid = view.findViewById(R.id.grid);


        getdata();


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        //리프레쉬
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                datas.removeAll(datas);
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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        datas.removeAll(datas);
        recyclerAdapter.notifyDataSetChanged();

        datasNormal.removeAll(datasNormal);
        normalAdapter.notifyDataSetChanged();


    }

    void getdata(){
        // Choice recyclerview
        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<ArrayList<Recipe>> call = retrofitService.getChoiceArray();
        call.enqueue(new Callback<ArrayList<Recipe>>() {
            @Override
            public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {
                if (response != null) {
                    ArrayList<Recipe> items = response.body();

                    datas.addAll(items);

                    //Log.e("length",datas.get(0).img+", "+datas.get(1).img+", "+datas.get(2).img);

                    recyclerView = view.findViewById(R.id.recycle01);
                    recyclerAdapter = new HomeChoiceAdapter(context,datas);
                    recyclerAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(recyclerAdapter);

                    //java.lang.IllegalStateException: An instance of OnFlingListener already set. 에러 수정하는 코드, setOnFlingListener(null);
                    recyclerView.setOnFlingListener(null);

                    SnapHelper snapHelper = new PagerSnapHelper();
                    snapHelper.attachToRecyclerView(recyclerView);

                }

            }

            @Override
            public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {
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

                    tvSub = view.findViewById(R.id.tv_sub);
                    tvSub.setText(datasSummer.size()+"개의 레시피");

                    for (int i=0 ; i<3 ; i++){
                        iv = view.findViewById(R.id.iv_01+i);
                        //Glide.with(context).load(imgUrl+"/recipeSummer/"+datasSummer.get(i).img).into(iv);
                        Glide.with(view).load(imgUrl+"/recipeSummer/"+datasSummer.get(i).img).into(iv);
                        //Picasso.get().load(imgUrl+"/recipeSummer/"+datasSummer.get(i).img).into(iv);


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
    }
}
