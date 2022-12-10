package com.example.carmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.carmanager.models.Car;
import com.example.carmanager.models.Notification;

import java.util.ArrayList;
import java.util.List;

public class Notifications extends AppCompatActivity {
    DbManager dbManager = DbManager.instanceOfDatabase(this);
    Button button_add,button_add_a,button_list;
    Button btnCar, btnMoreActivities, btnHistory, btnSettings, btnMainActivity;
    LinearLayout layout_add,layout_list;
    CheckBox checkBoxKm, checkBoxDate;
    EditText editTextKm,editTextDate,editTextDes;
    Spinner spino,selectCarSpinner;
    ListView notification_list;
    Car car=null;
    String[] option = {"Paliwo","Mandat","Naprawa"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        createNotificationChannel();
        notification_list = findViewById(R.id.list_not);
        btnCar = findViewById(R.id.car);
        btnMoreActivities = findViewById(R.id.more);
        btnMainActivity = findViewById(R.id.mainActivity);
        btnHistory = findViewById(R.id.history);
        checkBoxKm = findViewById(R.id.checkBox);
        checkBoxDate = findViewById(R.id.checkBox2);
        editTextKm = findViewById(R.id.editTextNumber);
        editTextDate = findViewById(R.id.editTextDate);
        editTextDes = findViewById(R.id.editTextTextMultiLine);
        spino = findViewById(R.id.spinner);
        selectCarSpinner = findViewById(R.id.spinner2);
        layout_add = findViewById(R.id.layout_add);
        layout_list = findViewById(R.id.layout_list);
        button_add_a = findViewById(R.id.button_add_a);
        button_add = findViewById(R.id.button_add);
        button_list = findViewById(R.id.button_list);
        button_add_a.setBackgroundColor(button_add_a.getContext().getResources().getColor(R.color.purple_700));
        button_list.setBackgroundColor(button_list.getContext().getResources().getColor(R.color.purple_500));
        ArrayAdapter ad = new ArrayAdapter(this,android.R.layout.simple_spinner_item, option);
        spino.setAdapter(ad);
        dbManager.fillCarArrayList();
        Noti_car_adapter noti_car_adapter = new Noti_car_adapter(getApplicationContext(),Car.listOfCars);
        selectCarSpinner.setAdapter(noti_car_adapter);
        button_add_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_add_a.setBackgroundColor(button_add_a.getContext().getResources().getColor(R.color.purple_700));
                button_list.setBackgroundColor(button_list.getContext().getResources().getColor(R.color.purple_500));
                layout_add.setVisibility(View.VISIBLE);
                layout_list.setVisibility(View.GONE);
            }
        });
        button_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbManager.fillNotificationArrayList();
                Notifications_adapter notifications_adapter = new Notifications_adapter(getApplicationContext(),Notification.listOfNotification);
                notification_list.setAdapter(notifications_adapter);
                button_add_a.setBackgroundColor(button_add_a.getContext().getResources().getColor(R.color.purple_500));
                button_list.setBackgroundColor(button_list.getContext().getResources().getColor(R.color.purple_700));
                layout_list.setVisibility(View.VISIBLE);
                layout_add.setVisibility(View.GONE);
            }
        });
        selectCarSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                car = (Car) selectCarSpinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                car = (Car) selectCarSpinner.getSelectedItem();
            }
        });
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Notification notification = new Notification(car.getCarId(),"12.12.12",editTextDes.getText().toString(),1,1);
                dbManager.addNotificationToDb(notification);
            }
        });
        btnCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(Notifications.this, CarActivity.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity( intentSP );
            }
        });
        btnMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(Notifications.this, MainActivity.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity( intentSP );
            }
        });
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(Notifications.this, History.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity( intentSP );
            }
        });
        btnMoreActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(Notifications.this, Notifications.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity( intentSP );
            }
        });
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "TestName";
            String description = "TestDes";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("CHANNEL_ID", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    private void createNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "CHANNEL_ID")
                .setSmallIcon(R.drawable.notification)
                .setContentTitle("Przypomnienie")
                .setContentText("Uwaga za 5 dni kończy sie polisa samochodu ")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, builder.build());

    }
    public void itemClicked(View v) {
        CheckBox checkBoxDate = (CheckBox)v;
        if(checkBoxDate.isChecked()){
            editTextDate.setVisibility(View.VISIBLE);
        }
        else {
            editTextDate.setVisibility(View.GONE);
        }

    }
    public void itemClicked2(View v) {
        CheckBox checkBoxKm = (CheckBox)v;
        if(checkBoxKm.isChecked()){
            editTextKm.setVisibility(View.VISIBLE);
        }
        else {
            editTextKm.setVisibility(View.GONE);
        }

    }




}