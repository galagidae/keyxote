package com.galagidae.keyxote;

import androidx.annotation.LayoutRes;
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
    private Button mSpaceKey;
    private Button mQwertyKey;
    private Button mSymbolKey;
    private Button mFaceKey;
    private LayoutInflater mInfalter;
    private QwertyBoard mQweryBoard;
    private BoardSelector mSelector;
    private SymbolsBoard mSymbolBoard;
    private FaceBoard mFaceBoard;
    private KeyxoteBoard mCurrentBoard;

    private LinearLayout mRowUtil;
    private Casing mCaseState = Casing.NORMAL;

    public KeyboardContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInfalter = LayoutInflater.from(context);
    }

    public void Initialize(KeyboardListener listener) {
        mKeyboardLIstener = listener;

        ShowBoard(R.layout.qwerty_board);

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

        mSpaceKey = findViewById(R.id.space);
        mSpaceKey.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ShowSelector();
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
        ShowBoard(R.layout.qwerty_board);
    }

    private void ShowSelector() {
        boolean wasReady = true;
        if (mSelector == null)  {
            wasReady = false;
            mSelector = (BoardSelector) mInfalter.inflate(R.layout.selector_board, this, false);
        }
        removeViewAt(0);
        mCurrentBoard = mSelector;
        addView(mSelector, 0);
        if (!wasReady) {
            mQwertyKey = findViewById(R.id.qwerty_key);
            mSymbolKey = findViewById(R.id.symbol_key);
            mFaceKey = findViewById(R.id.face_key);
            mQwertyKey.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ShowBoard(R.layout.qwerty_board);
                }
            });
            mSymbolKey.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ShowBoard(R.layout.symbols_board);
                }
            });
            mFaceKey.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ShowBoard(R.layout.face_board);
                }
            });
        }
    }

    private void ShowBoard (@LayoutRes int resource) {
        boolean init = false;
        switch (resource) {
            case R.layout.qwerty_board:
                if (mQweryBoard == null)  {
                    mQweryBoard = (QwertyBoard) mInfalter.inflate(resource, this, false);
                    init = true;
                }
                mCurrentBoard = mQweryBoard;
                break;
            case R.layout.selector_board:
                break;
            case R.layout.symbols_board:
                if (mSymbolBoard == null)  {
                    mSymbolBoard = (SymbolsBoard) mInfalter.inflate(resource, this, false);
                    init = true;
                }
                mCurrentBoard = mSymbolBoard;
                break;
            case R.layout.face_board:
                if (mFaceBoard == null)  {
                    mFaceBoard = (FaceBoard) mInfalter.inflate(resource, this, false);
                    init = true;
                }
                mCurrentBoard = mFaceBoard;
                break;
        }
        if (init)
            mCurrentBoard.Initialize(mOnKeyClick);
        removeViewAt(0);
        mCurrentBoard.ResetView();
        mCurrentBoard.ShiftKeys(mCaseState != Casing.NORMAL);
        addView((View)mCurrentBoard, 0);
    }
}
