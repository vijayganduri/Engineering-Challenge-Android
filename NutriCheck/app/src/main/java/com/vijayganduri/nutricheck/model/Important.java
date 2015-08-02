package com.vijayganduri.nutricheck.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vganduri on 8/2/2015.
 */
public class Important {

    @SerializedName("dietary_fibre")
    private DietaryFibre dietaryFibre;

    @SerializedName("trans")
    private TransFat transFat;

    private Saturated saturated;

    @SerializedName("total_carbs")
    private TotalCarbs totalCarbs;

    private Sodium sodium;
    private Potassium potassium;

    @SerializedName("polyunsaturated")
    private PolyUnsaturated polyUnsaturated;

    private Calories calories;
    private Sugar sugar;

    @SerializedName("total_fats")
    private TotalFats totalFats;

    @SerializedName("monounsaturated")
    private MonoUnsaturated monoUnsaturated;

    private Cholesterol cholesterol;

    private Protein protein;

    public DietaryFibre getDietaryFibre() {
        return dietaryFibre;
    }

    public void setDietaryFibre(DietaryFibre dietaryFibre) {
        this.dietaryFibre = dietaryFibre;
    }

    public TransFat getTransFat() {
        return transFat;
    }

    public void setTransFat(TransFat transFat) {
        this.transFat = transFat;
    }

    public Saturated getSaturated() {
        return saturated;
    }

    public void setSaturated(Saturated saturated) {
        this.saturated = saturated;
    }

    public TotalCarbs getTotalCarbs() {
        return totalCarbs;
    }

    public void setTotalCarbs(TotalCarbs totalCarbs) {
        this.totalCarbs = totalCarbs;
    }

    public Sodium getSodium() {
        return sodium;
    }

    public void setSodium(Sodium sodium) {
        this.sodium = sodium;
    }

    public Potassium getPotassium() {
        return potassium;
    }

    public void setPotassium(Potassium potassium) {
        this.potassium = potassium;
    }


    public Calories getCalories() {
        return calories;
    }

    public void setCalories(Calories calories) {
        this.calories = calories;
    }

    public Sugar getSugar() {
        return sugar;
    }

    public void setSugar(Sugar sugar) {
        this.sugar = sugar;
    }

    public TotalFats getTotalFats() {
        return totalFats;
    }

    public void setTotalFats(TotalFats totalFats) {
        this.totalFats = totalFats;
    }


    public Cholesterol getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(Cholesterol cholesterol) {
        this.cholesterol = cholesterol;
    }

    public Protein getProtein() {
        return protein;
    }

    public void setProtein(Protein protein) {
        this.protein = protein;
    }

    public PolyUnsaturated getPolyUnsaturated() {
        return polyUnsaturated;
    }

    public void setPolyUnsaturated(PolyUnsaturated polyUnsaturated) {
        this.polyUnsaturated = polyUnsaturated;
    }

    public MonoUnsaturated getMonoUnsaturated() {
        return monoUnsaturated;
    }

    public void setMonoUnsaturated(MonoUnsaturated monoUnsaturated) {
        this.monoUnsaturated = monoUnsaturated;
    }

    @Override
    public String toString() {
        return "Important{" +
                "dietaryFibre=" + dietaryFibre +
                ", transFat=" + transFat +
                ", saturated=" + saturated +
                ", totalCarbs=" + totalCarbs +
                ", sodium=" + sodium +
                ", potassium=" + potassium +
                ", polyUnsaturated=" + polyUnsaturated +
                ", calories=" + calories +
                ", sugar=" + sugar +
                ", totalFats=" + totalFats +
                ", monoUnsaturated=" + monoUnsaturated +
                ", cholesterol=" + cholesterol +
                ", protein=" + protein +
                '}';
    }
}
