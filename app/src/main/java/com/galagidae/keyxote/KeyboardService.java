package com.galagidae.keyxote;

import android.inputmethodservice.InputMethodService;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.view.KeyEvent;
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
        if (key == getString(R.string.backspace)) {
            mConnection.sendKeyEvent(
                    new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
            mConnection.sendKeyEvent(
                    new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DEL));
        } else
            mConnection.commitText(key, 0);
    }
}
