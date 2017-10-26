package com.example.enoch.makeapp.data.network;

import com.example.enoch.makeapp.data.model.BrandModel;
import com.example.enoch.makeapp.data.model.ItemDisplayModel;
import com.example.enoch.makeapp.data.model.ProductModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by mainza1992 on 18/10/2017.
 */

public class AppDataManager implements DataManager {

    private ApiHelper apiHelper;

    public AppDataManager( ) {
        this.apiHelper = new AppApiHelper();
    }

    @Override
    public Observable<List<ProductModel>> useCaseLipstick() {
        return apiHelper.useCaseLipstick();
    }

    @Override
    public Observable<List<ProductModel>> useCaseMascara() {
        return apiHelper.useCaseMascara();
    }

    @Override
    public Observable<List<ProductModel>> useCaseNailPolish() {
        return apiHelper.useCaseNailPolish();
    }

    @Override
    public Observable<List<ProductModel>> useCaseFoundation() {
        return apiHelper.useCaseFoundation();
    }

    @Override
    public Observable<List<BrandModel>> useCaseBrand(String brand) {
        return apiHelper.useCaseBrand(brand);
    }

    @Override
    public Observable<ItemDisplayModel> useCaseItemDisplay(int id) {
        return apiHelper.useCaseItemDisplay(id);
    }
}
