package com.vijayganduri.nutricheck.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vijayganduri.nutricheck.R;
import com.vijayganduri.nutricheck.ui.activity.SearchActivity;
import com.vijayganduri.nutricheck.ui.adapter.FoodListAdapter;
import com.vijayganduri.nutricheck.ui.adapter.TrendingListAdapter;

import java.util.ArrayList;
import java.util.List;

public class TrendingFragment extends Fragment implements TrendingListAdapter.OnItemClickListener{

    private static final String TAG = TrendingFragment.class.getSimpleName();

    public static TrendingFragment newInstance() {
        TrendingFragment fragment = new TrendingFragment();
        return fragment;
    }

    public TrendingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_nutrition_trending, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView trendingRecyclerView= (RecyclerView)view.findViewById(R.id.trending_recycler_view);
        trendingRecyclerView.setHasFixedSize(true);

        LinearLayoutManager gridManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        trendingRecyclerView.setLayoutManager(gridManager);
        trendingRecyclerView.setItemAnimator(new DefaultItemAnimator());

        TrendingListAdapter trendingAdapter = new TrendingListAdapter(getTrendingDummyData());
        trendingAdapter.setOnItemClickListener(this);
        trendingRecyclerView.setAdapter(trendingAdapter);
    }

    /**
     * TODO
     * Just return dummy data, need to create another end point to get trending food searches
     * Also end point to return suitable images for each
     * @return
     */
    private List<String> getTrendingDummyData(){

        List<String> items = new ArrayList<String>();
        items.add("Apples");
        items.add("Avocado");
        items.add("Bacon");
        items.add("Beef");
        items.add("Bread");
        items.add("Butter");
        items.add("Chicken");
        items.add("Chocolate");
        items.add("Eggs");
        items.add("Fish");
        items.add("Hamburger");
        items.add("lettuce");
        items.add("Milk");
        items.add("Oat Meal");
        items.add("Pasta");
        items.add("Peach");
        items.add("Pizza");
        items.add("Tuna");
        items.add("Turkey");
        items.add("Yogurt");
        return items;

    }

    @Override
    public void onItemClick(int position, String item) {
        Intent intent = new Intent(getActivity(), SearchActivity.class);
        intent.putExtra(SearchActivity.INTENT_FOOD_SEARCH_QUERY, item);
        startActivity(intent);
    }
}
