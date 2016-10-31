package com.example.minh.inoxia;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InventoryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InventoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InventoryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Button boutSpeedtiles, boutBackSpeed, boutBacksplashes, boutLoft, boutCare;
    private View myFragmentView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public InventoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InventoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InventoryFragment newInstance(String param1, String param2) {
        InventoryFragment fragment = new InventoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        //recupererComposante();
        //ecouterComposante();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        myFragmentView = inflater.inflate(R.layout.fragment_inventory, container, false);


        recupererComposante();
        ecouterComposante();

        return myFragmentView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void recupererComposante() {
        boutSpeedtiles = (Button) myFragmentView.findViewById(R.id.boutSpeedtiles);
        boutBackSpeed = (Button) myFragmentView.findViewById(R.id.boutBackSpeed);
        boutBacksplashes = (Button) myFragmentView.findViewById(R.id.boutBacksplashes);
        boutLoft = (Button) myFragmentView.findViewById(R.id.boutLoft);
        boutCare = (Button) myFragmentView.findViewById(R.id.boutCare);
    }

    public void ecouterComposante() {
        boutSpeedtiles.setOnClickListener(buttonListener);
        boutBackSpeed.setOnClickListener(buttonListener);
        boutBacksplashes.setOnClickListener(buttonListener);
        boutLoft.setOnClickListener(buttonListener);
        boutCare.setOnClickListener(buttonListener);
    }

    private View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View vue) {

            Intent intent = new Intent(getContext(), Products.class);
            switch (vue.getId()) {
                case R.id.boutSpeedtiles:
                    startActivityForResult(intent, 0);
                    break;

                case R.id.boutBackSpeed:
                    startActivityForResult(intent, 0);
                    break;

                case R.id.boutBacksplashes:
                    startActivityForResult(intent, 0);
                    break;
                case R.id.boutLoft:
                    startActivityForResult(intent, 0);
                    break;
                case R.id.boutCare:
                    startActivityForResult(intent, 0);
                    break;
                default:
                    break;
            }
        }
    };

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
