package com.example.carmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.carmanager.models.Action;
import com.example.carmanager.models.Car;
import com.example.carmanager.models.CarTodo;
import com.example.carmanager.models.Checkup;
import com.example.carmanager.models.Contact;
import com.example.carmanager.models.Fix;
import com.example.carmanager.models.FuelFill;
import com.example.carmanager.models.Insurance;
import com.example.carmanager.models.Maintenance;
import com.example.carmanager.models.Mileage;
import com.example.carmanager.models.Notification;

public class DbManager extends SQLiteOpenHelper {
    private static DbManager dbManager;
    private static String DB_NAME = "CarData";
    private static String TABLE_AUTO = "AUTO";
    private static String TABLE_EKSPLOATACYJNE = "EKSPLOATACYJNE";
    private static String TABLE_KONTAKT = "KONTAKT";
    private static String TABLE_NAPRAWA = "NAPRAWA";
    private static String TABLE_POWIADOMIENIA = "POWIADOMIENIA";
    private static String TABLE_PRZEBIEG = "PRZEBIEG";
    private static String TABLE_PRZEGLAD = "PRZEGLAD";
    private static String TABLE_SLOWNIK_AKCJI = "SLOWNIK_AKCJI";
    private static String TABLE_TANKOWANIE = "TANKOWANIE";
    private static String TABLE_UBEZPIECZENIE = "UBEZPIECZENIE";
    private static String TABLE_DO_ZROBIENIA = "DO_ZROBIENIA";
    private static String ID = "id";

    //AUTO
    private static String AUTO_MARKA = "marka";
    private static String AUTO_MODEL = "model";
    private static String AUTO_ROCZNIK = "rocznik";
    private static String AUTO_POJ_SILNIKA = "poj_silnika";
    private static String AUTO_VIN = "vin";
    private static String AUTO_OPIS = "opis";
    private static String AUTO_PALIWO = "pliwo";
    private static String AUTO_ZDJECIE = "zdjecie";
    private static String AUTO_NUMER_DOWODU_REJESTRACYJNEGO = "numer_dowodu_rejestracyjnego";
    private static String AUTO_NAZWA_WLASNA = "nazwa_wlasna";

    //DO_ZROBIENIA
    private static String DO_ZROBIENIA_ID_AUTA = "id_Auta";
    private static String DO_ZROBIENIA_NAZWA = "nazwa";
    private static String DO_ZROBIENIA_OPIS = "opis";

    //ESKPLOATACYJNE
    private static String EKSPLOATACYJNE_ID_AUTA = "id_Auta";
    private static String EKSPLOATACYJNE_DATA = "data";
    private static String EKSPLOATACYJNE_CO = "co";
    private static String EKSPLOATACYJNE_OPIS = "opis";
    private static String EKSPLOATACYJNE_NAST_DATA = "nast_Data";
    private static String EKSPLOATACYJNE_NAST_PRZEBIEG = "nast_Przebieg";
    private static String EKSPLOATACYJNE_KWOTA = "kwota";
    private static String EKSPLOATACYJNE_GDZIE = "gdzie";

    //KONTAKT
    private static String KONTAKT_NAZWA = "nazwa";
    private static String KONTAKT_NUMER = "numer";
    private static String KONTAKT_ADRES = "adres";
    private static String KONTAKT_EMAIL = "email";

    //NAPRAWA
    private static String NAPRAWA_ID_AUTA = "id_Auta";
    private static String NAPRAWA_DATA = "data";
    private static String NAPRAWA_CO = "co";
    private static String NAPRAWA_UWAGI = "uwagi";
    private static String NAPRAWA_CENA = "cena";
    private static String NAPRAWA_GDZIE = "gdzie";

    //POWIADOMIENIA
    private static String POWIADOMIENIA_ID_AUTA = "id_Auto";
    private static String POWIADOMIENIA_KIEDY = "kiedy";
    private static String POWIADOMIENIA_OPIS = "opis";
    private static String POWIADOMIENIA_STOPIEN_WAZNOSCI = "stopien_waznosci";
    private static String POWIADOMIENIA_TYP_POWIADOMIENIA = "typ";

    //PRZEBIEG
    private static String PRZEBIEG_ID_AUTA = "id_Auta";
    private static String PRZEBIEG_ID_AKCJI = "id_Akcji";
    private static String PRZEBIEG_DATA = "data";
    private static String PRZEBIEG_ILE = "ile";

    //PRZEGLAD
    private static String PRZEGLAD_ID_AUTA = "id_Auta";
    private static String PRZEGLAD_ID_PRZEBIEG = "id_Przebieg";
    private static String PRZEGLAD_KIEDY = "kiedy";
    private static String PRZEGLAD_DO_KIEDY = "do_Kiedy";
    private static String PRZEGLAD_GDZIE = "gdzie";
    private static String PRZEGLAD_CENA = "cena";
    private static String PRZEGLAD_POZYTYWNY = "pozytywny";
    private static String PRZEGLAD_OPIS = "opis";

