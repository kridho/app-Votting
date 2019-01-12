package com.example.kridho.aplikasivoting.popup;

import android.support.annotation.StringRes;

import com.example.kridho.aplikasivoting.R;

public class DialogFactory {
    private DialogFactory() {
    }

    public static OneButtonDialog makeSuccessDialog(@StringRes int titleId,
                                             @StringRes int messageId,
                                             @StringRes int buttonTextId,
                                             OneButtonDialog.ButtonDialogAction action) {
        return OneButtonDialog.newInstance(titleId,
                messageId,
                buttonTextId,
                R.drawable.ic_checked,
                R.color.green_500,
                action);
    }

    public static OneButtonDialog makeErrorDialog(@StringRes int titleId,
                                           @StringRes int messageId,
                                           @StringRes int buttonTextId,
                                           OneButtonDialog.ButtonDialogAction action) {
        return OneButtonDialog.newInstance(titleId,
                messageId,
                buttonTextId,
                R.drawable.ic_close,
                R.color.red_500,
                action);
    }

}

