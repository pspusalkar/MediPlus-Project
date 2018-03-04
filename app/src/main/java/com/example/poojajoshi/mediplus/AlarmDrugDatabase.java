package com.example.poojajoshi.mediplus;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.content.ContentValues;
import android.database.Cursor;

public class AlarmDrugDatabase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "alarmDrugManager";

    // Contacts table name
    private static final String TABLE_ALARM = "table_alarm";
    private static final String TABLE_DRUG = "table_drug";

    // Contacts Table Columns names
    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_PRICE = "price";
    private static final String KEY_ALARM_DATE = "date";
    private static final String KEY_ALARM_TIME = "time";

    public AlarmDrugDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            // create drug table
            String CREATE_DRUG_TABLE = "CREATE TABLE " + TABLE_DRUG + "( "
                    + "_id" + " INTEGER PRIMARY KEY, " + KEY_NAME + " TEXT, "
                    + KEY_DESCRIPTION + " TEXT, " + KEY_PRICE + " TEXT )";

            db.execSQL(CREATE_DRUG_TABLE);

            // create alarm table
            String CREATE_ALARM_TABLE = "CREATE TABLE " + TABLE_ALARM + "( "
                    + "_id" + " INTEGER PRIMARY KEY, " + KEY_NAME + " TEXT, "
                    + KEY_ALARM_DATE + " TEXT, " + KEY_ALARM_TIME + " TEXT )";

            db.execSQL(CREATE_ALARM_TABLE);
        } catch (SQLiteException  e) {
            e.printStackTrace();
        }
    }

    // Upgrading database
    // @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DRUG);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALARM);

        // DATABASE_VERSION = newVersion;
        // Create tables again
        onCreate(db);
    }

    // Adding new alarm
    public void addAlarm(AlarmDetails alarm) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, alarm.getAlarm_name());
        values.put(KEY_ALARM_DATE, alarm.getAlarm_date());
        values.put(KEY_ALARM_TIME, alarm.getAlarm_time());

        // Inserting Row
        db.insert(TABLE_ALARM, null, values);
        // db.close(); // Closing database connection
    }

    // Adding new drug
    public void addDrug(DrugDetails drug) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, drug.getDrug_name());
        values.put(KEY_DESCRIPTION, drug.getDrug_description());
        values.put(KEY_PRICE, drug.getDrug_price());

        // Inserting Row
        db.insert(TABLE_DRUG, null, values);
        // db.close(); // Closing database connection
    }

    // get all alarms
    public Cursor getAllAlarmData () {

        String buildSQL = "SELECT _id, name, date, time FROM " + TABLE_ALARM;

        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(buildSQL, null);
    }

    // get all alarms
    public Cursor getAllDrugData () {

        String buildSQL = "SELECT _id, name, description, price FROM " + TABLE_DRUG;

        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(buildSQL, null);
    }

    // Getting single alarm
    public AlarmDetails getAlarm(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ALARM, new String[] { KEY_ID,
                        KEY_NAME, KEY_ALARM_DATE, KEY_ALARM_TIME }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        AlarmDetails alarmDetails = new AlarmDetails(Integer.parseInt(cursor.getString(0)),
                                                    cursor.getString(1),
                                                    cursor.getString(2),
                                                    cursor.getString(3));
        // return alarm
        return alarmDetails;
    }

    // Getting single drug
    public DrugDetails getDrug(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DRUG, new String[] { KEY_ID,
                        KEY_NAME, KEY_DESCRIPTION, KEY_PRICE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        DrugDetails drugDetails= new DrugDetails();
        if (cursor != null && cursor.moveToFirst()) {
            drugDetails = new DrugDetails(Integer.parseInt(cursor.getString(0)),
                                                        cursor.getString(1),
                                                        cursor.getString(2),
                                                        cursor.getString(3));
        }
        // return drug
        return drugDetails;
    }

    // Updating single alarm
    public int updateAlarm(AlarmDetails alarmDetails) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, alarmDetails.getAlarm_name());
        values.put(KEY_ALARM_DATE, alarmDetails.getAlarm_date());
        values.put(KEY_ALARM_TIME, alarmDetails.getAlarm_time());

        // updating row
        return db.update(TABLE_ALARM, values, KEY_ID + " = ?",
                new String[] { String.valueOf(alarmDetails.getID()) });
    }

    // Updating single drug
    public int updateDrug(DrugDetails drugDetails) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, drugDetails.getDrug_name());
        values.put(KEY_DESCRIPTION, drugDetails.getDrug_description());
        values.put(KEY_PRICE, drugDetails.getDrug_price());

        // updating row
        return db.update(TABLE_DRUG, values, KEY_ID + " = ?",
                new String[] { String.valueOf(drugDetails.getID()) });
    }
}
