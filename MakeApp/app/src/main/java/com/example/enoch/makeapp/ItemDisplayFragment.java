package com.example.enoch.makeapp;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.enoch.makeapp.RealmAdapter.RealmIndividualAdapter;
import com.example.enoch.makeapp.data.database.localdb.IndividualItemDatabase;
import com.example.enoch.makeapp.data.database.localdb.controller.RealmController;
import com.example.enoch.makeapp.data.model.ItemDisplayModel;
import com.example.enoch.makeapp.di.component.DaggerIActivityComponent;
import com.example.enoch.makeapp.di.component.IActivityComponent;
import com.example.enoch.makeapp.di.module.ActivityModule;
import com.example.enoch.makeapp.ui.base.BaseFragment;
import com.example.enoch.makeapp.ui.itemDisplay.IItemDisplayMvpPresenter;
import com.example.enoch.makeapp.ui.itemDisplay.IItemDisplayMvpView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

import static com.example.enoch.makeapp.MyApp.getApplication;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemDisplayFragment extends BaseFragment implements IItemDisplayMvpView {

    IActivityComponent iActivityComponent;

    public IActivityComponent getiActivityComponent() {
        return iActivityComponent;
    }

    Realm realm;
    RealmController realmController;
    IndividualItemDatabase individualItemDatabase;



    @BindView(R.id.recyclerItem) RecyclerView recyclerView;

    @Inject
   IItemDisplayMvpPresenter<IItemDisplayMvpView> iItemDisplayMvpViewIItemDisplayMvpPresenter;

    public ItemDisplayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        realm= Realm.getDefaultInstance();
        realmController= new RealmController(realm);
        return inflater.inflate(R.layout.fragment_item, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this,view);

        initialiseRecycler(view);

        initialiseDagger();
        isNetworkConnected();

        int id = getArguments().getInt("id");

      /*  iItemDisplayMvpViewIItemDisplayMvpPresenter = new ItemDisplayPresenter<>(new AppDataManager(),new AppSchedulerProvider(),
                new CompositeDisposable()); */

        //Pass the id as a parameter here
        iItemDisplayMvpViewIItemDisplayMvpPresenter.onViewPrepared(id);
        iItemDisplayMvpViewIItemDisplayMvpPresenter.onAttach(this);


    }

    public void initialiseRecycler(View view){

        //recyclerView = (RecyclerView)view.findViewById(R.id.recyclerItem);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
    }

    private void initialiseDagger() {
        iActivityComponent = DaggerIActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .iApplicationComponent(((MyApp) getApplication()).getiApplicationComponent())
                .build();

        getiActivityComponent().inject(this);
    }

    @Override
    public void onClickSuccess(ItemDisplayModel itemDisplayModel) {

        individualItemDatabase = new IndividualItemDatabase();

        individualItemDatabase.setId(itemDisplayModel.getId());
        individualItemDatabase.setBrand(itemDisplayModel.getBrand());
        individualItemDatabase.setName(itemDisplayModel.getName());
        individualItemDatabase.setPrice(itemDisplayModel.getPrice());
        individualItemDatabase.setDescription(itemDisplayModel.getDescription());

        realmController.saveIndividualItem(individualItemDatabase);

        Log.i("itemDis",individualItemDatabase.getBrand());

        recyclerView.setAdapter(new ItemDisplayAdapter(itemDisplayModel,R.layout.row_item_display,getActivity().getApplicationContext()));

    }




    public boolean isNetworkConnected() {


        // get Connectivity Manager to get network status
        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Log.i("Connection test","passed");
            return true; //we have a connection
        } else {

            individualItemDatabase = realmController.getOneItem(getArguments().getInt("id"));
            recyclerView.setAdapter(new RealmIndividualAdapter(individualItemDatabase, R.layout.row_item_display, getActivity().getApplicationContext()));
        }
            return false; // no connection!
        }
    }


