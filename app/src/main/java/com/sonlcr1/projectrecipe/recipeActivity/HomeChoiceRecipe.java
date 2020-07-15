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
import com.sonlcr1.projectrecipe.adapter.HomeChoiceRecipeAdapter;
import com.sonlcr1.projectrecipe.member.Recipe;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeChoiceRecipe extends AppCompatActivity {

    //이거 집에서 받아지나
    ImageView ivback;
    ViewPager viewPager;
    Recipe data;


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

        getdata();




    }//onCreate....

    void getdata(){
        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<Recipe> call = retrofitService.getRecipe();    //여기에 레시피 제목 데이터를 파라미터로 넣고 이용해 그 줄의 데이터를 읽어 올것임.
        call.enqueue(new Callback<Recipe>() {
            @Override
            public void onResponse(Call<Recipe> call, Response<Recipe> response) {
                if (response.isSuccessful()) {
                    //이미 VO클래스 멤버 변수에 값 들어감
                    Recipe data = response.body();
                    Log.e("Recipe",""+data.firstimg+", "+data.firstsub+", "+data.firsttitle);

                }
            }

            @Override
            public void onFailure(Call<Recipe> call, Throwable t) {
                Log.e("Recipefail","retrofit fail");
            }
        });
    }
}
