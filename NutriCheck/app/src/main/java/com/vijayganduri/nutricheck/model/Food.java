package com.vijayganduri.nutricheck.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by vganduri on 8/2/2015.
 */
public class Food {

    private String _id;
    private String name;

    @SerializedName("brand_name")
    private String brandName;

    private List<Portion> portions;
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

    public List<Portion> getPortions() {
        return portions;
    }

    public void setPortions(List<Portion> portions) {
        this.portions = portions;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "Food{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", brandName='" + brandName + '\'' +
                ", portions=" + portions +
                ", source='" + source + '\'' +
                '}';
    }
}
