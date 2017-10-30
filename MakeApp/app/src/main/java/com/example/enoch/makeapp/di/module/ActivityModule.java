package com.example.enoch.makeapp.di.module;

import com.example.enoch.makeapp.ui.base.BaseFragment;
import com.example.enoch.makeapp.ui.brandList.BrandsListPresenter;
import com.example.enoch.makeapp.ui.brandList.IBrandListMvpPresenter;
import com.example.enoch.makeapp.ui.brandList.IBrandListMvpView;
import com.example.enoch.makeapp.ui.foundationList.FoundationListPresenter;
import com.example.enoch.makeapp.ui.foundationList.IFoundationListMvpPresenter;
import com.example.enoch.makeapp.ui.foundationList.IFoundationListMvpView;
import com.example.enoch.makeapp.ui.itemDisplay.IItemDisplayMvpPresenter;
import com.example.enoch.makeapp.ui.itemDisplay.IItemDisplayMvpView;
import com.example.enoch.makeapp.ui.itemDisplay.ItemDisplayPresenter;
import com.example.enoch.makeapp.ui.lipstickList.ILipstickMvpPresenter;
import com.example.enoch.makeapp.ui.lipstickList.ILipstickMvpView;
import com.example.enoch.makeapp.ui.lipstickList.LipStickPresenter;
import com.example.enoch.makeapp.ui.mascaraList.IMascaraMvpPresenter;
import com.example.enoch.makeapp.ui.mascaraList.IMascaraMvpView;
import com.example.enoch.makeapp.ui.mascaraList.MascaraPresenter;
import com.example.enoch.makeapp.ui.nailPolishList.INailPolishListMvpPresenter;
import com.example.enoch.makeapp.ui.nailPolishList.INailPolishListMvpView;
import com.example.enoch.makeapp.ui.nailPolishList.NailPolishListPresenter;
import com.example.enoch.makeapp.ui.utils.rx.AppSchedulerProvider;
import com.example.enoch.makeapp.ui.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by mainza1992 on 30/10/2017.
 */

@Module
public class ActivityModule {

    BaseFragment fragment;

    public ActivityModule(BaseFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    BaseFragment getFragment() {
        return fragment;
    }

    @Provides
    CompositeDisposable getCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider getSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    /**
     * presenter objects
     *
     */

    @Provides
    IBrandListMvpPresenter<IBrandListMvpView> iBrandListMvpPresenter (BrandsListPresenter<IBrandListMvpView> brandsListPresenter){
        return  brandsListPresenter;
    }

    @Provides
    IFoundationListMvpPresenter<IFoundationListMvpView> iFoundationListMvpPresenter(FoundationListPresenter<IFoundationListMvpView> foundationListPresenter){
        return foundationListPresenter;
    }

    @Provides
    IItemDisplayMvpPresenter<IItemDisplayMvpView> iItemDisplayMvpPresenter (ItemDisplayPresenter<IItemDisplayMvpView> itemDisplayPresenter){
        return itemDisplayPresenter;
    }

    @Provides
    ILipstickMvpPresenter<ILipstickMvpView> iLipstickMvpPresenter (LipStickPresenter<ILipstickMvpView> lipStickPresenter){
        return lipStickPresenter;
    }

    @Provides
    IMascaraMvpPresenter<IMascaraMvpView> iMascaraMvpPresenter (MascaraPresenter<IMascaraMvpView> mascaraPresenter){
        return mascaraPresenter;
    }

    @Provides
    INailPolishListMvpPresenter<INailPolishListMvpView> iNailPolishListMvpPresenter (NailPolishListPresenter<INailPolishListMvpView> nailPolishListPresenter){
        return nailPolishListPresenter;
    }

}
