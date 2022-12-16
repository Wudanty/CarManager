package com.example.carmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
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
    Contact contact, object;
    Button btnSave, btnExit;
    EditText etContactName, etContactNumber, etContactAddres, etContactEmail;
    Bundle extras;
    int idToEdit;
    SharedPreferences sh;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition_contact);

        dbManager = new DbManager(this);
        sh = getSharedPreferences("activeCar", MODE_PRIVATE);
        btnSave = findViewById(R.id.btnSave);
        btnExit = findViewById(R.id.btnExit);
        etContactName = findViewById(R.id.etContactName);
        etContactNumber = findViewById(R.id.etContactNumber);
        etContactAddres = findViewById(R.id.etContactAddres);
        etContactEmail = findViewById(R.id.etContactEmail);

        extras = getIntent().getExtras();
        if(extras != null){
            idToEdit = extras.getInt("id");
            dbManager.fillContactArrayList();
            object = Contact.listOfContact.get(idToEdit);

            etContactName.setText(object.getContactName());
            etContactNumber.setText(object.getPhoneNumber());
            etContactAddres.setText(object.getAddress());
            etContactEmail.setText(object.getEmail());

        }
    }

    public void Exit(View view) {

        finish();
    }

    public void Save(View view) {
        String contactName, contactAddres, contactEmail,contactNumber;

        contactName = etContactName.getText().toString();
        contactAddres = etContactAddres.getText().toString();
        contactEmail = etContactEmail.getText().toString();
        contactNumber = etContactNumber.getText().toString();

        if(extras!=null){
            contact = new Contact(object.getContactId(),contactName,contactNumber,contactEmail,contactAddres);
            dbManager.updateContactInDb(contact);
        }
        else
        {
            contact = new Contact(contactName,contactNumber,contactEmail,contactAddres);
            dbManager.addContactToDb(contact);
        }
        finish();
    }
}