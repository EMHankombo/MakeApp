package com.example.enoch.makeapp.data.network;

import com.example.enoch.makeapp.data.model.ProductModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by mainza1992 on 18/10/2017.
 */

public interface ApiHelper {

    Observable<List<ProductModel>> useCaseLipstick();

    Observable<List<ProductModel>>  useCaseMascara();

    Observable<List<ProductModel>>  useCaseNailPolish();

    Observable<List<ProductModel>>  useCaseFoundation();
}
