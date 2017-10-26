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

import com.example.enoch.makeapp.data.model.ProductModel;
import com.example.enoch.makeapp.data.network.AppDataManager;
import com.example.enoch.makeapp.ui.base.BaseFragment;
import com.example.enoch.makeapp.ui.foundationList.FoundationListPresenter;
import com.example.enoch.makeapp.ui.foundationList.IFoundationListMvpView;
import com.example.enoch.makeapp.ui.utils.rx.AppSchedulerProvider;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;


/**
 * A simple {@link Fragment} subclass.
 */
public class FoundationFragment extends BaseFragment implements IFoundationListMvpView {

    RecyclerView recyclerView;

    private FoundationListPresenter<IFoundationListMvpView> foundationListMvpViewFoundationListPresenter;

    public FoundationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_foundation, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initiiliazieRecycler(view);

        foundationListMvpViewFoundationListPresenter = new FoundationListPresenter<>(new AppDataManager(),new AppSchedulerProvider(),
                new CompositeDisposable());
        foundationListMvpViewFoundationListPresenter.onViewPrepared();
        foundationListMvpViewFoundationListPresenter.onAttach(this);
    }

    public void initiiliazieRecycler(View view){

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerFoundation);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
    }

    @Override
    public void onFetchSuccess(List<ProductModel> productModel) {

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
                        .replace(R.id.content,itemFragment).commit();
            }


        }));
        };
    }

