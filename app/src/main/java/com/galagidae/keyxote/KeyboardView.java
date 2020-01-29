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
    private LinearLayout mKeyView;
    private boolean mShifted = false;
    private RowScrollView mRowScrollView;
    private int mScrollOffset;
    public KeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void DoStuff(KeyboardListener listener) {
        mKeyboardLIstener = listener;

        mKeyView = findViewById(R.id.keyview);
        for (int c = 0; c < mKeyView.getChildCount(); c++) {
            View v = mKeyView.getChildAt(c);
            if (v instanceof Key) {
                v.setOnClickListener(mOnClick);
            }
        }

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
                    mScrollOffset = mKeyView.getBottom() - mKeyView.getTop();
                }
                mRowScrollView.smoothScrollTo(0, mScrollOffset);
            }
        });

    }

    private void ToggleShift() {
        mShifted = !mShifted;
        for (int c = 0; c < mKeyView.getChildCount(); c++) {
            View v = mKeyView.getChildAt(c);
            if (v instanceof Key) {
                ((Key)v).Shift(mShifted);
            }
        }
    }

}
