package com.galagidae.keyxote;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.view.View;

public class KeyboardView extends LinearLayout {

    private View.OnClickListener mOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view instanceof Key) {
                Key k = (Key)view;
                mKeyboardLIstener.OnKey(k.GetCurrentChar());
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
    private Button mSwitch;
    private Button mShiftKey;
    private LinearLayout mRowOne;
    private LinearLayout mRowTwo;
    private LinearLayout mRowThree;
    private LinearLayout mRowFour;
    private LinearLayout mRowUtil;
    private boolean mShifted = false;
    private RowScrollView mRowScrollView;
    private HorizontalScrollView mKeyScrollView;
    public KeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void Initialize(KeyboardListener listener) {
        mKeyboardLIstener = listener;

        mRowOne = findViewById(R.id.row_one);
        mRowTwo = findViewById(R.id.row_two);
        mRowThree = findViewById(R.id.row_three);
        mRowFour = findViewById(R.id.row_four);
        mRowUtil = findViewById(R.id.row_util);
        setListeners (mRowOne);
        setListeners (mRowTwo);
        setListeners (mRowThree);
        setListeners (mRowFour);
        setListeners (mRowUtil);

        mSwitch = findViewById(R.id.switchBoard);
        mSwitch.setOnClickListener(new View.OnClickListener() {
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
        mKeyScrollView = findViewById(R.id.key_scroll);
    }

    private void ToggleShift() {
        mShifted = !mShifted;
        ShiftKeys(mRowOne);
        ShiftKeys(mRowTwo);
        ShiftKeys(mRowThree);
        ShiftKeys(mRowFour);
    }

    private void ShiftKeys (LinearLayout layout) {
        // This is dum. Should add a more event-driven mechanism
        for (int c = 0; c < layout.getChildCount(); c++) {
            View v = layout.getChildAt(c);
            if (v instanceof Key) {
                ((Key)v).Shift(mShifted);
            }
        }
    }

    private void setListeners (LinearLayout layout){
        // This is dumb. Need to find a better way of wiring up keys
        for (int c = 0; c < layout.getChildCount(); c++) {
            View v = layout.getChildAt(c);
            if (v instanceof Key) {
                v.setOnClickListener(mOnClick);
            }
        }
    }

    public void ResetView() {
        mRowScrollView.scrollTo(0, 0);
        mKeyScrollView.scrollTo(0, 0);
    }
}
