package com.vijayganduri.nutricheck.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vijayganduri.nutricheck.R;
import com.vijayganduri.nutricheck.model.Food;
import com.vijayganduri.nutricheck.model.Portion;
import com.vijayganduri.nutricheck.model.Recent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by vganduri on 8/3/2015.
 */
public class RecentListAdapter extends RecyclerView.Adapter<RecentListAdapter.ViewHolder> {
    private List<Recent> items;
    private OnItemClickListener mOnItemClickListener;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView date;
        public ViewHolder(View v) {
            super(v);
            title = (TextView)v.findViewById(R.id.title);
            date = (TextView)v.findViewById(R.id.date_searched);

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

    public RecentListAdapter() {

    }

    public void setItems(List<Recent> items){
        this.items = items;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    @Override
    public RecentListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recent_list_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(items.get(position).getQuery());
        holder.date.setText(dateFormat.format(new Date(items.get(position).getTimestamp())));
    }

    @Override
    public int getItemCount() {
        return items!=null?items.size():0;
    }

    public static interface OnItemClickListener{
        public void onItemClick(int position, Recent item);
    }
}