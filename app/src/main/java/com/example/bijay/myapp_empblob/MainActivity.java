package com.example.bijay.myapp_empblob;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

public class MainActivity extends Activity {

    ASQLiteOpenHelperE db = new ASQLiteOpenHelperE(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        db.onUpgrade(db.getWritableDatabase(), 1, 2);
        try {
            db.createEmp(new Emp("Steve Job", 56));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
