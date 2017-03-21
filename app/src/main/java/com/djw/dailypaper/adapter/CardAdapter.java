package com.djw.dailypaper.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.djw.dailypaper.R;
import com.djw.dailypaper.base.BaseHolder;
import com.djw.dailypaper.model.data.gank.AndroidData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong on 2017/3/20.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardHolder> {

    private List<AndroidData.ResultsBean> list;

    private Context context;

    public CardAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void notifyListChange(List<AndroidData.ResultsBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CardHolder(LayoutInflater.from(context).inflate(R.layout.card_item, parent, false));
    }

    @Override
    public void onBindViewHolder(CardHolder holder, int position) {
        Glide.with(context).load(list.get(position).getUrl()).asBitmap().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CardHolder extends BaseHolder {

        private final ImageView imageView;

        public CardHolder(View itemView) {
            super(itemView);
            imageView = ((ImageView) itemView.findViewById(R.id.iv_card));
        }
    }
}
