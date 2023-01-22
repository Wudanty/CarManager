package com.example.carmanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carmanager.models.Car;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class CarActivity extends AppCompatActivity {

    //Toolbar-----------------------------------------------
    public androidx.appcompat.widget.Toolbar toolbar;
    Button btnCar, btnMoreActivities, btnHistory, btnSettings, btnMainActivity;
    //Toolbar-----------------------------------------------


    DbManager dbManager = DbManager.instanceOfDatabase(this);
    Spinner selectCarSpinner;
    String selectedCarName;
    Car selectedCar;
    TextView modelTextView, brandTextView, plateNumber, nicknameTextView;
    Button selectActiveCar, deleteCar, addCar;
    ImageView selectedCarPicture;
    int selectedPosition;
    SharedPreferences myPrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        selectCarSpinner = findViewById(R.id.spinnerSelectCar);
        modelTextView = findViewById(R.id.modelTextView);
        brandTextView = findViewById(R.id.brandTextView);
        plateNumber = findViewById(R.id.plateNumberTextView);
        nicknameTextView = findViewById(R.id.nicknameTextView);
        selectActiveCar = findViewById(R.id.selectActiveCarButton);
        deleteCar = findViewById(R.id.deleteCarButton);
        addCar = findViewById(R.id.buttonAdd);
        selectedCarPicture = findViewById(R.id.imageViewCarSelect);


        dbManager.fillCarArrayList();

        String uri = "@drawable/images";  // where myresource (without the extension) is the file
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);

        SharedPreferences sharedPref =  getSharedPreferences("activeCar", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        List<String> carNames = new ArrayList<>();

        for(Car car:Car.listOfCars){
            carNames.add(car.getCarNickname());
        }


        ArrayAdapter selectedCarAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, carNames);


        selectCarSpinner.setAdapter(selectedCarAdapter);
        myPrefs = getSharedPreferences("activeCar", MODE_PRIVATE);
        int carPosition = myPrefs.getInt("activePosition",0);
        selectCarSpinner.setSelection(carPosition);
        selectCarSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedPosition = position;
                selectedCarName = parent.getSelectedItem().toString();
                selectedCar = getCarByNickname(selectedCarName);
                modelTextView.setText(selectedCar.getModel());
                brandTextView.setText(selectedCar.getBrand());
                nicknameTextView.setText("\""+selectedCar.getCarNickname()+"\"");
                plateNumber.setText(selectedCar.getRegistry());
                try {
                    selectedCarPicture.setImageBitmap(BitmapFactory.decodeByteArray(selectedCar.getPicture(), 0, selectedCar.getPicture().length));

                }catch(Exception e){};
                Log.d("Selected car",selectedCarName);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

        selectActiveCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putInt("activePosition",selectedPosition);
                editor.putInt("activeCarId", selectedCar.getCarId());
                editor.putString("activeCarNickname",selectedCar.getCarNickname());
                editor.apply();
                SharedPreferences sh = getSharedPreferences("activeCar", MODE_PRIVATE);

                Log.d("CAR ID", String.valueOf(sh.getInt("activeCarId",0)));
                Log.d("CAR NICKNAME", sh.getString("activeCarNickname",""));

            }
        });

        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(CarActivity.this, AddCarActivity.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentSP);
            }
        });

        deleteCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbManager.deleteCar(selectedCar);
                Toast.makeText(getApplicationContext(),"Usunięto samochód.",Toast.LENGTH_SHORT).show();
                carNames.remove(selectedCarName);
                selectedCarAdapter.notifyDataSetChanged();
                selectCarSpinner.setAdapter(selectedCarAdapter);


                modelTextView.setText(selectedCar.getModel());
                brandTextView.setText(selectedCar.getBrand());
                nicknameTextView.setText("\""+selectedCar.getCarNickname()+"\"");
                plateNumber.setText(selectedCar.getRegistry());

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










        btnCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(CarActivity.this, CarActivity.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity( intentSP );
            }
        });
        btnMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(CarActivity.this, MainActivity.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity( intentSP );
            }
        });
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(CarActivity.this, History.class);
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
                Intent intentSP = new Intent(CarActivity.this, Raports.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity( intentSP );
            }
        });
        //Toolbar-----------------------------------------------
    }

    Car getCarByNickname(String nickname){
        for(Car car:Car.listOfCars){
            try{
                if(car.getCarNickname().equals(nickname)){
                    return car;
                }
            }catch(Exception e){
                Log.d("GetCarByNickname", "No Car");
            }

        }
        return null;
    }

    public void more(View view){
        final LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.more, null);
        Button btnContacts=linearLayout.findViewById(R.id.btnContacts);
        Button btnReminder=linearLayout.findViewById(R.id.btnReminder);
        Button btnSettings=linearLayout.findViewById(R.id.btnSettings);
        final AlertDialog builder = new AlertDialog.Builder(this)
                .setView(linearLayout)
                .setCancelable(true)
                .create();
        builder.show();
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(builder.getWindow().getAttributes());
        lp.width = 580;
        lp.x=25;
        lp.y=140;

        lp.gravity = Gravity.TOP | Gravity.END;
        lp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        builder.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        builder.getWindow().setAttributes(lp);

        btnContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CarActivity.this, MoreActivities.class);
                startActivity(intent);
                builder.cancel();
            }
        });
        btnReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CarActivity.this, Notifications.class);
                startActivity(intent);
                builder.cancel();
            }
        });
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CarActivity.this, SettingsActivity.class);
                startActivity(intent);
                builder.cancel();
            }
        });
    }
}