package com.example.beenlovememory.Database;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.beenlovememory.Model.TimeLine;

import java.sql.Struct;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void QueryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertImage(String Date,String Content, byte[] Image) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO Memory VALUES(null, ?, ?, ?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, Date);
        statement.bindString(2, Content);
        statement.bindBlob(3, Image);
        statement.executeInsert();
    }

    public void deleteData(String Content, String Date) {
        SQLiteDatabase database = getWritableDatabase();

        database.delete("Memory", "Content =? AND Date = ?" , new String[]{Content, Date});
        database.close();


//        String sql = "DELETE FROM Memory WHERE Content = " + Content;

//        database.execSQL(sql);
//        SQLiteStatement statement = database.compileStatement(sql);
//
//        statement.clearBindings();
//        statement.bindString(1, Content);
////        statement.bindString(1, Date);
////        statement.bindBlob(2, Image);
//        statement.executeUpdateDelete();
    }

    public void updateData(String Date,String Content, byte[] Image, String s1, String s2) {
        TimeLine timeLine = new TimeLine();
        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Date", Date);
        values.put("Content", Content);
        values.put("Imgages", Image);

        database.update("Memory", values, "Date = ? AND Content = ?", new String[]{s1, s2});
        database.close();
    }

    public Cursor GetData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
