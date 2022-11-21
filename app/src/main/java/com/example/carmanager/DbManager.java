package com.example.carmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.carmanager.models.Car;
import com.example.carmanager.models.CarTodo;

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
//                .append(AUTO_ZDJECIE)
//                .append(" BLOB, ")
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
                .append(" INT)");

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
                    String registery = result.getString(8);
                    String carNickname = result.getString(9);
                    Car car = new Car(id, brand, model, productionDate, tankVolume, vin, description, fuelType, registery, carNickname);
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

}
