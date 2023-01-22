package com.example.carmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.carmanager.models.Mileage;

import java.time.LocalDate;
import java.util.Calendar;

public class AdditionMileage extends AppCompatActivity {
    DbManager dbManager;
    Mileage mileage;
    EditText etMileage;
    TextView tvMileageDate;
    String data, strMonth, strDay;
    LocalDate ldt;
    Bundle extras;
    int idToEdit;
    SharedPreferences sh;
    Mileage object;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition_mileage);
        dbManager = new DbManager(this);

        tvMileageDate = findViewById(R.id.tvMileageDate);
        etMileage = findViewById(R.id.etMileage);
        sh = getSharedPreferences("activeCar", MODE_PRIVATE);

        ldt = java.time.LocalDate.now();
        tvMileageDate.setText(ldt.toString());

        extras = getIntent().getExtras();

        if(extras != null)
        {

            idToEdit = extras.getInt("id");
            dbManager.fillMileageArrayList();
            object = Mileage.listOfMIleage.get(idToEdit);

            ldt = LocalDate.parse(object.getMileageCheckDate());

            tvMileageDate.setText(object.getMileageCheckDate());
            etMileage.setText(object.getMileageValue().toString());
        }
    }

    public void Exit(View view) {
        finish();
    }

    public void Save(View view) {
        Double intMileage;
        String strMileageDate;
        strMileageDate = ldt.toString();
        intMileage = Double.parseDouble(etMileage.getText().toString());


        if(extras!=null){
            mileage = new Mileage(object.getMileageId(),sh.getInt("activeCarId",0),0, strMileageDate, Double.parseDouble(String.valueOf(intMileage)),0);
            dbManager.updateMileageInDb(mileage);
        }
        else
        {
            mileage = new Mileage(sh.getInt("activeCarId",0), strMileageDate, Double.parseDouble(String.valueOf(intMileage)));
            dbManager.addMileageToDb(mileage);
        }

        finish();
    }

    public void MileageDate(View view) {
        datePicker();
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
        DatePickerDialog datePickerDialog = new DatePickerDialog(AdditionMileage.this,
                (view, year, monthOfYear, dayOfMonth) -> {
                    monthOfYear = monthOfYear +1;
                    strDay = dayOfMonth+"";
                    if(strDay.length() == 1) strDay = "0"+strDay;

                    strMonth = monthOfYear+"";
                    if(strMonth.length() == 1) strMonth = "0"+strMonth;

                    String data = year + "-"+strMonth+"-"+strDay;

                    ldt = LocalDate.parse(data);
                    tvMileageDate.setText(data);


                }, mYear, mMonth, mDay);

        datePickerDialog.getDatePicker().setFirstDayOfWeek(Calendar.MONDAY);
        datePickerDialog.show();
    }
}