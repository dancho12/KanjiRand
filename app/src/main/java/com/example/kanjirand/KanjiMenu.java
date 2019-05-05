package com.example.kanjirand;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class KanjiMenu extends MainActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private final static String FILENAME = "myExcel.xls";

    //int rr;
    int P = 99;

    ArrayList<String> id = new ArrayList<>();
    ArrayList<String> title = new ArrayList<>();
    ArrayList<String> description = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kanji_menu);
        //readExcelFile(0);


        set_card_info();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    void set_card_info() {
        id.clear();
        title.clear();
        description.clear();

        for (int i = 0; i < r; i++) {
            //readExcelFile(i);
            return_val(i);
            id.add(word);
            title.add(dip);
            description.add(kun);
        }


        recyclerView = findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RecyclerAdapter(this, id, title, description);
        recyclerView.setAdapter(mAdapter);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void onClicker(View view, int position) {

        Intent myIntent = new Intent(view.getContext(), KanjiDescription.class);
        myIntent.putExtra(KanjiDescription.EXTRA_POSITION, position);
        Log.d("Back","1");
        startActivityForResult(myIntent, 0);

    }



    void showSnackbar() {

        //Toast.makeText(recyclerView, "Карточка с номером "+position, Toast.LENGTH_LONG).show();
        Toast.makeText(this, "Position: " + Integer.toString(P), Toast.LENGTH_SHORT).show();
    }
}
