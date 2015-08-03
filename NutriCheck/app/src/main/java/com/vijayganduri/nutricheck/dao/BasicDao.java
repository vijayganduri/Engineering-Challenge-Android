package com.vijayganduri.nutricheck.dao;

import android.util.Log;

import com.vijayganduri.nutricheck.model.Recent;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by vganduri on 8/3/2015.
 */
public class BasicDao{

    private static final String TAG = "REALM_METRICS";

    public String updated = "Updated";
    public String fetched = "Fetched";

    protected void printMetricLogs(String action, long timeinmillis, int rowsAffected){
        Log.d(TAG, String.format("%s %d row(s) in %d millisecs", action, rowsAffected, timeinmillis));
    }

}
