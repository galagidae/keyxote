package com.galagidae.keyxote;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class RowScrollView extends ScrollView {
    public RowScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            //case MotionEvent.ACTION_DOWN:
            //case MotionEvent.ACTION_UP:
                //return false;
            default:
                return super.onTouchEvent(ev);
        }
    }

    /*@Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return  false;
    }*/
}
