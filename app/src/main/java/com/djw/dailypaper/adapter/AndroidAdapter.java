package com.djw.dailypaper.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.djw.dailypaper.R;
import com.djw.dailypaper.base.BaseHolder;
import com.djw.dailypaper.model.data.gank.AndroidData;
import com.djw.dailypaper.view.activity.GankActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong on 2017/3/16.
 */

public class AndroidAdapter extends RecyclerView.Adapter<AndroidAdapter.AndroidHolder> {

    private final Context context;
    private List<AndroidData.ResultsBean> list;

    public AndroidAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void notifyDataChange(List<AndroidData.ResultsBean> list, boolean isLoadMore) {
        if (!isLoadMore)
            this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public AndroidHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AndroidHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gank, parent, false));
    }

    @Override
    public void onBindViewHolder(AndroidHolder holder, final int position) {
        holder.author.setText(list.get(position).getWho());
        holder.time.setText(list.get(position).getCreatedAt());
        holder.title.setText(list.get(position).getDesc());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, GankActivity.class).putExtra("url", list.get(position).getUrl()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class AndroidHolder extends BaseHolder {

        private final TextView title;
        private final TextView time;
        private final TextView author;
        private final CardView cardView;

        public AndroidHolder(View itemView) {
            super(itemView);
            title = ((TextView) itemView.findViewById(R.id.tv_title));
            time = ((TextView) itemView.findViewById(R.id.tv_time));
            author = ((TextView) itemView.findViewById(R.id.tv_author));
            cardView = ((CardView) itemView.findViewById(R.id.cv_all));
        }
    }
}
