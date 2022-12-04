package com.example.carmanager;

import androidx.annotation.NonUiContext;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.carmanager.models.Car;
import com.example.carmanager.models.Maintenance;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    public androidx.appcompat.widget.Toolbar toolbar;
    Button btnCar, btnMoreActivities, btnHistory, btnSettings, btnMainActivity,btnDataCar,btnDetailsCar,btnStatisticsCar;
    LinearLayout dataLayout, detailsLayout, statisticsLayout;
    FloatingActionButton floatingButton;
    TextView text_nazwa,text_marka,text_model,text_tablica,text_rok,text_polisa,text_vin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DbManager dbManager = DbManager.instanceOfDatabase(this);
        dbManager.fillCarArrayList();
        //Toolbar-----------------------------------------------
        btnCar = findViewById(R.id.car);
        btnMoreActivities = findViewById(R.id.more);
        btnSettings = findViewById(R.id.settings);
        btnMainActivity = findViewById(R.id.mainActivity);
        btnHistory = findViewById(R.id.history);
        btnDataCar = findViewById(R.id.button_data);
        btnDetailsCar = findViewById(R.id.button_details);
        btnStatisticsCar = findViewById(R.id.button_statistics);
        dataLayout = findViewById(R.id.ll_car_data);
        detailsLayout = findViewById(R.id.ll_details);
        statisticsLayout = findViewById(R.id.ll_statistics);
        floatingButton = findViewById(R.id.fab);
        Car object = Car.listOfCars.get(1);
        text_nazwa = findViewById(R.id.nazwa_v);
        text_marka = findViewById(R.id.marka_v);
        text_model = findViewById(R.id.model_v);
        text_tablica = findViewById(R.id.tablica_v);
        text_rok = findViewById(R.id.rok_v);
        text_polisa = findViewById(R.id.polisa_v);
        text_vin = findViewById(R.id.vin_v);
        text_nazwa.setText(object.getCarNickname());
        text_marka.setText(object.getBrand());
        text_model.setText(object.getModel());
        text_tablica.setText(object.getRegistry());
        text_rok.setText(object.getProductionDate().toString());
        text_polisa.setText(object.getTankVolume().toString());
        text_vin.setText(object.getVin());
        btnDataCar.setBackgroundColor(btnDataCar.getContext().getResources().getColor(R.color.purple_700));
        btnDetailsCar.setBackgroundColor(btnDetailsCar.getContext().getResources().getColor(R.color.purple_500));
        btnStatisticsCar.setBackgroundColor(btnStatisticsCar.getContext().getResources().getColor(R.color.purple_500));
        detailsLayout.setVisibility(View.GONE);
        statisticsLayout.setVisibility(View.GONE);



        toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(null);

        btnCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(MainActivity.this, CarActivity.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity( intentSP );
            }
        });
        btnMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(MainActivity.this, MainActivity.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity( intentSP );
            }
        });
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(MainActivity.this, History.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity( intentSP );
            }
        });
        btnMoreActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(MainActivity.this, MoreActivities.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity( intentSP );
            }
        });
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(MainActivity.this, Settings.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity( intentSP );
              //  Maintenance maintenance = new Maintenance();


            }
        });
        btnDataCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnDataCar.setBackgroundColor(btnDataCar.getContext().getResources().getColor(R.color.purple_700));
                btnDetailsCar.setBackgroundColor(btnDetailsCar.getContext().getResources().getColor(R.color.purple_500));
                btnStatisticsCar.setBackgroundColor(btnStatisticsCar.getContext().getResources().getColor(R.color.purple_500));
                dataLayout.setVisibility(View.VISIBLE);
                detailsLayout.setVisibility(View.GONE);
                statisticsLayout.setVisibility(View.GONE);
            }
        });
        btnDetailsCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnDataCar.setBackgroundColor(btnDataCar.getContext().getResources().getColor(R.color.purple_500));
                btnDetailsCar.setBackgroundColor(btnDataCar.getContext().getResources().getColor(R.color.purple_700));
                btnStatisticsCar.setBackgroundColor(btnDataCar.getContext().getResources().getColor(R.color.purple_500));
                detailsLayout.setVisibility(View.VISIBLE);
                dataLayout.setVisibility(View.GONE);
                statisticsLayout.setVisibility(View.GONE);
            }
        });
        btnStatisticsCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnDataCar.setBackgroundColor(btnDataCar.getContext().getResources().getColor(R.color.purple_500));
                btnDetailsCar.setBackgroundColor(btnDataCar.getContext().getResources().getColor(R.color.purple_500));
                btnStatisticsCar.setBackgroundColor(btnDataCar.getContext().getResources().getColor(R.color.purple_700));
                statisticsLayout.setVisibility(View.VISIBLE);
                detailsLayout.setVisibility(View.GONE);
                dataLayout.setVisibility(View.GONE);
            }
        });
        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                additions(null);
            }
        });


        //Toolbar-----------------------------------------------




    }
    public void additions(View view) {
        final LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.addition_menu, null);
        Button btnFuel = (Button) linearLayout.findViewById(R.id.btnFuel);
        Button btnMileage = (Button) linearLayout.findViewById(R.id.btnMileage);
        Button btnRepairs = (Button) linearLayout.findViewById(R.id.btnRepairs);
        Button btnCarInspection = (Button) linearLayout.findViewById(R.id.btnCarInspection);
        Button btnOperatingElements = (Button) linearLayout.findViewById(R.id.btnOperatingElements);

        final AlertDialog builder = new AlertDialog.Builder(this)
                .setView(linearLayout)
                .setCancelable(true)
                .create();
        builder.show();
        //builder.getWindow().setLayout(600, 530);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(builder.getWindow().getAttributes());
        lp.width = 600;
        lp.height = 790;
        lp.gravity = Gravity.BOTTOM | Gravity.END;
        lp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        builder.getWindow().setAttributes(lp);




        btnFuel.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddictionFuel.class);
                startActivity(intent);
            }
        });
        btnMileage.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

            }
        });
        btnRepairs.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

            }
        });
        btnCarInspection.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

            }
        });
        btnOperatingElements.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

            }
        });



    }

}