package com.vijayganduri.nutricheck.dao;

import com.vijayganduri.nutricheck.model.Favorite;
import com.vijayganduri.nutricheck.model.Food;
import com.vijayganduri.nutricheck.model.MyFood;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by vganduri on 8/3/2015.
 */
public class MyFoodDao extends BasicDao{

    private Realm realm;
    private static final String TAG = MyFoodDao.class.getSimpleName();

    protected MyFoodDao(Realm realm) {
        this.realm = realm;
    }

    public void addOrUpdateMyFoodItem(MyFood item){
        long startTime = new Date().getTime();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(item);
        realm.commitTransaction();
        printMetricLogs(updated, new Date().getTime() - startTime, 1);
    }

    public void removeMyFoodItem(String id){
        MyFood item = getMyFoodItemById(id);
        if(item==null){
            return;
        }

        long startTime = new Date().getTime();
        realm.beginTransaction();
        item.removeFromRealm();
        realm.commitTransaction();
        printMetricLogs(deleted, new Date().getTime() - startTime, 1);
    }

    public List<Food> getAllMyFoodItems(){
        long startTime = new Date().getTime();
        RealmResults<MyFood> results = realm.where(MyFood.class).findAll();
        printMetricLogs(fetched, new Date().getTime() - startTime, results != null ? results.size() : 0);

        if(results==null || results.size()==0){
            return null;
        }

        List<String> ids = new ArrayList<String>();
        for(MyFood myFood: results){
            ids.add(myFood.getId());
        }
        return getFoodItemsMatchingIds(ids);
    }

    public MyFood getMyFoodItemById(String id){
        long startTime = new Date().getTime();
        MyFood result = realm.where(MyFood.class).equalTo("id", id).findFirst();
        printMetricLogs(fetched, new Date().getTime() - startTime, 1);
        return result;
    }

    public List<Food> getFoodItemsMatchingIds(List<String> ids){
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
