package com.example.carmanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.carmanager.models.Checkup;
import com.example.carmanager.models.Contact;
import com.example.carmanager.models.Fix;
import com.example.carmanager.models.FuelFill;
import com.example.carmanager.models.Maintenance;
import com.example.carmanager.models.Mileage;

import java.util.Objects;

public class MoreActivities extends AppCompatActivity {

    //Toolbar-----------------------------------------------
    public androidx.appcompat.widget.Toolbar toolbar;
    Button btnCar, btnMoreActivities, btnHistory, btnSettings, btnMainActivity;
    //Toolbar-----------------------------------------------

    AdapterContact adapter;
    ListView contactListView;
    DbManager dbManager=DbManager.instanceOfDatabase(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_activities);

        Contact contact = new Contact("test22","123488888","test123@ukw.edu.pl","Kopernika 21");
        dbManager.addContactToDb(contact);

        //Toolbar-----------------------------------------------
        btnCar = findViewById(R.id.car);
        btnMoreActivities = findViewById(R.id.more);
        btnSettings = findViewById(R.id.settings);
        btnMainActivity = findViewById(R.id.mainActivity);
        btnHistory = findViewById(R.id.history);

        toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(null);

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
                Intent intentSP = new Intent(MoreActivities.this, MoreActivities.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity( intentSP );
            }
        });
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(MoreActivities.this, Settings.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity( intentSP );
            }
        });
        //Toolbar-----------------------------------------------

        initAdapter();

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
        adapter = new AdapterContact(getApplicationContext(), Contact.listOfContact);
        contactListView.setAdapter(adapter);
    }
}