    //SLOWNIK_AKCJI
    private static String SLOWNIKA_AKCJI_NAZWA_AKCJI = "nazwa";

    //TANKOWANIE
    private static String TANKOWANIE_ID_AUTA = "id_Auta";
    private static String TANKOWANIE_DATA = "data";
    private static String TANKOWANIE_CENAZALITR = "cena_za_litr";
    private static String TANKOWANIE_KWOTA = "kwota";
    private static String TANKOWANIE_NAZWA_STACJI = "nazwa_Stacji";
    private static String TANKOWANIE_ILOSC = "ilosc";
    private static String TANKOWANIE_JAKIE_PALIWO = "jakie_Paliwo";

    //UBEZPIECZENIE
    private static String UBEZPIECZENIE_ID_AUTA = "id_Auta";
    private static String UBEZPIECZENIE_KIEDY = "kiedy";
    private static String UBEZPIECZENIE_DO_KIEDY = "do_Kiedy";
    private static String UBEZPIECZENIE_UBEZPIECZYCIEL = "ubezpieczyciel";
    private static String UBEZPIECZENIE_CENA = "cena";
    private static String UBEZPIECZENIE_RODZAJ = "rodzaj";
    private static String UBEZPIECZENIE_NR_POLISY = "nr_Polisy";


    public DbManager(Context context) {
        super(context, DB_NAME, null, 1);
    }

