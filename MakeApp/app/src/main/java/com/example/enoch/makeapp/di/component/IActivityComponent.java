package com.example.enoch.makeapp.di.component;


import com.example.enoch.makeapp.BrandFragment;
import com.example.enoch.makeapp.FoundationFragment;
import com.example.enoch.makeapp.HomeBrandFragment;
import com.example.enoch.makeapp.HomeFragment;
import com.example.enoch.makeapp.ItemDisplayFragment;
import com.example.enoch.makeapp.LipstickFragment;
import com.example.enoch.makeapp.MascaraFragment;
import com.example.enoch.makeapp.NailPolishFragment;
import com.example.enoch.makeapp.di.module.ActivityModule;
import com.example.enoch.makeapp.di.scope.PerActivity;

import dagger.Component;

/**
 * Created by mainza1992 on 03/10/2017.
 */

@PerActivity

@Component(dependencies = IApplicationComponent.class, modules = ActivityModule.class)
public interface IActivityComponent {

    void inject(BrandFragment brandFragment);
    void inject(FoundationFragment foundationFragment);
    void inject(HomeBrandFragment homeBrandFragment);
    void inject(HomeFragment homeFragment);
    void inject(ItemDisplayFragment itemDisplayFragment);
    void inject(LipstickFragment lipstickFragment);
    void inject(MascaraFragment mascaraFragment);
    void inject(NailPolishFragment nailPolishFragment);
}
