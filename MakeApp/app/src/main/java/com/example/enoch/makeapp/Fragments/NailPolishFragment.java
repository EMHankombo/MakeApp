package com.example.enoch.makeapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.example.enoch.makeapp.MakeAppAdapter;
import com.example.enoch.makeapp.MyApp;
import com.example.enoch.makeapp.R;
import com.example.enoch.makeapp.data.model.ProductModel;
import com.example.enoch.makeapp.di.component.DaggerIActivityComponent;
import com.example.enoch.makeapp.di.component.IActivityComponent;
import com.example.enoch.makeapp.di.module.ActivityModule;
import com.example.enoch.makeapp.onClickListener;
import com.example.enoch.makeapp.ui.base.BaseFragment;
import com.example.enoch.makeapp.ui.nailPolishList.INailPolishListMvpView;
import com.example.enoch.makeapp.ui.nailPolishList.NailPolishListPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.enoch.makeapp.MyApp.getApplication;


/**
 *
 */
public class NailPolishFragment extends BaseFragment implements INailPolishListMvpView{
    //
    IActivityComponent iActivityComponent;

    public IActivityComponent getiActivityComponent() {
        return iActivityComponent;
    }

     @BindView(R.id.recyclerNail) RecyclerView recyclerView;

    @Inject
    NailPolishListPresenter<INailPolishListMvpView> nailPolishListMvpViewNailPolishListPresenter;

    public NailPolishFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nail_polish, container, false);

        ButterKnife.bind(this,view);

        setRetainInstance(true);



        ShimmerRecyclerView shimmerRecycler = (ShimmerRecyclerView) view.findViewById(R.id.recyclerNail);
        shimmerRecycler.showShimmerAdapter();

        initializeRecycler(view);
        initialiseDagger();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {




       /* nailPolishListMvpViewNailPolishListPresenter = new NailPolishListPresenter<>(new AppDataManager(),new AppSchedulerProvider(),
                new CompositeDisposable()); */

        nailPolishListMvpViewNailPolishListPresenter.onViewPrepared();
        nailPolishListMvpViewNailPolishListPresenter.onAttach(this);

        super.onViewCreated(view, savedInstanceState);
    }

    public void initializeRecycler(View view){

       // shimmerRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerNail);
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
                        .replace(R.id.content,itemFragment).addToBackStack(null).commit();
            }
        }));
    }
}
