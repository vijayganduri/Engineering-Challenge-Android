package com.vijayganduri.nutricheck.model;

/**
 * Created by vganduri on 8/2/2015.
 */
public class BaseImportant {

    private String unit;
    private float value;

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

    @Override
    public String toString() {
        return "{" +
                "unit='" + unit + '\'' +
                ", value=" + value +
                '}';
    }
}
