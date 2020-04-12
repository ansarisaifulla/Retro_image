package com.example.retro_image;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonApi {

    @GET("http://www.json-generator.com/api/json/get/ckxBoNKjyq?indent=2")
    Call<List<model>> getModels();
}
