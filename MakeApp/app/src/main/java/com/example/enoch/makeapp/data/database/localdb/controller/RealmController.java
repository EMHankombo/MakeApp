package com.example.enoch.makeapp.data.database.localdb.controller;

import com.example.enoch.makeapp.data.database.localdb.IndividualItemDatabase;
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


    public void saveIndividualItem(final IndividualItemDatabase individualItemDatabase){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(individualItemDatabase);
            }
        });

    }

    public ArrayList<IndividualItemDatabase> getAllIndividualItem(){
        ArrayList<IndividualItemDatabase> individualItemDatabaseArrayList = new ArrayList<>();

        RealmResults<IndividualItemDatabase> databaseRealmResults = realm.where(IndividualItemDatabase.class).findAll();

        for(IndividualItemDatabase individualItemDatabase: databaseRealmResults){
            individualItemDatabaseArrayList.add(individualItemDatabase);
        }
         return  individualItemDatabaseArrayList;
    }

    public IndividualItemDatabase getOneItem(int id){

        IndividualItemDatabase individualItemDatabase = realm.where(IndividualItemDatabase.class).equalTo("id",id).findFirst();

        return individualItemDatabase;
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
