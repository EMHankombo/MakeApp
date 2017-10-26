package com.example.enoch.makeapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.enoch.makeapp.data.model.ItemDisplayModel;
import com.example.enoch.makeapp.data.network.AppDataManager;
import com.example.enoch.makeapp.ui.base.BaseFragment;
import com.example.enoch.makeapp.ui.itemDisplay.IItemDisplayMvpPresenter;
import com.example.enoch.makeapp.ui.itemDisplay.IItemDisplayMvpView;
import com.example.enoch.makeapp.ui.itemDisplay.ItemDisplayPresenter;
import com.example.enoch.makeapp.ui.utils.rx.AppSchedulerProvider;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemDisplayFragment extends BaseFragment implements IItemDisplayMvpView {


    @BindView(R.id.recyclerItem) RecyclerView recyclerView;

    private IItemDisplayMvpPresenter<IItemDisplayMvpView> iItemDisplayMvpViewIItemDisplayMvpPresenter;
    public ItemDisplayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this,view);

        initialiseRecycler(view);

        int id = getArguments().getInt("id");

        iItemDisplayMvpViewIItemDisplayMvpPresenter = new ItemDisplayPresenter<>(new AppDataManager(),new AppSchedulerProvider(),
                new CompositeDisposable());

        //Pass the id as a parameter here
        iItemDisplayMvpViewIItemDisplayMvpPresenter.onViewPrepared(id);
        iItemDisplayMvpViewIItemDisplayMvpPresenter.onAttach(this);


    }

    public void initialiseRecycler(View view){

        //recyclerView = (RecyclerView)view.findViewById(R.id.recyclerItem);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
    }

    @Override
    public void onClickSuccess(ItemDisplayModel itemDisplayModel) {


        Log.i("itemDis",itemDisplayModel.getBrand());

        recyclerView.setAdapter(new ItemDisplayAdapter(itemDisplayModel,R.layout.row_item_display,getActivity().getApplicationContext()));

    }


}
