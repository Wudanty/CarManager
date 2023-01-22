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
import com.example.carmanager.models.Insurance;

import java.util.List;

public class AdapterInsurance extends ArrayAdapter<Insurance> {
    public AdapterInsurance(Context context, List<Insurance> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.insurance_cell, parent, false);

        TextView odKiedy = convertView.findViewById(R.id.textViewOdKiedy);
        TextView doKiedy = convertView.findViewById(R.id.textViewDoKiedy);
        TextView cena = convertView.findViewById(R.id.textViewCenaUbezpieczenia);
        TextView ubezpieczyciel = convertView.findViewById(R.id.textViewUbezpieczyciel);
        TextView rodzaj = convertView.findViewById(R.id.textViewRodzaj);
        TextView numer = convertView.findViewById(R.id.textViewNumerPolisy);

        odKiedy.setText(getItem(position).getStartDate()+"");
        doKiedy.setText(getItem(position).getExpirationDate()+"");
        cena.setText(getItem(position).getPrice()+"");
        ubezpieczyciel.setText(getItem(position).getProvider()+"");
        rodzaj.setText(getItem(position).getInsuranceType()+"");
        numer.setText(getItem(position).getInsuranceNumber()+"");

        return convertView;
    }
}
