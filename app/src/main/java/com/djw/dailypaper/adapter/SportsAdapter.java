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
import com.djw.dailypaper.model.data.sports.SportsData;
import com.djw.dailypaper.view.activity.GankActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong on 2017/3/17.
 */

public class SportsAdapter extends RecyclerView.Adapter<SportsAdapter.SportsHolder> {

    private List<SportsData.NewslistBean> list;

    private Context context;

    public SportsAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void notifyDataChange(List<SportsData.NewslistBean> list, boolean isLoadMore) {
        if (!isLoadMore)
            this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public SportsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SportsHolder(LayoutInflater.from(context).inflate(R.layout.sports_item, parent, false));
    }

    @Override
    public void onBindViewHolder(SportsHolder holder, final int position) {
        SportsData.NewslistBean newslistBean = list.get(position);
        holder.author.setText(newslistBean.getDescription());
        holder.time.setText(newslistBean.getCtime());
        holder.title.setText(newslistBean.getTitle());
        Glide.with(context).load(newslistBean.getPicUrl()).asBitmap().placeholder(R.mipmap.img_default_meizi).error(R.mipmap.img_default_meizi).into(holder.head);
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

    class SportsHolder extends BaseHolder {

        private final TextView title;
        private final TextView time;
        private final TextView author;
        private final ImageView head;
        private final CardView cardView;

        public SportsHolder(View itemView) {
            super(itemView);
            title = ((TextView) itemView.findViewById(R.id.tv_title));
            time = ((TextView) itemView.findViewById(R.id.tv_time));
            author = ((TextView) itemView.findViewById(R.id.tv_author));
            head = ((ImageView) itemView.findViewById(R.id.iv_sports));
            cardView = ((CardView) itemView.findViewById(R.id.cv_item));
        }
    }
}
