package com.tomato.curry.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tomato.curry.ModelItem;
import com.tomato.curry.R;

import java.util.ArrayList;
import java.util.List;


public class CitySelectionAdapter extends RecyclerView.Adapter<CitySelectionAdapter.ViewHolder> {
    public ArrayList<ModelItem> mDataset = new ArrayList<>();


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_selection_row_item, parent, false);
        return new ViewHolder(v);
    }

    public void addAll(List<ModelItem> items) {
        int pos = getItemCount();
        mDataset.addAll(items);
        notifyItemRangeInserted(pos, mDataset.size());
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(position);
    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private Button mbutton;

        public ViewHolder(View v) {
            super(v);
            mbutton = (Button) v.findViewById(R.id.list_item);
        }

        public void bind(int position) {
            mbutton.setText(mDataset.get(position).getCity());
        }
    }
}