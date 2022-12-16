package com.example.carmanager;

public class AdapterRekordRaports {
    private int id;
    private String Data1;
    private String Data2;
    private String Data3;
    private String Diff1;
    private String Diff2;
    private String Diff3;
    private String Date;
    private int DataType;


    public AdapterRekordRaports(String data1, String data2, String data3, String diff1, String diff2, String diff3, String date, int dataType) {
        Data1 = data1;
        Data2 = data2;
        Data3 = data3;
        Diff1 = diff1;
        Diff2 = diff2;
        Diff3 = diff3;
        Date = date;
        DataType = dataType;
    }

    public String getData1() {
        return Data1;
    }

    public void setData1(String data1) {
        Data1 = data1;
    }

    public String getData2() {
        return Data2;
    }

    public void setData2(String data2) {
        Data2 = data2;
    }

    public String getData3() {
        return Data3;
    }

    public void setData3(String data3) {
        Data3 = data3;
    }

    public String getDiff1() {
        return Diff1;
    }

    public void setDiff1(String diff1) {
        Diff1 = diff1;
    }

    public String getDiff2() {
        return Diff2;
    }

    public void setDiff2(String diff2) {
        Diff2 = diff2;
    }

    public String getDiff3() {
        return Diff3;
    }

    public void setDiff3(String diff3) {
        Diff3 = diff3;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getDataType() {
        return DataType;
    }

    public void setDataType(int dataType) {
        DataType = dataType;
    }
}
