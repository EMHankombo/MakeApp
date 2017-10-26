package com.example.enoch.makeapp.ui.brandList;

import com.example.enoch.makeapp.data.model.BrandModel;
import com.example.enoch.makeapp.ui.base.MvpView;

import java.util.List;

/**
 * Created by mainza1992 on 19/10/2017.
 */

public interface IBrandListMvpView extends MvpView {

    void onFetchBrandSuccess(List<BrandModel> brandModels);
}
