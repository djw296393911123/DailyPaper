package com.djw.dailypaper.view.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.djw.dailypaper.R;
import com.djw.dailypaper.adapter.ImagePageAdapter;
import com.djw.dailypaper.util.ImagePager;
import com.djw.dailypaper.util.MyBanner;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by JasonDong on 2017/3/21.
 */

public class BannerFragment extends DialogFragment {


    public static BannerFragment newInstance(List<String> urls, int position) {

        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putStringArrayList("urls", (ArrayList<String>) urls);
        BannerFragment fragment = new BannerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.banner_layout, null);
        MyBanner banner = (MyBanner) view.findViewById(R.id.banner_banner);
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(((ArrayList<String>) getArguments().getSerializable("urls")));
        banner.start();
        AlertDialog alertDialog = new AlertDialog
                .Builder(getActivity(), android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen)
                .setView(view)
                .create();
        Window window = alertDialog.getWindow();
        window.setWindowAnimations(R.style.dialog_style);
        return alertDialog;
    }

    private class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).asBitmap().fitCenter().into(imageView);
        }

        @Override
        public ImageView createImageView(Context context) {
            return new PhotoView(context);
        }
    }
}
