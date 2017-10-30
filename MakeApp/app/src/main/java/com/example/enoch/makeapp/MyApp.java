package com.example.enoch.makeapp;

import android.app.Application;
import android.content.Context;

import com.example.enoch.makeapp.di.component.DaggerIApplicationComponent;
import com.example.enoch.makeapp.di.component.IApplicationComponent;
import com.example.enoch.makeapp.di.module.ApplicationModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by mainza1992 on 18/10/2017.
 */

public class MyApp extends Application {

    IApplicationComponent iApplicationComponent;

    public IApplicationComponent getiApplicationComponent() {
        return iApplicationComponent;
    }

    public static Application sApplication;

    public static Application getApplication() {
        return sApplication;
    }

    public static Context getContext() {
        return getApplication().getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;


       iApplicationComponent = DaggerIApplicationComponent.builder()
               .applicationModule(new ApplicationModule(this))
               .build();

        getiApplicationComponent().inject(this);

        Realm.init(getApplicationContext());

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
