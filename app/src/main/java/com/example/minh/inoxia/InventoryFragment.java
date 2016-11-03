package com.example.minh.inoxia;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    private ListView listView;
    RequestQueue requestQueue;

    private String[] combinedArray;
    private String combinedText = "";

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

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        myFragmentView = inflater.inflate(R.layout.fragment_inventory, container, false);
        listView = (ListView) myFragmentView.findViewById(R.id.buttonView);
        Log.d("Test", listView + "");
        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonObjectRequest);
        //recupererComposante();
        //ecouterComposante();

        return myFragmentView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void recupererComposante() {


    }

    public void initialiserComposante() {

    }

    public void ecouterComposante() {

    }

   /* private View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View vue) {

            Intent intent = new Intent(getContext(), Products.class);

            switch (vue.getId()) {
                case R.id.boutSpeedtiles:
                    startActivityForResult(intent, 0);
                    //loadProductFragment();
                    break;

                case R.id.boutBackSpeed:
                    startActivityForResult(intent, 0);
                    //loadProductFragment();
                    break;

                case R.id.boutBacksplashes:
                    startActivityForResult(intent, 0);
                    //loadProductFragment();
                    break;
                case R.id.boutLoft:
                    startActivityForResult(intent, 0);
                    //loadProductFragment();
                    break;
                case R.id.boutCare:
                    startActivityForResult(intent, 0);
                    //loadProductFragment();
                    break;
                default:


                    break;
            }
        }
    };*/

    private void loadProductFragment() {
        ProductFragment product = new ProductFragment();
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(R.id.relative_layout_fragment, product).commit();
    }

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

    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
            "http://192.168.2.13/inoxia/get_categories.php", new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            Log.d(response.toString(), response.toString());
            try {
                JSONArray categories = response.getJSONArray("categories");
                for (int i = 0; i < categories.length(); i++) {
                    JSONObject category = categories.getJSONObject(i);
                    String category_name = category.getString("name");
                    combinedText += category_name + "-split-";
                    combinedArray = combinedText.split("-split-");
                }
                MainListAdapter adapter = new MainListAdapter(getActivity(), combinedArray);
                listView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            System.out.println(error);
        }
    });
}
