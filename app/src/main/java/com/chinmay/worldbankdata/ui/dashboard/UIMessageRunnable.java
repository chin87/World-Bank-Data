package com.chinmay.worldbankdata.ui.dashboard;

public class UIMessageRunnable<S> implements Runnable{
    private S message;
    private IShowToast showToast;

    public UIMessageRunnable(S message, IShowToast<S> showToast){
        this.message = message;
        this.showToast = showToast;
    }

    @Override
    public void run() {
        showToast.toastMessage(message);
    }
}