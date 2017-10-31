package com.chinmay.worldbankdata.communication;

import com.chinmay.worldbankdata.POJO.Catalog;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by chinmaydeshpande on 30/10/17.
 */
public interface ApiService {

    @GET("datacatalog?format=json")
    Call<Catalog> getTopCatalog();


/*    @GET("item/{storyid}.json")
    Call<StoryResponse> getStoryDetails(@Path("storyid") String storyid);

    @GET("autocomplete/json")
    Call<AutoCompleteGooglePlaces> getAutoCompleteResults(@Query("key") String API_KEY,
                                                          @Query("input") String encode);

    @GET("nearbysearch/json")
    Call<GetPlacesResponse> getPlaceDetails(@Query("location") String location,
                                            @Query("radius") int radius,
                                            @Query("key") String key);*/
}