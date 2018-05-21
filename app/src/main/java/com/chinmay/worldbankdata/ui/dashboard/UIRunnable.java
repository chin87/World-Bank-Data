package com.chinmay.worldbankdata.ui.dashboard;

import java.util.ArrayList;

public class UIRunnable<T> implements Runnable {
    private ArrayList<T> stories;
    private IRunonUi<T> runOnUi;

    public UIRunnable(ArrayList<T> stories, IRunonUi<T> runOnUi) {
        this.stories = stories;
        this.runOnUi = runOnUi;
    }

    @Override
    public void run() {
        runOnUi.runOnUi(stories);
    }
}
