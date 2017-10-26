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

import com.example.enoch.makeapp.data.model.BrandModel;
import com.example.enoch.makeapp.data.network.AppDataManager;
import com.example.enoch.makeapp.ui.base.BaseFragment;
import com.example.enoch.makeapp.ui.brandList.BrandsListPresenter;
import com.example.enoch.makeapp.ui.brandList.IBrandListMvpView;
import com.example.enoch.makeapp.ui.utils.rx.AppSchedulerProvider;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;


/**
 * A simple {@link Fragment} subclass.
 */
public class BrandFragment extends BaseFragment implements IBrandListMvpView{

    RecyclerView recyclerView;

    private BrandsListPresenter<IBrandListMvpView> brandListMvpViewBrandsListPresenter;

    String brand = "maybelline";


    public BrandFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_brand, container, false);

        brandListMvpViewBrandsListPresenter = new BrandsListPresenter<>(new AppDataManager(),new AppSchedulerProvider()
                ,new CompositeDisposable());
        brandListMvpViewBrandsListPresenter.onViewPrepared(brand);
        brandListMvpViewBrandsListPresenter.onAttach(this);


        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeRecycler(view);
    }



    public void initializeRecycler(View view){

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewBrand);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

    }



    @Override
    public void onFetchBrandSuccess(List<BrandModel> brandModels) {

        //Recycler view with on click listener, will identify what item has been pressed
        recyclerView.setAdapter(new BrandsAdapter(brandModels, R.layout.row, getActivity().getApplicationContext(), new onClickListener() {
            @Override
            public void onItemClick(BrandModel item) {
                Log.i("clickTest","clicked" + item.getName());

                //when clicked will launch the ItemDisplayFragment
                int id = item.getId();

                Bundle args = new Bundle();

                args.putInt("id",id);
                ItemDisplayFragment itemFragment = new ItemDisplayFragment();

                itemFragment.setArguments(args);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content,itemFragment).commit();
            }
        }));
    }


    public interface onClickListener {

        //void onItemClick(ProductModel item);

        void onItemClick(BrandModel brandModel);
    }

}
