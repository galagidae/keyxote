package com.galagidae.keyxote;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class BoardSelector extends LinearLayout implements KeyboardContainer.KeyxoteBoard {

    private LinearLayout mSymbol1;

    public BoardSelector(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void Initialize(View.OnClickListener keyListener) {
        mSymbol1 = findViewById(R.id.symbol1);
        Utilities.SetKeyListeners(mSymbol1, keyListener);
    }

    public void ResetView(){}

    public void ShiftKeys (boolean shifted) { }
}
