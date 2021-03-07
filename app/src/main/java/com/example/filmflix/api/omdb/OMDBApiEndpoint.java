package com.example.filmflix.api.omdb;

import com.example.filmflix.api.omdb.model.OMDBResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface OMDBApiEndpoint {

    @GET("/")
    Call<OMDBResponse> searchOMDB(@QueryMap Map<String, String> options);
}
