package com.sonlcr1.projectrecipe;

import com.sonlcr1.projectrecipe.member.Board;
import com.sonlcr1.projectrecipe.member.HomeChoice;
import com.sonlcr1.projectrecipe.member.HomeSummer;
import com.sonlcr1.projectrecipe.member.Recipe;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface RetrofitService {


//    @GET("/Recipe/recipeChoice/getChoice.php")
//    Call<ArrayList<HomeChoice>> getChoiceArray();

    @GET("/Recipe/recipeData/getRecipe.php")
    Call<ArrayList<Recipe>> getChoiceArray();

    @GET("/Recipe/recipeSummer/getSummer.php")
    Call<ArrayList<HomeSummer>> getSummerArray();

    @Multipart
    @POST("/Recipe/recipeBoardEdit/recipeBoardInsert.php")
    Call<String> insertData(@PartMap Map<String,String> datapart, @Part MultipartBody.Part filepart);

    @GET("/Recipe/recipeBoardEdit/recipeBoardLoad.php")
    Call<ArrayList<Board>> loadData();

    @POST("/Recipe/recipeData/getRecipe.php")
    Call<Recipe> getRecipe();
}

