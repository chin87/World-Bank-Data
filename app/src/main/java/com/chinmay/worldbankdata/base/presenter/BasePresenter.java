package com.chinmay.worldbankdata.base.presenter;


import com.chinmay.worldbankdata.base.activity.BaseView;

/**
 * Created by chinmay.deshpande on 17/05/18.
 */

public interface BasePresenter<T extends BaseView> {

    T getView();
    void detach(final boolean instance);
    void attach(final T view);

}
