package com.example.carmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carmanager.models.Car;

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


        dbManager.fillCarArrayList();


        SharedPreferences sharedPref =  getSharedPreferences("activeCar", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        List<String> carNames = new ArrayList<>();

        for(Car car:Car.listOfCars){
            carNames.add(car.getCarNickname());
        }


        ArrayAdapter selectedCarAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, carNames);


        selectCarSpinner.setAdapter(selectedCarAdapter);
        selectCarSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                selectedCarName = parent.getSelectedItem().toString();
                selectedCar = getCarByNickname(selectedCarName);
                modelTextView.setText(selectedCar.getModel());
                brandTextView.setText(selectedCar.getBrand());
                nicknameTextView.setText("\""+selectedCar.getCarNickname()+"\"");
                plateNumber.setText(selectedCar.getRegistry());
                Log.d("Selected car",selectedCarName);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

        selectActiveCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                Intent intentSP = new Intent(CarActivity.this, MoreActivities.class);
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
}