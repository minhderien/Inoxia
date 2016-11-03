package com.example.minh.inoxia;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment {

    private TextView txtName, txtType, txtPrice, txtInventory, txtImage;
    private View myFragmentView;


    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        myFragmentView = inflater.inflate(R.layout.fragment_product, container, false);
        recupererComposante();
        initialiserComposante();
        ecouterComposante();

        return myFragmentView;
    }

    public void recupererComposante() {
        txtName = (TextView) myFragmentView.findViewById(R.id.txtName);
        txtType = (TextView) myFragmentView.findViewById(R.id.txtType);
        txtPrice = (TextView) myFragmentView.findViewById(R.id.txtPrice);
        txtInventory = (TextView) myFragmentView.findViewById(R.id.txtInventory);
        txtImage = (TextView) myFragmentView.findViewById(R.id.txtImage);
    }

    public void initialiserComposante() {

    }

    public void ecouterComposante() {

    }

}
