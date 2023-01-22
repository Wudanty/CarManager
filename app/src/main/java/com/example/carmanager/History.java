package com.example.carmanager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.carmanager.models.Checkup;
import com.example.carmanager.models.Fix;
import com.example.carmanager.models.FuelFill;
import com.example.carmanager.models.Maintenance;
import com.example.carmanager.models.Mileage;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Collections;
import java.util.Objects;

public class History extends AppCompatActivity {

    //Toolbar-----------------------------------------------
    public androidx.appcompat.widget.Toolbar toolbar;
    Button btnCar, btnMoreActivities, btnHistory, btnSettings, btnMainActivity;
    //Toolbar-----------------------------------------------

    Button FuelFillBtn, MaintenanceBtn, CheckUpBtn, FixBtn, MileageBtn;
    ListView ListViewHistory;
    LinearLayout layoutColumnNames;
    FloatingActionButton floatingButton;
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
        floatingButton = findViewById(R.id.fab);

        FuelFillBtn = findViewById(R.id.buttonFuel);
        MaintenanceBtn = findViewById(R.id.buttonMaintenance);
        CheckUpBtn = findViewById(R.id.buttonCheckUp);
        FixBtn = findViewById(R.id.buttonFix);
        MileageBtn = findViewById(R.id.buttonMileage);

        ListViewHistory = findViewById(R.id.ListViewHistory);
        layoutColumnNames = findViewById(R.id.layoutColumnNames);





        SharedPreferences sh = getSharedPreferences("activeCar", MODE_PRIVATE);
        carID=sh.getInt("activeCarId",0);
        Log.d("dd", String.valueOf(carID));

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
                more(null);
            }
        });
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(History.this, Raports.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentSP);
            }
        });
        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                additions(null);
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
                            Intent intent = new Intent(view.getContext(), AddictionFuel.class);
                            intent.putExtra("id",i);
                            startActivity(intent);

                        } else if (number == 2) {
                            Fix object = (Fix) ListViewHistory.getItemAtPosition(i);
                            Intent intent = new Intent(view.getContext(), AdditionRepairs.class);
                            intent.putExtra("id",i);
                            startActivity(intent);

                        } else if (number == 3) {
                            Maintenance object = (Maintenance) ListViewHistory.getItemAtPosition(i);
                            Intent intent = new Intent(view.getContext(), AdditionOperatingElements.class);
                            intent.putExtra("id",i);
                            startActivity(intent);

                        } else if (number == 4) {
                            Mileage object = (Mileage) ListViewHistory.getItemAtPosition(i);
                            Intent intent = new Intent(view.getContext(), AdditionMileage.class);
                            intent.putExtra("id",i);
                            startActivity(intent);

                        } else if (number == 5) {
                            Checkup object = (Checkup) ListViewHistory.getItemAtPosition(i);
                            Intent intent = new Intent(view.getContext(), AdditionCheckup.class);
                            intent.putExtra("id",i);
                            startActivity(intent);

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
            Collections.reverse(FuelFill.listOfFuelFill);
            AdapterHistoryFuelFill adapter1 = new AdapterHistoryFuelFill(getApplicationContext(), FuelFill.listOfFuelFill);
            columns = getLayoutInflater().inflate(R.layout.fuell_fill_cell, null);
            ListViewHistory.setAdapter(adapter1);

        } else if (type == 2) {
            number = 2;
            Collections.reverse(Fix.listOfFix);
            AdapterFix adapter2 = new AdapterFix(getApplicationContext(), Fix.listOfFix);
            columns = getLayoutInflater().inflate(R.layout.fix_cell, null);
            ListViewHistory.setAdapter(adapter2);

        } else if (type == 3) {
            number = 3;
            Collections.reverse(Maintenance.listOfMaintance);
            AdapterMaintenance adapter3 = new AdapterMaintenance(getApplicationContext(), Maintenance.listOfMaintance);
            columns = getLayoutInflater().inflate(R.layout.maintenance_cell, null);
            ListViewHistory.setAdapter(adapter3);
        } else if (type == 4) {
            number = 4;
            Collections.reverse(Mileage.listOfMIleage);
            AdapterMileage adapter4 = new AdapterMileage(getApplicationContext(), Mileage.listOfMIleage);
            columns = getLayoutInflater().inflate(R.layout.mileage_cell, null);
            ListViewHistory.setAdapter(adapter4);
        } else if (type == 5) {
            number = 5;
            Collections.reverse(Checkup.listOfCheckup);
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

    public void more(View view){
        final LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.more, null);
        Button btnContacts=linearLayout.findViewById(R.id.btnContacts);
        Button btnReminder=linearLayout.findViewById(R.id.btnReminder);
        Button btnSettings=linearLayout.findViewById(R.id.btnSettings);
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
                Intent intent = new Intent(History.this, MoreActivities.class);
                startActivity(intent);
                builder.cancel();
            }
        });
        btnReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(History.this, Notifications.class);
                startActivity(intent);
                builder.cancel();
            }
        });
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(History.this, SettingsActivity.class);
                startActivity(intent);
                builder.cancel();
            }
        });
    }
    public void additions(View view) {
        final LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.addition_menu, null);
        Button btnFuel = (Button) linearLayout.findViewById(R.id.btnFuel);
        Button btnMileage = (Button) linearLayout.findViewById(R.id.btnMileage);
        Button btnRepairs = (Button) linearLayout.findViewById(R.id.btnRepairs);
        Button btnCarInspection = (Button) linearLayout.findViewById(R.id.btnCarInspection);
        Button btnOperatingElements = (Button) linearLayout.findViewById(R.id.btnOperatingElements);
        Button btnInsurance = (Button) linearLayout.findViewById(R.id.btnCarInsurance);

        final AlertDialog builder = new AlertDialog.Builder(this)
                .setView(linearLayout)
                .setCancelable(true)
                .create();
        builder.show();
        //builder.getWindow().setLayout(600, 530);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(builder.getWindow().getAttributes());
        lp.width = 600;
        lp.x=25;
        lp.y=25;

        lp.gravity = Gravity.BOTTOM | Gravity.END;
        lp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        builder.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        builder.getWindow().setAttributes(lp);
        btnInsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(History.this, AdditionsInsurance.class);
                startActivity(intent);
                builder.cancel();
            }
        });
        btnFuel.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(History.this, AddictionFuel.class);
                startActivity(intent);
                builder.cancel();
            }
        });
        btnMileage.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(History.this, AdditionMileage.class);
                startActivity(intent);
                builder.cancel();
            }
        });
        btnRepairs.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(History.this, AdditionRepairs.class);
                startActivity(intent);

                builder.cancel();
            }
        });
        btnCarInspection.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(History.this, AdditionCheckup.class);
                startActivity(intent);

                builder.cancel();
            }
        });
        btnOperatingElements.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(History.this, AdditionOperatingElements.class);
                startActivity(intent);

                builder.cancel();
            }
        });
    }
}