package com.example.catfacts3;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class CatFactRepo {
    private final String BASE_URL = "https://cat-fact.herokuapp.com/";
    Retrofit retrofit;

    public CatFactRepo() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public void requestCatFact(Callback<CatFact> callback) {
        CatFactWebApi webApi = retrofit.create(CatFactWebApi.class);
        Call<CatFact> call = webApi.getFact();
        call.enqueue(callback);
    }
}
interface CatFactWebApi {
    @GET("facts/random")
    Call<CatFact> getFact();
}