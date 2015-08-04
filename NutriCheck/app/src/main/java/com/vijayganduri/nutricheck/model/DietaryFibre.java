package com.vijayganduri.nutricheck.model;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by vganduri on 8/2/2015.
 */
public class DietaryFibre extends RealmObject implements Serializable{

    private String unit;
    private float value;

    public DietaryFibre() {

    }

    public DietaryFibre(String unit, float value) {
        this.unit = unit;
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

}
