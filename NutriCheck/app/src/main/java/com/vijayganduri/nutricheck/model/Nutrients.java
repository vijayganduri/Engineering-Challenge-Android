package com.vijayganduri.nutricheck.model;

/**
 * Created by vganduri on 8/2/2015.
 */
public class Nutrients {

    private Important important;

    public Important getImportant() {
        return important;
    }

    public void setImportant(Important important) {
        this.important = important;
    }

    @Override
    public String toString() {
        return "Nutrients{" +
                "important=" + important +
                '}';
    }
}
