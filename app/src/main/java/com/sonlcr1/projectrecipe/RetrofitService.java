package com.sonlcr1.projectrecipe;

import com.sonlcr1.projectrecipe.member.Board;
import com.sonlcr1.projectrecipe.member.HomeChoice;
import com.sonlcr1.projectrecipe.member.HomeSummer;

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


    @GET("/Recipe/recipeChoice/getChoice.php")
    Call<ArrayList<HomeChoice>> getChoiceArray();

    @GET("/Recipe/recipeSummer/getSummer.php")
    Call<ArrayList<HomeSummer>> getSummerArray();

    @Multipart
    @POST("/Recipe/recipeBoardEdit/recipeBoardInsert.php")
    Call<String> insertData(@PartMap Map<String,String> datapart, @Part MultipartBody.Part filepart);
}
//todo : 등록을 누르면 서버에 올린후 바로 내려 받는 php할 차례
