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
    private Button mSpaceKey;
    private Button mQwertyKey;
    private Button mSymbolKey;
    private Button mFaceKey;
    private LayoutInflater mInfalter;
    private QwertyBoard mQweryView;
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
        ShowQwerty();
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
                    ShowQwerty();
                }
            });
            mSymbolKey.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ShowSymbols();
                }
            });
            mFaceKey.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ShowFaces();
                }
            });
        }
    }

    private void ShowSymbols() {
        if (mSymbolBoard == null)  {
            mSymbolBoard = (SymbolsBoard) mInfalter.inflate(R.layout.symbols_board, this, false);
            mSymbolBoard.Initialize(mOnKeyClick);
        }
        removeViewAt(0);
        mCurrentBoard = mSymbolBoard;
        mCurrentBoard.ResetView();
        addView(mSymbolBoard, 0);
    }

    private void ShowFaces() {
        if (mFaceBoard == null)  {
            mFaceBoard = (FaceBoard) mInfalter.inflate(R.layout.face_board, this, false);
            mFaceBoard.Initialize(mOnKeyClick);
        }
        removeViewAt(0);
        mCurrentBoard = mFaceBoard;
        mCurrentBoard.ResetView();
        addView(mFaceBoard, 0);
    }

    private void ShowQwerty() {
        removeViewAt(0);
        mCurrentBoard = mQweryView;
        mCurrentBoard.ResetView();
        addView(mQweryView, 0);
    }
}
