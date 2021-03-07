package com.example.filmflix.api.omdb;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OMDBApiService {

    public static Retrofit getRetrofitInstance(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Contract.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static OMDBApiEndpoint apiInterface(){
        OMDBApiEndpoint apiService = getRetrofitInstance().create(OMDBApiEndpoint.class);
        return apiService;
    }
}
