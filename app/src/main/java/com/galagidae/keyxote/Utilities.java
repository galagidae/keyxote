package com.galagidae.keyxote;

import android.view.View;
import android.widget.LinearLayout;

public class Utilities {

    public static void ShiftRow (LinearLayout layout, boolean shifted) {
        for (int c = 0; c < layout.getChildCount(); c++) {
            View v = layout.getChildAt(c);
            if (v instanceof Key) {
                ((Key)v).Shift(shifted);
            }
        }
    }

    public static void SetKeyListeners (LinearLayout layout, View.OnClickListener listener){
        for (int c = 0; c < layout.getChildCount(); c++) {
            View v = layout.getChildAt(c);
            if (v instanceof Key) {
                v.setOnClickListener(listener);
            }
        }
    }
}
