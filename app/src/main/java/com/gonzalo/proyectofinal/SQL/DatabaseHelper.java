package com.gonzalo.proyectofinal.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, "segunda.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table users(nombreuser text primary key,passworduser text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
    }

    public boolean insertarUsuarios(String nombreUsuario, String passwordUsuario){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombreuser",nombreUsuario);
        contentValues.put("passworduser",passwordUsuario);
        long insert = db.insert("users", null, contentValues);
        if (insert == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean comprobarUsuario (String nombreUsuario){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select nombreuser from users where nombreuser =?", new String[] {nombreUsuario});
        if (cursor.getCount()>0){
            return false;
        }else{
            return true;
        }
    }

    public boolean comprobarLogin (String nombreUsuario, String passwordUsuario){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where nombreuser =? and passworduser =?", new String[] {nombreUsuario, passwordUsuario});
        if (cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }
}