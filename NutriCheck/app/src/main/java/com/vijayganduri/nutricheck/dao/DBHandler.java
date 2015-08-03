package com.vijayganduri.nutricheck.dao;

import android.content.Context;

import io.realm.Realm;

/**
 * Created by vganduri on 8/3/2015.
 */
public class DBHandler {

    private static DBHandler ourInstance;

    private Realm realm;
    private FoodDao foodDao;
    private RecentDao recentDao;

    public static DBHandler getInstance(Context context) {
        if(ourInstance == null){
            ourInstance = new DBHandler(context);
        }
        return ourInstance;
    }

    private DBHandler(Context context) {
        realm = Realm.getInstance(context);
        foodDao = new FoodDao(realm);
        recentDao = new RecentDao(realm);
    }

    public FoodDao getFoodDao(){
        return foodDao;
    }

    public RecentDao getRecentDao(){
        return recentDao;
    }

}
