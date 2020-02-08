package com.galagidae.keyxote;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Button;

public class Key extends Button {

    private String mBaseChar;
    private String mBaseLabel;
    private String mShiftChar;
    private String mShiftLabel;
    private boolean mShifted = false;

    public Key(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Get attribute values
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Key);

        mBaseChar = ta.getString(R.styleable.Key_baseChar);
        mBaseLabel = ta.getString(R.styleable.Key_baseLabel);
        if (mBaseLabel == null)
            mBaseLabel = mBaseChar;
        mShiftChar = ta.getString(R.styleable.Key_shiftChar);
        if (mShiftChar == null) {
            mShiftChar = mBaseChar;
            mShiftLabel = mBaseLabel;
        } else {
            mShiftLabel = ta.getString(R.styleable.Key_shiftLabel);
            if (mShiftLabel == null)
                mShiftLabel = mShiftChar;
        }

        super.setText(mBaseLabel);
    }

    public void Shift (boolean shift) {
        mShifted = shift;
        this.setText(mShifted? mShiftLabel : mBaseLabel);
    }

    public String GetCurrentChar() {
        return mShifted? mShiftChar : mBaseChar;
    }
}
