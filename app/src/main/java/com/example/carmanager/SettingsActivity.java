package com.example.carmanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {


    /*
    DO TOGGLA

     editor.putInt("activeCarId", selectedCar.getCarId());
                editor.putString("activeCarNickname",selectedCar.getCarNickname());
                editor.apply();
     */
    SwitchMaterial nightMode, notifications;
    Button clearDB;


    //Toolbar-----------------------------------------------
    public androidx.appcompat.widget.Toolbar toolbar;
    Button btnCar, btnMoreActivities, btnHistory, btnSettings, btnMainActivity;
    ListView contactListView;
    //Toolbar-----------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SharedPreferences sharedPref =  getSharedPreferences("notifications", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        nightMode = findViewById(R.id.switchNightMode);
        notifications = findViewById(R.id.switchNotifications);
        clearDB = findViewById(R.id.buttonClearDB);

        nightMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });

        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(notifications.isChecked()){
                    editor.putBoolean("notificationsOn", true);
                    editor.apply();
                    Log.d("NotificationsON", String.valueOf(sharedPref.getBoolean("notificationsOn",true)));

                }
                else{
                    editor.putBoolean("notificationsOn", false);
                    editor.apply();
                    Log.d("NotificationsON", String.valueOf(sharedPref.getBoolean("notificationsOn",false)));
                }
            }
        });

        clearDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DbManager dbManager = new DbManager(getApplicationContext());
                AlertDialog.Builder alert = new AlertDialog.Builder(SettingsActivity.this);
                alert.setTitle("Wyczyść Dane");
                alert.setMessage("Po wcisnięciu przycisku dane zostaną usunięte a aplikacja zatrzyma działanie wymagając ponownego uruchomienia");
                alert.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        getApplicationContext().deleteDatabase("CarData");
                        finishAffinity();
                        System.exit(0);
                        //dialog.dismiss();
                    }
                });

                alert.setNegativeButton("Nie", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alert.show();

            }
        });


        //Toolbar-----------------------------------------------

        btnCar = findViewById(R.id.car);
        btnMoreActivities = findViewById(R.id.more);
        btnSettings = findViewById(R.id.settings);
        btnMainActivity = findViewById(R.id.mainActivity);
        btnHistory = findViewById(R.id.history);

        toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(null);

        contactListView=findViewById(R.id.contactListView);

        btnCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(SettingsActivity.this, CarActivity.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity( intentSP );
            }
        });
        btnMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(SettingsActivity.this, MainActivity.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity( intentSP );
            }
        });
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(SettingsActivity.this, History.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity( intentSP );
            }
        });
        btnMoreActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(SettingsActivity.this, MoreActivities.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity( intentSP );
            }
        });
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(SettingsActivity.this, Raports.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity( intentSP );
            }
        });
        //Toolbar-----------------------------------------------
    }
}