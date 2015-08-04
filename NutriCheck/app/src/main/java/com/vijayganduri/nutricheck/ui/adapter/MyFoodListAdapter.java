package com.vijayganduri.nutricheck.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vijayganduri.nutricheck.R;
import com.vijayganduri.nutricheck.model.Food;
import com.vijayganduri.nutricheck.model.Portion;

import java.util.List;

/**
 * Created by vganduri on 8/3/2015.
 */
public class MyFoodListAdapter extends RecyclerView.Adapter<MyFoodListAdapter.ViewHolder> {
    private List<Food> items;
    private OnItemClickListener mOnItemClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView calInfo;
        public ViewHolder(View v) {
            super(v);
            title = (TextView)v.findViewById(R.id.title);
            calInfo = (TextView)v.findViewById(R.id.calInfo);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();
                    if(mOnItemClickListener!=null){
                        mOnItemClickListener.onItemClick(position, items!=null?items.get(position):null);
                    }
                }
            });

        }

    }

    public MyFoodListAdapter() {

    }

    public void setItems(List<Food> items){
        this.items = items;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    @Override
    public MyFoodListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.myfood_list_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(items.get(position).getName());
        String calInfoPerPortion = "";

        //Try to get 100gm cal info, if not choose last
        List<Portion> portions = items.get(position).getPortions();
        Portion portion = portions.get(0);
        String name = portion.getName();
        calInfoPerPortion = String.format("%s  %s %s",name, portion.getNutrients().getImportant().getCalories().getValue(),
                    portion.getNutrients().getImportant().getCalories().getUnit());

        holder.calInfo.setText(calInfoPerPortion);

    }

    @Override
    public int getItemCount() {
        return items!=null?items.size():0;
    }

    public static interface OnItemClickListener{
        public void onItemClick(int position, Food item);
    }
}