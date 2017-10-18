package com.example.enoch.makeapp.data.network.service;

import com.example.enoch.makeapp.data.model.ProductModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by mainza1992 on 18/10/2017.
 */

public interface IRequestInterface {

    //all the request that will be make
    //The data returned is a list
    //all the api end points have the same model structure so only one model is needed

    @GET(API_CONSTANTS.LIPSTICK_API)
    Observable<List<ProductModel>> getLipStick();

    @GET(API_CONSTANTS.MASCARA_API)
    Observable<List<ProductModel>>  getMascara();

    @GET(API_CONSTANTS.NAIL_POLISH_API)
    Observable<List<ProductModel>> getNailPolish();

    @GET(API_CONSTANTS.FOUNDATION_API)
    Observable<List<ProductModel>> getFoundation();
}
