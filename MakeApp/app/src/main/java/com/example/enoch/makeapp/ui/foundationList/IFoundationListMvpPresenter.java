package com.example.enoch.makeapp.ui.foundationList;

import com.example.enoch.makeapp.ui.base.MvpPresenter;

/**
 * Created by mainza1992 on 19/10/2017.
 */

public interface IFoundationListMvpPresenter < V extends IFoundationListMvpView> extends MvpPresenter<V> {
    void onViewPrepared();
}
