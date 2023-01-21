package com.example.carmanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.math.MathContext;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.carmanager.models.Checkup;
import com.example.carmanager.models.Fix;
import com.example.carmanager.models.FuelFill;
import com.example.carmanager.models.Insurance;
import com.example.carmanager.models.Maintenance;
import com.example.carmanager.models.Mileage;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.android.material.slider.LabelFormatter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Raports extends AppCompatActivity {

    //Toolbar-----------------------------------------------
    public androidx.appcompat.widget.Toolbar toolbar;
    Button btnCar, btnMoreActivities, btnHistory, btnSettings, btnMainActivity;
    //Toolbar-----------------------------------------------
    Button FuelFillBtn, MaintenanceBtn,  FixBtn, MileageBtn, MoneyBtn;
    TextView tvCarNameRaports;
    ListView listViewRaports;
    ArrayList<AdapterRekordRaports> adapterRekordRaports = new ArrayList<>();
    DbManager dbManager = DbManager.instanceOfDatabase(this);
    BarChart barChart;
    Mileage mileage;
    FuelFill fuelFill;
    Fix fix;
    Maintenance maintenance;
    Checkup checkup;
    Insurance insurance;
    AdapterRekordInflater customAdapter;
    Bundle extras;
    int idToEdit;
    SharedPreferences sh;
    LinearLayout columnNames;
    View column = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raports);
        sh = getSharedPreferences("activeCar", MODE_PRIVATE);
        //Toolbar-----------------------------------------------
        btnCar = findViewById(R.id.car);
        btnMoreActivities = findViewById(R.id.more);
        btnSettings = findViewById(R.id.settings);
        btnMainActivity = findViewById(R.id.mainActivity);
        btnHistory = findViewById(R.id.history);



