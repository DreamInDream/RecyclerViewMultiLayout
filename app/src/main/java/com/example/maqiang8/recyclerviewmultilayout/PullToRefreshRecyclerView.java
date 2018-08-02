package com.example.maqiang8.recyclerviewmultilayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.handmark.pulltorefresh.library.PullToRefreshBase;

/**
 * Created by houweihao on 2017/9/27.
 */

public class PullToRefreshRecyclerView extends PullToRefreshBase<RecyclerView> {
    private boolean canRefresh = true;

    public PullToRefreshRecyclerView(Context context) {
        super(context);
    }

    public PullToRefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullToRefreshRecyclerView(Context context, Mode mode) {
        super(context, mode);
    }

    public PullToRefreshRecyclerView(Context context, Mode mode, AnimationStyle animStyle) {
        super(context, mode, animStyle);
    }

    @Override
    public Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    @Override
    protected RecyclerView createRefreshableView(Context context, AttributeSet attrs) {
        RecyclerView gridRecyclerView = new RecyclerView(context,attrs);
        gridRecyclerView.setId(R.id.recyclerview);
        return gridRecyclerView;
    }

    @Override
    protected boolean isReadyForPullEnd() {
        if (mRefreshableView.computeVerticalScrollExtent() + mRefreshableView.computeVerticalScrollOffset() >= mRefreshableView.computeVerticalScrollRange())
            return true;
        return false;
    }

    @Override
    protected boolean isReadyForPullStart() {
        if (!canRefresh) {
            return false;
        }
        if (mRefreshableView.getChildCount() <= 0)
            return true;
        int firstVisiblePosition = mRefreshableView.getChildAdapterPosition(mRefreshableView.getChildAt(0));
        if (firstVisiblePosition == 0)
            return mRefreshableView.getChildAt(0).getTop() == mRefreshableView.getPaddingTop();
        else
            return false;
    }

    public void setCanRefresh(boolean canRefresh) {
        this.canRefresh = canRefresh;
    }
}
