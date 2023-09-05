package com.example.smsreceive;
//      Author : Md Liakot Ali liton
//      Student Id : 1802035
//      Dept. of CSE, HSTU

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ClassSQLiteHelper extends SQLiteOpenHelper {
    private Context context;
    private final static String databaseName = "SMSReceive.db";
    private final static int databaseVersion = 1;
    private final static String msgDetails = "MessageDetails";

    private final static String msgId = "MessageID";
    private final static String msgTime = "MessageTime";
    private final static String msgDate = "MessageDate";
    private final static String msgBody = "MessageBody";
    private final static String msgSender = "MessageSender";
    private final static String msgSeen = "MessageSeen";


    public ClassSQLiteHelper(@Nullable Context context) {
        super(context, databaseName, null, databaseVersion);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String msgDetailsTable = "CREATE TABLE " + msgDetails + " (" + msgId + " TEXT NOT NULL, " + msgTime + " TEXT, " +
                msgDate + " TEXT, " + msgBody + " TEXT, " + msgSender + " TEXT, " + msgSeen + " BOOLEAN);";
        db.execSQL(msgDetailsTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String table1 = "DROP TABLE IF EXISTS " + msgDetails;
        db.execSQL(table1);
        onCreate(db);
    }

    public void addNewMessage(ClassSMS  sms)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(msgId, sms.getId());
        cv.put(msgTime, sms.getTime());
        cv.put(msgDate, sms.getDate());
        cv.put(msgBody, sms.getBody());
        cv.put(msgSender, sms.getSender());
        cv.put(msgSeen, false);
        long result = database.insert(msgDetails, null, cv);
        database.close();
        Log.e("CLASS", "AddNewMessage: " + cv + " " + result);
//        if (result != -1) {
//            Toast.makeText(context, "New message added", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
//        }
    }

    public void UpdateSeen(String id)
    {
//        String query = "UPDATE " + msgDetails + " SET " + msgSeen + " = " + true + " WHERE " + msgId + " = " + id ;
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(msgSeen, true);
        database.update(msgDetails, cv, msgId + " = ? ", new String[]{id});
        database.close();
//        database.execSQL(query);
    }

    public Cursor showMessage() {
        Cursor cursor = null;
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM " + msgDetails;
        if (database != null) {
            cursor = database.rawQuery(query, null);
        }
        return cursor;
    }
}
