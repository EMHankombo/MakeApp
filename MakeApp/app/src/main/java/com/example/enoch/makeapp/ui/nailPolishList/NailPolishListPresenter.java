package com.example.enoch.makeapp.ui.nailPolishList;

import com.example.enoch.makeapp.data.model.ProductModel;
import com.example.enoch.makeapp.data.network.DataManager;
import com.example.enoch.makeapp.ui.base.BasePresenter;
import com.example.enoch.makeapp.ui.utils.rx.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by mainza1992 on 19/10/2017.
 */

public class NailPolishListPresenter<V extends INailPolishListMvpView> extends BasePresenter<V> implements INailPolishListMvpPresenter<V> {

    @Inject
    public NailPolishListPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared() {

        getCompositeDisposable()
                .add(getDataManager()
                        .useCaseNailPolish()
                        .observeOn(getSchedulerProvider().ui())
                        .subscribeOn(getSchedulerProvider().io())
                        .subscribe(new Consumer<List<ProductModel>>() {
                            @Override
                            public void accept(@NonNull List<ProductModel> productModels) throws Exception {
                                getMvpView().onFetchSuccess(productModels);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                      getMvpView().onError(throwable.getMessage());
                            }
                        }));

    }
}
