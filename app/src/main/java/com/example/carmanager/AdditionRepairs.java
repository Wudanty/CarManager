package com.example.carmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.carmanager.models.Fix;
import com.example.carmanager.models.FuelFill;

import java.time.LocalDate;
import java.util.Calendar;

public class AdditionRepairs extends AppCompatActivity {

    DbManager dbManager;
    Fix fix;
    Button btnSave, btnExit;
    TextView tvRepairsDate;
    EditText etRepairsNote, etRepairsPrice,etRepairsWhere, etRepairsPart;
    LocalDate ldt;
    String data, strMonth, strDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition_repairs);

        dbManager = new DbManager(this);

        btnSave = findViewById(R.id.btnSave);
        btnExit = findViewById(R.id.btnExit);
        tvRepairsDate = findViewById(R.id.tvRepairsDate);
        etRepairsNote = findViewById(R.id.etRepairsNote);
        etRepairsPrice = findViewById(R.id.etRepairsPrice);
        etRepairsWhere = findViewById(R.id.etRepairsWhere);
        etRepairsPart = findViewById(R.id.etRepairsPart);

        ldt = java.time.LocalDate.now();
        tvRepairsDate.setText(ldt.toString());

    }

    public void RepairsDate(View view) {
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
        DatePickerDialog datePickerDialog = new DatePickerDialog(AdditionRepairs.this,
                (view, year, monthOfYear, dayOfMonth) -> {
                    monthOfYear = monthOfYear +1;
                    strDay = dayOfMonth+"";
                    if(strDay.length() == 1) strDay = "0"+strDay;

                    strMonth = monthOfYear+"";
                    if(strMonth.length() == 1) strMonth = "0"+strMonth;

                    String data = year + "-"+strMonth+"-"+strDay;

                    ldt = LocalDate.parse(data);
                    tvRepairsDate.setText(data);


                }, mYear, mMonth, mDay);

        datePickerDialog.getDatePicker().setFirstDayOfWeek(Calendar.MONDAY);
        datePickerDialog.show();
    }

    public void Exit(View view) {
        finish();
    }

    public void Save(View view) {

        String RepairsPart, RepairsDate,   RepairsWhere,   RepairsNote;
        Double RepairsPrice;
        RepairsPrice = Double.parseDouble(etRepairsPrice.getText().toString());
        RepairsNote = etRepairsNote.getText().toString();
        RepairsWhere = etRepairsWhere.getText().toString();
        RepairsPart = etRepairsPart.getText().toString();
        RepairsDate = ldt.toString();


        fix = new Fix(1,RepairsDate,RepairsPart,RepairsNote,RepairsPrice,RepairsWhere);
        dbManager.addFixToDb(fix);

        finish();
    }
}