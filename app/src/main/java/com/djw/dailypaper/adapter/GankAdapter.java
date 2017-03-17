package com.djw.dailypaper.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.djw.dailypaper.R;
import com.djw.dailypaper.base.BaseHolder;
import com.djw.dailypaper.model.data.gank.AndroidData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong on 2017/3/16.
 */

public class GankAdapter extends RecyclerView.Adapter<GankAdapter.GankHolder> {

    private List<AndroidData.ResultsBean> list;

    private Context context;

    public GankAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void notifyListChange(List<AndroidData.ResultsBean> list, boolean isLoadMore) {
        if (!isLoadMore)
            this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public GankHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GankHolder(LayoutInflater.from(context).inflate(R.layout.item_gank, parent, false));
    }

    @Override
    public void onBindViewHolder(GankHolder holder, int position) {
        Log.i("holder", list.toString());
        holder.author.setText(list.get(position).getWho());
        holder.time.setText(list.get(position).getCreatedAt());
        holder.title.setText(list.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class GankHolder extends BaseHolder {

        private final TextView title;
        private final TextView time;
        private final TextView author;

        public GankHolder(View itemView) {
            super(itemView);
            title = ((TextView) itemView.findViewById(R.id.tv_title));
            time = ((TextView) itemView.findViewById(R.id.tv_time));
            author = ((TextView) itemView.findViewById(R.id.tv_author));
        }
    }
}
