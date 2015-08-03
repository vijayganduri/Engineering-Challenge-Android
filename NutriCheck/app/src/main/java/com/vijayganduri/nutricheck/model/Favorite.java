package com.vijayganduri.nutricheck.model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by vganduri on 8/2/2015.
 */
public class Favorite extends RealmObject implements Serializable{

    @PrimaryKey
    private String id;
    private long timestamp;

    public Favorite(){

    }

    public Favorite(String id, long timestamp){
        this.id = id;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
