package com.sonlcr1.projectrecipe.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sonlcr1.projectrecipe.BoardEdit;
import com.sonlcr1.projectrecipe.R;
import com.sonlcr1.projectrecipe.adapter.BoardAdapter;
import com.sonlcr1.projectrecipe.member.Board;

import java.util.ArrayList;

public class BoardTabFragment extends Fragment {

    ArrayList<Board> datas = new ArrayList<>();

    RecyclerView recyclerView;
    BoardAdapter boardAdapter;
    Context context;

    FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_boardtab,container,false);

        fab = view.findViewById(R.id.fabBtn);

        recyclerView = view.findViewById(R.id.recycle);
        context = getContext();

        datas.add(new Board(null,"1","1","msg1",null,0,1,"1"));
        datas.add(new Board(null,"2","2","msg2",null,0,2,"2"));
        datas.add(new Board(null,"1","1","msg1",null,0,1,"1"));
        datas.add(new Board(null,"2","2","msg2",null,0,2,"2"));
        datas.add(new Board(null,"1","1","msg1",null,0,1,"1"));
        datas.add(new Board(null,"2","2","msg2",null,0,2,"2"));
        datas.add(new Board(null,"1","1","msg1",null,0,1,"1"));
        datas.add(new Board(null,"2","2","msg2",null,0,2,"2"));

        boardAdapter = new BoardAdapter(context,datas);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(boardAdapter);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,BoardEdit.class);
                startActivity(intent);
            }
        });
    }
}
