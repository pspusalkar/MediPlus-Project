package com.example.poojajoshi.mediplus;

public class AlarmDetails {
    //private variables
    int alarm_id;
    String alarm_name;
    String alarm_date;
    String alarm_time;

    // Empty constructor
    public AlarmDetails(){

    }

    // constructor
    public AlarmDetails(int id, String name, String date, String time){
        this.alarm_id = id;
        this.alarm_name = name;
        this.alarm_date = date;
        this.alarm_time = time;
    }

    // constructor
    public AlarmDetails(String name, String date, String time){
        this.alarm_name = name;
        this.alarm_date = date;
        this.alarm_time = time;
    }

    // getting ID
    public int getID(){
        return this.alarm_id;
    }

    // setting id
    public void setID(int id){
        this.alarm_id = id;
    }

    // getting name
    public String getAlarm_name(){
        return this.alarm_name;
    }

    // setting name
    public void setAlarm_name(String name){
        this.alarm_name = name;
    }

    // getting alarm date
    public String getAlarm_date(){
        return this.alarm_date;
    }

    // setting alarm date
    public void setAlarm_date(String date){
        this.alarm_date = date;
    }

    // getting alarm time
    public String getAlarm_time(){
        return this.alarm_time;
    }

    // setting alarm time
    public void setAlarm_time(String time){
        this.alarm_time = time;
    }
}
