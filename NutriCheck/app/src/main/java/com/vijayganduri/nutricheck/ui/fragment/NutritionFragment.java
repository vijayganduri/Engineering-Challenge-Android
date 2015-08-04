package com.vijayganduri.nutricheck.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vijayganduri.nutricheck.R;
import com.vijayganduri.nutricheck.ui.activity.AddFoodActivity;
import com.vijayganduri.nutricheck.ui.activity.HomeActivity;
import com.vijayganduri.nutricheck.ui.activity.SearchActivity;

public class NutritionFragment extends Fragment implements View.OnClickListener{

    private static final String ARG_SECTION_NUMBER = "section_number";
    private ViewPager pager;
    private TabLayout slidingTabsLayout;
    private TabsPagerAdapter tabsPagerAdapter;
    private FloatingActionButton addFoodBtn;

    private static final String TAG = NutritionFragment.class.getSimpleName();

    public static NutritionFragment newInstance(int sectionNumber) {
        NutritionFragment fragment = new NutritionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public NutritionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nutrition, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pager = (ViewPager)view.findViewById(R.id.pager);
        slidingTabsLayout = (TabLayout)view.findViewById(R.id.sliding_tabs);
        addFoodBtn = (FloatingActionButton)view.findViewById(R.id.add_food);
        tabsPagerAdapter = new TabsPagerAdapter(getChildFragmentManager());

        pager.setAdapter(tabsPagerAdapter);

        if (ViewCompat.isLaidOut(slidingTabsLayout)) {
            slidingTabsLayout.setupWithViewPager(pager);
        } else {
            slidingTabsLayout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    slidingTabsLayout.setupWithViewPager(pager);
                    slidingTabsLayout.removeOnLayoutChangeListener(this);
                }
            });
        }

        addFoodBtn.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.w(TAG, "onActivityCreated");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.w(TAG, "onAttach");
        ((HomeActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==addFoodBtn.getId()){
            Intent intent = new Intent(getActivity(), AddFoodActivity.class);
            startActivity(intent);
        }
    }

    public class TabsPagerAdapter extends FragmentPagerAdapter{

        public TabsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position){
                case 0:
                    fragment = TrendingFragment.newInstance();
                    break;
                case 1:
                    fragment = RecentFragment.newInstance();
                    break;
                case 2:
                    fragment = FavoriteFragment.newInstance();
                    break;
                case 3:
                    fragment = MyFoodFragment.newInstance();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.tabs_title_section1);
                case 1:
                    return getString(R.string.tabs_title_section2);
                case 2:
                    return getString(R.string.tabs_title_section3);
                case 3:
                    return getString(R.string.tabs_title_section4);
            }
            return null;
        }
    }
}
