package com.vijayganduri.nutricheck.dao;

import com.vijayganduri.nutricheck.model.Food;

import java.util.List;

import io.realm.Realm;

/**
 * Created by vganduri on 8/3/2015.
 */
public class FoodDao extends BasicDao{

    private Realm realm;

    protected FoodDao(Realm realm) {
        this.realm = realm;
    }

    private void addOrUpdateFoodItems(List<Food> items){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(items);
        realm.commitTransaction();
    }

    private void addOrUpdateFoodItems(Food item){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(item);
        realm.commitTransaction();
    }





}
