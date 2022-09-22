package com.shubham.wallfusion.retrofit;

import static com.shubham.wallfusion.retrofit.ApiUtilities.API_KEY;

import com.shubham.wallfusion.models.SearchModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiInterface {

    String BASE_URL = "https://api.pexels.com/v1/";

    @Headers("Authorization: "+API_KEY)
    @GET("curated")
    Call<SearchModel> getImage(
            @Query("page") int page,
            @Query("per_page") int per_page
    );

    @Headers("Authorization: "+API_KEY)
    @GET("search")
    Call<SearchModel> getSearchImage(
            @Query("query") String query,
            @Query("page") int page,
            @Query("per_page") int per_page
    );

}
