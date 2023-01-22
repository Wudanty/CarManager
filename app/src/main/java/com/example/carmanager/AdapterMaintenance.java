package com.example.carmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.carmanager.models.Maintenance;

import java.util.List;

public class AdapterMaintenance extends ArrayAdapter<Maintenance> {
    public AdapterMaintenance(Context context, List<Maintenance> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.maintenance_cell, parent, false);

        TextView data = convertView.findViewById(R.id.textViewEksData);
        TextView doKiedy = convertView.findViewById(R.id.textViewEksDoKiedy);
        TextView Co =  convertView.findViewById(R.id.textViewEksCo);
        TextView Cena =  convertView.findViewById(R.id.textViewEksCena);
        TextView Gdzie =  convertView.findViewById(R.id.textViewEksGdzie);
        TextView nastPrzebieg = convertView.findViewById(R.id.textViewEksPrzebieg);

        data.setText(getItem(position).getMaintenanceDate()+"");
        doKiedy.setText(getItem(position).getNextMaintenanceDate()+"");
        Co.setText(getItem(position).getMaintenanceTarget()+"");
        Cena.setText(getItem(position).getPrice().toString()+"");
        Gdzie.setText(getItem(position).getPlace()+"");
        nastPrzebieg.setText(getItem(position).getNextMileage()+"");

        return convertView;
    }
}
