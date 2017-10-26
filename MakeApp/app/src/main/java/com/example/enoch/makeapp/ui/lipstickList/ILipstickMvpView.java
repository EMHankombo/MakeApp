package com.example.enoch.makeapp.ui.lipstickList;

import com.example.enoch.makeapp.data.model.ProductModel;
import com.example.enoch.makeapp.ui.base.MvpView;

import java.util.List;

/**
 * Created by mainza1992 on 19/10/2017.
 */

public interface ILipstickMvpView extends MvpView {

    void onFetchSuccess(List<ProductModel> productModel);

}
