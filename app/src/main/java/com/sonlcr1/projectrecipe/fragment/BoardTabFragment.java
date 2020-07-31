package com.sonlcr1.projectrecipe.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.sonlcr1.projectrecipe.recipeActivity.BoardEdit;
import com.sonlcr1.projectrecipe.R;
import com.sonlcr1.projectrecipe.RetrofitHelper;
import com.sonlcr1.projectrecipe.RetrofitService;
import com.sonlcr1.projectrecipe.adapter.BoardAdapter;
import com.sonlcr1.projectrecipe.member.Board;
import com.sonlcr1.projectrecipe.recipeActivity.KakaoLoginActivity;

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
    RelativeLayout loginLayout;
    private FirebaseAuth mAuth;
    private SignInButton signInButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_boardtab, container, false);

        fab = view.findViewById(R.id.fabBtn);
        refreshLayout = view.findViewById(R.id.refresh);
        loginLayout = (RelativeLayout)getLayoutInflater().inflate(R.layout.login_dialog,null);


        recyclerView = view.findViewById(R.id.recycle);
        context = getContext();

        //서버에서 데이터 얻어 와서 recyclerview에 띄우기
        activity();



        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth = FirebaseAuth.getInstance();
                //로그인 최초 1회 하면 후에 다시 어플 실행시 자동로그인
                if (mAuth.getCurrentUser() != null) {
                    Intent intent = new Intent(context, BoardEdit.class);
                    startActivity(intent);
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("로그인");
                    builder.setMessage("다양한 서비스 혜택을 위해 로그인하세요!");
                    if (loginLayout.getParent() != null)
                        ((ViewGroup) loginLayout.getParent()).removeView(loginLayout);
                    builder.setView(loginLayout);
                    signInButton = loginLayout.findViewById(R.id.btn);
                    signInButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            trylogin();
                        }
                    });

                    builder.create().show();
                }


            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                datas.removeAll(datas);
                activity();
                refreshLayout.setRefreshing(false);
            }
        });
    }

    void activity(){
        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<ArrayList<Board>> call = retrofitService.loadData();
        call.enqueue(new Callback<ArrayList<Board>>() {
            @Override
            public void onResponse(Call<ArrayList<Board>> call, Response<ArrayList<Board>> response) {
                if (response.isSuccessful()) {
                    ArrayList<Board> items = response.body();
                    for (Board e : items) {

                        datas.add(0, e);

                        boardAdapter = new BoardAdapter(context, datas);
                        boardAdapter.notifyDataSetChanged();

                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(boardAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Board>> call, Throwable t) {

            }
        });

    }

    public void trylogin(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }

}
