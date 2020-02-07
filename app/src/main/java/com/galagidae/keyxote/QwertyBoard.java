package com.galagidae.keyxote;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

public class QwertyBoard extends ScrollView
        implements KeyboardContainer.KeyxoteBoard {

    // Key rows
    private HorizontalScrollView mKeyScrollView;
    private LinearLayout mAlpha1;
    private LinearLayout mAlpha2;
    private LinearLayout mAlpha3;
    private LinearLayout mNumeric;

    private View.OnClickListener mKeyListener;
    private boolean mInitialDraw = false;

    public QwertyBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void Initialize(View.OnClickListener keyListener) {
        mAlpha1 = findViewById(R.id.alpha1);
        mAlpha2 = findViewById(R.id.alpha2);
        mAlpha3 = findViewById(R.id.alpha3);
        mNumeric = findViewById(R.id.numeric);
        mKeyScrollView = findViewById(R.id.key_scroll);

        Utilities.SetKeyListeners (mAlpha1, keyListener);
        Utilities.SetKeyListeners (mAlpha2, keyListener);
        Utilities.SetKeyListeners (mAlpha3, keyListener);
        Utilities.SetKeyListeners (mNumeric, keyListener);
    }

    @Override
    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);
        if (!mInitialDraw) {
            ResetView();
            mInitialDraw = true;
        }
    }

    public void ShiftKeys (boolean shifted) {
        Utilities.ShiftRow(mAlpha1, shifted);
        Utilities.ShiftRow(mAlpha2, shifted);
        Utilities.ShiftRow(mAlpha3, shifted);
        Utilities.ShiftRow(mNumeric, shifted);
    }

    public void ResetView() {
        scrollTo(0,mNumeric.getHeight());
        mKeyScrollView.scrollTo(0,0);
    }

}