//        ArrayList barArrayList;
//        barArrayList = new ArrayList<>();
//        for(AdapterRekordRaports anArray:adapterRekordRaports){
//            barArrayList.add(new BarEntry(adapterRekordRaports.indexOf(anArray),Integer.parseInt(anArray.getData1())));
//        }
//        BarDataSet barDataSet= new BarDataSet(barArrayList,"Wykres");
//        BarData barData = new BarData(barDataSet);
//        barChart.setData(barData);

        barChart = findViewById(R.id.barChart);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularityEnabled(true);
        barChart.getXAxis().setLabelCount(12);
        xAxis.setGranularity(1f);
        xAxis.setTextSize(5);




        FuelFillBtn = findViewById(R.id.buttonFuel);
        MaintenanceBtn = findViewById(R.id.buttonMaintenance);
        FixBtn = findViewById(R.id.buttonFix);
        MileageBtn = findViewById(R.id.buttonMileage);
        MoneyBtn = findViewById(R.id.buttonMoney);

        tvCarNameRaports = findViewById(R.id.tvCarNameRaports);

        listViewRaports = findViewById(R.id.listViewRaports);

        columnNames=findViewById(R.id.layoutColumnNamesRaports);

        toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(null);

        column = getLayoutInflater().inflate(R.layout.adapter_raports_fuel, null);
        columnNames.removeAllViews();
        columnNames.addView(column);
        LoadMonthyFuel();
        btnCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(Raports.this, CarActivity.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity( intentSP );
            }
        });
        btnMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(Raports.this, MainActivity.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity( intentSP );
            }
        });
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(Raports.this, History.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity( intentSP );
            }
        });
        btnMoreActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                more(null);
            }
        });
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(Raports.this, Raports.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity( intentSP );
            }
        });
        //Toolbar-----------------------------------------------
        FixBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                column = getLayoutInflater().inflate(R.layout.adapter_raports_fix, null);
                columnNames.removeAllViews();
                columnNames.addView(column);
                RaportMonthlyRepairs();
            }
        });
        FuelFillBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                column = getLayoutInflater().inflate(R.layout.adapter_raports_fuel, null);
                columnNames.removeAllViews();
                columnNames.addView(column);
                LoadMonthyFuel();
            }
        });
        MaintenanceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                column = getLayoutInflater().inflate(R.layout.adapter_raports_fix, null);
                columnNames.removeAllViews();
                columnNames.addView(column);
                RaportMonthlyMaintenance();
            }
        });
        MileageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                column = getLayoutInflater().inflate(R.layout.adapter_raports_mileage, null);
                columnNames.removeAllViews();
                columnNames.addView(column);
                RaportMonthlyMileage();
            }
        });
        MoneyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                column = getLayoutInflater().inflate(R.layout.adapter_raports_money, null);
                columnNames.removeAllViews();
                columnNames.addView(column);
                try{
                    RaportMonthlyMoneySpend();
                }
                catch(Exception e){};


            }
        });

    }
    //DataType: 0 = Fuel, 1 = mileage, 2 = Repairs, 3 = Maintence, 4 = Money
    public void RaportMonthlyMoneySpend(){
        dbManager.fillCheckupArrayList();
        dbManager.fillInsuranceArrayList();
        dbManager.fillMaintenanceArrayList();
        dbManager.fillFixArrayList();
        dbManager.fillFuelFillArrayList();
        customAdapter = new AdapterRekordInflater(this, R.layout.adapter_raports_money, adapterRekordRaports);
        adapterRekordRaports.clear();
        LocalDate dateFirst, dateLast, dateTemp;
        int firstMonth, lastMonth, firstYear, lastYear, monthTemp, yearTemp;
        String month;
        BigDecimal money = new BigDecimal(0);
        BigDecimal moneyDiff = new BigDecimal(0);
        List<LocalDate> dateList = new ArrayList<>();
        Locale userLocale = new Locale("pl");
        if(Insurance.listOfInsurance.size()>0){
            dateList.add(LocalDate.parse(Insurance.listOfInsurance.get(0).getStartDate()));
            dateList.add(LocalDate.parse(Insurance.listOfInsurance.get(Insurance.listOfInsurance.size()-1).getStartDate()));}
        if(Maintenance.listOfMaintance.size()>0){
            dateList.add(LocalDate.parse(Maintenance.listOfMaintance.get(0).getMaintenanceDate()));
            dateList.add(LocalDate.parse(Maintenance.listOfMaintance.get(Maintenance.listOfMaintance.size()-1).getMaintenanceDate()));}
        if(Fix.listOfFix.size()>0){
            dateList.add(LocalDate.parse(Fix.listOfFix.get(0).getDateOfFix()));
            dateList.add(LocalDate.parse(Fix.listOfFix.get(Fix.listOfFix.size()-1).getDateOfFix()));}
        if(Checkup.listOfCheckup.size()>0){
            dateList.add(LocalDate.parse(Checkup.listOfCheckup.get(0).getDate()));
            dateList.add(LocalDate.parse(Checkup.listOfCheckup.get(Checkup.listOfCheckup.size()-1).getDate()));}
        if(FuelFill.listOfFuelFill.size()>0){
            dateList.add(LocalDate.parse(FuelFill.listOfFuelFill.get(0).getFillDate()));
            dateList.add(LocalDate.parse(FuelFill.listOfFuelFill.get(FuelFill.listOfFuelFill.size()-1).getFillDate()));
        }


        dateFirst = Collections.min(dateList);
        dateLast = Collections.max(dateList);
        Log.d("test pieniądze",dateFirst+"  "+dateLast);

        firstMonth = dateFirst.getMonthValue();
        lastMonth = dateLast.getMonthValue();
        firstYear = dateFirst.getYear();
        lastYear = dateLast.getYear();

        int k = 0, l = 0, m = 0, n = 0,o = 0;
        Log.d("test pieniądze",firstMonth+"  "+lastMonth+"   "+firstYear+"   "+lastYear);

        for (int i = firstYear;i<=lastYear;i++){
            for (int j = firstMonth;j<=12;j++){
                for(;k<FuelFill.listOfFuelFill.size();k++){
                    dateTemp = LocalDate.parse(FuelFill.listOfFuelFill.get(k).getFillDate());
                    Log.d("test pieniądze paliwo data",dateTemp+"");
                    monthTemp = dateTemp.getMonthValue();
                    yearTemp = dateTemp.getYear();
                    if(monthTemp==j&&yearTemp==i&&FuelFill.listOfFuelFill.get(k).getCarId()==sh.getInt("activeCarId",0)){
                        money = money.add(BigDecimal.valueOf(FuelFill.listOfFuelFill.get(k).getPrice())) ;
                        Log.d("test pieniądze paliwo zapisz",money+" "+ monthTemp+" "+yearTemp);
                    }
                    if((monthTemp>j&&yearTemp==i)||yearTemp>i){
                        Log.d("test pieniądze paliwo przerwij",monthTemp+" "+yearTemp);


                        break;
                    }

                }
                for(;l<Maintenance.listOfMaintance.size();l++){
                    dateTemp = LocalDate.parse(Maintenance.listOfMaintance.get(l).getMaintenanceDate());
                    monthTemp = dateTemp.getMonthValue();
                    yearTemp = dateTemp.getYear();
                    if(monthTemp==j&&yearTemp==i&&Maintenance.listOfMaintance.get(l).getCarId()==sh.getInt("activeCarId",0)){
                        money = money.add(BigDecimal.valueOf(Maintenance.listOfMaintance.get(l).getPrice())) ;
                    }
                    if((monthTemp>j&&yearTemp==i)||yearTemp>i){
                        break;
                    }
                }
                for(;m<Fix.listOfFix.size();m++){
                    dateTemp = LocalDate.parse(Fix.listOfFix.get(m).getDateOfFix());
                    monthTemp = dateTemp.getMonthValue();
                    yearTemp = dateTemp.getYear();
                    if(monthTemp==j&&yearTemp==i&&Fix.listOfFix.get(m).getCarId()==sh.getInt("activeCarId",0)){
                        money = money.add(BigDecimal.valueOf(Fix.listOfFix.get(m).getPrice())) ;
                    }
                    if((monthTemp>j&&yearTemp==i)||yearTemp>i){
                        break;
                    }
                }
                for(;n<Insurance.listOfInsurance.size();n++){
                    dateTemp = LocalDate.parse(Insurance.listOfInsurance.get(n).getStartDate());
                    monthTemp = dateTemp.getMonthValue();
                    yearTemp = dateTemp.getYear();
                    if(monthTemp==j&&yearTemp==i&&Insurance.listOfInsurance.get(n).getCarId()==sh.getInt("activeCarId",0)){
                        money = money.add(BigDecimal.valueOf(Insurance.listOfInsurance.get(n).getPrice())) ;
                    }
                    if((monthTemp>j&&yearTemp==i)||yearTemp>i){
                        break;
                    }
                }
                for(;o<Checkup.listOfCheckup.size();o++){
                    dateTemp = LocalDate.parse(Checkup.listOfCheckup.get(o).getDate());
                    monthTemp = dateTemp.getMonthValue();
                    yearTemp = dateTemp.getYear();
                    if(monthTemp==j&&yearTemp==i&&Checkup.listOfCheckup.get(o).getCarId()==sh.getInt("activeCarId",0)){
                        money = money.add(BigDecimal.valueOf(Checkup.listOfCheckup.get(o).getPrice())) ;
                    }
                    if((monthTemp>j&&yearTemp==i)||yearTemp>i){
                        break;
                    }
                }



                if (adapterRekordRaports.size() > 0) {
                    moneyDiff = money.subtract(BigDecimal.valueOf(Double.parseDouble(adapterRekordRaports.get(adapterRekordRaports.size() - 1).getData1()))).setScale(2, RoundingMode.HALF_UP);
                }
                month = Month.of( j ).getDisplayName( TextStyle.FULL , userLocale )+" "+i;
                AdapterRekordRaports rekord = new AdapterRekordRaports(money + "", moneyDiff+"", "", "", "", "", month, 4);
                adapterRekordRaports.add(rekord);
                money = new BigDecimal(0);


                if(j==lastMonth&&i==lastYear){
                    break;
                }
            }

            firstMonth=1;
        }
        barChart.clear();
        ArrayList barArrayList=new ArrayList<>();
        ArrayList<String> ar = new ArrayList<String>();
        for(AdapterRekordRaports anArray:adapterRekordRaports){
            barArrayList.add(new BarEntry(adapterRekordRaports.indexOf(anArray),(int)Double.parseDouble(anArray.getData1())));
            ar.add(String.valueOf(anArray.getDate()));
        }
        BarDataSet barDataSet= new BarDataSet(barArrayList,"Zł");
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(ar));
        Collections.reverse(adapterRekordRaports);
        listViewRaports.setAdapter(customAdapter);
    }
    public void RaportMonthlyMileage(){
        customAdapter = new AdapterRekordInflater(this, R.layout.adapter_raports_mileage, adapterRekordRaports);

        String mileageStr, mileageDiffStr, mileageTempStr, dateStr, dateStrPlusOne, month, diffStr = "";
        int monthOfYearPlusOne=0;
        adapterRekordRaports.clear();
        dbManager.fillMileageArrayList();
        BigDecimal sumaBd = new BigDecimal(0);
        Locale userLocale = new Locale("pl");
        WeekFields weekNumbering = WeekFields.of(userLocale);
        for (int i = 0;i<Mileage.listOfMIleage.size();i++){
            if(Mileage.listOfMIleage.get(i).getCarId()==sh.getInt("activeCarId",0)) {
                dateStr = Mileage.listOfMIleage.get(i).getMileageCheckDate();
                LocalDate date = LocalDate.parse(dateStr);
                int monthOfYear = date.getMonthValue();

                if (i != Mileage.listOfMIleage.size() - 1) {
                    dateStrPlusOne = Mileage.listOfMIleage.get(i + 1).getMileageCheckDate();
                    LocalDate datePlusOne = LocalDate.parse(dateStrPlusOne);
                    monthOfYearPlusOne = datePlusOne.getMonthValue();
                }

                sumaBd = sumaBd.add(BigDecimal.valueOf(Mileage.listOfMIleage.get(i).getMileageValue())).setScale(0, RoundingMode.HALF_UP);

                if (i == Mileage.listOfMIleage.size() - 1 || monthOfYear != monthOfYearPlusOne) {

                    if (adapterRekordRaports.size() > 0) {
                        diffStr = String.valueOf(sumaBd.subtract(BigDecimal.valueOf(Double.parseDouble(adapterRekordRaports.get(adapterRekordRaports.size() - 1).getData1()))).setScale(0, RoundingMode.HALF_UP));
                    }
                    month = Month.of(date.getMonthValue()).getDisplayName(TextStyle.FULL_STANDALONE, userLocale) +
                            " " + date.getYear();

                    AdapterRekordRaports rekord = new AdapterRekordRaports(sumaBd + "", "", "", diffStr, "", "", month, 1);
                    adapterRekordRaports.add(rekord);

                    sumaBd = new BigDecimal(0);

                }
            }
        }
//        Collections.reverse(adapterRekordRaports);
//        ArrayList barArrayList;
//        barArrayList = new ArrayList<>();
//        ArrayList<String> ar = new ArrayList<String>();
//        for(AdapterRekordRaports anArray:adapterRekordRaports){
//            barArrayList.add(new BarEntry(adapterRekordRaports.indexOf(anArray),Integer.parseInt(anArray.getData2())));
//            ar.add(String.valueOf(anArray.getDate()));
//        }
//        BarDataSet barDataSet= new BarDataSet(barArrayList,"Zł");
//        BarData barData = new BarData(barDataSet);
//        barChart.setData(barData);
//        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(ar));
    }
    public void RaportMonthlyRepairs(){
        dbManager.fillFixArrayList();
        customAdapter = new AdapterRekordInflater(this, R.layout.adapter_raports_fix, adapterRekordRaports);
        String mileageStr, mileageDiffStr, mileageTempStr, dateStr, dateStrPlusOne, month, diffStr = "";
        int monthOfYearPlusOne=0, counter=0;
        adapterRekordRaports.clear();
        dbManager.fillMileageArrayList();
        BigDecimal sumaBd = new BigDecimal(0);
        Locale userLocale = new Locale("pl");
        WeekFields weekNumbering = WeekFields.of(userLocale);
        for (int i = 0;i<Fix.listOfFix.size();i++){
            if(Fix.listOfFix.get(i).getCarId()==sh.getInt("activeCarId",0)) {

                dateStr = Fix.listOfFix.get(i).getDateOfFix();
                LocalDate date = LocalDate.parse(dateStr);
                int monthOfYear = date.getMonthValue();

                if (i != Fix.listOfFix.size() - 1) {
                    dateStrPlusOne = Fix.listOfFix.get(i + 1).getDateOfFix();
                    LocalDate datePlusOne = LocalDate.parse(dateStrPlusOne);
                    monthOfYearPlusOne = datePlusOne.getMonthValue();
                }

                sumaBd = sumaBd.add(BigDecimal.valueOf(Fix.listOfFix.get(i).getPrice())).setScale(0, RoundingMode.HALF_UP);
                counter++;
                if (i == Fix.listOfFix.size() - 1 || monthOfYear != monthOfYearPlusOne) {

                    month = Month.of(date.getMonthValue()).getDisplayName(TextStyle.FULL_STANDALONE, userLocale) +
                            " " + date.getYear();

                    AdapterRekordRaports rekord = new AdapterRekordRaports(counter + "", sumaBd + "", "", "", "", "", month, 2);
                    adapterRekordRaports.add(rekord);

                    sumaBd = new BigDecimal(0);
                    counter = 0;
                }
            }
        }
        barChart.clear();
        ArrayList barArrayList;
        barArrayList = new ArrayList<>();
        ArrayList<String> ar = new ArrayList<String>();
        for(AdapterRekordRaports anArray:adapterRekordRaports){
            barArrayList.add(new BarEntry(adapterRekordRaports.indexOf(anArray),Integer.parseInt(anArray.getData2())));
            ar.add(String.valueOf(anArray.getDate()));
        }
        BarDataSet barDataSet= new BarDataSet(barArrayList,"Zł");
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(ar));
        Collections.reverse(adapterRekordRaports);
        listViewRaports.setAdapter(customAdapter);
    }
    public void RaportMonthlyMaintenance(){

        dbManager.fillMaintenanceArrayList();
        customAdapter = new AdapterRekordInflater(this, R.layout.adapter_raports_fix, adapterRekordRaports);
        Log.d("test","test");
        String mileageStr, mileageDiffStr, mileageTempStr, dateStr, dateStrPlusOne, month, diffStr = "";
        int monthOfYearPlusOne=0, counter=0;
        adapterRekordRaports.clear();

        BigDecimal sumaBd = new BigDecimal(0);
        Locale userLocale = new Locale("pl");
        WeekFields weekNumbering = WeekFields.of(userLocale);
        for (int i = 0;i<Maintenance.listOfMaintance.size();i++){
            if(Maintenance.listOfMaintance.get(i).getCarId()==sh.getInt("activeCarId",0)) {

                dateStr = Maintenance.listOfMaintance.get(i).getMaintenanceDate();
                LocalDate date = LocalDate.parse(dateStr);
                int monthOfYear = date.getMonthValue();

                if (i != Maintenance.listOfMaintance.size() - 1) {
                    dateStrPlusOne = Maintenance.listOfMaintance.get(i+1).getMaintenanceDate();
                    LocalDate datePlusOne = LocalDate.parse(dateStrPlusOne);
                    monthOfYearPlusOne = datePlusOne.getMonthValue();
                }

                sumaBd = sumaBd.add(BigDecimal.valueOf(Maintenance.listOfMaintance.get(i).getPrice())).setScale(0, RoundingMode.HALF_UP);
                counter++;
                if (i == Maintenance.listOfMaintance.size() - 1 || monthOfYear != monthOfYearPlusOne) {

                    month = Month.of(date.getMonthValue()).getDisplayName(TextStyle.FULL_STANDALONE, userLocale) +
                            " " + date.getYear();

                    AdapterRekordRaports rekord = new AdapterRekordRaports(counter + "", sumaBd + "", "", "", "", "", month, 3);
                    adapterRekordRaports.add(rekord);

                    sumaBd = new BigDecimal(0);
                    counter = 0;
                }
            }
        }
        barChart.clear();
        ArrayList barArrayList = new ArrayList<>();
        ArrayList<String> ar = new ArrayList<String>();
        for(AdapterRekordRaports anArray:adapterRekordRaports){
            barArrayList.add(new BarEntry(adapterRekordRaports.indexOf(anArray),Integer.parseInt(anArray.getData2())));
            ar.add(String.valueOf(anArray.getDate()));
        }
        BarDataSet barDataSet= new BarDataSet(barArrayList,"Zł");
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(ar));
        Collections.reverse(adapterRekordRaports);
        listViewRaports.setAdapter(customAdapter);
    }
    public void LoadMonthyFuel(){
        customAdapter = new AdapterRekordInflater(this, R.layout.adapter_raports_fuel, adapterRekordRaports);
        String mileageStr, mileageDiffStr, mileageTempStr, dateStr, dateStrPlusOne, month, diffStr = "";
        int monthOfYearPlusOne=0;
        adapterRekordRaports.clear();
        dbManager.fillFuelFillArrayList();
        BigDecimal fuelSum = new BigDecimal(0);
        BigDecimal moneySum = new BigDecimal(0);
        BigDecimal perLiterPrice;

        Locale userLocale = new Locale("pl");
        WeekFields weekNumbering = WeekFields.of(userLocale);
        for (int i = 0;i<FuelFill.listOfFuelFill.size();i++){
            if(FuelFill.listOfFuelFill.get(i).getCarId()==sh.getInt("activeCarId",0)) {
                dateStr = FuelFill.listOfFuelFill.get(i).getFillDate();
                LocalDate date = LocalDate.parse(dateStr);
                int monthOfYear = date.getMonthValue();

                if (i != FuelFill.listOfFuelFill.size() - 1) {
                    dateStrPlusOne = FuelFill.listOfFuelFill.get(i + 1).getFillDate();
                    LocalDate datePlusOne = LocalDate.parse(dateStrPlusOne);
                    monthOfYearPlusOne = datePlusOne.getMonthValue();
                }

                fuelSum = fuelSum.add(BigDecimal.valueOf(FuelFill.listOfFuelFill.get(i).getLiterAmount())).setScale(0, RoundingMode.HALF_UP);
                moneySum = moneySum.add(BigDecimal.valueOf(FuelFill.listOfFuelFill.get(i).getPrice())).setScale(0, RoundingMode.HALF_UP);

                if (i == FuelFill.listOfFuelFill.size() - 1 || monthOfYear != monthOfYearPlusOne) {

                    perLiterPrice = moneySum.divide(fuelSum, 2, RoundingMode.HALF_UP);

                    month = Month.of(date.getMonthValue()).getDisplayName(TextStyle.FULL_STANDALONE, userLocale) +
                            " " + date.getYear();

                    AdapterRekordRaports rekord = new AdapterRekordRaports(fuelSum + "", moneySum + "", perLiterPrice + "", "", "", "", month, 0);
                    adapterRekordRaports.add(rekord);

                    fuelSum = new BigDecimal(0);
                    moneySum = new BigDecimal(0);
                    perLiterPrice = new BigDecimal(0);

                }
            }
        }
        barChart.clear();
        ArrayList barArrayList=new ArrayList<>();
        ArrayList<String> ar = new ArrayList<String>();
        for(AdapterRekordRaports anArray:adapterRekordRaports){
            barArrayList.add(new BarEntry(adapterRekordRaports.indexOf(anArray),Integer.parseInt(anArray.getData2())));
            ar.add(String.valueOf(anArray.getDate()));
        }
        BarDataSet barDataSet= new BarDataSet(barArrayList,"Zł");
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(ar));
        Collections.reverse(adapterRekordRaports);
        listViewRaports.setAdapter(customAdapter);
    }


    public void more(View view){
        final LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.more, null);
        Button btnContacts=linearLayout.findViewById(R.id.btnContacts);
        Button btnReminder=linearLayout.findViewById(R.id.btnReminder);
        Button btnSettings=linearLayout.findViewById(R.id.btnContacts);
        final AlertDialog builder = new AlertDialog.Builder(this)
                .setView(linearLayout)
                .setCancelable(true)
                .create();
        builder.show();
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(builder.getWindow().getAttributes());
        lp.width = 580;
        lp.x=25;
        lp.y=140;

        lp.gravity = Gravity.TOP | Gravity.END;
        lp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        builder.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        builder.getWindow().setAttributes(lp);

        btnContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Raports.this, MoreActivities.class);
                startActivity(intent);
                builder.cancel();
            }
        });
        btnReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Raports.this, Notifications.class);
                startActivity(intent);
                builder.cancel();
            }
        });
    }



}
