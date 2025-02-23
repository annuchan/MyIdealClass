package com.example.myidealclass;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class RetrofitClient {
    private static Retrofit retrofit;
    private static final String BASE_URL = "http://192.168.0.2:8443/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public static ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class);
    }
}
