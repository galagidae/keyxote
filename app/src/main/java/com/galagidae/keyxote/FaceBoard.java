package com.galagidae.keyxote;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class FaceBoard extends ScrollView implements KeyboardContainer.KeyxoteBoard {

    private HorizontalScrollView mFaceScroll;
    private LinearLayout mFaces1;
    private LinearLayout mFaces2;
    private LinearLayout mFaces3;
    private LinearLayout mFaces4;
    private LinearLayout mFaces5;

    public FaceBoard (Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void Initialize (View.OnClickListener keyListener) {

        mFaces1 = findViewById(R.id.faces1);
        mFaces2 = findViewById(R.id.faces2);
        mFaces3 = findViewById(R.id.faces3);
        mFaces4 = findViewById(R.id.faces4);
        mFaces5 = findViewById(R.id.faces5);
        mFaceScroll = findViewById(R.id.faces_scroll);

        Utilities.SetKeyListeners(mFaces1, keyListener);
        Utilities.SetKeyListeners(mFaces2, keyListener);
        Utilities.SetKeyListeners(mFaces3, keyListener);
        Utilities.SetKeyListeners(mFaces4, keyListener);
        Utilities.SetKeyListeners(mFaces5, keyListener);
    }

    public void ShiftKeys (boolean shifted) {

    }

    public void ResetView() {
        scrollTo(0, 0);
        mFaceScroll.scrollTo(0,0);
    }
}
