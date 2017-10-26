package com.example.enoch.makeapp.ui.nailPolishList;

import com.example.enoch.makeapp.ui.base.MvpPresenter;

/**
 * Created by mainza1992 on 19/10/2017.
 */

public interface INailPolishListMvpPresenter < V extends INailPolishListMvpView> extends MvpPresenter<V> {

    void onViewPrepared();
}
