package com.sonlcr1.projectrecipe.recipeActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sonlcr1.projectrecipe.R;
import com.sonlcr1.projectrecipe.adapter.HomeChoiceRecipeAdapter;

public class HomeChoiceRecipe extends AppCompatActivity {

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

        HomeChoiceRecipeAdapter recipeAdapter = new HomeChoiceRecipeAdapter(getSupportFragmentManager());
        viewPager.setAdapter(recipeAdapter);



    }//onCreate....
}
