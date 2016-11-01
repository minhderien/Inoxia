package com.example.minh.inoxia;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class RemoveFragment extends Fragment {

    private Button boutRemove;
    private Spinner spinnerCategory, spinnerProduct;
    private View myFragmentView;


    public RemoveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        myFragmentView = inflater.inflate(R.layout.fragment_remove, container, false);
        recupererComposante();
        ecouterComposante();

        return myFragmentView;
    }

    public void recupererComposante() {
        boutRemove = (Button) myFragmentView.findViewById(R.id.boutRemove);
        spinnerCategory = (Spinner) myFragmentView.findViewById(R.id.spinnerCategory);
        spinnerProduct = (Spinner) myFragmentView.findViewById(R.id.spinnerProduct);
    }

    public void ecouterComposante() {
        boutRemove.setOnClickListener(buttonListener);
    }

    private View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View vue) {
            // TODO
        }
    };

}
