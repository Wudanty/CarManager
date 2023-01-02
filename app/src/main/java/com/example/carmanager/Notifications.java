package com.example.carmanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.carmanager.models.Car;
import com.example.carmanager.models.Mileage;
import com.example.carmanager.models.Notification;
import com.example.carmanager.models.NotificationReceiver;

import java.time.LocalDate;
import java.util.Calendar;

public class Notifications extends AppCompatActivity {
    DbManager dbManager = DbManager.instanceOfDatabase(this);
    Button button_add,button_add_a,button_list;
    Button btnCar, btnMoreActivities, btnHistory, btnSettings, btnMainActivity;
    LinearLayout layout_add,layout_list,layout_empty,layout_Data,layout_KM;
    CheckBox checkBoxKm, checkBoxDate;
    EditText editTextKm,editTextDate,editTextDes;
    Spinner spino,selectCarSpinner;
    ListView notification_list;
    Car car=null;
    String[] option = {"Paliwo","Mandat","Naprawa"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        createNotificationChannel();
        notification_list = findViewById(R.id.list_not);
        btnCar = findViewById(R.id.car);
        btnMoreActivities = findViewById(R.id.more);
        btnMainActivity = findViewById(R.id.mainActivity);
        btnHistory = findViewById(R.id.history);
        btnSettings = findViewById(R.id.settings);
        checkBoxKm = findViewById(R.id.checkBox);
        checkBoxDate = findViewById(R.id.checkBox2);
        editTextKm = findViewById(R.id.editKm);
        editTextDate = findViewById(R.id.editDate);
        editTextDes = findViewById(R.id.editTextTextMultiLine);
        spino = findViewById(R.id.spinner);
        selectCarSpinner = findViewById(R.id.spinner2);
        layout_add = findViewById(R.id.layout_add);
        layout_list = findViewById(R.id.layout_list);
        layout_empty = findViewById(R.id.empty_lay);
        layout_Data = findViewById(R.id.layout_Data);
        layout_KM = findViewById(R.id.layout_KM);
        button_add_a = findViewById(R.id.button_data);
        button_add = findViewById(R.id.button_add);
        button_list = findViewById(R.id.button_details);
        button_add_a.setBackgroundColor(button_add_a.getContext().getResources().getColor(R.color.purple_700));
        button_list.setBackgroundColor(button_list.getContext().getResources().getColor(R.color.purple_500));
        ArrayAdapter ad = new ArrayAdapter(this,android.R.layout.simple_spinner_item, option);
        spino.setAdapter(ad);
        dbManager.fillCarArrayList();
        Noti_car_adapter noti_car_adapter = new Noti_car_adapter(getApplicationContext(),Car.listOfCars);
        selectCarSpinner.setAdapter(noti_car_adapter);
        dbManager.fillNotificationArrayList();
        Notifications_adapter notifications_adapter = new Notifications_adapter(getApplicationContext(), Notification.listOfNotification);
        button_add_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_add_a.setBackgroundColor(button_add_a.getContext().getResources().getColor(R.color.purple_700));
                button_list.setBackgroundColor(button_list.getContext().getResources().getColor(R.color.purple_500));
                layout_add.setVisibility(View.VISIBLE);
                layout_list.setVisibility(View.GONE);
            }
        });
        button_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_add_a.setBackgroundColor(button_add_a.getContext().getResources().getColor(R.color.purple_500));
                button_list.setBackgroundColor(button_list.getContext().getResources().getColor(R.color.purple_700));
                layout_list.setVisibility(View.VISIBLE);
                layout_add.setVisibility(View.GONE);
                dbManager.fillNotificationArrayList();
                notification_list.deferNotifyDataSetChanged();
                notification_list.setAdapter(notifications_adapter);
                if(Notification.listOfNotification==null) {
                    layout_list.setVisibility(View.GONE);
                    layout_empty.setVisibility(View.VISIBLE);
                }
            }
        });
        notification_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Notifications.this);
                builder.setTitle("Co chcesz zrobić?");
                builder.setPositiveButton("Usuń", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Notification object = (Notification) notification_list.getItemAtPosition(i);
                        dbManager.deleteNotificationInDb(object);
                        notifications_adapter.remove(object);
                        notification_list.deferNotifyDataSetChanged();
                    }
                });
                builder.show();
            }
        });
        selectCarSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                car = (Car) selectCarSpinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                car = (Car) selectCarSpinner.getSelectedItem();
            }
        });
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                int typ = 0;
                Double kilometry= 0.0;
                String data=null;
                if (checkBoxDate.isChecked()){
                    typ=1;
                    data =editTextDate.getText().toString();
                }
                else if (checkBoxKm.isChecked()){
                    dbManager.getCarHistory(car.getCarId());
                    typ=0;
                    kilometry = Mileage.listOfMIleage.get(Mileage.listOfMIleage.size() - 1).getMileageValue()+Double.parseDouble(editTextKm.getText().toString());
                }

              Notification notification = new Notification(car.getCarId(),data,editTextDes.getText().toString(),null,typ,kilometry.intValue(),spino.getSelectedItem().toString());
              dbManager.addNotificationToDb(notification);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Dodaj samochód aby dodac przypomienie", Toast.LENGTH_LONG).show();
                }
                createAlarm();

            }
        });
        btnCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(Notifications.this, CarActivity.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity( intentSP );
            }
        });
        btnMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(Notifications.this, MainActivity.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity( intentSP );
            }
        });
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSP = new Intent(Notifications.this, History.class);
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
                Intent intentSP = new Intent(Notifications.this, Raports.class);
                intentSP.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentSP);
            }
        });
    }

    private void createAlarm() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY,17 );
        calendar.set(Calendar.MINUTE, 18);
        long triger = System.currentTimeMillis()+5000;
        Intent intent = new Intent(this, NotificationReceiver.class);
        AlarmManager alarmMgr = (AlarmManager) this.getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_MUTABLE);
        alarmMgr.set(AlarmManager.RTC_WAKEUP, triger,pendingIntent);

    }
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "NotificationChannel";
            String description = "Channel for notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("CHANNEL_ID", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void itemClicked(View v) {
        CheckBox checkBoxDate = (CheckBox)v;
        if(checkBoxDate.isChecked()){
            layout_Data.setVisibility(View.VISIBLE);
            checkBoxKm.setChecked(false);
            layout_KM.setVisibility(View.GONE);
        }
        else {
            layout_Data.setVisibility(View.GONE);
            checkBoxKm.setChecked(true);
            layout_KM.setVisibility(View.VISIBLE);
        }

    }
    public void itemClicked2(View v) {
        CheckBox checkBoxKm = (CheckBox)v;
        if(checkBoxKm.isChecked()){
            layout_KM.setVisibility(View.VISIBLE);
            checkBoxDate.setChecked(false);
            layout_Data.setVisibility(View.GONE);
        }
        else {
            layout_KM.setVisibility(View.GONE);
            checkBoxDate.setChecked(true);
            layout_Data.setVisibility(View.VISIBLE);
        }

    }
    public void datePicker(View v){
        int mYear, mMonth, mDay;
        final Calendar c = Calendar.getInstance();

        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        /*if(extras != null)
        {
            ldt = LocalDate.parse();
            mYear = ldt.getYear();
            mMonth = ldt.getMonthValue()-1;
            mDay = ldt.getDayOfMonth();
        }*/
        DatePickerDialog datePickerDialog = new DatePickerDialog(Notifications.this,
                (view, year, monthOfYear, dayOfMonth) -> {
            String strDay,strMonth;
            LocalDate ldt;
                    monthOfYear = monthOfYear +1;
                    strDay = dayOfMonth+"";
                    if(strDay.length() == 1) strDay = "0"+strDay;

                    strMonth = monthOfYear+"";
                    if(strMonth.length() == 1) strMonth = "0"+strMonth;

                    String data = year + "-"+strMonth+"-"+strDay;

                    ldt = LocalDate.parse(data);
                    editTextDate.setText(data);


                }, mYear, mMonth, mDay);

        datePickerDialog.getDatePicker().setFirstDayOfWeek(Calendar.MONDAY);
        datePickerDialog.show();
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
                Intent intent = new Intent(Notifications.this, MoreActivities.class);
                startActivity(intent);
                builder.cancel();
            }
        });
        btnReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Notifications.this, Notifications.class);
                startActivity(intent);
                builder.cancel();
            }
        });
    }

}