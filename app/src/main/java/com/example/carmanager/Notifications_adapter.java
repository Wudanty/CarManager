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

        TextView NotiName = convertView.findViewById(R.id.textView5);

        NotiName.setText(getItem(position).getDescription()+"");

        return convertView;
    }
}
