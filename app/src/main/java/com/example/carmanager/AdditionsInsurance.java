package com.example.carmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.carmanager.models.Insurance;
import com.example.carmanager.models.Maintenance;
import com.example.carmanager.models.Mileage;

import java.time.LocalDate;
import java.util.Calendar;

public class AdditionsInsurance extends AppCompatActivity {

    DbManager dbManager;
    Boolean whatDatePicker;
    Insurance insurance, object;
    Button btnSave, btnExit;
    TextView tvInsuranceFromDate, tvInsuranceNextDate;
    EditText etInsurancePrice, etInsuranceCompany,etInsuranceNumber, etInsuranceNote;
    LocalDate ldt, ldt2;
    String data, strMonth, strDay;
    Bundle extras;
    int idToEdit;
    SharedPreferences sh;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additions_insurance);
        sh = getSharedPreferences("activeCar", MODE_PRIVATE);
        dbManager = new DbManager(this);

        btnSave = findViewById(R.id.btnSave);
        btnExit = findViewById(R.id.btnExit);
        tvInsuranceFromDate = findViewById(R.id.tvInsuranceFromDate);
        tvInsuranceNextDate = findViewById(R.id.tvInsuranceNextDate);
        etInsurancePrice = findViewById(R.id.etInsurancePrice);
        etInsuranceCompany = findViewById(R.id.etInsuranceCompany);
        etInsuranceNumber = findViewById(R.id.etInsuranceNumber);
        etInsuranceNote = findViewById(R.id.etInsuranceNote);

        ldt = java.time.LocalDate.now();
        ldt2 = java.time.LocalDate.now().plusYears(1);


        extras = getIntent().getExtras();

        if(extras != null){
            idToEdit = extras.getInt("id");
            dbManager.fillMileageArrayList();
            for (int i = 0; i < Insurance.listOfInsurance.size(); i++) {
                if (Insurance.listOfInsurance.get(i).getInsuranceId() == idToEdit) {
                    object = Insurance.listOfInsurance.get(i);
                }}
            etInsurancePrice.setText(object.getPrice().toString());
            etInsuranceCompany.setText(object.getProvider().toString());
            etInsuranceNumber.setText(object.getInsuranceNumber().toString());
            etInsuranceNote.setText(object.getInsuranceType().toString());

            ldt = LocalDate.parse(object.getStartDate());
            ldt2 = LocalDate.parse(object.getExpirationDate());
        }

        tvInsuranceFromDate.setText(ldt.toString());
        tvInsuranceNextDate.setText(ldt2.toString());
    }

    public void InsuranceFromDate(View view) {
        whatDatePicker = true;
        datePicker();

    }

    public void InsuranceNextDate(View view) {
        whatDatePicker = false;
        datePicker();

    }

    public void Exit(View view) {
        finish();
    }

    public void Save(View view) {
        String InsuranceFromDate, InsuranceNextDate, InsuranceCompany, InsuranceNumber, InsuranceNote;
        Double InsurancePrice;
        InsurancePrice = Double.parseDouble(etInsurancePrice.getText().toString());
        InsuranceCompany = etInsuranceCompany.getText().toString();
        InsuranceNumber = etInsuranceNumber.getText().toString();
        InsuranceNote = etInsuranceNote.getText().toString();

        InsuranceFromDate = ldt.toString();
        InsuranceNextDate = ldt2.toString();

        if(extras!=null){
            insurance = new Insurance(object.getInsuranceId(),sh.getInt("activeCarId",0),InsuranceFromDate,InsuranceNextDate,InsuranceCompany,InsurancePrice,InsuranceNote,InsuranceNumber);
            dbManager.updateInsuranceInDb(insurance);
        }
        else
        {
            insurance = new Insurance(sh.getInt("activeCarId",0),InsuranceFromDate,InsuranceNextDate,InsuranceCompany,InsurancePrice,InsuranceNote,InsuranceNumber);
            dbManager.addInsuranceToDb(insurance);
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
            if(whatDatePicker == false) {
                mYear = ldt2.getYear();
                mMonth = ldt2.getMonthValue() - 1;
                mDay = ldt2.getDayOfMonth();
            }
        }
        DatePickerDialog datePickerDialog = new DatePickerDialog(AdditionsInsurance.this,
                (view, year, monthOfYear, dayOfMonth) -> {
                    monthOfYear = monthOfYear +1;
                    strDay = dayOfMonth+"";
                    if(strDay.length() == 1) strDay = "0"+strDay;

                    strMonth = monthOfYear+"";
                    if(strMonth.length() == 1) strMonth = "0"+strMonth;

                    String data = year + "-"+strMonth+"-"+strDay;

                    if(whatDatePicker == true){
                        ldt = LocalDate.parse(data);
                        tvInsuranceFromDate.setText(data);
                    }else{
                        ldt2 = LocalDate.parse(data);
                        tvInsuranceNextDate.setText(data);
                    }



                }, mYear, mMonth, mDay);

        datePickerDialog.getDatePicker().setFirstDayOfWeek(Calendar.MONDAY);
        datePickerDialog.show();
    }
}