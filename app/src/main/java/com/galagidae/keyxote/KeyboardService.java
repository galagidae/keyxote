package com.galagidae.keyxote;

import android.inputmethodservice.InputMethodService;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.view.View;

public class KeyboardService extends InputMethodService
            implements KeyboardView.KeyboardListener{
    private KeyboardView mKeyboardView;
    private InputMethodManager mInputManager;
    private InputConnection mConnection;

    @Override
    public void onCreate () {
        super.onCreate();
        mInputManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
    }

    @Override
    public View onCreateInputView() {
        mKeyboardView = (KeyboardView) getLayoutInflater().inflate(
                R.layout.view_input, null);
        mKeyboardView.DoStuff(this);
        return mKeyboardView;
    }

    public void onSwitchBoard() {
        this.switchToNextInputMethod(false);
    }

    public void OnKey(String key) {
        if (mConnection == null) {
            mConnection = getCurrentInputConnection();
        }
        mConnection.commitText(key, 0);
    }
}
