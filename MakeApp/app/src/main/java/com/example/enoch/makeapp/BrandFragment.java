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

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.example.enoch.makeapp.data.model.BrandModel;
import com.example.enoch.makeapp.di.component.DaggerIActivityComponent;
import com.example.enoch.makeapp.di.component.IActivityComponent;
import com.example.enoch.makeapp.di.module.ActivityModule;
import com.example.enoch.makeapp.ui.base.BaseFragment;
import com.example.enoch.makeapp.ui.brandList.BrandsListPresenter;
import com.example.enoch.makeapp.ui.brandList.IBrandListMvpView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.enoch.makeapp.MyApp.getApplication;


/**
 * A simple {@link Fragment} subclass.
 */
public class BrandFragment extends BaseFragment implements IBrandListMvpView{

    @BindView(R.id.recyclerViewBrand) RecyclerView recyclerView;




    IActivityComponent iActivityComponent;

    public IActivityComponent getiActivityComponent() {
        return iActivityComponent;
    }

    @Inject
    BrandsListPresenter<IBrandListMvpView> brandListMvpViewBrandsListPresenter;

    public BrandFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_brand, container, false);

        ButterKnife.bind(this,view);

        String brand = getArguments().getString("brand");

        ShimmerRecyclerView shimmerRecycler = (ShimmerRecyclerView) view.findViewById(R.id.recyclerViewBrand);
        shimmerRecycler.showShimmerAdapter();

       /* brandListMvpViewBrandsListPresenter = new BrandsListPresenter<>(new AppDataManager(),new AppSchedulerProvider()
                ,new CompositeDisposable());
                */

        initializeRecycler(view);
        initialiseDagger();

        brandListMvpViewBrandsListPresenter.onViewPrepared(brand);
        brandListMvpViewBrandsListPresenter.onAttach(this);


        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




    }



    public void initializeRecycler(View view){

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewBrand);
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
                        .replace(R.id.content,itemFragment).addToBackStack(null).commit();
            }
        }));
    }


    public interface onClickListener {

        //void onItemClick(ProductModel item);

        void onItemClick(BrandModel brandModel);
    }

}
