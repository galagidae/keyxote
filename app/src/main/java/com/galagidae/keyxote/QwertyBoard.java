package com.galagidae.keyxote;

import android.content.Context;
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

    public QwertyBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void Initialize(View.OnClickListener keyListener) {

        mAlpha1 = findViewById(R.id.row_one);
        mAlpha2 = findViewById(R.id.row_two);
        mAlpha3 = findViewById(R.id.row_three);
        mNumeric = findViewById(R.id.row_four);
        mKeyScrollView = findViewById(R.id.key_scroll);

        Utilities.SetKeyListeners (mAlpha1, keyListener);
        Utilities.SetKeyListeners (mAlpha2, keyListener);
        Utilities.SetKeyListeners (mAlpha3, keyListener);
        Utilities.SetKeyListeners (mNumeric, keyListener);
    }

    public void ShiftKeys (boolean shifted) {
        Utilities.ShiftRow(mAlpha1, shifted);
        Utilities.ShiftRow(mAlpha2, shifted);
        Utilities.ShiftRow(mAlpha3, shifted);
        Utilities.ShiftRow(mNumeric, shifted);
    }

    public void ResetView() {
        scrollTo(0,0);
        mKeyScrollView.scrollTo(0,0);
    }

}
