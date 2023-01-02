package com.example.carmanager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.carmanager.models.Car;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    public androidx.appcompat.widget.Toolbar toolbar;
    Button btnCar, btnMoreActivities, btnHistory, btnSettings, btnMainActivity,btnDataCar,btnDetailsCar;
    LinearLayout dataLayout, detailsLayout;
    FloatingActionButton floatingButton;
    TextView text_nazwa,text_marka,text_model,text_tablica,text_rok,text_polisa,text_vin;
    TextView text_poj,text_moc,text_przebieg,text_waga,text_paliwo,text_nadwozie,text_kolor,text_skrzynia;
    ImageView imageCar1,imageCar2,imageCar3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DbManager dbManager = DbManager.instanceOfDatabase(this);
        Car object = dbManager.getCarById(1);
        //Toolbar-----------------------------------------------
        btnCar = findViewById(R.id.car);
        btnMoreActivities = findViewById(R.id.more);
        btnSettings = findViewById(R.id.settings);
        btnMainActivity = findViewById(R.id.mainActivity);
        btnHistory = findViewById(R.id.history);
        btnDataCar = findViewById(R.id.button_data);
        btnDetailsCar = findViewById(R.id.button_details);
        dataLayout = findViewById(R.id.ll_car_data);
        detailsLayout = findViewById(R.id.ll_details);
        floatingButton = findViewById(R.id.fab);

        imageCar1 = findViewById(R.id.image_car1);
        text_nazwa = findViewById(R.id.nazwa_v);
        text_marka = findViewById(R.id.marka_v);
        text_model = findViewById(R.id.model_v);
        text_tablica = findViewById(R.id.tablica_v);
        text_rok = findViewById(R.id.rok_v);
        text_polisa = findViewById(R.id.polisa_v);
        text_vin = findViewById(R.id.vin_v);

        imageCar2 = findViewById(R.id.image_car2);
        text_poj = findViewById(R.id.poj_v);
        text_moc = findViewById(R.id.moc_v);
        text_przebieg = findViewById(R.id.przebieg_v);
        text_waga  = findViewById(R.id.waga_v);
        text_paliwo = findViewById(R.id.paliwo_v);
        text_nadwozie = findViewById(R.id.nadwozie_v);
        text_kolor = findViewById(R.id.kolor_v);
        text_skrzynia = findViewById(R.id.skrzynia_v);

        imageCar3 = findViewById(R.id.image_car2);

        try {
            imageCar1.setImageBitmap(BitmapFactory.decodeByteArray(object.getPicture(), 0, object.getPicture().length));

         /*
        text_nazwa.setText(object.getCarNickname());
        text_marka.setText(object.getBrand());
        text_model.setText(object.getModel());
        text_tablica.setText(object.getRegistry());
        text_rok.setText(object.getProductionDate().toString());
        text_polisa.setText(object.getTankVolume().toString());
        text_vin.setText(object.getVin());

        text_poj.setText(object.getEngineCapacity().toString());
        text_moc.setText(String.valueOf(object.getEnginePower()));
        text_przebieg.setText(String.valueOf(object.getEnginePower()));
        text_waga.setText(object.getWeight().toString());
        text_paliwo.setText(object.getFuelType());
        text_nadwozie.setText(object.getBodyType());
        text_kolor.setText(object.getColour());
        text_skrzynia.setText(object.getShifterType());

*/

    } catch (Exception e) {
    }



        btnDataCar.setBackgroundColor(btnDataCar.getContext().getResources().getColor(R.color.purple_700));
        btnDetailsCar.setBackgroundColor(btnDetailsCar.getContext().getResources().getColor(R.color.purple_500));
        detailsLayout.setVisibility(View.GONE);



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
                more(null);
            }
        });
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(MainActivity.this, Raports.class);
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
                dataLayout.setVisibility(View.VISIBLE);
                detailsLayout.setVisibility(View.GONE);
            }
        });
        btnDetailsCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnDataCar.setBackgroundColor(btnDataCar.getContext().getResources().getColor(R.color.purple_500));
                btnDetailsCar.setBackgroundColor(btnDataCar.getContext().getResources().getColor(R.color.purple_700));
                detailsLayout.setVisibility(View.VISIBLE);
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
    public void more(View view){
        final LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.more, null);
        Button btnContacts=linearLayout.findViewById(R.id.btnContacts);
        Button btnReminder=linearLayout.findViewById(R.id.btnReminder);
        Button btnSettings=linearLayout.findViewById(R.id.btnContacts);
        final AlertDialog builder = new AlertDialog.Builder(this)
                .setView(linearLayout)
                .setCancelable(true)
                .create();
        builder.show();
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(builder.getWindow().getAttributes());
        lp.width = 480;
        lp.x=25;
        lp.y=100;

        lp.gravity = Gravity.TOP | Gravity.END;
        lp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        builder.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        builder.getWindow().setAttributes(lp);

        btnContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MoreActivities.class);
                startActivity(intent);
                builder.cancel();
            }
        });
        btnReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Notifications.class);
                startActivity(intent);
                builder.cancel();
            }
        });
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
        lp.x=25;
        lp.y=25;

        lp.gravity = Gravity.BOTTOM | Gravity.END;
        lp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        builder.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        builder.getWindow().setAttributes(lp);

        btnFuel.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddictionFuel.class);
                startActivity(intent);
                builder.cancel();
            }
        });
        btnMileage.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AdditionMileage.class);
                startActivity(intent);
                builder.cancel();
            }
        });
        btnRepairs.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AdditionRepairs.class);
                startActivity(intent);

                builder.cancel();
            }
        });
        btnCarInspection.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AdditionCheckup.class);
                startActivity(intent);

                builder.cancel();
            }
        });
        btnOperatingElements.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AdditionOperatingElements.class);
                startActivity(intent);

                builder.cancel();
            }
        });
    }
}