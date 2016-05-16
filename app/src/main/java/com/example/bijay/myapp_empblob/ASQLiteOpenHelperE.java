package com.example.bijay.myapp_empblob;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Bijay on 14-05-2016.
 */
public class ASQLiteOpenHelperE extends SQLiteOpenHelper{

    private static final int db_version = 1;
    private static final String db_name = "dbEmp";
    private static final String tb_name = "tbEmp";
    private static final String emp_name = "name";
    private static final String emp_age =   "age";
    private static final String emp_photo = "photo";

    private static final String[] columns = {emp_age,emp_name,emp_photo};

    public ASQLiteOpenHelperE(Context context){super(context, db_name,null, db_version);}

    public void onCreate(SQLiteDatabase db){
        String create_names_table = "CREATE TABLE " + tb_name + " ( " + emp_name +" TEXT, " + emp_age +  " INTEGER, " + emp_photo + " BLOB )";
        db.execSQL(create_names_table);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        String drop_names_table = "DROP TABLE IF EXISTS " + tb_name;
        db.execSQL(drop_names_table);
    }

    public void createEmp(Emp emp) throws IOException {
        SQLiteDatabase db = this.getWritableDatabase();

        URL url = null;
        try {
            url = new URL("https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcSZlD2OrmZpH3kEj42I6Vr008HMP-8EG-0abTVC-FmS5MmfZLxL");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        URLConnection ucon = url.openConnection();
        InputStream is = ucon.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is,128);
        ByteArrayOutputStream bos = new ByteArrayOutputStream(128);

        int current = 0;
        while ((current = bis.read())!=-1){
            bos.write((byte) current);
        }

        ContentValues values = new ContentValues();

        values.put(emp_name, emp.getEname());
        values.put(emp_age,emp.getEage());
        values.put(emp_photo,bos.toByteArray());

        db.insert(tb_name,null,values);
        db.close();
    }

    public void display() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from " + tb_name;
        Cursor cursor = db.rawQuery(query, null);

        byte[] imageBytesArray=cursor.getBlob(cursor.getColumnIndex(emp_photo));
        cursor.close();
        ByteArrayInputStream imageStream = new ByteArrayInputStream(imageBytesArray);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        //how to display this on screen
        db.close();
    }
}
