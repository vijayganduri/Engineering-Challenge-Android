package com.vijayganduri.nutricheck.dao;

import android.util.Log;

import com.vijayganduri.nutricheck.model.Recent;

import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by vganduri on 8/3/2015.
 */
public class RecentDao extends BasicDao{

    private Realm realm;
    private static final String TAG = RecentDao.class.getSimpleName();

    protected RecentDao(Realm realm) {
        this.realm = realm;
    }

    public void addOrUpdateRecentItems(List<Recent> items){
        long startTime = new Date().getTime();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(items);
        realm.commitTransaction();
        printMetricLogs(updated, new Date().getTime()-startTime, items.size());
    }

    public void addOrUpdateRecentItem(Recent item){

        RealmResults<Recent> recentResults = getRecentItemsMatchingQuery(item.getQuery());
        if(recentResults!=null && recentResults.size()>0){
            item.setId(recentResults.get(0).getId());
        }else{
            item.setId((int) (realm.where(Recent.class).maximumInt("id") + 1));//auto increment
        }

        long startTime = new Date().getTime();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(item);
        realm.commitTransaction();
        printMetricLogs(updated, new Date().getTime() - startTime, 1);
    }

    public RealmResults<Recent> getAllRecentItems(){
        long startTime = new Date().getTime();
        RealmResults<Recent> results = realm.where(Recent.class).findAllSorted("timestamp",false);
        printMetricLogs(fetched, new Date().getTime() - startTime, results!=null?results.size():0);
        return results;
    }

    public RealmResults<Recent> getRecentItemsMatchingQuery(String query){
        long startTime = new Date().getTime();
        RealmResults<Recent> results = realm.where(Recent.class).equalTo("query", query).findAll();
        printMetricLogs(fetched, new Date().getTime() - startTime, results!=null?results.size():0);
        return results;
    }

}
