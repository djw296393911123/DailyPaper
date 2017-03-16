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
import com.djw.dailypaper.model.data.DaypaperData;
import com.djw.dailypaper.model.data.paper.PaperBaseData;
import com.djw.dailypaper.model.data.paper.BannerData;
import com.djw.dailypaper.model.data.paper.PaperData;
import com.djw.dailypaper.model.data.paper.TypeData;
import com.djw.dailypaper.view.activity.WebviewActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by JasonDong on 2017/3/10.
 */

public class PaperAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnBannerClickListener {

    private List<PaperBaseData> list;
    private List<BannerData> urls;
    private Context context;

    public PaperAdapter(Context context) {
        this.context = context;
        this.list = new LinkedList<>();
        this.urls = new ArrayList<>();
    }

    public void notifyListChange(DaypaperData daypaperData, String date, boolean isBefore) {
        if (!isBefore) {
            this.list.clear();
            List<DaypaperData.TopStoriesBean> top_stories = daypaperData.getTop_stories();
            List<String> urls = new ArrayList<>();
            List<String> titles = new ArrayList<>();
            List<String> ids = new ArrayList<>();
            for (int i = 0; i < top_stories.size(); i++) {
                DaypaperData.TopStoriesBean topStoriesBean = top_stories.get(i);
                urls.add(topStoriesBean.getImage());
                titles.add(topStoriesBean.getTitle());
                ids.add(String.valueOf(topStoriesBean.getId()));
            }
            list.add(new BannerData(titles, urls, ids));
        }
        list.add(new TypeData(date));
        List<DaypaperData.StoriesBean> stories = daypaperData.getStories();
        for (int i = 0; i < stories.size(); i++) {
            DaypaperData.StoriesBean storiesBean = stories.get(i);
            list.add(new PaperData(storiesBean.getTitle(), storiesBean.getImages().get(0), storiesBean.getId()));
        }
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case PaperBaseData.BANNER:
                return new BannerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_paper_banner, parent, false));
            case PaperBaseData.TYPE:
                return new TypeHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_paper_type, parent, false));
            case PaperBaseData.PAPER:
                return new PaperHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_paper_news, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (list.get(position).getType()) {
            case PaperBaseData.BANNER:
                Banner banner = ((BannerHolder) holder).banner;
                BannerData bannerData = (BannerData) list.get(position);
                banner.setImageLoader(new GlideImageLoader());
                banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
                banner.setOffscreenPageLimit(1);
                banner.setOnBannerClickListener(this);
                banner.setBannerTitles(bannerData.getTitle());
                banner.setImages(bannerData.getUrl());
                banner.start();
                break;
            case PaperBaseData.TYPE:
                ((TypeHolder) holder).type.setText(((TypeData) list.get(position)).getData());
                break;
            case PaperBaseData.PAPER:
                PaperHolder paperHolder = (PaperHolder) holder;
                final PaperData paperData = (PaperData) list.get(position);
                Glide.with(context).load(paperData.getUrl()).asBitmap().into(paperHolder.imageView);
                paperHolder.title.setText(paperData.getTitle());
                paperHolder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        context.startActivity(new Intent(context, WebviewActivity.class).putExtra("id", paperData.getId()));
                    }
                });
                break;

        }
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void OnBannerClick(int position) {
        context.startActivity(new Intent(context, WebviewActivity.class).putExtra("id", ((BannerData) list.get(0)).getId().get(position - 1)));
    }

    class PaperHolder extends BaseHolder {

        private final TextView title;
        private final ImageView imageView;
        private final CardView cardView;

        public PaperHolder(View itemView) {
            super(itemView);
            cardView = ((CardView) itemView.findViewById(R.id.cv_item));
            title = ((TextView) itemView.findViewById(R.id.tv_paper_title));
            imageView = ((ImageView) itemView.findViewById(R.id.iv_paper_img));
        }
    }

    class BannerHolder extends BaseHolder {

        private final Banner banner;

        public BannerHolder(View itemView) {
            super(itemView);
            banner = ((Banner) itemView.findViewById(R.id.banner_paper));
        }
    }

    class TypeHolder extends BaseHolder {

        private final TextView type;

        public TypeHolder(View itemView) {
            super(itemView);
            type = ((TextView) itemView.findViewById(R.id.tv_paper_type));
        }
    }

    private class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).asBitmap().into(imageView);
        }

        @Override
        public ImageView createImageView(Context context) {
            return new ImageView(context);
        }
    }
}
