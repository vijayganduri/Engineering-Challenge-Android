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
import com.vijayganduri.nutricheck.model.Food;
import com.vijayganduri.nutricheck.ui.activity.FoodDetailActivity;
import com.vijayganduri.nutricheck.ui.adapter.MyFoodListAdapter;

import java.util.List;

public class MyFoodFragment extends Fragment implements MyFoodListAdapter.OnItemClickListener{

    private static final String TAG = MyFoodFragment.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private MyFoodListAdapter mAdapter;

    public static MyFoodFragment newInstance() {
        MyFoodFragment fragment = new MyFoodFragment();
        return fragment;
    }

    public MyFoodFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_nutrition_myfood, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.myfood_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new MyFoodListAdapter();
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        fetchFavorites();
    }

    private void fetchFavorites(){
        List<Food> results = DBHandler.getInstance(getActivity()).getMyFoodDao().getAllMyFoodItems();
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

