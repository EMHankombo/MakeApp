package com.example.enoch.makeapp.di.module;

import android.app.Application;
import android.content.Context;

import com.example.enoch.makeapp.data.network.ApiHelper;
import com.example.enoch.makeapp.data.network.AppApiHelper;
import com.example.enoch.makeapp.data.network.AppDataManager;
import com.example.enoch.makeapp.data.network.DataManager;
import com.example.enoch.makeapp.di.scope.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mainza1992 on 30/10/2017.
 */

@Module
public class ApplicationModule {

    Application application;


    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @ApplicationContext
    Context context(){
        return  application;
    }

    @Provides
    Application getApplication(){
        return application;
    }

    @Provides
    @Singleton
    DataManager provideAppDataManager(AppDataManager appDataManager){
        return  appDataManager;
    }

    @Provides
    @Singleton
    ApiHelper provideAppApiHelper(AppApiHelper appApiHelper){
        return appApiHelper;
    }
}
