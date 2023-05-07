package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DBAdapter extends SQLiteOpenHelper {
    SQLiteDatabase myDataBase;
    public static final String DBName = "Exercise Data.db";
    public static String DBpath = "";
    Context context;

    public DBAdapter(Context context) {
        super(context, DBName, null, 2);
        this.context = context;
        this.DBpath = "/data/data/" + context.getPackageName() + "/" + "databases/";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void CheckDB(){
        SQLiteDatabase database = null;
        String db = DBpath+DBName;
        try{
            database = SQLiteDatabase.openDatabase(db,null,0);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(database == null){
            copyDatabase();
        }
    }

    public void copyDatabase() {
        this.getReadableDatabase();
        try{
            InputStream ios = context.getAssets().open(DBName);
            OutputStream os = new FileOutputStream(DBpath+DBName);

            byte[] buffer = new byte[1024];
            int len;
            while ((len = ios.read(buffer))>0)
            {
                os.write(buffer,0,len);
            }
            os.flush();
            ios.close();
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void openDatabase() {
        String dbPath = context.getDatabasePath(DBName).getPath();
        if (myDataBase != null && myDataBase.isOpen()) {
            return;
        }
        myDataBase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase() {
        if (myDataBase != null) {
            myDataBase.close();
        }
    }

    public ArrayList<String> get_names_data(String table) {
        openDatabase();
        String query = "SELECT Name FROM " + table;
        Cursor cursor = myDataBase.rawQuery(query, null);
        ArrayList name_list = new ArrayList<String>();
        while (cursor.moveToNext()) {
            name_list.add(cursor.getString(0));
        }
        cursor.close();
        closeDatabase();
        return name_list;
    }

    public ArrayList<String> get_image_urls(String table) {
        openDatabase();
        String query = "SELECT Image_link FROM " + table;
        Cursor cursor = myDataBase.rawQuery(query, null);
        ArrayList list = new ArrayList<String>();
        while (cursor.moveToNext()) {
            list.add(cursor.getString(0));
        }
        cursor.close();
        closeDatabase();
        return list;
    }
    public ArrayList<String> get_vid_url(String table, String name) {
        openDatabase();
        String query = "SELECT Video_link FROM " + table + " Where Name = \"" + name+"\"";
        Cursor cursor = myDataBase.rawQuery(query, null);
        ArrayList list = new ArrayList<String>();
        while (cursor.moveToNext()) {
            list.add(cursor.getString(0));
        }
        cursor.close();
        closeDatabase();
        return list;
    }
}
