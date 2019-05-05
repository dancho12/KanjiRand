package com.example.kanjirand;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class KanjiDescription extends KanjiMenu {

    public static final String EXTRA_POSITION = "position";
    int postion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kanji_descriptoin);
        //P=xHolder.getVal();
        postion = getIntent().getIntExtra(EXTRA_POSITION, 0);


        //readExcelFile(postion);
        return_val(postion);
        TextView word_t,men_t,kun_t,on_t;
        word_t = (TextView)findViewById(R.id.textView);
        men_t = (TextView)findViewById(R.id.meaning_text);
        kun_t = (TextView)findViewById(R.id.kun_text);
        on_t = (TextView)findViewById(R.id.on_text);

        word_t.setText(word);
        men_t.setText(dip);
        kun_t.setText(kun);
        on_t.setText(on);

        ActionBar actionBar =getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
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

    public void edit(View v)
    {
        //set_card_info();
        Intent myIntent = new Intent(v.getContext(), KanjiAdd.class);
        myIntent.putExtra(KanjiDescription.EXTRA_POSITION, postion);
        this.finish();

        startActivity(myIntent);
    }
}
