package com.example.enoch.makeapp.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.enoch.makeapp.R;
import com.example.enoch.makeapp.di.component.IActivityComponent;
import com.example.enoch.makeapp.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {

    IActivityComponent iActivityComponent;
    public IActivityComponent getiActivityComponent() {
        return iActivityComponent;
    }



    @BindView(R.id.lip) Button lipstick;
    @BindView(R.id.mascara) Button mascara;
    @BindView(R.id.nailPolish) Button nailPolish;
    @BindView(R.id.foundation) Button foundation;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // / Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

       /** lipstick =(Button) view.findViewById(R.id.lip);
        my_mascara_bg = (Button) view.findViewById(R.id.my_mascara_bg);
        nailPolish = (Button) view.findViewById(R.id.nailPolish);
        foundation = (Button) view.findViewById(R.id.foundation); **/

        ButterKnife.bind(this,view);





        //Set onClick listeners for the lipstick button
        lipstick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content,new LipstickFragment()).addToBackStack(null).commit();
            }
        });

        mascara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content,new MascaraFragment()).addToBackStack(null).commit();
            }
        });

        nailPolish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content,new NailPolishFragment()).addToBackStack(null).commit();
            }
        });

        foundation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content,new FoundationFragment()).addToBackStack(null).commit();
            }
        });

       // my_mascara_bg.setOnClickListener(this);
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
                        .replace(R.id.content,new MascaraFragment()).addToBackStack(null).commit();

            case R.id.nailPolish:
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content,new NailPolishFragment()).addToBackStack(null).commit();

            case R.id.foundation:
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content,new FoundationFragment()).addToBackStack(null).commit();


        }

    }
}
