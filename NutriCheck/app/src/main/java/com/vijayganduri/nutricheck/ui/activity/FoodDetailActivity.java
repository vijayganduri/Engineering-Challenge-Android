package com.vijayganduri.nutricheck.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.vijayganduri.nutricheck.R;
import com.vijayganduri.nutricheck.dao.DBHandler;
import com.vijayganduri.nutricheck.dao.FavoriteDao;
import com.vijayganduri.nutricheck.model.Favorite;
import com.vijayganduri.nutricheck.model.Food;
import com.vijayganduri.nutricheck.model.Important;
import com.vijayganduri.nutricheck.model.Portion;

import java.util.Date;

public class FoodDetailActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private static final String TAG = FoodDetailActivity.class.getSimpleName();
    public static final String INTENT_FOOD_INFO = "food.info.id";
    private String itemId;
    private Food item;

    private TextView title;
    private TextView source;
    private TextView protein;
    private TextView fat;
    private TextView carbs;
    private TextView calories;

    private TextView fibre;
    private TextView trans;
    private TextView saturated;
    private TextView sodium;
    private TextView potassium;
    private TextView poly;
    private TextView sugar;
    private TextView mono;
    private TextView cholesterol;

    private boolean favorite;

    private Spinner portionSpinner;

    private FloatingActionButton favButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionbar();
        setContentView(R.layout.activity_food_detail);

        if(getIntent()!=null && getIntent().getExtras()!=null){
            itemId = getIntent().getExtras().getString(INTENT_FOOD_INFO, null);
        }
        if(itemId==null && savedInstanceState!=null){
            itemId = savedInstanceState.getString(INTENT_FOOD_INFO, null);
        }
        if(itemId==null){
            Toast.makeText(this, "Could not find any item", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        title = (TextView)findViewById(R.id.title);
        source = (TextView)findViewById(R.id.source);
        fat = (TextView)findViewById(R.id.fat);
        protein = (TextView)findViewById(R.id.protien);
        calories = (TextView)findViewById(R.id.calories);
        carbs = (TextView)findViewById(R.id.carbs);

        fibre = (TextView)findViewById(R.id.fibre);
        trans = (TextView)findViewById(R.id.trans);
        saturated = (TextView)findViewById(R.id.saturated);
        sodium = (TextView)findViewById(R.id.sodium);
        potassium = (TextView)findViewById(R.id.potassium);
        poly = (TextView)findViewById(R.id.poly);
        sugar = (TextView)findViewById(R.id.sugar);
        mono = (TextView)findViewById(R.id.mono);
        cholesterol = (TextView)findViewById(R.id.cholesterol);

        portionSpinner = (Spinner)findViewById(R.id.portionsSize);

        favButton = (FloatingActionButton)findViewById(R.id.favoriteButton);

        setupInfo();
    }

    private void setupActionbar(){
        ActionBar actionbar = getSupportActionBar();
        actionbar.setElevation(0);
        actionbar.setDisplayShowTitleEnabled(false);
        actionbar.setDisplayShowHomeEnabled(true);
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeButtonEnabled(true);
    }

    private void setupInfo(){

        item = DBHandler.getInstance(this).getFoodDao().getFoodItemMatchingId(itemId);
        if(item==null){
            Toast.makeText(this, "Could not find any item", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        title.setText(item.getName());
        source.setText(String.format("Source : %s", item.getSource()));

        favButton.setOnClickListener(this);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        portionSpinner.setAdapter(spinnerAdapter);
        for(Portion portion: item.getPortions()) {
            spinnerAdapter.add(portion.getName());
        }
        portionSpinner.setOnItemSelectedListener(this);
        setupValues(0);
    }

    private void setupValues(int position){
        Important important = item.getPortions().get(position).getNutrients().getImportant();
        fat.setText(String.format("%.2f %s", important.getTotalFats().getValue(), important.getTotalFats().getUnit()));
        carbs.setText(String.format("%.2f %s", important.getTotalCarbs().getValue(), important.getTotalCarbs().getUnit()));
        protein.setText(String.format("%.2f %s", important.getProtein().getValue(), important.getProtein().getUnit()));
        calories.setText(String.format("%.2f %s", important.getCalories().getValue(), important.getCalories().getUnit()));

        if(important.getDietaryFibre()!=null) {
            fibre.setText(String.format("%.2f %s", important.getDietaryFibre().getValue(), important.getDietaryFibre().getUnit()));
        }
        if(important.getTransFat()!=null) {
            trans.setText(String.format("%.2f %s", important.getTransFat().getValue(), important.getTransFat().getUnit()));
        }
        if(important.getSaturated()!=null) {
            saturated.setText(String.format("%.2f %s", important.getSaturated().getValue(), important.getSaturated().getUnit()));
        }
        if(important.getSodium()!=null) {
            sodium.setText(String.format("%.2f %s", important.getSodium().getValue(), important.getSodium().getUnit()));
        }
        if(important.getPotassium()!=null) {
            potassium.setText(String.format("%.2f %s", important.getPotassium().getValue(), important.getPotassium().getUnit()));
        }
        if(important.getPolyUnsaturated()!=null) {
            poly.setText(String.format("%.2f %s", important.getPolyUnsaturated().getValue(), important.getPolyUnsaturated().getUnit()));
        }
        if(important.getSugar()!=null) {
            sugar.setText(String.format("%.2f %s", important.getSugar().getValue(), important.getSugar().getUnit()));
        }
        if(important.getMonoUnsaturated()!=null) {
            mono.setText(String.format("%.2f %s", important.getMonoUnsaturated().getValue(), important.getMonoUnsaturated().getUnit()));
        }
        if(important.getCholesterol()!=null) {
            cholesterol.setText(String.format("%.2f %s", important.getCholesterol().getValue(), important.getCholesterol().getUnit()));
        }

        FavoriteDao favoriteDao = DBHandler.getInstance(this).getFavoriteDao();
        syncFavState(favoriteDao.isFoodFavoriteById(item.get_id()));
    }

    private void syncFavState(boolean favorite){
        this.favorite = favorite;
        if(favorite){
            favButton.setImageResource(R.mipmap.ic_star);
        }else{
            favButton.setImageResource(R.mipmap.ic_star_border);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_food_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        setupValues(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.favoriteButton){
            if(!favorite) {
                Favorite favorite = new Favorite(item.get_id(), new Date().getTime());
                DBHandler.getInstance(this).getFavoriteDao().addOrUpdateFavoriteItem(favorite);
            }else{
                DBHandler.getInstance(this).getFavoriteDao().removeFavoriteItem(item.get_id());
            }
            syncFavState(!favorite);
        }
    }
}
