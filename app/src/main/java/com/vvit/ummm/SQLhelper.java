package com.vvit.ummm;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;

public class SQLhelper extends SQLiteOpenHelper {
    public SQLhelper( Context context) {
        super(context,"userdata", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table userdetails(name Text,phone Text,speed Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop table if exists userdetails");
    }


    public boolean insertdata(String n,String p,String s)
    {
         SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues  contentValues=new ContentValues();
        contentValues.put("name",n);
        contentValues.put("phone",p);
        contentValues.put("speed",s);

        long res= DB.insert("userdetails",null,contentValues);

        if(res==-1)
        {
           return false;

        }else{
            return true;
        }

    }

    public boolean updatephone(String p)
    {

        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues  contentValues=new ContentValues();
        contentValues.put("phone",p);
        long res= DB.update("userdetails",contentValues,"name=?",null);
        if(res == -1)
        {
            return false;
        }else{
            return true;
        }
    }
    public boolean updatename(String p)
    {

        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues  contentValues=new ContentValues();
        contentValues.put("name",p);
        long res= DB.update("userdetails",contentValues,"phone=?",null);
        if(res == -1)
        {
            return false;
        }else{
            return true;
        }
    }
    public Cursor getdata(){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("select * from userdetails",null);
        return  cursor;
    }


    public Cursor getphone()
    {
        SQLiteDatabase DB=this.getReadableDatabase();
        Cursor cursor=DB.rawQuery("select phone from userdetails",null);
        return  cursor;
    }
    public Cursor getspeed()
    {
        SQLiteDatabase DB=this.getReadableDatabase();
        Cursor cursor=DB.rawQuery("select speed from userdetails",null);
        return  cursor;
    }


    /*public boolean updatedata(String n,String p)
    {
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues  contentValues=new ContentValues();

        contentValues.put("phone",p);
        long res= DB.update("userdetails",contentValues,"name=?",new String[] {name});

        if(res==-1)
        {
            return false;

        }else{
            return true;
        }

    }*/


}
