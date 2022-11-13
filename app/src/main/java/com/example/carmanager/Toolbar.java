package com.example.carmanager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
/*
public class Toolbar extends AppCompatActivity {

    Button btnCar, btnMoreActivities, btnHistory, btnSettings, btnMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar);

        btnCar = findViewById(R.id.car);


        btnCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(Toolbar.this, Car.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity( intentSP );
            }
        });
    }
    /*public void btnCar(View view) {
        Log.d("test","");
        Intent intentSP = new Intent(this, Car.class);
        intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity( intentSP );
    }
}
*/