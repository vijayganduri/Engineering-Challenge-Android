package com.vijayganduri.nutricheck.ui.activity;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.vijayganduri.nutricheck.R;
import com.vijayganduri.nutricheck.dao.DBHandler;
import com.vijayganduri.nutricheck.model.Calories;
import com.vijayganduri.nutricheck.model.Cholesterol;
import com.vijayganduri.nutricheck.model.DietaryFibre;
import com.vijayganduri.nutricheck.model.Food;
import com.vijayganduri.nutricheck.model.Important;
import com.vijayganduri.nutricheck.model.MonoUnsaturated;
import com.vijayganduri.nutricheck.model.MyFood;
import com.vijayganduri.nutricheck.model.Nutrients;
import com.vijayganduri.nutricheck.model.PolyUnsaturated;
import com.vijayganduri.nutricheck.model.Portion;
import com.vijayganduri.nutricheck.model.Potassium;
import com.vijayganduri.nutricheck.model.Protein;
import com.vijayganduri.nutricheck.model.Saturated;
import com.vijayganduri.nutricheck.model.Sodium;
import com.vijayganduri.nutricheck.model.Sugar;
import com.vijayganduri.nutricheck.model.TotalCarbs;
import com.vijayganduri.nutricheck.model.TotalFats;
import com.vijayganduri.nutricheck.model.TransFat;

import java.util.Date;

import io.realm.RealmList;

public class AddFoodActivity extends AppCompatActivity {

    private MaterialEditText title;
    private MaterialEditText portionSize;
    private MaterialEditText protein;
    private MaterialEditText fat;
    private MaterialEditText carbs;
    private MaterialEditText calories;
    private MaterialEditText fibre;
    private MaterialEditText trans;
    private MaterialEditText saturated;
    private MaterialEditText sodium;
    private MaterialEditText potassium;
    private MaterialEditText poly;
    private MaterialEditText sugar;
    private MaterialEditText mono;
    private MaterialEditText cholesterol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        title = (MaterialEditText)findViewById(R.id.foodName);
        portionSize = (MaterialEditText)findViewById(R.id.portion);
        fat = (MaterialEditText)findViewById(R.id.fat);
        protein = (MaterialEditText)findViewById(R.id.protein);
        calories = (MaterialEditText)findViewById(R.id.calories);
        carbs = (MaterialEditText)findViewById(R.id.carbs);
        fibre = (MaterialEditText)findViewById(R.id.fibre);
        trans = (MaterialEditText)findViewById(R.id.trans);
        saturated = (MaterialEditText)findViewById(R.id.saturated);
        sodium = (MaterialEditText)findViewById(R.id.sodium);
        potassium = (MaterialEditText)findViewById(R.id.potassium);
        poly = (MaterialEditText)findViewById(R.id.poly);
        sugar = (MaterialEditText)findViewById(R.id.sugar);
        mono = (MaterialEditText)findViewById(R.id.mono);
        cholesterol = (MaterialEditText)findViewById(R.id.cholesterol);

    }

    private void addFoodDetailsToDb(){

        if(title.getText().toString().length()<=2){
            Toast.makeText(this,"Please enter valid food name", Toast.LENGTH_SHORT).show();
            return;
        }else if(portionSize.getText().toString().length()==0){
            Toast.makeText(this,"Please enter portion size", Toast.LENGTH_SHORT).show();
            return;
        }

        Food item = new Food();
        item.setName(title.getText().toString());
        item.setSource("Self Added");
        final Portion portion = new Portion();
        portion.setName(portionSize.getText().toString());
        Nutrients nutrients = new Nutrients();
        Important important = new Important();
        important.setCalories(
                new Calories("kcal", Float.parseFloat((calories.getText().toString().length() > 0 ? calories.getText().toString() : "0"))));
        important.setDietaryFibre(
                new DietaryFibre("mg", Float.parseFloat((fibre.getText().toString().length() > 0 ? fibre.getText().toString() : "0"))));
        important.setTransFat(
                new TransFat("mg", Float.parseFloat((trans.getText().toString().length() > 0 ? trans.getText().toString() : "0"))));
        important.setSaturated(
                new Saturated("mg", Float.parseFloat((saturated.getText().toString().length() > 0 ? saturated.getText().toString() : "0"))));
        important.setTotalCarbs(
                new TotalCarbs("mg", Float.parseFloat((carbs.getText().toString().length() > 0 ? carbs.getText().toString() : "0"))));
        important.setSodium(
                new Sodium("mg", Float.parseFloat((sodium.getText().toString().length() > 0 ? sodium.getText().toString() : "0"))));
        important.setPotassium(
                new Potassium("mg", Float.parseFloat((potassium.getText().toString().length() > 0 ? potassium.getText().toString() : "0"))));
        important.setPolyUnsaturated(
                new PolyUnsaturated("mg", Float.parseFloat((poly.getText().toString().length() > 0 ? poly.getText().toString() : "0"))));
        important.setCalories(
                new Calories("mg", Float.parseFloat((calories.getText().toString().length() > 0 ? calories.getText().toString() : "0"))));
        important.setSugar(
                new Sugar("mg", Float.parseFloat((sugar.getText().toString().length() > 0 ? sugar.getText().toString() : "0"))));
        important.setTotalFats(
                new TotalFats("mg", Float.parseFloat((fat.getText().toString().length() > 0 ? fat.getText().toString() : "0"))));
        important.setMonoUnsaturated(
                new MonoUnsaturated("mg", Float.parseFloat((mono.getText().toString().length() > 0 ? mono.getText().toString() : "0"))));
        important.setCholesterol(
                new Cholesterol("mg", Float.parseFloat((cholesterol.getText().toString().length() > 0 ? cholesterol.getText().toString() : "0"))));
        important.setProtein(
                new Protein("mg", Float.parseFloat((protein.getText().toString().length() > 0 ? protein.getText().toString() : "0"))));

        nutrients.setImportant(important);
        portion.setNutrients(nutrients);
        item.setPortions(new RealmList<Portion>() {{
            add(portion);
        }});
        item = DBHandler.getInstance(this).getFoodDao().addOrUpdateFoodItem(item);

        MyFood myFood = new MyFood();
        myFood.setId(item.get_id());
        myFood.setTimestamp(new Date().getTime());

        DBHandler.getInstance(this).getMyFoodDao().addOrUpdateMyFoodItem(myFood);

        Toast.makeText(this, "Successfully saved a food item - " + title.getText().toString(), Toast.LENGTH_LONG).show();
        NavUtils.navigateUpFromSameTask(this);// finish this and return to parent activity
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_food, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add) {
            addFoodDetailsToDb();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
