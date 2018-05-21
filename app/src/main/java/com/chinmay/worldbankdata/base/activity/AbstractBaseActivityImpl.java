package com.chinmay.worldbankdata.base.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.chinmay.worldbankdata.base.presenter.BasePresenter;


/**
 * Created by chinmay.deshpande on 17/05/18.
 */

public abstract class AbstractBaseActivityImpl<T extends BaseView, U extends BasePresenter<T>> extends AppCompatActivity {

    protected U presenter;
    private T view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = createPresenter();
        view = presenter.getView();
    }

    protected abstract U createPresenter();

    public U getPresenter() {
        return presenter;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (view == null) {
            presenter.attach(view);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.detach(true);
    }
}
