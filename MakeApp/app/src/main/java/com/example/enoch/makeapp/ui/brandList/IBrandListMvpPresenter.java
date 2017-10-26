package com.example.enoch.makeapp.ui.brandList;

import com.example.enoch.makeapp.ui.base.MvpPresenter;

/**
 * Created by mainza1992 on 19/10/2017.
 */

public interface IBrandListMvpPresenter < V extends IBrandListMvpView> extends MvpPresenter<V> {

    //a brand name is going to be passed into it
    void onViewPrepared(String brand);
}
