package com.galagidae.keyxote;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;
import android.view.LayoutInflater;
import android.view.View;

public class KeyboardContainer extends LinearLayout {

    private enum Casing {
        NORMAL, SHIFT, CAPS
    }

    public interface KeyxoteBoard {
        void Initialize(OnClickListener keyListener);
        void ResetView();
        void ShiftKeys(boolean shifted);
    }
    public interface KeyboardListener {
        void OnKey(String key);
    }

    private View.OnClickListener mOnKeyClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view instanceof Key) {
                Key k = (Key)view;
                mKeyboardLIstener.OnKey(k.GetCurrentChar());
                if (mCaseState == Casing.SHIFT) {
                    SetCasing(Casing.NORMAL);
                }
            }
        }
    };

    private KeyboardListener mKeyboardLIstener;
    private Button mShiftKey;
    private LayoutInflater mInfalter;
    private QwertyBoard mQweryView;
    private KeyxoteBoard mCurrentBoard;

    private LinearLayout mRowUtil;
    private Casing mCaseState = Casing.NORMAL;

    public KeyboardContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInfalter = LayoutInflater.from(context);
    }

    public void Initialize(KeyboardListener listener) {
        mKeyboardLIstener = listener;

        // Init with qwerty
        mQweryView = (QwertyBoard) mInfalter.inflate(R.layout.qwerty_board, this, false);
        mCurrentBoard = mQweryView;
        mCurrentBoard.Initialize(mOnKeyClick);
        addView(mQweryView, 0);

        mRowUtil = findViewById(R.id.row_util);
        Utilities.SetKeyListeners(mRowUtil, mOnKeyClick);

        mShiftKey = findViewById(R.id.shift);
        mShiftKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetCasing((mCaseState != Casing.NORMAL)? Casing.NORMAL : Casing.SHIFT);
            }
        });
        mShiftKey.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                SetCasing((mCaseState == Casing.CAPS)? Casing.NORMAL : Casing.CAPS);
                return true;
            }
        });
    }

    private void SetCasing (Casing casing) {
        mCaseState = casing;
        mCurrentBoard.ShiftKeys(casing != Casing.NORMAL);
        mShiftKey.setSelected(mCaseState == Casing.CAPS);
    }

    public void  ResetView(){
        mCurrentBoard.ResetView();
    }
}
