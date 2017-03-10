package com.djw.dailypaper.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhy.autolayout.utils.AutoUtils;

/**
 * Created by JasonDong on 2017/3/10.
 */

public class BaseHolder extends RecyclerView.ViewHolder {
    public BaseHolder(View itemView) {
        super(itemView);
        AutoUtils.autoSize(itemView);
    }
}
