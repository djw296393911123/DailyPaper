package com.djw.dailypaper.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.djw.dailypaper.R;
import com.djw.dailypaper.base.BaseHolder;
import com.djw.dailypaper.model.data.WxData;
import com.djw.dailypaper.view.activity.GankActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong on 2017/3/10.
 */

public class WxAdapter extends RecyclerView.Adapter<WxAdapter.HotHolder> {

    private List<WxData.NewslistBean> list;

    private Context context;

    public WxAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void notifyListChange(List<WxData.NewslistBean> list, boolean isLoadMore) {
        if (!isLoadMore)
            this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public HotHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HotHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wx, parent, false));
    }

    @Override
    public void onBindViewHolder(HotHolder holder, final int position) {
        Glide.with(context).load(list.get(position).getPicUrl()).asBitmap().into(holder.imageView);
        holder.textView.setText(list.get(position).getTitle());
        holder.time.setText(list.get(position).getCtime());
        holder.author.setText(list.get(position).getDescription());
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

    class HotHolder extends BaseHolder {

        private final TextView textView;
        private final ImageView imageView;
        private final CardView cardView;
        private final TextView time;
        private final TextView author;

        public HotHolder(View itemView) {
            super(itemView);
            textView = ((TextView) itemView.findViewById(R.id.tv_hot));
            imageView = ((ImageView) itemView.findViewById(R.id.iv_hot));
            time = ((TextView) itemView.findViewById(R.id.tv_time));
            author = ((TextView) itemView.findViewById(R.id.tv_autohor));
            cardView = ((CardView) itemView.findViewById(R.id.cv_item));
        }
    }
}
