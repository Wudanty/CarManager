package com.example.carmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.carmanager.models.FuelFill;

import java.util.List;

public class AdapterHistoryFuelFill extends ArrayAdapter<FuelFill> {
    public AdapterHistoryFuelFill(Context context, List<FuelFill> FuelFillData) {
        super(context, 0, FuelFillData);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView==null)
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.fuell_fill_cell,parent,false);
        TextView data = convertView.findViewById(R.id.textViewData);
        TextView cena = convertView.findViewById(R.id.textViewCena);
        TextView ile = convertView.findViewById(R.id.textViewIle);
        TextView gdzie = convertView.findViewById(R.id.textViewGdzie);
        TextView ileZaLitr= convertView.findViewById(R.id.textViewIleZaLitr);
        TextView jakie = convertView.findViewById(R.id.textViewJakie);

        data.setText(""+getItem(position).getFillDate());
        cena.setText(""+getItem(position).getPrice());
        ile.setText(""+getItem(position).getLiterAmount());
        gdzie.setText(""+getItem(position).getStationName());
        ileZaLitr.setText(""+getItem(position).getPricePerLiter());
        jakie.setText(""+getItem(position).getFuelType());

        return convertView;
    }

}
