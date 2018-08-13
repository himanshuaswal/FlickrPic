package com.atg.gautam.flickerpic.network;

import com.atg.gautam.flickerpic.model.FlickrResponse;
import com.atg.gautam.flickerpic.model.Image;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*
 ** Created by Gautam Krishnan {@link https://github.com/GautiKrish}
 */public interface RetrofitAPI {

    String BASE_URL = "https://api.flickr.com/services/";

    @GET("rest")
    Call<FlickrResponse> getImages(@Query("method") String methodName, @Query("api_key") String API_KEY, @Query("format") String format, @Query("nojsoncallback") int value, @Query("extras") String urlS);
    @GET("rest")
    Call<FlickrResponse> getSearchQueryImages(@Query("method") String methodName, @Query("api_key") String API_KEY, @Query("format") String format, @Query("nojsoncallback") int value, @Query("extras") String urlS,@Query("text") String userSearchText);
}
