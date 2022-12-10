package com.example.carmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.carmanager.models.Checkup;
import com.example.carmanager.models.Mileage;

import java.time.LocalDate;
import java.util.Calendar;

public class AdditionCheckup extends AppCompatActivity {

    DbManager dbManager;
    Boolean whatDatePicker;
    Checkup checkup;
    Mileage mileage;
    Button btnSave, btnExit;
    TextView tvCheckupFromDate, tvCheckupNextDate;
    EditText etCheckupPrice, etCheckupWhere, etCheckupNote, etMileageAmount;
    LocalDate ldt, ldt2;
    String data, strMonth, strDay;
    int intResult, intCarId;
    RadioButton rbCheckupPositive,rbCheckupNegative;
    SharedPreferences sh;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition_checkup);

        dbManager = new DbManager(this);
        sh = getSharedPreferences("activeCar", MODE_PRIVATE);

        btnSave = findViewById(R.id.btnSave);
        btnExit = findViewById(R.id.btnExit);
        tvCheckupFromDate = findViewById(R.id.tvCheckupFromDate);
        tvCheckupNextDate = findViewById(R.id.tvCheckupNextDate);
        etCheckupPrice = findViewById(R.id.etCheckupPrice);
        etCheckupWhere = findViewById(R.id.etCheckupWhere);
        etCheckupNote = findViewById(R.id.etCheckupNote);
        etMileageAmount = findViewById(R.id.etMileageAmount);

        ldt = java.time.LocalDate.now();
        ldt2 = java.time.LocalDate.now().plusYears(1);
        tvCheckupFromDate.setText(ldt.toString());
        tvCheckupNextDate.setText(ldt2.toString());

        rbCheckupPositive = findViewById(R.id.rbCheckupPositive);
        rbCheckupNegative = findViewById(R.id.rbCheckupNegative);

        intResult = 1;
    }

    public void CheckupFromDate(View view) {
        whatDatePicker = true;
        datePicker();
    }

    public void CheckupNextDate(View view) {
        whatDatePicker = false;
        datePicker();
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.rbCheckupPositive:
                if (checked)
                    intResult = 1;
                break;
            case R.id.rbCheckupNegative:
                if (checked)
                    intResult = 0;
                break;
        }
    }

    public void Save(View view) {
        Double intMileage;
        String strMileageDate;
        strMileageDate = ldt.toString();
        intMileage = Double.parseDouble(etMileageAmount.getText().toString());

        mileage = new Mileage(0, strMileageDate, intMileage);
        dbManager.addMileageToDb(mileage);

        dbManager.fillMileageArrayList();



        String CheckupFromDate, CheckupNextDate, CheckupNote, CheckupWhere;
        Double CheckupPrice;
        int mileageId= Mileage.listOfMIleage.get(Mileage.listOfMIleage.size()-1).getMileageId();
        CheckupPrice = Double.parseDouble(etCheckupPrice.getText().toString());
        CheckupWhere = etCheckupWhere.getText().toString();
        CheckupNote = etCheckupNote.getText().toString();

        CheckupFromDate = ldt.toString();
        CheckupNextDate = ldt2.toString();

        checkup = new Checkup(sh.getInt("activeCarId",0),mileageId,CheckupFromDate,CheckupNextDate,CheckupWhere,CheckupPrice, intResult,CheckupNote);

        dbManager.addCheckupToDb(checkup);

        finish();
    }

    public void Exit(View view) {
        finish();
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
        DatePickerDialog datePickerDialog = new DatePickerDialog(AdditionCheckup.this,
                (view, year, monthOfYear, dayOfMonth) -> {
                    monthOfYear = monthOfYear +1;
                    strDay = dayOfMonth+"";
                    if(strDay.length() == 1) strDay = "0"+strDay;

                    strMonth = monthOfYear+"";
                    if(strMonth.length() == 1) strMonth = "0"+strMonth;

                    String data = year + "-"+strMonth+"-"+strDay;

                    if(whatDatePicker == true){
                        ldt = LocalDate.parse(data);
                        tvCheckupFromDate.setText(data);
                    }else{
                        ldt2 = LocalDate.parse(data);
                        tvCheckupNextDate.setText(data);
                    }



                }, mYear, mMonth, mDay);

        datePickerDialog.getDatePicker().setFirstDayOfWeek(Calendar.MONDAY);
        datePickerDialog.show();
    }
}