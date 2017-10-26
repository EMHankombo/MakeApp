package com.example.enoch.makeapp.ui.itemDisplay;

import com.example.enoch.makeapp.ui.base.MvpPresenter;

/**
 * Created by mainza1992 on 20/10/2017.
 */

public interface IItemDisplayMvpPresenter < V extends IItemDisplayMvpView> extends MvpPresenter<V> {

    void onViewPrepared(int id);
}
