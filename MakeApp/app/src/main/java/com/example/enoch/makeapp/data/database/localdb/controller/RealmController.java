package com.example.enoch.makeapp.data.database.localdb.controller;

import com.example.enoch.makeapp.data.database.localdb.ProductsDatabase;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by mainza1992 on 29/10/2017.
 */

public class RealmController {


    Realm realm;

    //realm constructor
    public RealmController(Realm realm) {
        this.realm = realm;
    }

    public void saveLipStick(final ProductsDatabase productsDatabase){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(productsDatabase);
            }
        });

    }

    public ArrayList<ProductsDatabase> getLipstick(){
        ArrayList<ProductsDatabase> productsDatabaseArrayList = new ArrayList<>();

        RealmResults<ProductsDatabase> databaseRealmResults = realm.where(ProductsDatabase.class).findAll();

        for(ProductsDatabase lipstickDatabase: databaseRealmResults){
            productsDatabaseArrayList.add(lipstickDatabase);
        }
          return productsDatabaseArrayList;
    }




    public void deleteDatabase() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.deleteAll();
            }
        });
    }

}
