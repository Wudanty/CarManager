package com.example.carmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.carmanager.models.Checkup;

import java.util.List;

public class AdapterCheckUp extends ArrayAdapter<Checkup> {
    public AdapterCheckUp( Context context,  List<Checkup> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.check_up_cell, parent, false);

        TextView data = convertView.findViewById(R.id.textViewCheckDate);
        TextView expiration = convertView.findViewById(R.id.textViewCheckExpiration);
        TextView gdzie = convertView.findViewById(R.id.textViewCheckGdzie);
        TextView cena = convertView.findViewById(R.id.textViewCheckPrice);
        TextView wynik = convertView.findViewById(R.id.textViewCheckWynik);

        data.setText(getItem(position).getDate()+"");
        expiration.setText(getItem(position).getExpirationDate()+"");
        gdzie.setText(getItem(position).getCheckupLocation()+"");
        cena.setText(getItem(position).getPrice()+"");
        if(getItem(position).getPassed()==1)
            wynik.setText("Pozytywny");
        else{
            wynik.setText("Negatywny");
        }


        return convertView;
    }
}
