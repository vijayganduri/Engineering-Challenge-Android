package com.vijayganduri.nutricheck.model;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by vganduri on 8/2/2015.
 */
public class Nutrients  extends RealmObject implements Serializable {

    private Important important;

    public Important getImportant() {
        return important;
    }

    public void setImportant(Important important) {
        this.important = important;
    }

}
