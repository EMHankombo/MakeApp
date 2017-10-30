package com.example.enoch.makeapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.enoch.makeapp.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeBrandFragment extends BaseFragment {



    @BindView(R.id.maybelline) Button maybelline;

    @BindView(R.id.revlon) Button revlon;

    @BindView(R.id.loreal) Button loreal;

    @BindView(R.id.annabelle) Button annabelle;

    public HomeBrandFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_home_brand, container, false);

        ButterKnife.bind(this,view);


       /** maybelline = (Button)view.findViewById(R.id.maybelline);
        revlon = (Button)view.findViewById(R.id.revlon);
        loreal = (Button)view.findViewById(R.id.loreal);
        annabelle = (Button)view.findViewById(R.id.annabelle);**/


        maybelline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String brand = "maybelline";

                Bundle args = new Bundle();

                args.putString("brand",brand);

                BrandFragment bf = new BrandFragment();
                bf.setArguments(args);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content, bf).addToBackStack(null).commit();
            }
        });

        revlon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String brand ="revlon";

                Bundle args = new Bundle();
                args.putString("brand",brand);

                BrandFragment bf = new BrandFragment();
                bf.setArguments(args);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content,bf).addToBackStack(null).commit();
            }
        });

        loreal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String brand ="l'oreal";

                Bundle args = new Bundle();
                args.putString("brand",brand);

                BrandFragment bf = new BrandFragment();
                bf.setArguments(args);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content,bf).addToBackStack(null).commit();
            }
        });

        annabelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String brand ="annabelle";

                Bundle args = new Bundle();
                args.putString("brand",brand);

                BrandFragment bf = new BrandFragment();
                bf.setArguments(args);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content,bf).addToBackStack(null).commit();
            }
        });


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
