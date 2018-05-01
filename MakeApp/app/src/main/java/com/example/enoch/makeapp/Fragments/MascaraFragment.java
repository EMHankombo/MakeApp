package com.example.enoch.makeapp.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
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
import com.example.enoch.makeapp.ui.mascaraList.IMascaraMvpView;
import com.example.enoch.makeapp.ui.mascaraList.MascaraPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.enoch.makeapp.MyApp.getApplication;


/**
 * A simple {@link Fragment} subclass.
 */
public class MascaraFragment extends BaseFragment implements IMascaraMvpView {

    IActivityComponent iActivityComponent;

    public IActivityComponent getiActivityComponent() {
        return iActivityComponent;
    }

    @BindView(R.id.recyclerMascara) ShimmerRecyclerView shimmerRecyclerView;

    @Inject
    MascaraPresenter<IMascaraMvpView> mascaraMvpViewMascaraPresenter;

    public MascaraFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_mascara, container, false);

        ButterKnife.bind(this,view);


       // ShimmerRecyclerView shimmerRecycler = (ShimmerRecyclerView) view.findViewById(R.id.recyclerMascara);
        shimmerRecyclerView.showShimmerAdapter();

        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        initializeRecycler(view);
        initialiseDagger();

        /*mascaraMvpViewMascaraPresenter = new MascaraPresenter<>(new AppDataManager(),new AppSchedulerProvider(),
                new CompositeDisposable()); */

        mascaraMvpViewMascaraPresenter.onViewPrepared();
        mascaraMvpViewMascaraPresenter.onAttach(this);

    }

    public void initializeRecycler(View view){

       // shimmerRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerMascara);
        shimmerRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

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

        shimmerRecyclerView.setAdapter(new MakeAppAdapter(productModel, R.layout.row, getActivity().getApplicationContext(), new onClickListener() {
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
