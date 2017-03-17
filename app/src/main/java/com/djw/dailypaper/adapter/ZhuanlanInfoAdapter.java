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
import com.djw.dailypaper.model.data.gank.ZhuanlanInfoData;
import com.djw.dailypaper.view.activity.WebviewActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong on 2017/3/10.
 */

public class ZhuanlanInfoAdapter extends RecyclerView.Adapter<ZhuanlanInfoAdapter.HotHolder> {

    private List<ZhuanlanInfoData.StoriesBean> list;

    private Context context;

    public ZhuanlanInfoAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void notifyListChange(List<ZhuanlanInfoData.StoriesBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public HotHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HotHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hot, parent, false));
    }

    @Override
    public void onBindViewHolder(HotHolder holder, final int position) {
        List<String> images = list.get(position).getImages();
        if (images != null)
            Glide.with(context).load(images.get(0)).asBitmap().into(holder.imageView);
        else holder.imageView.setBackgroundResource(R.drawable.ic_backspace_black_24dp);
        holder.textView.setText(list.get(position).getTitle());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, WebviewActivity.class).putExtra("id", String.valueOf(list.get(position).getId())));
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

        public HotHolder(View itemView) {
            super(itemView);
            textView = ((TextView) itemView.findViewById(R.id.tv_hot));
            imageView = ((ImageView) itemView.findViewById(R.id.iv_hot));
            cardView = ((CardView) itemView.findViewById(R.id.cv_item));
        }
    }
}
