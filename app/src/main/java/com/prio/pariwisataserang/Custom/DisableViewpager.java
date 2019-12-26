package com.prio.pariwisataserang.Custom;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.prio.pariwisataserang.R;

public class DisableViewpager extends ViewPager {


    private boolean swipeable;

    public DisableViewpager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DisableViewpager);
        try {
            swipeable = a.getBoolean(R.styleable.DisableViewpager_swipeable,true);
        } finally {
            a.recycle();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return swipeable ? super.onInterceptTouchEvent(ev):false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return swipeable?super.onTouchEvent(ev):false;
    }
}
