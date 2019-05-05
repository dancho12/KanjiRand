package com.example.kanjirand;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KanjiRand extends MainActivity {
    private int selectedTest_on;
    RadioGroup radio_on;
    private int selectedTest_kun;
    RadioGroup radio_kun;
    private int selectedTest_men;
    RadioGroup radio_men;
    String word_r, dip_r, kun_r, on_r;
    int random_word = -1;
    List numbers;
    TextView kanji;
    public static final String EXTRA_POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rand_kanji);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


        RadioGroup radio_on = (RadioGroup) findViewById(R.id.radioGroup_on);
        selectedTest_on = radio_on.getCheckedRadioButtonId();
        RadioGroup radio_kun = (RadioGroup) findViewById(R.id.radioGrup_kun);
        selectedTest_kun = radio_kun.getCheckedRadioButtonId();
        RadioGroup radio_men = (RadioGroup) findViewById(R.id.radioGrup_men);
        selectedTest_men = radio_men.getCheckedRadioButtonId();


        numbers = new ArrayList();
        for (int i = 0; i < r; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        for (int i = 0; i < r; i++) {
            int y = (int) numbers.get(i);
            Log.d("List=", Integer.toString(y));
        }
        next();

    }

    public void onClickRadioSelectTest_on(View v) {
        selectedTest_on = v.getId();
        Log.d("onClickRadioGroupSelectTest", "selectedTest=" + selectedTest_on);
    }

    public void onClickRadioSelectTest_kun(View v) {
        selectedTest_kun = v.getId();
        Log.d("onClickRadioGroupSelectTest", "selectedTest=" + selectedTest_kun);
    }

    public void onClickRadioSelectTest_men(View v) {
        selectedTest_men = v.getId();
        Log.d("onClickRadioGroupSelectTest", "selectedTest=" + selectedTest_men);
    }

    int r_rabt_id_on;

    void randRadio_on() {
        TextView btr;
        int radio_id_on[] = {R.id.on_radio_1, R.id.on_radio_2, R.id.on_radio_3, R.id.on_radio_4};
        int cr_bt = (int) (Math.random() * 4 + 1);
        r_rabt_id_on = radio_id_on[cr_bt - 1];
        btr = (TextView) findViewById(r_rabt_id_on);
        btr.setTypeface(null, Typeface.NORMAL);
        btr.setTextColor(Color.BLACK);
        btr.setText(on_r);
        for (int i = 0; i < 4; i++) {
            if (i != (cr_bt - 1)) {
                int ii = (int) (Math.random() * r);
                if (ii == random_word) {
                    while (ii == random_word) {
                        ii = (int) (Math.random() * r);
                    }
                }
                int id = radio_id_on[i];
                btr = (TextView) findViewById(id);
                //readExcelFile(ii);
                return_val(ii);
                btr.setTypeface(null, Typeface.NORMAL);
                btr.setTextColor(Color.BLACK);
                btr.setText(on);
            }
        }


    }

    int r_rabt_id_kun;

    void randRadio_kun() {
        TextView btr;

        int radio_id_on[] = {R.id.kun_radio_1, R.id.kun_radio_2, R.id.kun_radio_3, R.id.kun_radio_4};
        int cr_bt = (int) (Math.random() * 4 + 1);
        r_rabt_id_kun = radio_id_on[cr_bt - 1];
        btr = (TextView) findViewById(r_rabt_id_kun);
        btr.setTypeface(null, Typeface.NORMAL);
        btr.setTextColor(Color.BLACK);
        btr.setText(kun_r);
        for (int i = 0; i < 4; i++) {
            if (i != (cr_bt - 1)) {
                int ii = (int) (Math.random() * r);
                if (ii == random_word) {
                    while (ii == random_word) {
                        ii = (int) (Math.random() * r);
                    }
                }
                int id = radio_id_on[i];
                btr = (TextView) findViewById(id);
                //readExcelFile(ii);
               return_val(ii);
                btr.setTypeface(null, Typeface.NORMAL);
                btr.setTextColor(Color.BLACK);
                btr.setText(kun);
            }
        }

    }

    int r_rabt_id_men;

    void randRadio_men() {
        TextView btr;
        int u =0;
        int radio_id_on[] = {R.id.men_radio_1, R.id.men_radio_2, R.id.men_radio_3, R.id.men_radio_4};
        int cr_bt = (int) (Math.random() * 4 + 1);
        r_rabt_id_men = radio_id_on[cr_bt - 1];
        btr = (TextView) findViewById(r_rabt_id_men);
        btr.setTextColor(Color.BLACK);
        btr.setTypeface(null, Typeface.NORMAL);
        btr.setText(dip_r);
        for (int i = 0; i < 4; i++) {
            if (i != (cr_bt - 1)) {
                int ii = (int) (Math.random() * r);
                if (ii == random_word) {
                    while (ii == random_word) {
                        ii = (int) (Math.random() * r);
                    }
                }
                int id = radio_id_on[i];
                btr = (TextView) findViewById(id);
                //readExcelFile(ii);
                return_val(ii);
                btr.setTextColor(Color.BLACK);
                btr.setTypeface(null, Typeface.NORMAL);
                btr.setText(dip);
            }
        }

    }

    int f = 0;
    int w = -1;

