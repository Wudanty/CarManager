package com.example.carmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.carmanager.models.FuelFill;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class AddictionFuel extends AppCompatActivity {
    DbManager dbManager;
    FuelFill fuelFill;
    Button btnSave, btnExit;
    TextView tvFuelDate;
    EditText etFuelAmount, etFuelPrice,etFuelFullPrice,etFuelWhere;
    Spinner spinnerFuelType;
    LocalDate ldt;
    String data, strMonth, strDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addiction_fuel);
        dbManager = new DbManager(this);

        btnSave = findViewById(R.id.btnSave);
        btnExit = findViewById(R.id.btnExit);
        tvFuelDate = findViewById(R.id.tvFuelDate);
        etFuelAmount = findViewById(R.id.etFuelAmount);
        etFuelPrice = findViewById(R.id.etFuelPrice);
        etFuelFullPrice = findViewById(R.id.etFuelFullPrice);
        etFuelWhere = findViewById(R.id.etFuelWhere);
        spinnerFuelType = findViewById(R.id.spinnerFuelType);

        ldt = java.time.LocalDate.now();
        tvFuelDate.setText(ldt.toString());

        List<String> listFuelTypes = Arrays.asList("LPG", "Benzyna" ,"Diesel", "Wodór" ,"Prąd");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, listFuelTypes);
        spinnerFuelType.setAdapter(adapter);


    }

    public void Exit(View view) {
        finish();
    }

    public void Save(View view) {
        String fuelType, fuelDate,   fuelWhere;
        Double fuelPrice, fuelFullPrice,fuelAmount;
        fuelPrice = Double.parseDouble(etFuelPrice.getText().toString());
        fuelFullPrice = Double.parseDouble(etFuelFullPrice.getText().toString());
        fuelWhere = etFuelWhere.getText().toString();
        fuelAmount = Double.parseDouble(etFuelAmount.getText().toString());
        fuelDate = ldt.toString();
        fuelType = spinnerFuelType.getSelectedItem().toString();

        fuelFill = new FuelFill(1, fuelDate,fuelPrice,fuelFullPrice,fuelWhere,fuelAmount,fuelType);
        dbManager.addFuelFIllToDb(fuelFill);

    }
    public Double FuelFullPrice(Double amount, Double price){
        BigDecimal bdAmount = BigDecimal.valueOf(amount);
        BigDecimal bdPrice = BigDecimal.valueOf(price);
        bdPrice = bdPrice.multiply(bdAmount).setScale(2);

        return Double.valueOf(String.valueOf(bdPrice));
    }

    public void FuelDate(View view) {
        datePicker();
    }

    public void datePicker(){
        int mYear, mMonth, mDay;
        final Calendar c = Calendar.getInstance();

        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        /*if(extras != null)
        {
            ldt = LocalDate.parse();
            mYear = ldt.getYear();
            mMonth = ldt.getMonthValue()-1;
            mDay = ldt.getDayOfMonth();
        }*/
        DatePickerDialog datePickerDialog = new DatePickerDialog(AddictionFuel.this,
                (view, year, monthOfYear, dayOfMonth) -> {
                    monthOfYear = monthOfYear +1;
                    strDay = dayOfMonth+"";
                    if(strDay.length() == 1) strDay = "0"+strDay;

                    strMonth = monthOfYear+"";
                    if(strMonth.length() == 1) strMonth = "0"+strMonth;

                    String data = year + "-"+strMonth+"-"+strDay;

                    ldt = LocalDate.parse(data);
                    tvFuelDate.setText(data);


                }, mYear, mMonth, mDay);


        datePickerDialog.getDatePicker().setFirstDayOfWeek(Calendar.MONDAY);
        datePickerDialog.show();
    }
}