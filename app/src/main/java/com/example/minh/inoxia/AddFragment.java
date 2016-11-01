package com.example.minh.inoxia;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import static com.example.minh.inoxia.R.id.boutSpeedtiles;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {

    private Button boutAdd;
    private Spinner spinnerCategory, spinnerProduct;
    private View myFragmentView;


    public AddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragmentView = inflater.inflate(R.layout.fragment_add, container, false);
        recupererComposante();
        ecouterComposante();

        return myFragmentView;
    }

    public void recupererComposante() {
        boutAdd = (Button) myFragmentView.findViewById(R.id.boutAdd);
        spinnerCategory = (Spinner) myFragmentView.findViewById(R.id.spinnerCategory);
        spinnerProduct = (Spinner) myFragmentView.findViewById(R.id.spinnerProduct);
    }

    public void ecouterComposante() {
        boutAdd.setOnClickListener(buttonListener);
    }

    private View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View vue) {
            // TODO
        }
    };

}
