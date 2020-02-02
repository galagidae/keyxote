package com.galagidae.keyxote;

import android.inputmethodservice.InputMethodService;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.view.KeyEvent;
import android.view.View;

public class KeyboardService extends InputMethodService
            implements KeyboardView.KeyboardListener{

    private KeyboardView mKeyboardView;

    @Override
    public View onCreateInputView() {
        mKeyboardView = (KeyboardView) getLayoutInflater().inflate(
                R.layout.view_input, null);
        mKeyboardView.Initialize(this);
        return mKeyboardView;
    }

    @Override
    public void onStartInput(EditorInfo attribute, boolean restarting) {
        super.onStartInput(attribute, restarting);

        // Scroll the keyboard back to top/left
        if (mKeyboardView != null)
            mKeyboardView.ResetView();
    }

    public void onSwitchBoard() {
        this.switchToNextInputMethod(false);
    }

    public void OnKey(String key) {
        if (key == getString(R.string.backspace)) {
            getCurrentInputConnection().sendKeyEvent(
                    new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
            getCurrentInputConnection().sendKeyEvent(
                    new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DEL));
        } else
            getCurrentInputConnection().commitText(key, 0);
    }
}
