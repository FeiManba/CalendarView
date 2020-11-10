package com.feima.calendar;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author mr.zang
 * 如果嵌套各种View出现事件冲突，可以实现这个方法即可
 */
public class CalendarLinearLayout extends LinearLayout implements CalendarLayout.CalendarScrollView {

    public CalendarLinearLayout(Context context) {
        super(context);
    }

    public CalendarLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 是否滚动到顶部
     *
     * @return 是否滚动到顶部
     */
    @Override
    public boolean isScrollToTop() {
        if (getChildCount() > 1 && getChildAt(1) instanceof FrameLayout) {
            FrameLayout f = (FrameLayout) getChildAt(1);
            if (f.getChildCount() > 0 && f.getChildAt(0) instanceof RecyclerView) {
                RecyclerView mRecyclerView = (RecyclerView) f.getChildAt(0);
                return mRecyclerView != null && mRecyclerView.computeVerticalScrollOffset() == 0;
            }
        }

        if (getChildCount() > 1 && getChildAt(1) instanceof NestedScrollView) {
            NestedScrollView nestedScrollView = (NestedScrollView) getChildAt(1);
            if (nestedScrollView == null) return false;
            return nestedScrollView.getScrollY() == 0;
        }
        return false;
    }
}
