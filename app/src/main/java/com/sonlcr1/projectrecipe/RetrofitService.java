package com.sonlcr1.projectrecipe;

import com.sonlcr1.projectrecipe.member.Choice;
import com.sonlcr1.projectrecipe.member.Summer;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {


    @GET("/Recipe/recipeChoice/getChoice.php")
    Call<ArrayList<Choice>> getChoiceArray();

    @GET("/Recipe/recipeSummer/getSummer.php")
    Call<ArrayList<Summer>> getSummerArray();
}
