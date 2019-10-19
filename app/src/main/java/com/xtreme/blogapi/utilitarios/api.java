package com.xtreme.blogapi.utilitarios;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class api {

    private static String url = "http://itla.hectorvent.com/api/";

    public static Retrofit getApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
