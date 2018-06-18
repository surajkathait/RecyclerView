package com.brillicaservices.databaseapplication;

/**
 * Created by win-8 on 10-06-2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "student_db";

    private static final String TABLE_NAME = "student_record";
    private static final String STUDENT_NAME = "student_name";
    private static final String STUDENT_ID = "student_id";
    private static final String STUDENT_COLLEGE = "student_college";
    private static final String STUDENT_ADDRESS = "student_address";
    private static final String STUDENT_PHONE_NUMBER = "student_phone";


    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " +
            STUDENT_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + STUDENT_NAME + " TEXT, " +
            STUDENT_COLLEGE + " TEXT, " + STUDENT_ADDRESS
            + " TEXT, " + STUDENT_PHONE_NUMBER + " INTEGER ); ";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }


    public long addNewStudent(StudentData StudentData) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(STUDENT_NAME, StudentData.name);
        contentValues.put(STUDENT_COLLEGE, StudentData.collegeName);
        contentValues.put(STUDENT_ADDRESS, StudentData.address);
        contentValues.put(STUDENT_PHONE_NUMBER, StudentData.phoneNumber);

        long id = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        sqLiteDatabase.close();

        return id;
    }

    public StudentData getSingleStudentDetails(long id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, new String[]{STUDENT_ID, STUDENT_NAME, STUDENT_COLLEGE, STUDENT_ADDRESS,
                        STUDENT_PHONE_NUMBER}, STUDENT_ID + "=?", new String[]{String.valueOf(id)}, null, null,
                null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        StudentData StudentData = new StudentData(cursor.getInt(cursor.getColumnIndex(STUDENT_ID)),
                cursor.getString(cursor.getColumnIndex(STUDENT_NAME)), cursor.getString(cursor.getColumnIndex(STUDENT_COLLEGE)),
                cursor.getString(cursor.getColumnIndex(STUDENT_ADDRESS)), cursor.getLong(cursor.getColumnIndex(STUDENT_PHONE_NUMBER)));

        cursor.close();

        return StudentData;
    }

    public List<StudentData> allStudentsDetails() {
        List<StudentData> studentsList = new ArrayList<>();

        String selectQuery = " SELECT * FROM " + TABLE_NAME ;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                StudentData StudentData = new StudentData();
                StudentData.setId(cursor.getInt(cursor.getColumnIndex(STUDENT_ID)));
                StudentData.setName(cursor.getString(cursor.getColumnIndex(STUDENT_NAME)));
                StudentData.setCollegeName(cursor.getString(cursor.getColumnIndex(STUDENT_COLLEGE)));
                StudentData.setAddress(cursor.getString(cursor.getColumnIndex(STUDENT_ADDRESS)));
                StudentData.setPhoneNumber(cursor.getLong(cursor.getColumnIndex(STUDENT_PHONE_NUMBER)));

                studentsList.add(StudentData);
            } while (cursor.moveToNext());
        }

        sqLiteDatabase.close();

        return  studentsList;
    }

    public int getStudentsCount() {

        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        int totalStudentsCount = cursor.getCount();
        cursor.close();

        return totalStudentsCount;
    }

    public int updateIndividualStudentDetails(StudentData StudentData) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STUDENT_NAME, StudentData.getName());
        values.put(STUDENT_COLLEGE, StudentData.getCollegeName());
        values.put(STUDENT_ADDRESS, StudentData.getAddress());
        values.put(STUDENT_PHONE_NUMBER, StudentData.getPhoneNumber());

        // updating row
        return sqLiteDatabase.update(TABLE_NAME, values, STUDENT_ID + " = ?",
                new String[]{String.valueOf(StudentData.getId())});
    }
    public void deleteAll()
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        sqLiteDatabase.execSQL(" delete from "+TABLE_NAME);
        sqLiteDatabase.close();
    }

}

