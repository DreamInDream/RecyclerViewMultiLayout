package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation;
import com.handmark.pulltorefresh.library.R;

import pl.droidsonroids.gif.GifDrawable;

/**
 * Created by yangchangan on 2016/10/13.
 */
public class JDLoadingLayout extends LoadingLayout {
    private boolean falg;
    public JDLoadingLayout(Context context, Mode mode, Orientation scrollDirection, TypedArray attrs) {
        super(context, mode, scrollDirection, attrs);
    }

    @Override
    protected int getDefaultDrawableResId() {
        return R.drawable.refresh_gif;
    }

    @Override
    protected void onLoadingDrawableSet(Drawable imageDrawable) {

    }

    @Override
    protected void onPullImpl(float scaleOfLayout) {
        Log.e("JDLoadingLayout",scaleOfLayout+"");
    }

    @Override
    protected void pullToRefreshImpl() {
//        GifDrawable drawable = (GifDrawable) mHeaderGifImage.getDrawable();
//        drawable.start();
        // NO-OP
    }

    @Override
    protected void refreshingImpl() {
        if(!falg){
            falg = true;
            GifDrawable drawable = (GifDrawable) mHeaderGifImage.getDrawable();
            drawable.recycle();
            mHeaderGifImage.setImageResource(getDefaultDrawableResId());
        }
    }

    @Override
    protected void releaseToRefreshImpl() {
        // NO-OP
    }

    @Override
    protected void resetImpl() {
        falg = false;
    }
}
