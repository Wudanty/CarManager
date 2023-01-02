package com.example.carmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.carmanager.models.Notification;

import java.util.List;

public class Notifications_adapter extends ArrayAdapter<Notification> {
    public Notifications_adapter(Context context, List<Notification> objects) {
        super(context, 0, objects);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.notifiactions_adapter, parent, false);
        DbManager dbManager = DbManager.instanceOfDatabase(getContext());
        TextView NotiName = convertView.findViewById(R.id.listtext1);
        TextView NotiDes = convertView.findViewById(R.id.listtext2);
        TextView NotiCar = convertView.findViewById(R.id.listtext3);
        TextView NotiType = convertView.findViewById(R.id.listtext4);
        TextView NotiKmOrData = convertView.findViewById(R.id.listtext5);

        NotiName.setText(getItem(position).getName()+"");
        NotiDes.setText(getItem(position).getDescription()+"");
        NotiCar.setText(dbManager.getCarById(getItem(position).getCarId()).getCarNickname());
        if(getItem(position).getNotificationType()==1){
        NotiType.setText("Typ: Data");
        NotiKmOrData.setText(getItem(position).getDate().toString());
        }
        else if(getItem(position).getNotificationType()==0){
            NotiType.setText("Typ: Km");
            NotiKmOrData.setText(getItem(position).getKilometre().toString()+" Km");
        }

        return convertView;
    }
}
