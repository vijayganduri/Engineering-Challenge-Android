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
public class TrendingListAdapter extends RecyclerView.Adapter<TrendingListAdapter.ViewHolder> {
    private List<String> items;
    private OnItemClickListener mOnItemClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ViewHolder(View v) {
            super(v);
            title = (TextView)v.findViewById(R.id.title);

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

    public TrendingListAdapter(List<String> items) {
        this.items = items;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    @Override
    public TrendingListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trending_list_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items!=null?items.size():0;
    }

    public static interface OnItemClickListener{
        public void onItemClick(int position, String item);
    }
}