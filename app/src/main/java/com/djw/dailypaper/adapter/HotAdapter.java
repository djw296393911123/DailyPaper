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
import com.djw.dailypaper.model.data.HotData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong on 2017/3/10.
 */

public class HotAdapter extends RecyclerView.Adapter<HotAdapter.HotHolder> {

    private List<HotData.RecentBean> list;

    private Context context;

    public HotAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void notifyListChange(List<HotData.RecentBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public HotHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HotHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hot, parent, false));
    }

    @Override
    public void onBindViewHolder(HotHolder holder, int position) {
        Glide.with(context).load(list.get(position).getThumbnail()).asBitmap().into(holder.imageView);
        holder.textView.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class HotHolder extends BaseHolder {

        private final TextView textView;
        private final ImageView imageView;

        public HotHolder(View itemView) {
            super(itemView);
            textView = ((TextView) itemView.findViewById(R.id.tv_hot));
            imageView = ((ImageView) itemView.findViewById(R.id.iv_hot));
        }
    }
}
