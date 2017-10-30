package com.example.enoch.makeapp.ui.itemDisplay;

import com.example.enoch.makeapp.data.model.ItemDisplayModel;
import com.example.enoch.makeapp.data.network.DataManager;
import com.example.enoch.makeapp.ui.base.BasePresenter;
import com.example.enoch.makeapp.ui.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by mainza1992 on 20/10/2017.
 */

public class ItemDisplayPresenter <V extends IItemDisplayMvpView> extends BasePresenter<V> implements IItemDisplayMvpPresenter<V> {

    @Inject
    public ItemDisplayPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared(int id) {


        getCompositeDisposable()
                .add(getDataManager()
                        .useCaseItemDisplay(id)
                        .observeOn(getSchedulerProvider().ui())
                        .subscribeOn(getSchedulerProvider().io())
                        .subscribe(new Consumer<ItemDisplayModel>() {
                            @Override
                            public void accept(@NonNull ItemDisplayModel itemDisplayModel) throws Exception {

                                getMvpView().onClickSuccess(itemDisplayModel);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                 getMvpView().onError(throwable.getMessage());
                            }
                        }));
    }
}
