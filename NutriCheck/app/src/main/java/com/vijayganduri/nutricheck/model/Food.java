package com.vijayganduri.nutricheck.model;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by vganduri on 8/2/2015.
 */
public class Food extends RealmObject implements Serializable{

    @PrimaryKey
    private String _id;
    private String name;

    @SerializedName("brand_name")
    private String brandName;

    private RealmList<Portion> portions;
    private String source;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public RealmList<Portion> getPortions() {
        return portions;
    }

    public void setPortions(RealmList<Portion> portions) {
        this.portions = portions;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

}
