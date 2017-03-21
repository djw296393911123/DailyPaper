package com.djw.dailypaper.util;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.mcxtzhang.layoutmanager.swipecard.*;

import java.util.List;

/**
 * Created by JasonDong on 2017/3/21.
 */

public class CardCallback extends ItemTouchHelper.SimpleCallback {
    protected RecyclerView mRv;
    protected List mDatas;
    protected RecyclerView.Adapter mAdapter;

    public CardCallback(RecyclerView rv, RecyclerView.Adapter adapter, List datas) {
        this(0, 15, rv, adapter, datas);
    }

    public CardCallback(int dragDirs, int swipeDirs, RecyclerView rv, RecyclerView.Adapter adapter, List datas) {
        super(dragDirs, swipeDirs);
        this.mRv = rv;
        this.mAdapter = adapter;
        this.mDatas = datas;
    }

    public float getThreshold(RecyclerView.ViewHolder viewHolder) {
        return (float) this.mRv.getWidth() * this.getSwipeThreshold(viewHolder);
    }

    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        Object remove = this.mDatas.remove(viewHolder.getLayoutPosition());
        this.mDatas.remove(remove);
        this.mAdapter.notifyDataSetChanged();
    }

    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        double swipValue = Math.sqrt((double) (dX * dX + dY * dY));
        double fraction = swipValue / (double) this.getThreshold(viewHolder);
        if (fraction > 1.0D) {
            fraction = 1.0D;
        }

        int childCount = recyclerView.getChildCount();

        for (int i = 0; i < childCount; ++i) {
            View child = recyclerView.getChildAt(i);
            int level = childCount - i - 1;
            if (level > 0) {
                child.setScaleX((float) ((double) (1.0F - com.mcxtzhang.layoutmanager.swipecard.CardConfig.SCALE_GAP * (float) level) + fraction * (double) com.mcxtzhang.layoutmanager.swipecard.CardConfig.SCALE_GAP));
                if (level < com.mcxtzhang.layoutmanager.swipecard.CardConfig.MAX_SHOW_COUNT - 1) {
                    child.setScaleY((float) ((double) (1.0F - com.mcxtzhang.layoutmanager.swipecard.CardConfig.SCALE_GAP * (float) level) + fraction * (double) com.mcxtzhang.layoutmanager.swipecard.CardConfig.SCALE_GAP));
                    child.setTranslationY((float) ((double) (com.mcxtzhang.layoutmanager.swipecard.CardConfig.TRANS_Y_GAP * level) - fraction * (double) com.mcxtzhang.layoutmanager.swipecard.CardConfig.TRANS_Y_GAP));
                }
            }
        }

    }
}
