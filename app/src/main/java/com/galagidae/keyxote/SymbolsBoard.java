package com.galagidae.keyxote;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class SymbolsBoard  extends HorizontalScrollView implements KeyboardContainer.KeyxoteBoard {

    private LinearLayout mSymbol1;
    private LinearLayout mSymbol2;

    public SymbolsBoard (Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void Initialize (View.OnClickListener keyListener) {
        mSymbol1 = findViewById(R.id.symbol1);
        mSymbol2 = findViewById(R.id.symbol2);
        Utilities.SetKeyListeners(mSymbol1, keyListener);
        Utilities.SetKeyListeners(mSymbol2, keyListener);
    }

    public void ShiftKeys (boolean shifted) {
        Utilities.ShiftRow(mSymbol1, shifted);
        Utilities.ShiftRow(mSymbol2, shifted);
    }

    public void ResetView() {
        scrollTo(0,0);
    }
}
