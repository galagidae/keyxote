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
    private int mKeyCode;
    private String mNormalLabel;
    private String mShiftLabel;
    private String mCurrentLabel;
    private static ColorStateList keyColors = new ColorStateList(
            new int[][]{
                    new int[]{android.R.attr.state_pressed},
                    new int[]{}
            },
            new int[] {
                    Color.BLUE,
                    Color.BLACK
            }
    );
    public Key(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Key);
        mKeyCode = ta.getInt(R.styleable.Key_keyCode, 113);
        mCurrentLabel = mNormalLabel = Character.toString((char)mKeyCode);
        mShiftLabel = Character.toString((char)(mKeyCode - 32));
        super.setText(mCurrentLabel);

        super.setTextSize(98);
        super.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        super.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);

        super.setBackgroundTintList(keyColors);
        super.setTextColor(Color.WHITE);
    }

    public void Shift (boolean shift) {
        if (shift) {
            mCurrentLabel = mShiftLabel;
        } else {
            mCurrentLabel = mNormalLabel;
        }
        this.setText(mCurrentLabel);
    }

    public String GetCurrentLabel() {
        return mCurrentLabel;
    }
}
