package com.example.enoch.makeapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.enoch.makeapp.ui.base.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {


    Button lipstick,mascara,nailPolish,foundation;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // / Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        lipstick =(Button) view.findViewById(R.id.lip);
        mascara = (Button) view.findViewById(R.id.mascara);
        nailPolish = (Button) view.findViewById(R.id.nailPolish);
        foundation = (Button) view.findViewById(R.id.foundation);


        //Set onClick listeners for the lipstick button
        lipstick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content,new LipstickFragment()).commit();
            }
        });

        mascara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content,new MascaraFragment()).commit();
            }
        });

        nailPolish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content,new NailPolishFragment()).commit();
            }
        });

        foundation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content,new FoundationFragment()).commit();
            }
        });

       // mascara.setOnClickListener(this);
       // nailPolish.setOnClickListener(this);
       // foundation.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.mascara:
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content,new MascaraFragment()).commit();

            case R.id.nailPolish:
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content,new NailPolishFragment()).commit();

            case R.id.foundation:
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content,new FoundationFragment()).commit();


        }

    }
}
