package com.example.kanjirand;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class KanjiAdd extends MainActivity {
    private final static String FILENAME = "myExcel.xls";
    int rr;
    public static final String EXTRA_POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kanji_add);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        rr = r;
        T = getIntent().getIntExtra(EXTRA_POSITION, -11);
        if (T != -11) {
            edit_cell();

        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                if(T!=-11)
                {

                    //Intent myIntent = new Intent(this, KanjiMenu.class);
                    this.finish();
                    //startActivity(myIntent);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void edit_cell() {
        rr = T;
        Log.d("rrr", Integer.toString(rr));
        EditText word_ed = (EditText) findViewById(R.id.word_edit);
        EditText on_ed = (EditText) findViewById(R.id.on_text_edit);
        EditText kun_ed = (EditText) findViewById(R.id.kun_text_edit);
        EditText men_ed = (EditText) findViewById(R.id.meaning_text_edit);
        return_val(rr);
        word_ed.setText(word);
        on_ed.setText(on);
        kun_ed.setText(kun);
        men_ed.setText(dip);
    }


    public void main(View v) {
        EditText word = (EditText) findViewById(R.id.word_edit);
        EditText on_ed = (EditText) findViewById(R.id.on_text_edit);
        EditText kun_ed = (EditText) findViewById(R.id.kun_text_edit);
        EditText men_ed = (EditText) findViewById(R.id.meaning_text_edit);

        if (st.equals(word.getText().toString()) || st.equals(on_ed.getText().toString()) ||
                st.equals(kun_ed.getText().toString()) || st.equals(men_ed.getText().toString())) {
            Toast.makeText(this, "Есть пустые поля", Toast.LENGTH_SHORT).show();
        } else {


            String fileName = FILENAME;
            Context context = this;
            //readExcelFileR();
            //int T = 3;
            try {

                File file = new File(context.getExternalFilesDir(null), fileName);
                FileInputStream myInput = new FileInputStream(file);

                // Create a POIFSFileSystem object
                POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);

                // Create a workbook using the File System
                HSSFWorkbook workbook = new HSSFWorkbook(myFileSystem);

                // Get first sheet from the workbook

                HSSFCell cell = null;
                HSSFSheet sheet = workbook.getSheetAt(0);

                HSSFRow sheetrow = sheet.getRow(rr);
                if (sheetrow == null) {
                    sheetrow = sheet.createRow(rr);
                }
//Update the value of cell
                cell = sheetrow.getCell(0);
                if (cell == null) {
                    cell = sheetrow.createCell(0);
                }
                cell.setCellValue(word.getText().toString());

                cell = sheetrow.getCell(1);
                if (cell == null) {
                    cell = sheetrow.createCell(1);
                }
                cell.setCellValue(kun_ed.getText().toString());

                cell = sheetrow.getCell(2);
                if (cell == null) {
                    cell = sheetrow.createCell(2);
                }
                cell.setCellValue(on_ed.getText().toString());

                cell = sheetrow.getCell(3);
                if (cell == null) {
                    cell = sheetrow.createCell(3);
                }
                cell.setCellValue(men_ed.getText().toString());


                myInput.close();

                // Write File
                FileOutputStream out = new FileOutputStream(file);
                workbook.write(out);
                out.close();
                Log.w("FileUtils", "GG ");
            } catch (IOException e) {
                Log.w("FileUtils", "Error writing ", e);
            } catch (Exception e) {
                Log.w("FileUtils", "Failed to save file", e);

            }

            word.getText().clear();
            on_ed.getText().clear();
            kun_ed.getText().clear();
            men_ed.getText().clear();

        }
    }

}
