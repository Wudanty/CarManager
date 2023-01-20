package com.example.carmanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.carmanager.models.Checkup;
import com.example.carmanager.models.Contact;
import com.example.carmanager.models.Fix;
import com.example.carmanager.models.FuelFill;
import com.example.carmanager.models.Maintenance;
import com.example.carmanager.models.Mileage;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class MoreActivities extends AppCompatActivity {

    //Toolbar-----------------------------------------------
    public androidx.appcompat.widget.Toolbar toolbar;
    Button btnCar, btnMoreActivities, btnHistory, btnSettings, btnMainActivity;
    //Toolbar-----------------------------------------------

    AdapterContact adapter;
    ListView contactListView;
    private FloatingActionButton addContact;
    DbManager dbManager=DbManager.instanceOfDatabase(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_activities);

        //Toolbar-----------------------------------------------
        btnCar = findViewById(R.id.car);
        btnMoreActivities = findViewById(R.id.more);
        btnSettings = findViewById(R.id.settings);
        btnMainActivity = findViewById(R.id.mainActivity);
        btnHistory = findViewById(R.id.history);

        toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(null);

        addContact = findViewById(R.id.addContactButton);
        contactListView=findViewById(R.id.contactListView);

        btnCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(MoreActivities.this, CarActivity.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity( intentSP );
            }
        });
        btnMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(MoreActivities.this, MainActivity.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity( intentSP );
            }
        });
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(MoreActivities.this, History.class);
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
                Intent intentSP = new Intent(MoreActivities.this, Raports.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity( intentSP );
            }
        });
        //Toolbar-----------------------------------------------

        initAdapter();
        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(MoreActivities.this, AdditionContact.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentSP);
                finish();
            }
        });
        contactListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MoreActivities.this);
                builder.setTitle("Co chcesz zrobić?");

                builder.setPositiveButton("Usuń", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Contact object = (Contact) contactListView.getItemAtPosition(i);
                        dbManager.deleteContactInDb(object);
                        dbManager.fillContactArrayList();
                        Collections.reverse(Contact.listOfContact);
                        adapter.notifyDataSetChanged();

                    }
                });

                builder.setNegativeButton("Edytuj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            Contact object = (Contact) contactListView.getItemAtPosition(i);
                            Intent intent = new Intent(view.getContext(), AdditionContact.class);
                            intent.putExtra("id",object.getContactId());
                            startActivity(intent);
                            finish();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    public void initAdapter(){
        dbManager.fillContactArrayList();
        Collections.reverse(Contact.listOfContact);
        adapter = new AdapterContact(getApplicationContext(), Contact.listOfContact);
        contactListView.setAdapter(adapter);
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
        lp.width = 480;
        lp.x=25;
        lp.y=100;

        lp.gravity = Gravity.TOP | Gravity.END;
        lp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        builder.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        builder.getWindow().setAttributes(lp);

        btnContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MoreActivities.this, MoreActivities.class);
                startActivity(intent);
                builder.cancel();
            }
        });
        btnReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MoreActivities.this, Notifications.class);
                startActivity(intent);
                builder.cancel();
            }
        });
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MoreActivities.this, SettingsActivity.class);
                startActivity(intent);
                builder.cancel();
            }
        });
    }
}