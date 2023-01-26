package com.example.carmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.carmanager.models.Mileage;

import java.util.List;

public class AdapterMileage extends ArrayAdapter<Mileage> {

    public AdapterMileage(Context context, List<Mileage> objects) {
    super(context, 0, objects);


}
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.mileage_cell, parent, false);

        TextView data = convertView.findViewById(R.id.textViewMilDate);
        TextView przebieg = convertView.findViewById(R.id.textViewMilValue);

        data.setText(getItem(position).getMileageCheckDate()+"");
        przebieg.setText(getItem(position).getMileageValue()+"");

        return convertView;
    }


}
