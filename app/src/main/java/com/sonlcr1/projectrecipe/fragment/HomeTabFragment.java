package com.sonlcr1.projectrecipe.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.sonlcr1.projectrecipe.R;
import com.sonlcr1.projectrecipe.RetrofitHelper;
import com.sonlcr1.projectrecipe.RetrofitService;
import com.sonlcr1.projectrecipe.adapter.HomeChoiceAdapter;
import com.sonlcr1.projectrecipe.adapter.HomeNormalAdapter;
import com.sonlcr1.projectrecipe.member.Recipe;
import com.sonlcr1.projectrecipe.recipeActivity.HomeChoiceRecipe;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeTabFragment extends Fragment {
    //hotfixthird

    String imgUrl = "http://jeondh9971.dothome.co.kr/Recipe";  //뒤에 주소 덧 붙여야함

    TextView tv, tvSub;
    ImageView iv1,iv2,iv3;

    Context mContext;
    FragmentActivity fragmentActivity;

    RecyclerView recyclerView;
    RecyclerView recyclergrid;
    RecyclerView recyclerView2;

    //ArrayList<Recipe> datas = new ArrayList<>();
    ArrayList<Recipe> datasChoice = new ArrayList<>();
    ArrayList<Recipe> datasSummer = new ArrayList<>();

    //ArrayList<HomeSummer> datasSummer = new ArrayList<>();
    ArrayList<Recipe> datasNormal = new ArrayList<>();


    HomeChoiceAdapter recyclerAdapter;
    HomeNormalAdapter normalAdapter;
    HomeNormalAdapter normalAdapter2;

    SwipeRefreshLayout refreshLayout;
    View view;

    ProgressDialog dialog;
    Resources resources;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_hometab,container,false);
        refreshLayout = view.findViewById(R.id.refresh);
        mContext = getContext();
        recyclergrid = view.findViewById(R.id.recy_grid);
        recyclerView = view.findViewById(R.id.recycle01);
        recyclerView2 = view.findViewById(R.id.recycle02);
        resources = getResources();

        getdata();

        //리프레쉬
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                datasChoice.removeAll(datasChoice);
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

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onStart() {
        super.onStart();

        //getdata();
    }

    void getdata(){
        AssetManager assetManager = mContext.getAssets();
        StringBuffer buffer = new StringBuffer();
        try {
            InputStream inputStream = assetManager.open("home_tab.json");
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(isr);

            while (true){
                String line = reader.readLine();
                if (line == null) break;
                buffer.append(line+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        Recipe[] recipes = gson.fromJson(buffer.toString(),Recipe[].class);
        for (int i=0;i<3;i++){
            datasChoice.add(recipes[i]);
        }
        for (int i=3;i<6;i++){
            datasSummer.add(recipes[i]);
        }
        for (int i=6;i<recipes.length;i++){
            datasNormal.add(recipes[i]);
        }

        //choice
        recyclerAdapter = new HomeChoiceAdapter(mContext,datasChoice,resources);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setOnFlingListener(null);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        //summer
        normalAdapter2 = new HomeNormalAdapter(mContext,datasSummer,resources);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(mContext,3);
        recyclerView2.setLayoutManager(layoutManager);
        recyclerView2.setAdapter(normalAdapter2);

        //normal
        normalAdapter = new HomeNormalAdapter(mContext,datasNormal,resources);
        RecyclerView.LayoutManager layoutManager2 = new GridLayoutManager(mContext,2);
        recyclergrid.setLayoutManager(layoutManager2);
        recyclergrid.setAdapter(normalAdapter);


    }

//    void getdata_ex(){
//        // Choice recyclerview
//        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
//        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
//        Call<ArrayList<Recipe>> call = retrofitService.getChoiceArray();
//        call.enqueue(new Callback<ArrayList<Recipe>>() {
//            @Override
//            public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {
//                if (response != null) {
//                    ArrayList<Recipe> items = response.body();
//
//                    for (int i=0;i<3;i++){
//                        datasChoice.add(items.get(i));
//                    }
//
//
//                    //Log.e("length",datas.get(0).img+", "+datas.get(1).img+", "+datas.get(2).img);
//
//
//
//
//                    //java.lang.IllegalStateException: An instance of OnFlingListener already set. 에러 수정하는 코드, setOnFlingListener(null);
//
//
//
//
//
//                    datasSummer.add(items.get(3));
//                    datasSummer.add(items.get(4));
//                    datasSummer.add(items.get(5));
//
//
//
//                    //summer img 클릭 리스너, iv3 fid는 위에 for문에서 참조됨
//                    iv1 = view.findViewById(R.id.iv_01);
//                    iv2 = view.findViewById(R.id.iv_02);
//
//                    iv1.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            sendPutExtra(datasSummer.get(0));
//                        }
//                    });
//
//                    iv2.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            sendPutExtra(datasSummer.get(1));
//                        }
//                    });
//
//                    iv3.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            sendPutExtra(datasSummer.get(2));
//                        }
//                    });
//
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {
//                new AlertDialog.Builder(context).setMessage(t.toString()).create().show();
//            }
//        }); //choice part...
//
////        datasNormal.add(new HomeNormal(R.string.normal_01_title,R.string.normal_01_msg,R.drawable.normal01));
////        datasNormal.add(new HomeNormal(R.string.normal_02_title,R.string.normal_02_msg,R.drawable.normal02));
////        datasNormal.add(new HomeNormal(R.string.normal_03_title,R.string.normal_03_msg,R.drawable.normal03));
////        datasNormal.add(new HomeNormal(R.string.normal_04_title,R.string.normal_04_msg,R.drawable.normal04));
////        datasNormal.add(new HomeNormal(R.string.normal_05_title,R.string.normal_05_msg,R.drawable.normal05));
////        datasNormal.add(new HomeNormal(R.string.normal_06_title,R.string.normal_06_msg,R.drawable.normal06));
////        datasNormal.add(new HomeNormal(R.string.normal_07_title,R.string.normal_07_msg,R.drawable.normal07));
////        datasNormal.add(new HomeNormal(R.string.normal_08_title,R.string.normal_08_msg,R.drawable.normal08));
//
//        AssetManager assetManager = getContext().getAssets();
//        StringBuffer buffer = new StringBuffer();
//        try {
//            InputStream is = assetManager.open("home_tab.json");
//            InputStreamReader isr = new InputStreamReader(is);
//            BufferedReader reader = new BufferedReader(isr);
//
//
//            while (true){
//                String line = reader.readLine();
//                if (line==null)break;
//                buffer.append(line+"\n");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Gson gson = new Gson();
//        Recipe[] recipes = gson.fromJson(buffer.toString(),Recipe[].class);
//        for (Recipe e : recipes){
//            datasNormal.add(e);
//
//        }
//
//        Log.e("aaa",datasNormal.get(0).firsttitle+", "+datasNormal.get(1).firstimg+", "+datasNormal.get(1).firsttitle);
//
//        normalAdapter = new HomeNormalAdapter(context,datasNormal);
//
//
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context,2);
//        recyclergrid.setLayoutManager(layoutManager);
//
//        recyclergrid.setAdapter(normalAdapter);
//
//        dialog.dismiss();
//    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        datasChoice.removeAll(datasChoice);
        recyclerAdapter.notifyDataSetChanged();

        datasSummer.removeAll(datasSummer);
        normalAdapter2.notifyDataSetChanged();

        datasNormal.removeAll(datasNormal);
        normalAdapter.notifyDataSetChanged();


    }



    void sendPutExtra(Recipe data){
        Intent intent = new Intent(mContext, HomeChoiceRecipe.class);
        intent.putExtra("firstsub",data.firstsub);
        intent.putExtra("firsttile",data.firsttitle);
        intent.putExtra("firstimg",data.firstimg);

        intent.putExtra("secondessential",data.secondessential);
        intent.putExtra("secondessentialmsg",data.secondessentialmsg);
        intent.putExtra("secondchoice",data.secondchoice);
        intent.putExtra("secondchoicemsg",data.secondchoicemsg);
        intent.putExtra("secondsource",data.secondsource);
        intent.putExtra("secondsourcemsg",data.secondsourcemsg);

        intent.putExtra("thirdimg",data.thirdimg);
        intent.putExtra("thirdmsg",data.thirdmsg);

        intent.putExtra("fourthimg",data.fourthimg);
        intent.putExtra("fourthmsg",data.fourthmsg);

        intent.putExtra("fifthimg",data.fifthimg);
        intent.putExtra("fifthmsg",data.fifthmsg);

        intent.putExtra("sixthimg",data.sixthimg);
        intent.putExtra("sixthmsg",data.sixthmsg);

        intent.putExtra("seventhimg",data.seventhimg);
        intent.putExtra("seventhmsg",data.seventhmsg);

        intent.putExtra("eighthimg",data.eighthimg);
        intent.putExtra("eighthmsg",data.eighthmsg);

        mContext.startActivity(intent);
    }
}
