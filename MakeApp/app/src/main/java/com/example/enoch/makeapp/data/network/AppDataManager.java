package com.example.enoch.makeapp.data.network;

import com.example.enoch.makeapp.data.model.ProductModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by mainza1992 on 18/10/2017.
 */

public class AppDataManager implements DataManager {

    @Override
    public Observable<List<ProductModel>> useCaseLipstick() {
        return null;
    }

    @Override
    public Observable<List<ProductModel>> useCaseMascara() {
        return null;
    }

    @Override
    public Observable<List<ProductModel>> useCaseNailPolish() {
        return null;
    }

    @Override
    public Observable<List<ProductModel>> useCaseFoundation() {
        return null;
    }
}
