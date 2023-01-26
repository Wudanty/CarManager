package com.example.carmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.carmanager.models.Car;
import com.example.carmanager.models.Checkup;

import java.util.List;

public class Noti_car_adapter extends ArrayAdapter<Car> {
    public Noti_car_adapter(Context context, List<Car> objects) {
        super(context, 0, objects);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_noti_car, parent, false);

        TextView carName = convertView.findViewById(R.id.textView6);
        carName.setText(getItem(position).getCarNickname() + "");


        return convertView;
    }
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_noti_car, parent, false);

        TextView carName = convertView.findViewById(R.id.textView6);

        carName.setText(getItem(position).getCarNickname()+"");

        return convertView;
    }

}
