package com.example.enoch.makeapp;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.enoch.makeapp.RealmAdapter.RealmProductAdapter;
import com.example.enoch.makeapp.data.database.localdb.ProductsDatabase;
import com.example.enoch.makeapp.data.database.localdb.controller.RealmController;
import com.example.enoch.makeapp.data.model.ProductModel;
import com.example.enoch.makeapp.di.component.DaggerIActivityComponent;
import com.example.enoch.makeapp.di.component.IActivityComponent;
import com.example.enoch.makeapp.di.module.ActivityModule;
import com.example.enoch.makeapp.ui.base.BaseFragment;
import com.example.enoch.makeapp.ui.lipstickList.ILipstickMvpView;
import com.example.enoch.makeapp.ui.lipstickList.LipStickPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

import static com.example.enoch.makeapp.MyApp.getApplication;


/**
 * A simple {@link Fragment} subclass.
 */
public class LipstickFragment extends BaseFragment implements ILipstickMvpView{

    IActivityComponent iActivityComponent;
    public IActivityComponent getiActivityComponent() {
        return iActivityComponent;
    }

    @BindView(R.id.recyclerLip) RecyclerView recyclerView;

    @BindView(R.id.swipeToRefreshLipstick) SwipeRefreshLayout swipeRefreshLayout;

    @Inject
    LipStickPresenter<ILipstickMvpView> lipstickMvpViewLipStickPresenter;

    Realm realm;
    RealmController realmController;
    ProductsDatabase productsDatabase;

    public LipstickFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        realm=Realm.getDefaultInstance();
        realmController= new RealmController(realm);


        return inflater.inflate(R.layout.fragment_lipstick, container, false);



    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        ButterKnife.bind(this,view);

        initializeRecycler(view);
        initialiseDagger();
        isNetworkConnected();

       /* lipstickMvpViewLipStickPresenter = new LipStickPresenter<>(new AppDataManager(),new AppSchedulerProvider(),
                new CompositeDisposable()); */

        lipstickMvpViewLipStickPresenter.onViewPrepared();
        lipstickMvpViewLipStickPresenter.onAttach(this);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();

            }
        });

        super.onViewCreated(view, savedInstanceState);

    }

    public void initializeRecycler(View view){

       // recyclerView = (RecyclerView) view.findViewById(R.id.recyclerLip);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

    }

    private void initialiseDagger() {
        iActivityComponent = DaggerIActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .iApplicationComponent(((MyApp) getApplication()).getiApplicationComponent())
                .build();

        getiActivityComponent().inject(this);
    }

    private void refreshContent() {

        Log.i("refresh","hello");

        onItemsLoadComplete();
    }

    private void onItemsLoadComplete() {

        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onFetchSuccess(List<ProductModel> productModel) {

        //Log.i("data",productModel.get(1).getName().toString());

        for(ProductModel resultForResult: productModel){

            productsDatabase = new ProductsDatabase();

            productsDatabase.setBrand(resultForResult.getBrand());
            productsDatabase.setName(resultForResult.getName());
            productsDatabase.setPrice(resultForResult.getPrice());






            realmController.saveLipStick(productsDatabase);
            Log.i("realm",productsDatabase.getName());
        }



        recyclerView.setAdapter(new MakeAppAdapter(productModel, R.layout.row, getActivity().getApplicationContext(), new onClickListener() {
            @Override
            public void onItemClick(ProductModel productModel) {

                int id = productModel.getId();

                Bundle args = new Bundle();

                args.putInt("id",id);

                Log.i("clickTest","clicked" + productModel.getName());
                ItemDisplayFragment itemFragment = new ItemDisplayFragment();

                itemFragment.setArguments(args);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content,itemFragment).addToBackStack(null).commit();
            }

    }));
    }



    @Override
    public void onError(String message) {
        super.onError(message);

        Log.i("stick",message);
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
            recyclerView.setAdapter(new RealmProductAdapter(realmController.getLipstick(), R.layout.row, getActivity().getApplicationContext(), new RealmProductOnclick() {
                @Override
                public void onItemClick(ProductsDatabase productsDatabase) {

                    int id = productsDatabase.getId();

                    Bundle args = new Bundle();

                    args.putInt("id",id);

                    Log.i("clickTest","clicked" + productsDatabase.getName());
                    ItemDisplayFragment itemFragment = new ItemDisplayFragment();

                    itemFragment.setArguments(args);

                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content,itemFragment).addToBackStack(null).commit();
                }


            }));
            return false; // no connection!
        }
    }


}
