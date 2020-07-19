package com.sonlcr1.projectrecipe.recipeActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sonlcr1.projectrecipe.R;
import com.sonlcr1.projectrecipe.adapter.HomeNormalAdapter;
import com.sonlcr1.projectrecipe.adapter.ThemaRecipeAdapter;
import com.sonlcr1.projectrecipe.member.ThemaIcon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ThemaActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView tv;
    RecyclerView recyclerView;
    ArrayList<ThemaIcon2.Apple> lists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thema);

        tv = findViewById(R.id.tv_title);
        tv.setText(getIntent().getExtras().getString("title"));

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        recyclerView = findViewById(R.id.recycle);

        lists = (ArrayList<ThemaIcon2.Apple>)getIntent().getSerializableExtra("list");
        ThemaRecipeAdapter adapter = new ThemaRecipeAdapter(this,lists,getResources());
        recyclerView.setAdapter(adapter);

        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);








    }//onCreate...

    void getdata(){
        AssetManager assetManager = getAssets();
        StringBuffer buffer = new StringBuffer();
        try {
            InputStream is = assetManager.open("thema_list_recipe.json");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);

            while (true){
                String line = reader.readLine();
                if (line==null) break;
                buffer.append(line+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        ThemaIcon2[] themaIcon2 = gson.fromJson(buffer.toString(), ThemaIcon2[].class);






    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
