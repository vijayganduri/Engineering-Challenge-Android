package com.vijayganduri.nutricheck.rest;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.vijayganduri.nutricheck.Config;
import com.vijayganduri.nutricheck.model.Food;
import com.vijayganduri.nutricheck.model.Portion;

import java.lang.reflect.Type;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by vganduri on 8/2/2015.
 */
public class RestHandler {

    private static RestHandler ourInstance = new RestHandler();

    private RestService service;

    public static RestHandler getInstance() {
        return ourInstance;
    }
    Type token = new TypeToken<RealmList<Portion>>(){}.getType();
    Gson gson = new GsonBuilder()
            .setExclusionStrategies(new ExclusionStrategy() {
                @Override
                public boolean shouldSkipField(FieldAttributes f) {
                    return f.getDeclaringClass().equals(RealmObject.class);
                }

                @Override
                public boolean shouldSkipClass(Class<?> clazz) {
                    return false;
                }
            })
            .create();

    private RestHandler() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Config.BASE_URL)
                .setConverter(new GsonConverter(gson))
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