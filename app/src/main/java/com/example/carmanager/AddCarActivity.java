package com.example.carmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.carmanager.car.NewCar;
import com.example.carmanager.models.Car;

import java.util.Objects;

public class AddCarActivity extends AppCompatActivity {

    //Toolbar-----------------------------------------------
    public androidx.appcompat.widget.Toolbar toolbar;
    Button btnCar, btnMoreActivities, btnHistory, btnSettings, btnMainActivity;
    //Toolbar-----------------------------------------------

    EditText brandEditText,modelEditText,tankVolumeEditText,engineVolumeEditText,powerEditText,
            weightEditText,vinNumberEditText,bodyTypeEditText,colorEditText,
            fuelTypeEditText,plateNumberEditText,productionYearEditText;
    Button goNextButton;
    Spinner bodyTypeSpinner, fuelTypeSpinner;
    RadioButton manual, automatic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        NewCar.newCar = new Car();

        brandEditText = findViewById(R.id.editTextBrand);
        modelEditText = findViewById(R.id.editTextModel);
        tankVolumeEditText = findViewById(R.id.editTextTankVolume);
        engineVolumeEditText = findViewById(R.id.editTextEngineCapacity);
        powerEditText = findViewById(R.id.editTextEnginePower);
        weightEditText = findViewById(R.id.editTextWeight);
        vinNumberEditText = findViewById(R.id.editTextVin);
        bodyTypeSpinner = findViewById(R.id.spinnerBodyType);
        colorEditText = findViewById(R.id.editTextColour);
        manual = findViewById(R.id.radioButtonManual);
        automatic = findViewById(R.id.radioButtonAutomatic);
        fuelTypeSpinner = findViewById(R.id.spinnerFuelType);
        plateNumberEditText = findViewById(R.id.editTextRegistry);
        productionYearEditText = findViewById(R.id.editTextProdDate);

        goNextButton = findViewById(R.id.buttonAdd);


        goNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Boolean parsingError, weightParseErr, tankVolumeParseErr, engineVolumeParseErr, enginePowerParseErr, prodYearParseErr;
                //parsing
                try{
                    NewCar.newCar.setTankVolume(Double.parseDouble(getETString(tankVolumeEditText)));
                    tankVolumeParseErr = false;
                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Podana pojemność baku nie jest liczbą, Wpisz ponownie.",Toast.LENGTH_SHORT).show();
                    tankVolumeEditText.setText("");
                    tankVolumeParseErr = true;
                }
                try{
                    NewCar.newCar.setEngineCapacity(Double.parseDouble(getETString(engineVolumeEditText)));
                    engineVolumeParseErr = false;
                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Podana pojemność silnika nie jest liczbą, Wpisz ponownie.",Toast.LENGTH_SHORT).show();
                    engineVolumeEditText.setText("");
                    engineVolumeParseErr = true;
                }
                try{
                    NewCar.newCar.setWeight(Double.parseDouble(getETString(weightEditText)));
                    weightParseErr = false;
                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Podana waga samochodu nie jest liczbą, Wpisz ponownie.",Toast.LENGTH_SHORT).show();
                    tankVolumeEditText.setText("");
                    weightParseErr = true;
                }

                try{
                    NewCar.newCar.setEnginePower(Integer.parseInt(getETString(weightEditText)));
                    enginePowerParseErr = false;
                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Podana moc silnika nie jest liczbą, Wpisz ponownie.",Toast.LENGTH_SHORT).show();
                    powerEditText.setText("");
                    enginePowerParseErr = true;
                }

                try{
                    NewCar.newCar.setProductionDate(Integer.parseInt(getETString(weightEditText)));
                    prodYearParseErr = false;
                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Podana data produkcji samochodu nie jest liczbą, Wpisz ponownie.",Toast.LENGTH_SHORT).show();
                    tankVolumeEditText.setText("");
                    prodYearParseErr = true;
                }

                parsingError = (tankVolumeParseErr || engineVolumeParseErr || weightParseErr || enginePowerParseErr || prodYearParseErr);
                try{

                    NewCar.newCar.setBrand(getETString(brandEditText));
                    NewCar.newCar.setModel(getETString(modelEditText));
                    NewCar.newCar.setTankVolume(Double.parseDouble(getETString(tankVolumeEditText)));
                    NewCar.newCar.setEngineCapacity(Double.parseDouble(getETString(engineVolumeEditText)));
                    NewCar.newCar.setEnginePower(Integer.parseInt(getETString(powerEditText)));
                    NewCar.newCar.setWeight(Double.parseDouble(getETString(weightEditText)));
                    NewCar.newCar.setVin(getETString(vinNumberEditText));
                    NewCar.newCar.setBodyType(bodyTypeSpinner.getSelectedItem().toString());
                    NewCar.newCar.setColour(getETString(colorEditText));
                    if(manual.isChecked()) {
                        NewCar.newCar.setShifterType("Manualna");
                    }else if(automatic.isChecked()){
                        NewCar.newCar.setShifterType("Automatyczna");
                    }
                    NewCar.newCar.setFuelType(fuelTypeSpinner.getSelectedItem().toString());
                    NewCar.newCar.setRegistry(getETString(plateNumberEditText));
                    NewCar.newCar.setProductionDate(Integer.parseInt(getETString(productionYearEditText)));
                    //Log.d("BRAND OF NEW CAR",NewCar.newCar.getBrand());
                    if(!parsingError) {
                        Intent intentSP = new Intent(AddCarActivity.this, AddCarActivity2.class);
                        intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intentSP);
                    }

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Jedno z pól nie zostało wypełnione",Toast.LENGTH_SHORT).show();
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
                Intent intentSP = new Intent(AddCarActivity.this, CarActivity.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity( intentSP );
            }
        });
        btnMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(AddCarActivity.this, MainActivity.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity( intentSP );
            }
        });
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(AddCarActivity.this, History.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity( intentSP );
            }
        });
        btnMoreActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(AddCarActivity.this, MoreActivities.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity( intentSP );
            }
        });
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(AddCarActivity.this, Raports.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity( intentSP );
            }
        });
        //Toolbar-----------------------------------------------

    }

    String getETString(EditText editText){
        return String.valueOf(editText.getText());
    }
}