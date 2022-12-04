package com.example.carmanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.carmanager.models.Checkup;
import com.example.carmanager.models.Fix;
import com.example.carmanager.models.FuelFill;
import com.example.carmanager.models.Maintenance;
import com.example.carmanager.models.Mileage;

import java.util.Objects;

public class History extends AppCompatActivity {

    //Toolbar-----------------------------------------------
    public androidx.appcompat.widget.Toolbar toolbar;
    Button btnCar, btnMoreActivities, btnHistory, btnSettings, btnMainActivity;
    //Toolbar-----------------------------------------------

    Button FuelFillBtn, MaintenanceBtn, CheckUpBtn, FixBtn, MileageBtn;
    ListView ListViewHistory;
    LinearLayout layoutColumnNames;
    DbManager dbManager = DbManager.instanceOfDatabase(this);
    int number = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //Toolbar-----------------------------------------------
        btnCar = findViewById(R.id.car);
        btnMoreActivities = findViewById(R.id.more);
        btnSettings = findViewById(R.id.settings);
        btnMainActivity = findViewById(R.id.mainActivity);
        btnHistory = findViewById(R.id.history);

        toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(null);

        FuelFillBtn = findViewById(R.id.buttonFuel);
        MaintenanceBtn = findViewById(R.id.buttonMaintenance);
        CheckUpBtn = findViewById(R.id.buttonCheckUp);
        FixBtn = findViewById(R.id.buttonFix);
        MileageBtn = findViewById(R.id.buttonMileage);

        ListViewHistory = findViewById(R.id.ListViewHistory);
        layoutColumnNames = findViewById(R.id.layoutColumnNames);

        btnCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(History.this, CarActivity.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentSP);
            }
        });
        btnMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(History.this, MainActivity.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentSP);
            }
        });
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(History.this, History.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentSP);
            }
        });
        btnMoreActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(History.this, MoreActivities.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentSP);
            }
        });
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(History.this, Settings.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentSP);
            }
        });
        //Toolbar-----------------------------------------------

        listeners();
        initAdapter(1);
    }

    private void listeners() {

        ListViewHistory.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                FuelFill object = (FuelFill) ListViewHistory.getItemAtPosition(i);
                AlertDialog.Builder builder = new AlertDialog.Builder(History.this);
                builder.setTitle("Co chcesz zrobić?");

                builder.setPositiveButton("Usuń", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteFuelFill(object);
                    }
                });

                builder.setNegativeButton("Edytuj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        updateFuelFill(object);
                    }
                });
                builder.show();
                return true;
            }
        });

        ListViewHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(History.this);
                if (number == 2) {
                    Fix object = (Fix) ListViewHistory.getItemAtPosition(i);
                    builder.setTitle("" + object.getWarnings());
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                } else if (number == 3) {
                    Maintenance object = (Maintenance) ListViewHistory.getItemAtPosition(i);
                    builder.setTitle("" + object.getDescription());
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                } else if (number == 5) {
                    Checkup object = (Checkup) ListViewHistory.getItemAtPosition(i);
                    builder.setTitle("" + object.getDescription());
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                }

            }
        });

        FuelFillBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initAdapter(1);
            }
        });
        FixBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initAdapter(2);
            }
        });
        MaintenanceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initAdapter(3);
            }
        });
        MileageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initAdapter(4);
            }
        });

        CheckUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initAdapter(5);
            }
        });
    }

    public void initAdapter(int type) {

        View columns = null;

        if (type == 1) {
            number = 1;
            dbManager.fillFuelFillArrayList();
            AdapterHistoryFuelFill adapter = new AdapterHistoryFuelFill(getApplicationContext(), FuelFill.listOfFuelFill);
            columns = getLayoutInflater().inflate(R.layout.fuell_fill_cell, null);
            ListViewHistory.setAdapter(adapter);

        } else if (type == 2) {
            number = 2;
            dbManager.fillFixArrayList();
            AdapterFix adapter = new AdapterFix(getApplicationContext(), Fix.listOfFix);
            columns = getLayoutInflater().inflate(R.layout.fix_cell, null);
            ListViewHistory.setAdapter(adapter);

        } else if (type == 3) {
            number = 3;
            dbManager.fillMaintenanceArrayList();
            AdapterMaintenance adapter = new AdapterMaintenance(getApplicationContext(), Maintenance.listOfMaintance);
            columns = getLayoutInflater().inflate(R.layout.maintenance_cell, null);
            ListViewHistory.setAdapter(adapter);
        } else if (type == 4) {
            number = 4;
            AdapterMileage adapter = new AdapterMileage(getApplicationContext(), Mileage.listOfMIleage);
            columns = getLayoutInflater().inflate(R.layout.mileage_cell, null);
            dbManager.fillMileageArrayList();
            ListViewHistory.setAdapter(adapter);
        } else if (type == 5) {
            number = 5;
            AdapterCheckUp adapter = new AdapterCheckUp(getApplicationContext(), Checkup.listOfCheckup);
            columns = getLayoutInflater().inflate(R.layout.check_up_cell, null);
            dbManager.fillCheckupArrayList();
            ListViewHistory.setAdapter(adapter);
        }

        layoutColumnNames.removeAllViews();
        layoutColumnNames.addView(columns);
    }

    public void deleteFuelFill(FuelFill object) {
        dbManager.deleteFuelFillInDb(object);
        dbManager.fillFuelFillArrayList();
        AdapterHistoryFuelFill adapter = new AdapterHistoryFuelFill(getApplicationContext(), FuelFill.listOfFuelFill);
        ListViewHistory.setAdapter(adapter);
    }

    public void updateFuelFill(FuelFill object) {
        dbManager.updateFuelFillInDb(object);
        dbManager.fillFuelFillArrayList();
        AdapterHistoryFuelFill adapter = new AdapterHistoryFuelFill(getApplicationContext(), FuelFill.listOfFuelFill);
        ListViewHistory.setAdapter(adapter);
    }
}