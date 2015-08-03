package com.vijayganduri.nutricheck.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vijayganduri.nutricheck.R;
import com.vijayganduri.nutricheck.dao.DBHandler;
import com.vijayganduri.nutricheck.model.Recent;
import com.vijayganduri.nutricheck.ui.activity.SearchActivity;
import com.vijayganduri.nutricheck.ui.adapter.FoodListAdapter;
import com.vijayganduri.nutricheck.ui.adapter.RecentListAdapter;
import com.vijayganduri.nutricheck.ui.adapter.TrendingListAdapter;
import com.vijayganduri.nutricheck.util.DividerItemDecoration;

import io.realm.RealmResults;

public class RecentFragment extends Fragment implements RecentListAdapter.OnItemClickListener {

    private static final String TAG = RecentFragment.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private RecentListAdapter mAdapter;

    public static RecentFragment newInstance() {
        RecentFragment fragment = new RecentFragment();
        return fragment;
    }

    public RecentFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_nutrition_recent, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recent_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new RecentListAdapter();
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        fetchRecents();
    }

    private void fetchRecents(){
        RealmResults<Recent> results = DBHandler.getInstance(getActivity()).getRecentDao().getAllRecentItems();
        mAdapter.setItems(results);
    }

    @Override
    public void onItemClick(int position, Recent item) {
        if(item==null || TextUtils.isEmpty(item.getQuery())){
            return;
        }
        Intent intent = new Intent(getActivity(), SearchActivity.class);
        intent.putExtra(SearchActivity.INTENT_FOOD_SEARCH_QUERY, item.getQuery());
        startActivity(intent);
    }
}
