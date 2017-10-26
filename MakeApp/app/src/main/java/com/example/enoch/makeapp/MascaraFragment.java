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
import com.example.enoch.makeapp.ui.mascaraList.IMascaraMvpView;
import com.example.enoch.makeapp.ui.mascaraList.MascaraPresenter;
import com.example.enoch.makeapp.ui.utils.rx.AppSchedulerProvider;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;


/**
 * A simple {@link Fragment} subclass.
 */
public class MascaraFragment extends BaseFragment implements IMascaraMvpView {

    @BindView(R.id.recyclerMascara) RecyclerView recyclerView;

    private MascaraPresenter<IMascaraMvpView> mascaraMvpViewMascaraPresenter;
    public MascaraFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_mascara, container, false);

        ButterKnife.bind(this,view);

        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeRecycler(view);

        mascaraMvpViewMascaraPresenter = new MascaraPresenter<>(new AppDataManager(),new AppSchedulerProvider(),
                new CompositeDisposable());
        mascaraMvpViewMascaraPresenter.onViewPrepared();
        mascaraMvpViewMascaraPresenter.onAttach(this);

    }

    public void initializeRecycler(View view){

       // recyclerView = (RecyclerView) view.findViewById(R.id.recyclerMascara);
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
                        .replace(R.id.content,itemFragment).addToBackStack(null).commit();

            }
        }));
    }
}
