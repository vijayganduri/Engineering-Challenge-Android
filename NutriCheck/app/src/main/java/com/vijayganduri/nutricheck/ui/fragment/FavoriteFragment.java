package com.vijayganduri.nutricheck.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vijayganduri.nutricheck.R;
import com.vijayganduri.nutricheck.dao.DBHandler;
import com.vijayganduri.nutricheck.model.Favorite;
import com.vijayganduri.nutricheck.model.Food;
import com.vijayganduri.nutricheck.model.Recent;
import com.vijayganduri.nutricheck.ui.activity.FoodDetailActivity;
import com.vijayganduri.nutricheck.ui.activity.SearchActivity;
import com.vijayganduri.nutricheck.ui.adapter.FavoriteListAdapter;
import com.vijayganduri.nutricheck.ui.adapter.RecentListAdapter;

import java.util.List;

import io.realm.RealmResults;

public class FavoriteFragment extends Fragment implements FavoriteListAdapter.OnItemClickListener{

    private static final String TAG = FavoriteFragment.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private FavoriteListAdapter mAdapter;

    public static FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();
        return fragment;
    }

    public FavoriteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_nutrition_favorite, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.favorite_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new FavoriteListAdapter();
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        fetchFavorites();
    }

    private void fetchFavorites(){
        List<Food> results = DBHandler.getInstance(getActivity()).getFavoriteDao().getAllFavoriteFoodItems();
        mAdapter.setItems(results);
    }

    @Override
    public void onItemClick(int position, Food item) {
        if(item==null || TextUtils.isEmpty(item.get_id())){
            return;
        }
        Intent intent = new Intent(getActivity(), FoodDetailActivity.class);
        intent.putExtra(FoodDetailActivity.INTENT_FOOD_INFO, item.get_id());
        startActivity(intent);
    }
}

