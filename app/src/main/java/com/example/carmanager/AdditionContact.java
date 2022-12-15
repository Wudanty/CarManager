package com.example.carmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.carmanager.models.Checkup;
import com.example.carmanager.models.Contact;
import com.example.carmanager.models.Mileage;

public class AdditionContact extends AppCompatActivity {

    DbManager dbManager;
    Contact contact;
    Button btnSave, btnExit;
    EditText etContactName, etContactNumber, etContactAddres, etContactEmail;
    Bundle extras;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition_contact);

        dbManager = new DbManager(this);

        btnSave = findViewById(R.id.btnSave);
        btnExit = findViewById(R.id.btnExit);
        etContactName = findViewById(R.id.etContactName);
        etContactNumber = findViewById(R.id.etContactNumber);
        etContactAddres = findViewById(R.id.etContactAddres);
        etContactEmail = findViewById(R.id.etContactEmail);

        extras = getIntent().getExtras();

        if (extras != null) {
            Contact contact = null;
            int id = extras.getInt("id");
            for (int i = 0; i < Contact.listOfContact.size(); i++) {
                if (Contact.listOfContact.get(i).getContactId() == id) {
                    contact = Contact.listOfContact.get(i);
                }
            }

            etContactName.setText(contact.getContactName());
            etContactAddres.setText(contact.getAddress());
            etContactEmail.setText(contact.getEmail());
            etContactNumber.setText(contact.getPhoneNumber());
        }

    }

    public void Exit(View view) {

        finish();
    }

    public void Save(View view) {
        String contactName, contactAddres, contactEmail, contactNumber;


        contactName = etContactName.getText().toString();
        contactAddres = etContactAddres.getText().toString();
        contactEmail = etContactEmail.getText().toString();
        contactNumber = etContactNumber.getText().toString();

        contact = new Contact(extras.getInt("id"), contactName, contactNumber, contactEmail, contactAddres);
        if (extras != null) {
            dbManager.updateContactInDb(contact);
            Intent intent = new Intent(AdditionContact.this,MoreActivities.class);
            startActivity(intent);
        }
        finish();
    }
}