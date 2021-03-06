package com.vijayganduri.nutricheck.dao;

import android.content.Context;

import com.vijayganduri.nutricheck.model.Favorite;
import com.vijayganduri.nutricheck.model.MyFood;

import io.realm.Realm;

/**
 * Created by vganduri on 8/3/2015.
 */
public class DBHandler {

    private static DBHandler ourInstance;

    private Realm realm;
    private FoodDao foodDao;
    private RecentDao recentDao;
    private FavoriteDao favoriteDao;
    private MyFoodDao myFoodDao;

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
        favoriteDao = new FavoriteDao(realm);
        myFoodDao = new MyFoodDao(realm);
    }

    public FoodDao getFoodDao(){
        return foodDao;
    }

    public RecentDao getRecentDao(){
        return recentDao;
    }

    public FavoriteDao getFavoriteDao(){
        return favoriteDao;
    }

    public MyFoodDao getMyFoodDao(){
        return myFoodDao;
    }
}
