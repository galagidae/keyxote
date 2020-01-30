package com.galagidae.keyxote;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;
import android.view.View;

public class KeyboardView extends LinearLayout {

    private View.OnClickListener mOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view instanceof Key) {
                Key k = (Key)view;
                mKeyboardLIstener.OnKey(k.GetCurrentLabel());
                if (mShifted) {
                    ToggleShift();
                }
            }
        }
    };
    public interface KeyboardListener {
        void onSwitchBoard();
        void OnKey(String key);
    }

    private KeyboardListener mKeyboardLIstener;
    private Button mButton;
    private Button mShiftKey;
    private Button mRowUp;
    private Button mRowDown;
    private LinearLayout mRowOne;
    private LinearLayout mRowTwo;
    private LinearLayout mRowThree;
    private LinearLayout mRowUtil;
    private boolean mShifted = false;
    private RowScrollView mRowScrollView;
    private int mScrollOffset;
    public KeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void DoStuff(KeyboardListener listener) {
        mKeyboardLIstener = listener;

        mRowOne = findViewById(R.id.row_one);
        mRowTwo = findViewById(R.id.row_two);
        mRowThree = findViewById(R.id.row_three);
        mRowUtil = findViewById(R.id.row_util);
        setListeners (mRowOne);
        setListeners (mRowTwo);
        setListeners (mRowThree);
        setListeners (mRowUtil);

        mButton = findViewById(R.id.switchBoard);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mKeyboardLIstener.onSwitchBoard();
            }
        });

        mShiftKey = findViewById(R.id.shift);
        mShiftKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToggleShift();
            }
        });
        mRowScrollView = findViewById(R.id.row_scroll);
        mRowUp = findViewById(R.id.row_up);
        mRowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRowScrollView.smoothScrollBy(0, 0 - mScrollOffset);
            }
        });
        mRowDown = findViewById(R.id.row_down);
        mRowDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mScrollOffset == 0) {
                    mScrollOffset = mRowOne.getBottom() - mRowOne.getTop();
                }
                mRowScrollView.smoothScrollBy(0, mScrollOffset);
            }
        });

    }

    private void ToggleShift() {
        mShifted = !mShifted;
        ShiftKeys(mRowOne);
        ShiftKeys(mRowTwo);
        ShiftKeys(mRowThree);
    }

    private void ShiftKeys (LinearLayout layout) {
        for (int c = 0; c < layout.getChildCount(); c++) {
            View v = layout.getChildAt(c);
            if (v instanceof Key) {
                ((Key)v).Shift(mShifted);
            }
        }
    }

    private void setListeners (LinearLayout layout){
        for (int c = 0; c < layout.getChildCount(); c++) {
            View v = layout.getChildAt(c);
            if (v instanceof Key) {
                v.setOnClickListener(mOnClick);
            }
        }
    }
}
