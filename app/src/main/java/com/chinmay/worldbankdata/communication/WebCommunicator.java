package com.chinmay.worldbankdata.communication;

import com.chinmay.worldbankdata.pojo.Catalog;
import com.chinmay.worldbankdata.pojo.CatalogMessageEvent;
import com.chinmay.worldbankdata.pojo.MessageEvent;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class WebCommunicator {
    private static final String API_BASE = "http://api.worldbank.org/v2/";
    private static Retrofit retrofit;

    public static void getAllCatalog() {
        Retrofit retrofit = getRetrofitBuild();
        ApiService requestInterface = retrofit.create(ApiService.class);
        Call<Catalog> call = requestInterface.getTopCatalog();
        call.enqueue(new Callback<Catalog>() {
            @Override
            public void onResponse(Call<Catalog> call, Response<Catalog> response) {
                EventBus.getDefault().post(new CatalogMessageEvent(MessageEvent.SUCCESS, response.body()));
            }

            @Override
            public void onFailure(Call<Catalog> call, Throwable t) {
                EventBus.getDefault().post(new CatalogMessageEvent(MessageEvent.FAIL, null));
            }
        });
    }

    private static Retrofit getRetrofitBuild() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(API_BASE)
                    .addConverterFactory(GsonConverterFactory.create(getGsonInstance()))
                    .callbackExecutor(Executors.newSingleThreadExecutor())
                    .build();
        }
        return retrofit;
    }

    private static Gson getGsonInstance() {
        return new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return false;
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        }).create();
    }
}
