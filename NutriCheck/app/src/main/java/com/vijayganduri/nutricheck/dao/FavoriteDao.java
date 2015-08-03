package com.vijayganduri.nutricheck.dao;

import com.vijayganduri.nutricheck.model.Favorite;
import com.vijayganduri.nutricheck.model.Food;
import com.vijayganduri.nutricheck.model.Recent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by vganduri on 8/3/2015.
 */
public class FavoriteDao extends BasicDao{

    private Realm realm;
    private static final String TAG = FavoriteDao.class.getSimpleName();

    protected FavoriteDao(Realm realm) {
        this.realm = realm;
    }

    public void addOrUpdateFavoriteItem(Favorite item){
        long startTime = new Date().getTime();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(item);
        realm.commitTransaction();
        printMetricLogs(updated, new Date().getTime() - startTime, 1);
    }

    public void removeFavoriteItem(String id){
        Favorite item = getFavoriteItemById(id);
        if(item==null){
            return;
        }

        long startTime = new Date().getTime();
        realm.beginTransaction();
        item.removeFromRealm();
        realm.commitTransaction();
        printMetricLogs(deleted, new Date().getTime() - startTime, 1);
    }

    public List<Food> getAllFavoriteFoodItems(){
        long startTime = new Date().getTime();
        RealmResults<Favorite> favResults = realm.where(Favorite.class).findAll();
        printMetricLogs(fetched, new Date().getTime() - startTime, favResults != null ? favResults.size() : 0);

        if(favResults==null || favResults.size()==0){
            return null;
        }

        List<String> ids = new ArrayList<String>();
        for(String id: ids){
            ids.add(id);
        }
        return getFoodItemsMatchingIds(ids);
    }

    public Favorite getFavoriteItemById(String id){
        long startTime = new Date().getTime();
        Favorite result = realm.where(Favorite.class).equalTo("id", id).findFirst();
        printMetricLogs(fetched, new Date().getTime() - startTime, 1);
        return result;
    }

    public boolean isFoodFavoriteById(String id){
        long startTime = new Date().getTime();
        Favorite result = getFavoriteItemById(id);
        printMetricLogs(fetched, new Date().getTime() - startTime, 1);
        return result!=null;
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
