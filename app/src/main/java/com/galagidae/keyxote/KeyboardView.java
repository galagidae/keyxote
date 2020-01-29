package com.galagidae.keyxote;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;
import android.view.View;

public class KeyboardView extends LinearLayout {

    public interface KeyboardListener {
        void onSwitchBoard();
        void OnKey();
    }

    private KeyboardListener mKeyboardLIstener;
    private Button mButton;
    private Button ma;
    public KeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void DoStuff(KeyboardListener listener) {
        mKeyboardLIstener = listener;
        ma = findViewById(R.id.lowera);
        ma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mKeyboardLIstener.OnKey();
            }
        });
        mButton = findViewById(R.id.switchBoard);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mKeyboardLIstener.onSwitchBoard();
            }
        });

    }


}
