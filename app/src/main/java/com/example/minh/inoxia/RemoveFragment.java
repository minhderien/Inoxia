package com.example.minh.inoxia;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class RemoveFragment extends Fragment {

    private Button boutRemove;
    private Spinner spinnerCategory, spinnerProduct;
    private EditText editTextNumber;

    private int amount;

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
        initialiserComposante();
        ecouterComposante();

        return myFragmentView;
    }

    public void recupererComposante() {
        boutRemove = (Button) myFragmentView.findViewById(R.id.boutRemove);

        spinnerCategory = (Spinner) myFragmentView.findViewById(R.id.spinnerCategory);
        spinnerProduct = (Spinner) myFragmentView.findViewById(R.id.spinnerProduct);

        editTextNumber = (EditText) myFragmentView.findViewById(R.id.editTextNumber);
    }

    public void initialiserComposante() {
        boutRemove.setEnabled(false);
    }

    public void ecouterComposante() {
        boutRemove.setOnClickListener(buttonListener);
        editTextNumber.addTextChangedListener(txtListener);
    }

    private View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View vue) {
            // TODO
        }
    };

    private TextWatcher txtListener = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            // Appeler apres que le texte de la zone cible soit change.
            checkEmptyField();
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // Appeler lorsque le texte de la zone cible est sur le point d'etre change.
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            checkEmptyField();
        }
    };

    private void checkEmptyField() {
        String number = editTextNumber.getText().toString();

        if (!number.equals("")) {
            amount = Integer.parseInt(number);
            if (amount > 0) {
                boutRemove.setEnabled(true);
            } else {
                boutRemove.setEnabled(false);
            }
        } else {
            boutRemove.setEnabled(false);
        }
    }

}
