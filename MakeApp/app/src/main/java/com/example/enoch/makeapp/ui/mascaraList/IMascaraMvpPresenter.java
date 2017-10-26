package com.example.enoch.makeapp.ui.mascaraList;

import com.example.enoch.makeapp.ui.base.MvpPresenter;

/**
 * Created by mainza1992 on 19/10/2017.
 */

public interface IMascaraMvpPresenter < V extends IMascaraMvpView> extends MvpPresenter<V> {

    void onViewPrepared();

}
