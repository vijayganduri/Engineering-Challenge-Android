package com.vijayganduri.nutricheck.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vijayganduri.nutricheck.R;

public class MyFoodFragment extends Fragment {

    private static final String TAG = MyFoodFragment.class.getSimpleName();

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
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

}
