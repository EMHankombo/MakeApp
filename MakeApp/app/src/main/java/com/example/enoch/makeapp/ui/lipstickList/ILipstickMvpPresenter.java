package com.example.enoch.makeapp.ui.lipstickList;

import com.example.enoch.makeapp.ui.base.MvpPresenter;

/**
 * Created by mainza1992 on 19/10/2017.
 */

public interface ILipstickMvpPresenter < V extends ILipstickMvpView> extends MvpPresenter<V> {

    void onViewPrepared();
}
