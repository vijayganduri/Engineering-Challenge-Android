package com.vijayganduri.nutricheck.model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by vganduri on 8/2/2015.
 */
public class Recent extends RealmObject implements Serializable{

    @PrimaryKey
    private int id;
    private String query;
    private long timestamp;

    public Recent(){

    }

    public Recent(String query, long timestamp){
        this.query = query;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

}
