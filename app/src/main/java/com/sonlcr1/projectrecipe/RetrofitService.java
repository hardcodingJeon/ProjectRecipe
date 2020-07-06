package com.sonlcr1.projectrecipe;

import com.sonlcr1.projectrecipe.member.Choice;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {


    @GET("/Recipe/recipeChoice/getChoice.php")
    Call<ArrayList<Choice>> getChoiceArray();
}
