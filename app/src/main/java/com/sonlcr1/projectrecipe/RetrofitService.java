package com.sonlcr1.projectrecipe;

import com.sonlcr1.projectrecipe.member.HomeChoice;
import com.sonlcr1.projectrecipe.member.HomeSummer;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {


    @GET("/Recipe/recipeChoice/getChoice.php")
    Call<ArrayList<HomeChoice>> getChoiceArray();

    @GET("/Recipe/recipeSummer/getSummer.php")
    Call<ArrayList<HomeSummer>> getSummerArray();
}
