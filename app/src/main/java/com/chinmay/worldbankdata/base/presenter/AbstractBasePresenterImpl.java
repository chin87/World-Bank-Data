package com.chinmay.worldbankdata.base.presenter;



import com.chinmay.worldbankdata.base.activity.BaseView;

import java.lang.ref.WeakReference;

/**
 * Created by chinmay.deshpande on 17/05/18.
 */

public abstract class AbstractBasePresenterImpl<T extends BaseView> implements BasePresenter<T> {

    private WeakReference<T> mView;

    private AbstractBasePresenterImpl() {
        // do nothing
    }

    public AbstractBasePresenterImpl(final T view) {
        super();
        attach(view);
    }

    @Override
    public void attach(T view) {
        if (getView() == null) {
            this.mView = new WeakReference<T>(view);
        }
    }

    @Override
    public void detach(boolean instance) {
        if (this.mView != null) {
            this.mView.clear();
        }

    }

    public T getView() {
        return mView == null ? null : this.mView.get();
    }

    public boolean isViewAttached() {
        return this.mView != null && this.mView.get() != null;
    }
}
