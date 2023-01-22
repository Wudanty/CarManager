package com.example.carmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.carmanager.models.Fix;
import com.example.carmanager.models.Maintenance;

import java.time.LocalDate;
import java.util.Calendar;

public class AdditionOperatingElements extends AppCompatActivity {

    DbManager dbManager;
    Boolean whatDatePicker;
    Maintenance maintenance;
    Button btnSave, btnExit;
    TextView tvOperatingElementsDate, tvOperatingElementsNextSlash, tvOperatingElementsNextDate;
    EditText etOperatingElementsPrice, etOperatingElementsWhere,etOperatingElementsNote, etOperatingElementsPart,etOperatingElementsNextMileage;
    RadioButton rbOperationalElementChangeByDate,rbOperationalElementChangeByBoth,rbOperationalElementChangeByMileage;
    LocalDate ldt, ldt2;
    String data, strMonth, strDay;
    Bundle extras;
    int idToEdit;
    SharedPreferences sh;
    Maintenance object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition_operating_elements);

        dbManager = new DbManager(this);
        sh = getSharedPreferences("activeCar", MODE_PRIVATE);
        btnSave = findViewById(R.id.btnSave);
        btnExit = findViewById(R.id.btnExit);
        tvOperatingElementsDate = findViewById(R.id.tvOperatingElementsDate);
        tvOperatingElementsNextDate = findViewById(R.id.tvOperatingElementsNextDate);
        etOperatingElementsNextMileage = findViewById(R.id.etOperatingElementsNextMileage);
        tvOperatingElementsNextSlash = findViewById(R.id.tvOperatingElementsNextSlash);
        etOperatingElementsNote = findViewById(R.id.etOperatingElementsNote);
        etOperatingElementsPrice = findViewById(R.id.etOperatingElementsPrice);
        etOperatingElementsWhere = findViewById(R.id.etOperatingElementsWhere);
        etOperatingElementsPart = findViewById(R.id.etOperatingElementsPart);
        rbOperationalElementChangeByDate = findViewById(R.id.rbOperationalElementChangeByDate);
        rbOperationalElementChangeByMileage = findViewById(R.id.rbOperationalElementChangeByMileage);
        rbOperationalElementChangeByBoth = findViewById(R.id.rbOperationalElementChangeByBoth);

        ldt = java.time.LocalDate.now();
        ldt2 = java.time.LocalDate.now().plusYears(1);
        tvOperatingElementsDate.setText(ldt.toString());
        tvOperatingElementsNextDate.setText(ldt2.toString());

        extras = getIntent().getExtras();

        if(extras != null) {
            idToEdit = extras.getInt("id");
            dbManager.fillFuelFillArrayList();
            for (int i = 0; i < Maintenance.listOfMaintance.size(); i++) {
                if (Maintenance.listOfMaintance.get(i).getMaintenanceId() == idToEdit) {
                    object = Maintenance.listOfMaintance.get(i);
                }}

            ldt = LocalDate.parse(object.getMaintenanceDate());
            try {
                ldt2 = LocalDate.parse(object.getNextMaintenanceDate());
            }
            catch(Exception e) {
                tvOperatingElementsNextDate.setText(ldt2.toString());
            }





            tvOperatingElementsDate.setText(object.getMaintenanceDate());
            tvOperatingElementsNextDate.setText(object.getNextMaintenanceDate());
            etOperatingElementsNextMileage.setText(object.getNextMileage().toString());
            etOperatingElementsNote.setText(object.getDescription());
            etOperatingElementsPrice.setText(object.getPrice().toString());
            etOperatingElementsWhere.setText(object.getPlace());
            etOperatingElementsPart.setText(object.getMaintenanceTarget());

            if(!object.getNextMileage().equals(0.0) && !object.getNextMaintenanceDate().equals("")){
                rbOperationalElementChangeByBoth.setChecked(true);
                etOperatingElementsNextMileage.setVisibility(View.VISIBLE);
                tvOperatingElementsNextSlash.setVisibility(View.VISIBLE);
                tvOperatingElementsNextDate.setVisibility(View.VISIBLE);
            }
            else if(object.getNextMileage().equals(0.0)){
                rbOperationalElementChangeByDate.setChecked(true);
                tvOperatingElementsNextDate.setVisibility(View.VISIBLE);
                etOperatingElementsNextMileage.setVisibility(View.GONE);
                tvOperatingElementsNextSlash.setVisibility(View.GONE);            }
            else{
                rbOperationalElementChangeByMileage.setChecked(true);
                etOperatingElementsNextMileage.setVisibility(View.VISIBLE);
                tvOperatingElementsNextDate.setVisibility(View.GONE);
                tvOperatingElementsNextSlash.setVisibility(View.GONE);            }

        }



    }
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rbOperationalElementChangeByBoth:
                if (checked)
                    etOperatingElementsNextMileage.setVisibility(View.VISIBLE);
                    tvOperatingElementsNextSlash.setVisibility(View.VISIBLE);
                    tvOperatingElementsNextDate.setVisibility(View.VISIBLE);
                    break;
            case R.id.rbOperationalElementChangeByDate:
                if (checked)
                    tvOperatingElementsNextDate.setVisibility(View.VISIBLE);
                    etOperatingElementsNextMileage.setVisibility(View.GONE);
                    tvOperatingElementsNextSlash.setVisibility(View.GONE);
                    break;
            case R.id.rbOperationalElementChangeByMileage:
                if (checked)
                    etOperatingElementsNextMileage.setVisibility(View.VISIBLE);
                    tvOperatingElementsNextDate.setVisibility(View.GONE);
                    tvOperatingElementsNextSlash.setVisibility(View.GONE);
                    break;
        }
    }

    public void OperatingElementsDate(View view) {
        whatDatePicker = true;
        datePicker();
    }

    public void OperatingElementsNextDate(View view) {
        whatDatePicker = false;
        datePicker();

    }

    public void Exit(View view) {

        finish();
    }

    public void Save(View view) {
        String OperatingElementsDate, OperatingElementsNextDate, OperatingElementsWhere, OperatingElementsNote, OperatingElementsPart;
        Double OperatingElementsNextMileage, OperatingElementsPrice;
        OperatingElementsPrice = Double.parseDouble(etOperatingElementsPrice.getText().toString());
        OperatingElementsWhere = etOperatingElementsWhere.getText().toString();
        OperatingElementsPart = etOperatingElementsPart.getText().toString();
        OperatingElementsNote = etOperatingElementsNote.getText().toString();


        OperatingElementsDate = ldt.toString();

        if (extras != null) {
            if (rbOperationalElementChangeByDate.isChecked() == true) {
                OperatingElementsNextDate = ldt2.toString();
                maintenance = new Maintenance(object.getMaintenanceId(),sh.getInt("activeCarId",0),OperatingElementsDate,OperatingElementsPart,OperatingElementsNextDate,0.0,OperatingElementsPrice,OperatingElementsNote,OperatingElementsWhere);
            }
            else if (rbOperationalElementChangeByMileage.isChecked() == true) {
                OperatingElementsNextMileage = Double.parseDouble(etOperatingElementsNextMileage.getText().toString());
                maintenance = new Maintenance(object.getMaintenanceId(),sh.getInt("activeCarId",0),OperatingElementsDate,OperatingElementsPart,"",OperatingElementsNextMileage,OperatingElementsPrice,OperatingElementsNote,OperatingElementsWhere);
            }
            else if (rbOperationalElementChangeByBoth.isChecked() == true) {
                OperatingElementsNextDate = ldt2.toString();
                OperatingElementsNextMileage = Double.parseDouble(etOperatingElementsNextMileage.getText().toString());
                maintenance = new Maintenance(object.getMaintenanceId(),sh.getInt("activeCarId",0),OperatingElementsDate,OperatingElementsPart,OperatingElementsNextDate,OperatingElementsNextMileage,OperatingElementsPrice,OperatingElementsNote,OperatingElementsWhere);
            }
            dbManager.updateMaintenanceInDb(maintenance);

        }
        else
        {
            if (rbOperationalElementChangeByDate.isChecked() == true) {
                OperatingElementsNextDate = ldt2.toString();
                maintenance = new Maintenance(sh.getInt("activeCarId",0),OperatingElementsDate,OperatingElementsPart,OperatingElementsNextDate,0.0,OperatingElementsPrice,OperatingElementsNote,OperatingElementsWhere);

            }
            else if (rbOperationalElementChangeByMileage.isChecked() == true) {
                OperatingElementsNextMileage = Double.parseDouble(etOperatingElementsNextMileage.getText().toString());
                maintenance = new Maintenance(sh.getInt("activeCarId",0),OperatingElementsDate,OperatingElementsPart,"",OperatingElementsNextMileage,OperatingElementsPrice,OperatingElementsNote,OperatingElementsWhere);

            }
            else if (rbOperationalElementChangeByBoth.isChecked() == true) {
                OperatingElementsNextDate = ldt2.toString();
                OperatingElementsNextMileage = Double.parseDouble(etOperatingElementsNextMileage.getText().toString());
                maintenance = new Maintenance(sh.getInt("activeCarId",0),OperatingElementsDate,OperatingElementsPart,OperatingElementsNextDate,OperatingElementsNextMileage,OperatingElementsPrice,OperatingElementsNote,OperatingElementsWhere);

            }
            dbManager.addMaintenanceToDb(maintenance);
        }



        Intent intent = new Intent(view.getContext(), History.class);
        startActivity(intent);
        finish();
    }

    public void datePicker(){
        int mYear, mMonth, mDay;
        final Calendar c = Calendar.getInstance();

        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        if(extras != null)
        {
            mYear = ldt.getYear();
            mMonth = ldt.getMonthValue()-1;
            mDay = ldt.getDayOfMonth();
        }
        DatePickerDialog datePickerDialog = new DatePickerDialog(AdditionOperatingElements.this,
                (view, year, monthOfYear, dayOfMonth) -> {
                    monthOfYear = monthOfYear +1;
                    strDay = dayOfMonth+"";
                    if(strDay.length() == 1) strDay = "0"+strDay;

                    strMonth = monthOfYear+"";
                    if(strMonth.length() == 1) strMonth = "0"+strMonth;

                    String data = year + "-"+strMonth+"-"+strDay;

                    if(whatDatePicker == true){
                        ldt = LocalDate.parse(data);
                        tvOperatingElementsDate.setText(data);
                    }else{
                        ldt2 = LocalDate.parse(data);
                        tvOperatingElementsNextDate.setText(data);
                    }



                }, mYear, mMonth, mDay);

        datePickerDialog.getDatePicker().setFirstDayOfWeek(Calendar.MONDAY);
        datePickerDialog.show();
    }
}