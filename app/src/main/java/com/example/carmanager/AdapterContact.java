package com.example.carmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.carmanager.models.Contact;
import com.example.carmanager.models.FuelFill;

import java.util.List;

public class AdapterContact extends ArrayAdapter<Contact> {
    public AdapterContact(Context context, List<Contact> object) {
        super(context, 0, object);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView==null)
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.contact_cell,parent,false);
        TextView nazwa = convertView.findViewById(R.id.textViewNazwa);
        TextView numer = convertView.findViewById(R.id.textViewNumer);
        TextView email = convertView.findViewById(R.id.textViewEmail);
        TextView adres = convertView.findViewById(R.id.textViewAdres);
        ImageButton phone = convertView.findViewById(R.id.imageButtonPhone);

        nazwa.setText(getItem(position).getContactName());
        numer.setText(getItem(position).getPhoneNumber());
        email.setText(getItem(position).getEmail());
        adres.setText(getItem(position).getAddress());

        return convertView;
    }
}