    public static DbManager instanceOfDatabase(Context context) {
        if (dbManager == null)
            dbManager = new DbManager(context);

        return dbManager;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuilder auto, doZrobienia, eksploatacyjne, kontakt, naprawa, powiadomienia, przebieg, przeglad, slownikAkcji, tankowanie, ubezpieczenie;

        auto = new StringBuilder()
                .append("CREATE TABLE ")
                .append(TABLE_AUTO)
                .append("(")
                .append(ID)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(AUTO_MARKA)
                .append(" TEXT, ")
                .append(AUTO_MODEL)
                .append(" TEXT, ")
                .append(AUTO_ROCZNIK)
                .append(" INT, ")
                .append(AUTO_POJ_SILNIKA)
                .append(" REAL, ")
                .append(AUTO_VIN)
                .append(" TEXT, ")
                .append(AUTO_OPIS)
                .append(" TEXT, ")
                .append(AUTO_PALIWO)
                .append(" TEXT, ")
                .append(AUTO_ZDJECIE)
                .append(" BLOB, ")
                .append(AUTO_NUMER_DOWODU_REJESTRACYJNEGO)
                .append(" TEXT, ")
                .append(AUTO_NAZWA_WLASNA)
                .append(" TEXT)");

        doZrobienia = new StringBuilder()
                .append("CREATE TABLE ")
                .append(TABLE_DO_ZROBIENIA)
                .append("(")
                .append(ID)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(DO_ZROBIENIA_ID_AUTA)
                .append(" INT, ")
                .append(DO_ZROBIENIA_OPIS)
                .append(" TEXT, ")
                .append(DO_ZROBIENIA_NAZWA)
                .append(" TEXT)");

        eksploatacyjne = new StringBuilder()
                .append("CREATE TABLE ")
                .append(TABLE_EKSPLOATACYJNE)
                .append("(")
                .append(ID)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(EKSPLOATACYJNE_OPIS)
                .append(" TEXT, ")
                .append(EKSPLOATACYJNE_CO)
                .append(" TEXT, ")
                .append(EKSPLOATACYJNE_DATA)
                .append(" TEXT, ")
                .append(EKSPLOATACYJNE_GDZIE)
                .append(" TEXT, ")
                .append(EKSPLOATACYJNE_KWOTA)
                .append(" REAL, ")
                .append(EKSPLOATACYJNE_NAST_DATA)
                .append(" TEXT, ")
                .append(EKSPLOATACYJNE_NAST_PRZEBIEG)
                .append(" REAL, ")
                .append(EKSPLOATACYJNE_ID_AUTA).append(" INT)");

        kontakt = new StringBuilder().append("CREATE TABLE ")
                .append(TABLE_KONTAKT)
                .append("(")
                .append(ID)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(KONTAKT_NAZWA)
                .append(" TEXT, ")
                .append(KONTAKT_NUMER)
                .append(" TEXT, ")
                .append(KONTAKT_ADRES)
                .append(" TEXT, ")
                .append(KONTAKT_EMAIL)
                .append(" TEXT)");

        naprawa = new StringBuilder().append("CREATE TABLE ")
                .append(TABLE_NAPRAWA)
                .append("(")
                .append(ID)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(NAPRAWA_ID_AUTA)
                .append(" INT, ")
                .append(NAPRAWA_DATA)
                .append(" TEXT, ")
                .append(NAPRAWA_CO)
                .append(" TEXT, ")
                .append(NAPRAWA_UWAGI)
                .append(" TEXT, ")
                .append(NAPRAWA_CENA)
                .append(" REAL, ")
                .append(NAPRAWA_GDZIE)
                .append(" TEXT)");

        powiadomienia = new StringBuilder().append("CREATE TABLE ")
                .append(TABLE_POWIADOMIENIA)
                .append("(")
                .append(ID)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(NAPRAWA_ID_AUTA)
                .append(" INT, ")
                .append(POWIADOMIENIA_KIEDY)
                .append(" TEXT, ")
                .append(POWIADOMIENIA_OPIS)
                .append(" TEXT, ")
                .append(POWIADOMIENIA_STOPIEN_WAZNOSCI)
                .append(" INT, ")
                .append(POWIADOMIENIA_TYP_POWIADOMIENIA)
                .append(" INT)");

        przebieg = new StringBuilder().append("CREATE TABLE ")
                .append(TABLE_PRZEBIEG)
                .append("(")
                .append(ID)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(PRZEBIEG_ID_AUTA)
                .append(" INT, ")
                .append(PRZEBIEG_ID_AKCJI)
                .append(" INT, ")
                .append(PRZEBIEG_DATA)
                .append(" TEXT, ")
                .append(PRZEBIEG_ILE)
                .append(" Double)");

        przeglad = new StringBuilder().append("CREATE TABLE ")
                .append(TABLE_PRZEGLAD)
                .append("(")
                .append(ID)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(PRZEGLAD_ID_AUTA)
                .append(" INT, ")
                .append(PRZEGLAD_ID_PRZEBIEG)
                .append(" INT, ")
                .append(PRZEGLAD_KIEDY)
                .append(" TEXT, ")
                .append(PRZEGLAD_DO_KIEDY)
                .append(" TEXT, ")
                .append(PRZEGLAD_GDZIE)
                .append(" TEXT, ")
                .append(PRZEGLAD_CENA)
                .append(" REAL, ")
                .append(PRZEGLAD_POZYTYWNY)
                .append(" INT, ")
                .append(PRZEGLAD_OPIS)
                .append(" TEXT)");

        slownikAkcji = new StringBuilder().append("CREATE TABLE ")
                .append(TABLE_SLOWNIK_AKCJI)
                .append("(")
                .append(ID)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(SLOWNIKA_AKCJI_NAZWA_AKCJI)
                .append(" TEXT)");

        tankowanie = new StringBuilder().append("CREATE TABLE ")
                .append(TABLE_TANKOWANIE)
                .append("(")
                .append(ID)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(TANKOWANIE_ID_AUTA)
                .append(" INT, ")
                .append(TANKOWANIE_DATA)
                .append(" TEXT, ")
                .append(TANKOWANIE_CENAZALITR)
                .append(" REAL, ")
                .append(TANKOWANIE_KWOTA)
                .append(" REAL, ")
                .append(TANKOWANIE_NAZWA_STACJI)
                .append(" TEXT, ")
                .append(TANKOWANIE_ILOSC)
                .append(" REAL, ")
                .append(TANKOWANIE_JAKIE_PALIWO)
                .append(" TEXT)");

        ubezpieczenie = new StringBuilder().append("CREATE TABLE ")
                .append(TABLE_UBEZPIECZENIE)
                .append("(")
                .append(ID)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(UBEZPIECZENIE_ID_AUTA)
                .append(" INT, ")
                .append(UBEZPIECZENIE_KIEDY)
                .append(" TEXT, ")
                .append(UBEZPIECZENIE_DO_KIEDY)
                .append(" TEXT, ")
                .append(UBEZPIECZENIE_UBEZPIECZYCIEL)
                .append(" TEXT, ")
                .append(UBEZPIECZENIE_CENA)
                .append(" REAL, ")
                .append(UBEZPIECZENIE_RODZAJ)
                .append(" TEXT, ")
                .append(UBEZPIECZENIE_NR_POLISY)
                .append(" TEXT)");

        sqLiteDatabase.execSQL(auto.toString());
        sqLiteDatabase.execSQL(doZrobienia.toString());
        sqLiteDatabase.execSQL(eksploatacyjne.toString());
        sqLiteDatabase.execSQL(kontakt.toString());
        sqLiteDatabase.execSQL(naprawa.toString());
        sqLiteDatabase.execSQL(powiadomienia.toString());
        sqLiteDatabase.execSQL(przebieg.toString());
        sqLiteDatabase.execSQL(przeglad.toString());
        sqLiteDatabase.execSQL(slownikAkcji.toString());
        sqLiteDatabase.execSQL(tankowanie.toString());
        sqLiteDatabase.execSQL(ubezpieczenie.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //CAR
    public void addCarToDb(Car car) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(AUTO_MARKA, car.getBrand());
        contentValues.put(AUTO_MODEL, car.getModel());
        contentValues.put(AUTO_ROCZNIK, car.getProductionDate());
        contentValues.put(AUTO_POJ_SILNIKA, car.getTankVolume());
        contentValues.put(AUTO_VIN, car.getVin());
        contentValues.put(AUTO_OPIS, car.getDescription());
        contentValues.put(AUTO_PALIWO, car.getFuelType());
        contentValues.put(AUTO_ZDJECIE, car.getPicture());
        contentValues.put(AUTO_NUMER_DOWODU_REJESTRACYJNEGO, car.getRegistry());
        contentValues.put(AUTO_NAZWA_WLASNA, car.getCarNickname());

        sqLiteDatabase.insert(TABLE_AUTO, null, contentValues);
    }

    public void fillCarArrayList() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Car.listOfCars.clear();

        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_AUTO, null)) {
            if (result.getCount() != 0) {
                while (result.moveToNext()) {
                    int id = result.getInt(0);
                    String brand = result.getString(1);
                    String model = result.getString(2);
                    int productionDate = result.getInt(3);
                    Double tankVolume = result.getDouble(4);
                    String vin = result.getString(5);
                    String description = result.getString(6);
                    String fuelType = result.getString(7);
                    byte[] image = result.getBlob(8);
                    String registery = result.getString(9);
                    String carNickname = result.getString(10);
                    Car car = new Car(id, brand, model, productionDate, tankVolume, vin, description, fuelType, image, registery, carNickname);
                    Car.listOfCars.add(car);
                }
            }
        }
    }

    public void updateCarInDb(Car car) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(AUTO_MARKA, car.getBrand());
        contentValues.put(AUTO_MODEL, car.getModel());
        contentValues.put(AUTO_ROCZNIK, car.getProductionDate());
        contentValues.put(AUTO_POJ_SILNIKA, car.getTankVolume());
        contentValues.put(AUTO_VIN, car.getVin());
        contentValues.put(AUTO_OPIS, car.getDescription());
        contentValues.put(AUTO_PALIWO, car.getFuelType());
        contentValues.put(AUTO_NUMER_DOWODU_REJESTRACYJNEGO, car.getRegistry());
        contentValues.put(AUTO_NAZWA_WLASNA, car.getCarNickname());

        sqLiteDatabase.update(TABLE_AUTO, contentValues, ID + " =? ", new String[]{String.valueOf(car.getCarId())});
    }

    public void deleteCar(Car car) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.delete(TABLE_AUTO, ID + " =? ", new String[]{String.valueOf(car.getCarId())});
    }

    //CarTodo
    public void addCarTodoToDb(CarTodo carTodo) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DO_ZROBIENIA_ID_AUTA, carTodo.getCarId());
        contentValues.put(DO_ZROBIENIA_OPIS, carTodo.getDescription());
        contentValues.put(DO_ZROBIENIA_NAZWA, carTodo.getName());

        sqLiteDatabase.insert(TABLE_DO_ZROBIENIA, null, contentValues);
    }

    public void fillCarTodoArrayList() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        CarTodo.listOfCarTodo.clear();

        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_DO_ZROBIENIA, null)) {
            if (result.getCount() != 0) {
                while (result.moveToNext()) {
                    int carTodoId = result.getInt(0);
                    int carId = result.getInt(1);
                    String name = result.getString(3);
                    String description = result.getString(2);

                    CarTodo carTodo = new CarTodo(carTodoId, carId, name, description);
                    CarTodo.listOfCarTodo.add(carTodo);

                }
            }
        }
    }

    public void updateCarTodoInDb(CarTodo carTodo) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DO_ZROBIENIA_ID_AUTA, carTodo.getCarId());
        contentValues.put(DO_ZROBIENIA_OPIS, carTodo.getDescription());
        contentValues.put(DO_ZROBIENIA_NAZWA, carTodo.getName());

        sqLiteDatabase.update(TABLE_DO_ZROBIENIA, contentValues, ID + " =? ", new String[]{String.valueOf(carTodo.getCarId())});
    }

    public void deleteCarTodo(CarTodo carTodo) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.delete(TABLE_DO_ZROBIENIA, ID + " =? ", new String[]{String.valueOf(carTodo.getCarId())});
    }

    //MAINTENANCE
    public void addMaintenanceToDb(Maintenance maintenance) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(EKSPLOATACYJNE_OPIS, maintenance.getDescription());
        contentValues.put(EKSPLOATACYJNE_CO, maintenance.getMaintenanceTarget());
        contentValues.put(EKSPLOATACYJNE_DATA, maintenance.getMaintenanceDate());
        contentValues.put(EKSPLOATACYJNE_GDZIE, maintenance.getPlace());
        contentValues.put(EKSPLOATACYJNE_KWOTA, maintenance.getPrice());
        contentValues.put(EKSPLOATACYJNE_NAST_DATA, maintenance.getNextMaintenanceDate());
        contentValues.put(EKSPLOATACYJNE_NAST_PRZEBIEG, maintenance.getNextMileage());
        contentValues.put(EKSPLOATACYJNE_ID_AUTA, maintenance.getCarId());

        sqLiteDatabase.insert(TABLE_EKSPLOATACYJNE,null,contentValues);
    }

    public void fillMaintenanceArrayList() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Maintenance.listOfMaintance.clear();

        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_EKSPLOATACYJNE, null)) {
            if (result.getCount() != 0) {
                while (result.moveToNext()) {
                    int id = result.getInt(0);
                    String description = result.getString(1);
                    String target = result.getString(2);
                    String date = result.getString(3);
                    String where = result.getString(4);
                    Double price = result.getDouble(5);
                    String nextDate = result.getString(6);
                    Double nextMileage = result.getDouble(7);
                    int carId = result.getInt(8);

                    Maintenance maintenance = new Maintenance(id, carId, date, target, nextDate, nextMileage, price, description, where);
                    Maintenance.listOfMaintance.add(maintenance);

                }
            }
        }
    }

    public void updateMaintenanceInDb(Maintenance maintenance) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(EKSPLOATACYJNE_OPIS, maintenance.getDescription());
        contentValues.put(EKSPLOATACYJNE_CO, maintenance.getMaintenanceTarget());
        contentValues.put(EKSPLOATACYJNE_DATA, maintenance.getMaintenanceDate());
        contentValues.put(EKSPLOATACYJNE_GDZIE, maintenance.getPlace());
        contentValues.put(EKSPLOATACYJNE_KWOTA, maintenance.getPrice());
        contentValues.put(EKSPLOATACYJNE_NAST_DATA, maintenance.getNextMaintenanceDate());
        contentValues.put(EKSPLOATACYJNE_NAST_PRZEBIEG, maintenance.getNextMileage());
        contentValues.put(EKSPLOATACYJNE_ID_AUTA, maintenance.getCarId());

        sqLiteDatabase.update(TABLE_EKSPLOATACYJNE, contentValues, ID + " =? ", new String[]{String.valueOf(maintenance.getMaintenanceId())});
    }

    public void deleteMaintenance(Maintenance maintenance) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.delete(TABLE_EKSPLOATACYJNE, ID + " =? ", new String[]{String.valueOf(maintenance.getMaintenanceId())});
    }

    //CONTACT
    public void addContactToDb(Contact contact) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(KONTAKT_NAZWA, contact.getContactName());
        contentValues.put(KONTAKT_NUMER, contact.getPhoneNumber());
        contentValues.put(KONTAKT_ADRES, contact.getAddress());
        contentValues.put(KONTAKT_EMAIL, contact.getEmail());

        sqLiteDatabase.insert(TABLE_KONTAKT, null, contentValues);
    }

    public void fillContactArrayList() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Contact.listOfContact.clear();

        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_KONTAKT, null)) {
            if (result.getCount() != 0) {
                while (result.moveToNext()) {
                    int id = result.getInt(0);
                    String name = result.getString(1);
                    String number = result.getString(2);
                    String address = result.getString(3);
                    String email = result.getString(4);

                    Contact contact = new Contact(id, name, number, email, address);
                    Contact.listOfContact.add(contact);
                }
            }
        }
    }

    public void updateContactInDb(Contact contact) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(KONTAKT_NAZWA, contact.getContactName());
        contentValues.put(KONTAKT_NUMER, contact.getPhoneNumber());
        contentValues.put(KONTAKT_ADRES, contact.getAddress());
        contentValues.put(KONTAKT_EMAIL, contact.getEmail());

        sqLiteDatabase.update(TABLE_KONTAKT, contentValues, ID + " =? ", new String[]{String.valueOf(contact.getContactId())});
    }

    public void deleteContactInDb(Contact contact) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.delete(TABLE_KONTAKT, ID + " =? ", new String[]{String.valueOf(contact.getContactId())});
    }

    //FIX
    public void addFixToDb(Fix fix) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(NAPRAWA_ID_AUTA, fix.getCarId());
        contentValues.put(NAPRAWA_DATA, fix.getDateOfFix());
        contentValues.put(NAPRAWA_CO, fix.getFixDescription());
        contentValues.put(NAPRAWA_UWAGI, fix.getWarnings());
        contentValues.put(NAPRAWA_CENA, fix.getPrice());
        contentValues.put(NAPRAWA_GDZIE, fix.getPlaceOfFix());

        sqLiteDatabase.insert(TABLE_NAPRAWA, null, contentValues);
    }

    public void fillFixArrayList() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Fix.listOfFix.clear();
        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAPRAWA, null)) {
            if (result.getCount() != 0) {
                while (result.moveToNext()) {
                    int id = result.getInt(0);
                    int carId = result.getInt(1);
                    String date = result.getString(2);
                    String description = result.getString(3);
                    String warnings = result.getString(4);
                    Double price = result.getDouble(5);
                    String where = result.getString(6);

                    Fix fix = new Fix(id, carId, date, description, warnings, price, where);
                    Fix.listOfFix.add(fix);

                }
            }
        }
    }

    public void updateFixInDb(Fix fix) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(NAPRAWA_ID_AUTA, fix.getCarId());
        contentValues.put(NAPRAWA_DATA, fix.getDateOfFix());
        contentValues.put(NAPRAWA_CO, fix.getFixDescription());
        contentValues.put(NAPRAWA_UWAGI, fix.getWarnings());
        contentValues.put(NAPRAWA_CENA, fix.getPrice());
        contentValues.put(NAPRAWA_GDZIE, fix.getPlaceOfFix());

        sqLiteDatabase.update(TABLE_NAPRAWA, contentValues, ID + " =? ", new String[]{String.valueOf(fix.getFixId())});
    }

    public void deleteFixInDb(Fix fix) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.delete(TABLE_NAPRAWA, ID + " =? ", new String[]{String.valueOf(fix.getFixId())});
    }

    //NOTIFICATION
    public void addNotificationToDb(Notification notification) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(POWIADOMIENIA_ID_AUTA, notification.getCarId());
        contentValues.put(POWIADOMIENIA_KIEDY, notification.getDate());
        contentValues.put(POWIADOMIENIA_OPIS, notification.getDescription());
        contentValues.put(POWIADOMIENIA_STOPIEN_WAZNOSCI, notification.getImportance());
        contentValues.put(POWIADOMIENIA_TYP_POWIADOMIENIA, notification.getNotificationType());

        sqLiteDatabase.insert(TABLE_POWIADOMIENIA, null, contentValues);
    }

    public void fillNotificationArrayList() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Notification.listOfNotification.clear();

        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_POWIADOMIENIA, null)) {
            if (result.getCount() != 0) {
                while (result.moveToNext()) {
                    int id = result.getInt(0);
                    int carId = result.getInt(1);
                    String date = result.getString(2);
                    String description = result.getString(3);
                    int importance = result.getInt(4);
                    int type = result.getInt(5);

                    Notification notification = new Notification(id, carId, date, description, importance, type);
                    Notification.listOfNotification.add(notification);
                }
            }
        }
    }

    public void updateNotificationInDb(Notification notification) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(POWIADOMIENIA_ID_AUTA, notification.getCarId());
        contentValues.put(POWIADOMIENIA_KIEDY, notification.getDate());
        contentValues.put(POWIADOMIENIA_OPIS, notification.getDescription());
        contentValues.put(POWIADOMIENIA_STOPIEN_WAZNOSCI, notification.getImportance());
        contentValues.put(POWIADOMIENIA_TYP_POWIADOMIENIA, notification.getNotificationType());

        sqLiteDatabase.update(TABLE_POWIADOMIENIA, contentValues, ID + " =? ", new String[]{String.valueOf(notification.getNotificationId())});
    }

    public void deleteNotificationInDb(Notification notification) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.delete(TABLE_POWIADOMIENIA, ID + " =? ", new String[]{String.valueOf(notification.getNotificationId())});
    }

    //MILEAGE
    public void addMileageToDb(Mileage mileage) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(PRZEBIEG_ID_AUTA, mileage.getCarId());
        contentValues.put(PRZEBIEG_ID_AKCJI, mileage.getActionId());
        contentValues.put(PRZEBIEG_DATA, mileage.getMileageCheckDate());
        contentValues.put(PRZEBIEG_ILE, mileage.getMileageValue());

        sqLiteDatabase.insert(TABLE_PRZEBIEG, null, contentValues);
    }

    public void fillMileageArrayList() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Mileage.listOfMIleage.clear();
        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_PRZEBIEG, null)) {
            if (result.getCount() != 0) {
                while (result.moveToNext()) {
                    int id = result.getInt(0);
                    int carId = result.getInt(1);
                    int actionId = result.getInt(2);
                    String date = result.getString(3);
                    Double mileageValue = result.getDouble(4);

                    Mileage mileage = new Mileage(id, carId, actionId, date, mileageValue, actionId);
                    Mileage.listOfMIleage.add(mileage);

                }
            }
        }
    }

    public void updateMileageInDb(Mileage mileage) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(PRZEBIEG_ID_AUTA, mileage.getCarId());
        contentValues.put(PRZEBIEG_ID_AKCJI, mileage.getActionId());
        contentValues.put(PRZEBIEG_DATA, mileage.getMileageCheckDate());
        contentValues.put(PRZEBIEG_ILE, mileage.getMileageValue());
        sqLiteDatabase.update(TABLE_PRZEBIEG, contentValues, ID + " =? ", new String[]{String.valueOf(mileage.getMileageId())});
    }

    public void deleteMileageInDb(Mileage mileage) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.delete(TABLE_PRZEBIEG, ID + " =? ", new String[]{String.valueOf(mileage.getMileageId())});
    }

    //CHECKUP
    public void addCheckupToDb(Checkup checkup) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(PRZEBIEG_ID_AUTA, checkup.getCarId());
        contentValues.put(PRZEGLAD_ID_PRZEBIEG, checkup.getMileageId());
        contentValues.put(PRZEGLAD_KIEDY, checkup.getDate());
        contentValues.put(PRZEGLAD_DO_KIEDY, checkup.getExpirationDate());
        contentValues.put(PRZEGLAD_GDZIE, checkup.getCheckupLocation());
        contentValues.put(PRZEGLAD_CENA, checkup.getPrice());
        contentValues.put(PRZEGLAD_POZYTYWNY, checkup.getPassed());
        contentValues.put(PRZEGLAD_OPIS, checkup.getDescription());

        sqLiteDatabase.insert(TABLE_PRZEGLAD, null, contentValues);
    }

    public void fillCheckupArrayList() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Checkup.listOfCheckup.clear();

        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_PRZEGLAD, null)) {
            if (result.getCount() != 0) {
                while (result.moveToNext()) {
                    int id = result.getInt(0);
                    int carId = result.getInt(1);
                    int mileageId = result.getInt(2);
                    String date = result.getString(3);
                    String expirationDate = result.getString(4);
                    String place = result.getString(5);
                    Double price = result.getDouble(6);
                    int passed = result.getInt(7);
                    String description = result.getString(8);

                    Checkup checkup = new Checkup(id, carId, mileageId, date, expirationDate, place, price, passed, description);
                    Checkup.listOfCheckup.add(checkup);
                }
            }
        }
    }

    public void updateCheckupInDb(Checkup checkup) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(PRZEBIEG_ID_AUTA, checkup.getCarId());
        contentValues.put(PRZEGLAD_ID_PRZEBIEG, checkup.getMileageId());
        contentValues.put(PRZEGLAD_KIEDY, checkup.getDate());
        contentValues.put(PRZEGLAD_DO_KIEDY, checkup.getExpirationDate());
        contentValues.put(PRZEGLAD_GDZIE, checkup.getCheckupLocation());
        contentValues.put(PRZEGLAD_CENA, checkup.getPrice());
        contentValues.put(PRZEGLAD_POZYTYWNY, checkup.getPassed());
        contentValues.put(PRZEGLAD_OPIS, checkup.getDescription());

        sqLiteDatabase.update(TABLE_PRZEGLAD, contentValues, ID + " =? ", new String[]{String.valueOf(checkup.getCheckupId())});
    }

    public void deleteCheckupInDb(Checkup checkup) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.delete(TABLE_PRZEGLAD, ID + " =? ", new String[]{String.valueOf(checkup.getCheckupId())});
    }

    //ACTION
    public void addActionToDb(Action action) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(SLOWNIKA_AKCJI_NAZWA_AKCJI, action.getName());

        sqLiteDatabase.insert(TABLE_SLOWNIK_AKCJI, null, contentValues);
    }

    public void fillActionArrayList() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Action.listOfActions.clear();

        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_SLOWNIK_AKCJI, null)) {
            if (result.getCount() != 0) {
                while (result.moveToNext()) {
                    int id = result.getInt(0);
                    String name = result.getString(1);

                    Action action = new Action(id, name);
                    Action.listOfActions.add(action);
                }
            }
        }
    }

    public void updateActionInDb(Action action) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(SLOWNIKA_AKCJI_NAZWA_AKCJI, action.getName());
        sqLiteDatabase.update(TABLE_SLOWNIK_AKCJI, contentValues, ID + " =? ", new String[]{String.valueOf(action.getActionId())});
    }

    public void deleteActionInDb(Action action) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.delete(TABLE_SLOWNIK_AKCJI, ID + " =? ", new String[]{String.valueOf(action.getActionId())});
    }

    //FUEL FILL
    public void addFuelFIllToDb(FuelFill fuelFill) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TANKOWANIE_ID_AUTA, fuelFill.getCarId());
        contentValues.put(TANKOWANIE_DATA, fuelFill.getFillDate());
        contentValues.put(TANKOWANIE_CENAZALITR, fuelFill.getPricePerLiter());
        contentValues.put(TANKOWANIE_KWOTA, fuelFill.getPrice());
        contentValues.put(TANKOWANIE_NAZWA_STACJI, fuelFill.getStationName());
        contentValues.put(TANKOWANIE_ILOSC, fuelFill.getLiterAmount());
        contentValues.put(TANKOWANIE_JAKIE_PALIWO, fuelFill.getFuelType());

        sqLiteDatabase.insert(TABLE_TANKOWANIE, null, contentValues);
    }

    public void fillFuelFillArrayList() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        FuelFill.listOfFuelFill.clear();

        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_TANKOWANIE, null)) {
            if (result.getCount() != 0) {
                while (result.moveToNext()) {
                    int id = result.getInt(0);
                    int carId = result.getInt(1);
                    String date = result.getString(2);
                    Double pricePerLiter = result.getDouble(3);
                    Double price = result.getDouble(4);
                    String stationName = result.getString(5);
                    Double amount = result.getDouble(6);
                    String fuelType = result.getString(7);

                    FuelFill fuelFill = new FuelFill(id, carId, date, pricePerLiter, price, stationName, amount, fuelType);

                    FuelFill.listOfFuelFill.add(fuelFill);
                }
            }
        }
    }

    public void updateFuelFillInDb(FuelFill fuelFill) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TANKOWANIE_ID_AUTA, fuelFill.getCarId());
        contentValues.put(TANKOWANIE_DATA, fuelFill.getFillDate());
        contentValues.put(TANKOWANIE_CENAZALITR, fuelFill.getPricePerLiter());
        contentValues.put(TANKOWANIE_KWOTA, fuelFill.getPrice());
        contentValues.put(TANKOWANIE_NAZWA_STACJI, fuelFill.getStationName());
        contentValues.put(TANKOWANIE_ILOSC, fuelFill.getLiterAmount());
        contentValues.put(TANKOWANIE_JAKIE_PALIWO, fuelFill.getFuelType());

        sqLiteDatabase.update(TABLE_TANKOWANIE, contentValues, ID + " =? ", new String[]{String.valueOf(fuelFill.getFillId())});
    }

    public void deleteFuelFillInDb(FuelFill fuelFill) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_TANKOWANIE, ID + " =? ", new String[]{String.valueOf(fuelFill.getFillId())});
    }

    //INSURANCE
    public void addInsuranceToDb(Insurance insurance) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(UBEZPIECZENIE_ID_AUTA, insurance.getCarId());
        contentValues.put(UBEZPIECZENIE_KIEDY, insurance.getStartDate());
        contentValues.put(UBEZPIECZENIE_DO_KIEDY, insurance.getExpirationDate());
        contentValues.put(UBEZPIECZENIE_UBEZPIECZYCIEL, insurance.getProvider());
        contentValues.put(UBEZPIECZENIE_CENA, insurance.getPrice());
        contentValues.put(UBEZPIECZENIE_RODZAJ, insurance.getInsuranceType());
        contentValues.put(UBEZPIECZENIE_NR_POLISY, insurance.getInsuranceNumber());

        sqLiteDatabase.insert(TABLE_UBEZPIECZENIE, null, contentValues);
    }

    public void fillInsuranceArrayList() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Insurance.listOfInsurance.clear();

        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_UBEZPIECZENIE, null)) {
            if (result.getCount() != 0) {
                while (result.moveToNext()) {
                    int id = result.getInt(0);
                    int carId = result.getInt(1);
                    String startDate = result.getString(2);
                    String endDate = result.getString(3);
                    String provider = result.getString(4);
                    Double price = result.getDouble(5);
                    String insuranceType = result.getString(6);
                    String insuranceNumber = result.getString(7);

                    Insurance insurance = new Insurance(id, carId, startDate, endDate, provider, price, insuranceType, insuranceNumber);
                    Insurance.listOfInsurance.add(insurance);

                }
            }
        }
    }

    public void updateInsuranceInDb(Insurance insurance) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(UBEZPIECZENIE_ID_AUTA, insurance.getCarId());
        contentValues.put(UBEZPIECZENIE_KIEDY, insurance.getStartDate());
        contentValues.put(UBEZPIECZENIE_DO_KIEDY, insurance.getExpirationDate());
        contentValues.put(UBEZPIECZENIE_UBEZPIECZYCIEL, insurance.getProvider());
        contentValues.put(UBEZPIECZENIE_CENA, insurance.getPrice());
        contentValues.put(UBEZPIECZENIE_RODZAJ, insurance.getInsuranceType());
        contentValues.put(UBEZPIECZENIE_NR_POLISY, insurance.getInsuranceNumber());

        sqLiteDatabase.update(TABLE_UBEZPIECZENIE, contentValues, ID + " =? ", new String[]{String.valueOf(insurance.getInsuranceId())});
    }

    public void deleteInsuranceInDb(Insurance insurance) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_UBEZPIECZENIE, ID + " =? ", new String[]{String.valueOf(insurance.getInsuranceId())});
    }
}
