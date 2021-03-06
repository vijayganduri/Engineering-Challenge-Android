package com.vijayganduri.nutricheck.dao;

import android.text.TextUtils;

import com.vijayganduri.nutricheck.model.Food;
import com.vijayganduri.nutricheck.model.Recent;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by vganduri on 8/3/2015.
 */
public class FoodDao extends BasicDao{

    private Realm realm;

    protected FoodDao(Realm realm) {
        this.realm = realm;
    }
    public Food addOrUpdateFoodItem(Food item){

        if(item.get_id()==null || item.get_id().length()==0){
            item.set_id(UUID.randomUUID().toString().replaceAll("-", ""));//TODO find a better way
        }

        long startTime = new Date().getTime();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(item);
        realm.commitTransaction();
        printMetricLogs(updated, new Date().getTime()-startTime, 1);
        return item;
    }
    public void addOrUpdateFoodItems(List<Food> items){
        long startTime = new Date().getTime();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(items);
        realm.commitTransaction();
        printMetricLogs(updated, new Date().getTime()-startTime, items != null ? items.size() : 0);
    }

    public RealmResults<Food> getAllFoodItems(){
        long startTime = new Date().getTime();
        RealmResults<Food> results = realm.where(Food.class).findAll();
        printMetricLogs(fetched, new Date().getTime() - startTime, results != null ? results.size() : 0);
        return results;
    }

    public Food getFoodItemMatchingId(String id){
        long startTime = new Date().getTime();
        Food result = realm.where(Food.class).equalTo("_id", id).findFirst();
        printMetricLogs(fetched, new Date().getTime() - startTime, 1);
        return result;
    }

    public List<Food> getFoodItemsMatchingIds(String[] ids){
        long startTime = new Date().getTime();
        RealmQuery<Food> query = realm.where(Food.class);
        boolean first = true;
        for(String id : ids){
            if(first){
                query.equalTo("_id",id);
                first = false;
            }else {
                query.or().equalTo("_id", id);
            }
        }
        RealmResults<Food> results = query.findAll();

        printMetricLogs(fetched, new Date().getTime() - startTime, results != null ? results.size() : 0);
        return results;
    }

}
