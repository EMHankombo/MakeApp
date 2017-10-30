package com.example.enoch.makeapp.di.component;

import android.app.Application;

import com.example.enoch.makeapp.MyApp;
import com.example.enoch.makeapp.data.network.DataManager;
import com.example.enoch.makeapp.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by mainza1992 on 03/10/2017.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface IApplicationComponent {

    void inject(MyApp myApp);

    Application getApplication();
    DataManager getDataManger();

}
