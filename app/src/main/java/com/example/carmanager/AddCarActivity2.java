package com.example.carmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.carmanager.car.NewCar;

import java.util.Objects;

public class AddCarActivity2 extends AppCompatActivity {

    Button uploadPicture, addCar;
    EditText nicknameEditText, descriptionEditText;
    ImageView carImage;

    DbManager dbManager = DbManager.instanceOfDatabase(this);

    //Toolbar-----------------------------------------------
    public androidx.appcompat.widget.Toolbar toolbar;
    Button btnCar, btnMoreActivities, btnHistory, btnSettings, btnMainActivity;
    //Toolbar-----------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car2);

        uploadPicture = findViewById(R.id.buttonUploadImage);
        addCar = findViewById(R.id.buttonAdd);

        nicknameEditText = findViewById(R.id.editTextNickname);
        descriptionEditText = findViewById(R.id.editTextMultiLineDescription);

        carImage = findViewById(R.id.imageViewCar);

        dbManager.fillCarArrayList();


        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    NewCar.newCar.setCarNickname(String.valueOf(nicknameEditText.getText()));
                    NewCar.newCar.setDescription(String.valueOf(descriptionEditText.getText()));
                    //NewCar.newCar.setPicture(carImage.get);
                    dbManager.addCarToDb(NewCar.newCar);
                    Toast.makeText(getApplicationContext(),"Dodano Samochód",Toast.LENGTH_SHORT).show();
                    Intent intentSP = new Intent(AddCarActivity2.this, CarActivity.class);
                    intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intentSP);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Jedno z pól nie zostało wypełnione",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
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
                Intent intentSP = new Intent(AddCarActivity2.this, CarActivity.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity( intentSP );
            }
        });
        btnMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(AddCarActivity2.this, MainActivity.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity( intentSP );
            }
        });
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(AddCarActivity2.this, History.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity( intentSP );
            }
        });
        btnMoreActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(AddCarActivity2.this, MoreActivities.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity( intentSP );
            }
        });
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(AddCarActivity2.this, Raports.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity( intentSP );
            }
        });
        //Toolbar-----------------------------------------------

    }
}