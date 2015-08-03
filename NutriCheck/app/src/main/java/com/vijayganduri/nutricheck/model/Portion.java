package com.vijayganduri.nutricheck.model;

import java.io.Serializable;

/**
 * Created by vganduri on 8/2/2015.
 */
public class Portion  implements Serializable {

    private String name;
    private Nutrients nutrients;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Nutrients getNutrients() {
        return nutrients;
    }

    public void setNutrients(Nutrients nutrients) {
        this.nutrients = nutrients;
    }

    @Override
    public String toString() {
        return "Portion{" +
                "name='" + name + '\'' +
                ", nutrients=" + nutrients +
                '}';
    }
}
