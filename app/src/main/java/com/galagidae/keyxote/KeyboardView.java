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
            }
        }
    };
    public interface KeyboardListener {
        void onSwitchBoard();
        void OnKey(String key);
    }

    private KeyboardListener mKeyboardLIstener;
    private Button mButton;
    private Button ma;
    private LinearLayout mKeyView;
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

        //ma = findViewById(R.id.lowera);
        //ma.setOnClickListener(mOnClick);
        mButton = findViewById(R.id.switchBoard);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mKeyboardLIstener.onSwitchBoard();
            }
        });
    }
}
