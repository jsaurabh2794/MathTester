package com.example.saurabhsinghrajput.mathtester;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by SAURABH SINGH RAJPUT on 08-10-2016.
 */
public class score_saver extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="Score.db";
    public static final String TABLE_NAME="UserScore";

    public static final String col1="HighestScore";




    public score_saver(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TABLE_NAME+"(HighestScore Integer)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public boolean insertDATA(Integer Score)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues content=new ContentValues();
        content.put(col1,Score);
        long res=db.insert(TABLE_NAME,null,content);
        if(res==-1)
            return false;
        else
            return true;
    }

    public Cursor getalldata()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cr=db.rawQuery("Select * from " +TABLE_NAME,null);
        return  cr;
    }

    public int updatescore(Integer Score)
    {   int res=0;
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues content=new ContentValues();
        content.put(col1,Score);
        Cursor cr=db.rawQuery("Select * from " +TABLE_NAME,null);
        cr.moveToNext();
        if(cr.getCount()==0)
        {
            insertDATA(Score);
        }else
        {  if(cr.getInt(0)<Score)
        {
            res=  db.update(TABLE_NAME,content,"HighestScore= ?", new String[] {String.valueOf(cr.getInt(0))});
        }

        }
        return  res;

    }

}
