package com.example.carmanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
    int number = 1, carID;

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

        Mileage mileage1 = new Mileage(1, "2022-12-12", 199999.11);
        Mileage mileage2 = new Mileage(1, "2022-12-12", 199999.11);
        Mileage mileage3 = new Mileage(2, "2022-12-12", 199999.22);

        dbManager.addMileageToDb(mileage1);
        dbManager.addMileageToDb(mileage2);
        dbManager.addMileageToDb(mileage3);
        SharedPreferences sharedPref =  getSharedPreferences("activeCar", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("activeCarId", 1);
        editor.apply();
        SharedPreferences sh = getSharedPreferences("activeCar", MODE_PRIVATE);
        carID=sh.getInt("activeCarId",0);

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

                AlertDialog.Builder builder = new AlertDialog.Builder(History.this);
                builder.setTitle("Co chcesz zrobić?");

                builder.setPositiveButton("Usuń", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (number == 1) {
                            FuelFill object = (FuelFill) ListViewHistory.getItemAtPosition(i);
                            deleteFuelFill(object);
                        } else if (number == 2) {
                            Fix object = (Fix) ListViewHistory.getItemAtPosition(i);
                            deleteFix(object);
                        } else if (number == 3) {
                            Maintenance object = (Maintenance) ListViewHistory.getItemAtPosition(i);
                            deleteMaintenance(object);
                        } else if (number == 4) {
                            Mileage object = (Mileage) ListViewHistory.getItemAtPosition(i);
                            deleteMileage(object);
                        } else if (number == 5) {
                            Checkup object = (Checkup) ListViewHistory.getItemAtPosition(i);
                            deleteCheckUp(object);
                        }


                    }
                });

                builder.setNegativeButton("Edytuj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (number == 1) {
                            FuelFill object = (FuelFill) ListViewHistory.getItemAtPosition(i);
                        } else if (number == 2) {
                            Fix object = (Fix) ListViewHistory.getItemAtPosition(i);

                        } else if (number == 3) {
                            Maintenance object = (Maintenance) ListViewHistory.getItemAtPosition(i);

                        } else if (number == 4) {
                            Mileage object = (Mileage) ListViewHistory.getItemAtPosition(i);

                        } else if (number == 5) {
                            Checkup object = (Checkup) ListViewHistory.getItemAtPosition(i);

                        }
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
        dbManager.getCarHistory(carID);
        if (type == 1) {
            number = 1;
            // dbManager.fillFuelFillArrayList();
            AdapterHistoryFuelFill adapter1 = new AdapterHistoryFuelFill(getApplicationContext(), FuelFill.listOfFuelFill);
            columns = getLayoutInflater().inflate(R.layout.fuell_fill_cell, null);
            ListViewHistory.setAdapter(adapter1);

        } else if (type == 2) {
            number = 2;
            AdapterFix adapter2 = new AdapterFix(getApplicationContext(), Fix.listOfFix);
            columns = getLayoutInflater().inflate(R.layout.fix_cell, null);
            ListViewHistory.setAdapter(adapter2);

        } else if (type == 3) {
            number = 3;
            AdapterMaintenance adapter3 = new AdapterMaintenance(getApplicationContext(), Maintenance.listOfMaintance);
            columns = getLayoutInflater().inflate(R.layout.maintenance_cell, null);
            ListViewHistory.setAdapter(adapter3);
        } else if (type == 4) {
            number = 4;
            AdapterMileage adapter4 = new AdapterMileage(getApplicationContext(), Mileage.listOfMIleage);
            columns = getLayoutInflater().inflate(R.layout.mileage_cell, null);
            ListViewHistory.setAdapter(adapter4);
        } else if (type == 5) {
            number = 5;
            AdapterCheckUp adapter5 = new AdapterCheckUp(getApplicationContext(), Checkup.listOfCheckup);
            columns = getLayoutInflater().inflate(R.layout.check_up_cell, null);
            ListViewHistory.setAdapter(adapter5);
        }

        layoutColumnNames.removeAllViews();
        layoutColumnNames.addView(columns);
    }

    public void deleteFuelFill(FuelFill object) {
        dbManager.deleteFuelFillInDb(object);
        initAdapter(1);

    }

    public void deleteFix(Fix object) {
        dbManager.deleteFixInDb(object);
        initAdapter(2);
    }

    public void deleteMaintenance(Maintenance object) {
        dbManager.deleteMaintenance(object);
        initAdapter(3);
    }

    public void deleteMileage(Mileage object) {
        dbManager.deleteMileageInDb(object);
        initAdapter(4);
    }

    public void deleteCheckUp(Checkup object) {
        dbManager.deleteCheckupInDb(object);
        initAdapter(5);
    }


}