package com.galagidae.keyxote;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

public class Key extends Button {

    // New fields
    private String mBaseChar;
    private String mBaseLabel;
    private String mShiftChar;
    private String mShiftLabel;
    private boolean mShifted = false;

    public Key(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Key);

        mBaseChar = ta.getString(R.styleable.Key_baseChar);
        mBaseLabel = ta.getString(R.styleable.Key_baseLabel);
        if (mBaseLabel == null)
            mBaseLabel = mBaseChar;
        mShiftChar = ta.getString(R.styleable.Key_shiftChar);
        if (mShiftChar == null)
            mShiftChar = mBaseChar;
        mShiftLabel = ta.getString(R.styleable.Key_shiftLabel);
        if (mShiftLabel == null)
            mShiftLabel = mShiftChar;

        super.setText(mBaseLabel);

        //super.setTextSize(98);
        //super.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        //super.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
    }

    public void Shift (boolean shift) {

        mShifted = shift;
        this.setText(mShifted? mShiftLabel : mBaseLabel);
    }

    public String GetCurrentLabel() {
        return mShifted? mShiftChar : mBaseChar;
    }
}
