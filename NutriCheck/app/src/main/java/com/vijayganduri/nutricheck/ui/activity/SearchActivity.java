package com.vijayganduri.nutricheck.ui.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.vijayganduri.nutricheck.R;
import com.vijayganduri.nutricheck.dao.DBHandler;
import com.vijayganduri.nutricheck.model.Food;
import com.vijayganduri.nutricheck.model.Recent;
import com.vijayganduri.nutricheck.rest.RestHandler;
import com.vijayganduri.nutricheck.ui.adapter.FoodListAdapter;
import com.vijayganduri.nutricheck.util.DividerItemDecoration;

import java.util.Date;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SearchActivity extends AppCompatActivity implements FoodListAdapter.OnItemClickListener{

    private RecyclerView mRecyclerView;
    private FoodListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static final String INTENT_FOOD_SEARCH_QUERY = "food.search.query";
    private static final String TAG = SearchActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mRecyclerView = (RecyclerView) findViewById(R.id.search_results_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new FoodListAdapter();
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        handleIntent(getIntent());
    }
    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            performQuery(query);
        }else if (getIntent().getExtras()!=null) {
            String query = getIntent().getExtras().getString(INTENT_FOOD_SEARCH_QUERY);
            if(query!=null) {
                performQuery(query);
            }
        }
    }

    private void performQuery(String query){
        Log.w(TAG, "Searched for " + query);

        RestHandler.getInstance().getFoodByQuery(query, new Callback<List<Food>>() {
            @Override
            public void success(List<Food> foods, Response response) {
                Log.w(TAG, "response"+response);
                mAdapter.setItems(foods);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error :" + error);
                //TODO : add ui unable to load
                Toast.makeText(SearchActivity.this, "Unable to load " + error, Toast.LENGTH_LONG).show();
            }
        });
        Recent recent = new Recent(query, new Date().getTime());
        DBHandler.getInstance(this).getRecentDao().addOrUpdateRecentItem(recent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_auto_search));
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClick(int position, Food item) {
        Intent intent = new Intent(this, FoodDetailActivity.class);
        intent.putExtra(FoodDetailActivity.INTENT_FOOD_INFO, item);
        startActivity(intent);
    }
}
