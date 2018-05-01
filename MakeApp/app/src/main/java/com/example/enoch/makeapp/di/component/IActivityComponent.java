package com.example.enoch.makeapp.di.component;


import com.example.enoch.makeapp.Fragments.BrandFragment;
import com.example.enoch.makeapp.Fragments.FoundationFragment;
import com.example.enoch.makeapp.Fragments.HomeBrandFragment;
import com.example.enoch.makeapp.Fragments.HomeFragment;
import com.example.enoch.makeapp.Fragments.ItemDisplayFragment;
import com.example.enoch.makeapp.Fragments.LipstickFragment;
import com.example.enoch.makeapp.Fragments.MascaraFragment;
import com.example.enoch.makeapp.Fragments.NailPolishFragment;
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
