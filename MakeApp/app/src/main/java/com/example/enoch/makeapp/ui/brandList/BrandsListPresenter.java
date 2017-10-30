package com.example.enoch.makeapp.ui.brandList;

import android.util.Log;

import com.example.enoch.makeapp.data.model.BrandModel;
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

public class BrandsListPresenter <V extends IBrandListMvpView> extends BasePresenter<V> implements IBrandListMvpPresenter<V> {

    @Inject
    public BrandsListPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared(String brand) {

        getCompositeDisposable()
                .add(getDataManager()
                        .useCaseBrand(brand)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<List<BrandModel>>() {
                            @Override
                            public void accept(@NonNull List<BrandModel> brandModels) throws Exception {
                                getMvpView().onFetchBrandSuccess(brandModels);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                 getMvpView().onError(throwable.getMessage());

                                Log.i("dta","no data");
                            }
                        }));

    }
}
