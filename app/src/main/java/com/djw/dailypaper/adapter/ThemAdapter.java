package com.djw.dailypaper.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.djw.dailypaper.R;
import com.djw.dailypaper.base.BaseHolder;
import com.djw.dailypaper.model.data.Them.ThemData;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by JasonDong on 2017/3/10.
 */

public class ThemAdapter extends RecyclerView.Adapter<ThemAdapter.ThemHolder> {

    private List<ThemData.OthersBean> list;

    private Context context;

    public ThemAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void notifyListChange(List<ThemData.OthersBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public ThemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ThemHolder(LayoutInflater.from(context).inflate(R.layout.item_them, parent, false));
    }

    @Override
    public void onBindViewHolder(ThemHolder holder, int position) {
        holder.textView.setText(list.get(position).getName());
        Glide.with(context).load(list.get(position).getThumbnail()).bitmapTransform(new RoundedCornersTransformation(context,10,5)).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ThemHolder extends BaseHolder {

        private final ImageView imageView;
        private final TextView textView;

        public ThemHolder(View itemView) {
            super(itemView);
            imageView = ((ImageView) itemView.findViewById(R.id.iv_them));
            textView = ((TextView) itemView.findViewById(R.id.tv_them));
        }
    }
}
