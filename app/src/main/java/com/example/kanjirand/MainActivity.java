package com.example.kanjirand;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends  AppCompatActivity /*implements OnClickListener*/
{
    int T=-11,r;
    String st ="";
    String word,dip,kun,on;
    private final static String FILENAME = "myExcel.xls";

    DBHelper dbHelper;

    static String TAG = "ExelLog";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readExcelFile();
        dbHelper = new DBHelper(this);
        readExcelFileToDB();
    }






    public void onClick(View view) {
        Intent myIntent = new Intent(view.getContext(), KanjiMenu.class);
        startActivityForResult(myIntent, 0);
    }

    public void onClickRand(View view) {
        Intent myIntent = new Intent(view.getContext(), KanjiRand.class);
        startActivityForResult(myIntent, 0);
    }
    public void onClickAdd(View view) {
        Intent myIntent = new Intent(view.getContext(), KanjiAdd.class);
        //startActivityForResult(myIntent, 0);
        startActivity(myIntent);

    }

    //---------------------------------//


    void readExcelFile() {
        String filename = FILENAME;
        Context context=this;
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly())
        {
            Log.e(TAG, "Storage not available or read only");
            return;
        }

        try{
            // Creating Input Stream
            File file = new File(context.getExternalFilesDir(null), filename);
            FileInputStream myInput = new FileInputStream(file);

            // Create a POIFSFileSystem object
            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);

            // Create a workbook using the File System
            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);

            // Get the first sheet from workbook
            HSSFSheet mySheet = myWorkBook.getSheetAt(0);
            Iterator rowIter = mySheet.rowIterator();
            int rr = mySheet.getPhysicalNumberOfRows();

            for(int i=0;i<rr;i++){
                HSSFRow row = mySheet.getRow(i);
                HSSFCell wordC = row.getCell(0);
                String word = wordC.toString();
                if(!word.equals(st))
                {
                    r++;
                }
            }
            myInput.close();
            /** We now need something to iterate through the cells.**/

        }catch (Exception e){e.printStackTrace(); }

        return;
    }

    void return_val(int y)
    {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int kanjiIndex = cursor.getColumnIndex(DBHelper.KEY_KANJI);
            int kunIndex = cursor.getColumnIndex(DBHelper.KEY_KUN);
            int onIndex = cursor.getColumnIndex(DBHelper.KEY_ON);
            int menIndex = cursor.getColumnIndex(DBHelper.KEY_MEN);
            cursor.moveToPosition(y);
            word =cursor.getString(kanjiIndex);
            kun = cursor.getString(kunIndex);
            on = cursor.getString(onIndex);
            dip = cursor.getString(menIndex);
                Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                        ", kanji = " + cursor.getString(kanjiIndex) +
                        ", kunyomi = " + cursor.getString(kunIndex)+
                        ", onyomi = " + cursor.getString(onIndex) +
                        ", meaning = " + cursor.getString(menIndex));

        } else
            Log.d("mLog","0 rows");

        cursor.close();
    }


    void readExcelFileToDB() {
        String filename = FILENAME;


        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        database.delete(DBHelper.TABLE_CONTACTS, null, null);
        Context context=this;
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly())
        {
            Log.e(TAG, "Storage not available or read only");
            return;
        }

        try{
            // Creating Input Stream
            File file = new File(context.getExternalFilesDir(null), filename);
            FileInputStream myInput = new FileInputStream(file);

            // Create a POIFSFileSystem object
            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);

            // Create a workbook using the File System
            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);

            // Get the first sheet from workbook
            HSSFSheet mySheet = myWorkBook.getSheetAt(0);

            Iterator rowIter = mySheet.rowIterator();
            int rr = mySheet.getPhysicalNumberOfRows();


            Log.d("Rows:", "Rows: " +  Integer.toString(r));
            for(int i=0;i<r;i++)
            {
                HSSFRow row = mySheet.getRow(i);
                HSSFCell wordC = row.getCell(0);
                contentValues.put(DBHelper.KEY_KANJI, wordC.toString());
                HSSFCell kunC = row.getCell(1);
                contentValues.put(DBHelper.KEY_KUN, kunC.toString());
                HSSFCell onC = row.getCell(2);
                contentValues.put(DBHelper.KEY_ON, onC.toString());
                HSSFCell diC = row.getCell(3);
                contentValues.put(DBHelper.KEY_MEN, diC.toString());
                database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
            }



            myInput.close();
            /** We now need something to iterate through the cells.**/

            Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                int nameIndex = cursor.getColumnIndex(DBHelper.KEY_KANJI);
                int emailIndex = cursor.getColumnIndex(DBHelper.KEY_KUN);
                int nameIndex1 = cursor.getColumnIndex(DBHelper.KEY_ON);
                int emailIndex1 = cursor.getColumnIndex(DBHelper.KEY_MEN);
                do {
                    Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                            ", kanji = " + cursor.getString(nameIndex) +
                            ", kunyomi = " + cursor.getString(emailIndex)+
                            ", onyomi = " + cursor.getString(nameIndex1) +
                            ", meaning = " + cursor.getString(emailIndex1));
                } while (cursor.moveToNext());
            } else
                Log.d("mLog","0 rows");

            cursor.close();

        }catch (Exception e){e.printStackTrace(); }

        return;
    }

    public static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    public static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }
}
//http://dajver.blogspot.com/2014/02/ms-excel-android.html
//        https://www.cuelogic.com/blog/creatingreading-an-excel-file-in-android
