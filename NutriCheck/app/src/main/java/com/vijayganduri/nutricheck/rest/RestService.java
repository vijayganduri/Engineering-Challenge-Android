package com.vijayganduri.nutricheck.rest;

import com.vijayganduri.nutricheck.model.Food;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by vganduri on 8/2/2015.
 */
public interface RestService {

    @GET("/food/search")
    List<Food> getFoodByQuery(@Query("q") String query);

    @GET("/food/search")
    void getFoodByQuery(@Query("q") String query, Callback<List<Food>> cb);

}
