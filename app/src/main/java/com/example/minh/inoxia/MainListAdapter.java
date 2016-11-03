package com.example.minh.inoxia;

/**
 * Created by MinhMontmorency on 2016-11-01.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainListAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public MainListAdapter(Context context, String[] values) {
        super(context, R.layout.button_list_layout, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.button_list_layout, parent, false);
        TextView imageView = (TextView) rowView.findViewById(R.id.buttonView);
        imageView.setText(values[position]);
        // Change the icon for Windows and iPhone
        return rowView;
    }
}
