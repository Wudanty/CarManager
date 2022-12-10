package com.example.carmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.carmanager.models.Fix;

import java.util.List;

public class AdapterFix extends ArrayAdapter<Fix> {

    public AdapterFix(Context context, List<Fix> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fix_cell, parent, false);

        TextView data = convertView.findViewById(R.id.textViewDate);
        TextView co = convertView.findViewById(R.id.textViewCo);
        TextView ile = convertView.findViewById(R.id.textViewCena);
        TextView gdzie = convertView.findViewById(R.id.textViewGdzie);

        data.setText(getItem(position).getDateOfFix()+"");
        co.setText(getItem(position).getFixDescription()+"");
        ile.setText(getItem(position).getPrice()+"");
        gdzie.setText(getItem(position).getPlaceOfFix()+"");

        return convertView;
    }
}
