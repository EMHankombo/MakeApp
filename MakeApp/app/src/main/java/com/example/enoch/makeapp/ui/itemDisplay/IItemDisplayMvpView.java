package com.example.enoch.makeapp.ui.itemDisplay;

import com.example.enoch.makeapp.data.model.ItemDisplayModel;
import com.example.enoch.makeapp.ui.base.MvpView;

/**
 * Created by mainza1992 on 19/10/2017.
 */

public interface IItemDisplayMvpView extends MvpView {

    void onClickSuccess(ItemDisplayModel itemDisplayModel);
}
