
package com.feima.calendar;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Constructor;

final class YearViewAdapter extends BaseRecyclerAdapter<com.feima.calendar.Month> {
    private com.feima.calendar.CalendarViewDelegate mDelegate;
    private int mItemWidth, mItemHeight;

    YearViewAdapter(Context context) {
        super(context);
    }

    final void setup(com.feima.calendar.CalendarViewDelegate delegate) {
        this.mDelegate = delegate;
    }


    final void setYearViewSize(int width, int height) {
        this.mItemWidth = width;
        this.mItemHeight = height;
    }

    @Override
    RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        com.feima.calendar.YearView yearView;
        if (TextUtils.isEmpty(mDelegate.getYearViewClassPath())) {
            yearView = new com.feima.calendar.DefaultYearView(mContext);
        } else {
            try {
                Constructor constructor = mDelegate.getYearViewClass().getConstructor(Context.class);
                yearView = (com.feima.calendar.YearView) constructor.newInstance(mContext);
            } catch (Exception e) {
                e.printStackTrace();
                yearView = new com.feima.calendar.DefaultYearView(mContext);
            }
        }
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.MATCH_PARENT);
        yearView.setLayoutParams(params);
        return new YearViewHolder(yearView, mDelegate);
    }

    @Override
    void onBindViewHolder(RecyclerView.ViewHolder holder, com.feima.calendar.Month item, int position) {
        YearViewHolder h = (YearViewHolder) holder;
        com.feima.calendar.YearView view = h.mYearView;
        view.init(item.getYear(), item.getMonth());
        view.measureSize(mItemWidth, mItemHeight);
    }

    private static class YearViewHolder extends RecyclerView.ViewHolder {
        com.feima.calendar.YearView mYearView;
        YearViewHolder(View itemView, com.feima.calendar.CalendarViewDelegate delegate) {
            super(itemView);
            mYearView = (com.feima.calendar.YearView) itemView;
            mYearView.setup(delegate);
        }
    }
}
