package com.sonlcr1.projectrecipe.recipeActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.sonlcr1.projectrecipe.R;
import com.sonlcr1.projectrecipe.RetrofitHelper;
import com.sonlcr1.projectrecipe.RetrofitService;
import com.sonlcr1.projectrecipe.adapter.HomeChoiceRecipeAdapter2;
import com.sonlcr1.projectrecipe.adapter.HomeChoiceRecipeAdapter3;
import com.sonlcr1.projectrecipe.member.Recipe;
import com.sonlcr1.projectrecipe.member.Recipe2;
import com.sonlcr1.projectrecipe.member.ThemaIcon2;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RecipeActivity extends AppCompatActivity {

    //이거 집에서 받아지나
    ImageView ivback;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_choice_recipe);

        viewPager = findViewById(R.id.viewpager);

        ivback = findViewById(R.id.iv_back);
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        ThemaIcon2.Apple list = (ThemaIcon2.Apple)getIntent().getSerializableExtra("list");

        if (list.recipe == null){
            HomeChoiceRecipeAdapter2 recipeAdapter2 = new HomeChoiceRecipeAdapter2(getSupportFragmentManager());
            viewPager.setAdapter(recipeAdapter2);
        }else{
            HomeChoiceRecipeAdapter3 recipeAdapter3 = new HomeChoiceRecipeAdapter3(getSupportFragmentManager());
            viewPager.setAdapter(recipeAdapter3);
        }

    }//onCreate....

}
