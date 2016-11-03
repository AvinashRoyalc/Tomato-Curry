package com.tomato.curry.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tomato.curry.R;

import java.util.ArrayList;


public class SaloonListAdapter extends RecyclerView.Adapter<SaloonListAdapter.ViewHolder> {

    private ArrayList<Integer> banner_imgs = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView,mivbadge;

        public ViewHolder(View v) {
            super(v);
            mImageView = (ImageView) v.findViewById(R.id.saloon_banner);
            mivbadge=(ImageView)v.findViewById(R.id.ivbadge);
        }
    }


    public SaloonListAdapter(ArrayList<Integer> banner_imgs) {
        this.banner_imgs = banner_imgs;
    }

    @Override
    public SaloonListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mImageView.setImageResource(banner_imgs.get(position));


    }


    @Override
    public int getItemCount() {
        return banner_imgs.size();
    }
}