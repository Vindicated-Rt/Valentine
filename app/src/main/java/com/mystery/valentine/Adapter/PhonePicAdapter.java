package com.mystery.valentine.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.mystery.valentine.R;

import java.util.ArrayList;

/**
 * Created by Vindicated-Rt
 * 2020-02-03 20:09
 */
public class PhonePicAdapter extends BaseAdapter {

    private ArrayList<Integer> views = new ArrayList<>();
    private View view;

    public PhonePicAdapter(){
        views.add(R.mipmap.vangogh_1);
        views.add(R.mipmap.vangogh_2);
        views.add(R.mipmap.vangogh_3);
        views.add(R.mipmap.vangogh_4);
        views.add(R.mipmap.vangogh_5);
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public Object getItem(int i) {
        return view;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.phone_pic, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.chooseItemIv.setBackgroundResource(views.get(i));
        this.view = view;
        return view;
    }

    public ArrayList<Integer> getViews() {
        return views;
    }

    public static class ViewHolder {
        private ImageView chooseItemIv;
        private ImageView chooseItemLikeIv;
        private ImageView chooseItemPassIv;

        public ViewHolder(View itemView) {
            chooseItemIv = itemView.findViewById(R.id.choose_item_iv);
            chooseItemLikeIv = itemView.findViewById(R.id.choose_item_like_iv);
            chooseItemPassIv = itemView.findViewById(R.id.choose_item_pass_iv);
        }
    }
}
