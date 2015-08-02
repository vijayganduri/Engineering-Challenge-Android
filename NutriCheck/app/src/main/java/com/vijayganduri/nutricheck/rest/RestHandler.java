package com.vijayganduri.nutricheck.rest;

import com.vijayganduri.nutricheck.Config;
import com.vijayganduri.nutricheck.model.Food;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;

/**
 * Created by vganduri on 8/2/2015.
 */
public class RestHandler {

    private static RestHandler ourInstance = new RestHandler();

    private RestService service;

    public static RestHandler getInstance() {
        return ourInstance;
    }

    private RestHandler() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Config.BASE_URL)
                .build();
        service = restAdapter.create(RestService.class);
    }

    public List<Food> getFoodByQuery(String query){
      return service.getFoodByQuery(query);
    }

    public void getFoodByQuery(String query, Callback<List<Food>> cb){
        service.getFoodByQuery(query, cb);
    }

}