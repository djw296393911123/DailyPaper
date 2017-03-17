package com.djw.dailypaper.util;

import android.support.v7.widget.RecyclerView;

/**
 * Created by JasonDong on 2017/3/17.
 */

public class RecyclerViewUtil {

    public static boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
                >= recyclerView.computeVerticalScrollRange() && recyclerView.canScrollVertically(-1))
            return true;
        return false;
    }
}