//    void set_color(TextView r,TextView r2,int x)
//    {
//        if (r.toString().equals(on_r)) {
//            r.setTextColor(Color.parseColor("#03DAC6"));
//        } else {
//            r.setTextColor(Color.parseColor("#B00020"));
//
//            r2.setTypeface(null, Typeface.BOLD);
//            r_on_ch.setTextColor(Color.parseColor("#03DAC6"));
//
//        }
//    }

    public void prov(View v) {
        int y = 0;
        final TextView r_on = (TextView) findViewById(selectedTest_on);
        final TextView r_on_ch = (TextView) findViewById(r_rabt_id_on);
        r_on.setTypeface(null, Typeface.BOLD);
        if (r_on.toString().equals(on_r)) {
            r_on.setTextColor(Color.parseColor("#03DAC6"));
        } else {
            r_on.setTextColor(Color.parseColor("#B00020"));

            r_on_ch.setTypeface(null, Typeface.BOLD);
            r_on_ch.setTextColor(Color.parseColor("#03DAC6"));

        }

        final TextView r_kun = (TextView) findViewById(selectedTest_kun);
        final TextView r_kun_ch = (TextView) findViewById(r_rabt_id_kun);
        if (r_kun.toString().equals(kun_r)) {
            r_kun.setTypeface(null, Typeface.BOLD);
            r_kun.setTextColor(Color.parseColor("#03DAC6"));
        } else {
            r_kun.setTypeface(null, Typeface.BOLD);
            r_kun.setTextColor(Color.parseColor("#B00020"));

            r_kun_ch.setTypeface(null, Typeface.BOLD);
            r_kun_ch.setTextColor(Color.parseColor("#03DAC6"));


        }

        final TextView r_men = (TextView) findViewById(selectedTest_men);
        final TextView r_men_ch = (TextView) findViewById(r_rabt_id_men);
        if (r_men.toString().equals(on_r)) {
            r_men.setTypeface(null, Typeface.BOLD);
            r_men.setTextColor(Color.parseColor("#03DAC6"));

        } else {
            r_men.setTypeface(null, Typeface.BOLD);
            r_men.setTextColor(Color.parseColor("#B00020"));

            r_men_ch.setTypeface(null, Typeface.BOLD);
            r_men_ch.setTextColor(Color.parseColor("#03DAC6"));

        }

//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                next();
//            }
//        }, 500);
    }

    public void Next(View v) {
//        RadioButton radio_oon = (RadioButton)findViewById(selectedTest_on);
//        radio_oon.setChecked(false);
//        radio_oon = (RadioButton)findViewById(selectedTest_kun);
//        radio_oon.setChecked(false);
//        radio_oon = (RadioButton)findViewById(selectedTest_men);
//        radio_oon.setChecked(false);

        next();
    }


    void next() {
        if (f == r) {
            this.finish();
        }
        else {
            random_word = (int) numbers.get(f);
//            numbers_wrong = new ArrayList();
//            for (int i = 0; i < (r - f); i++) {
//                if (i != f) {
//                    numbers_wrong.add(i);
//                }
//            }
//            //Collections.sort(numbers_wrong);
//            Collections.shuffle(numbers_wrong);
            f++;
            return_val(random_word);
            word_r = word;
            dip_r = dip;
            kun_r = kun;
            on_r = on;

            kanji = (TextView) findViewById(R.id.rand_kanji);
            kanji.setText(word_r);
            Log.d("word=", "[" + Integer.toString(f) + "] " + word_r);
            randRadio_on();
            randRadio_kun();
            randRadio_men();
        }
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

}
