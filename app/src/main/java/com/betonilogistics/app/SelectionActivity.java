package com.betonilogistics.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

/**
 * Created by voldemarich on 18.3.2016.
 */
public class SelectionActivity extends AppCompatActivity{


    String[] languages = new String[20];//{ "A","C++","Java","C#","PHP","JavaScript","jQuery","AJAX","JSON" };

//    private  EditText etTest;
//    private ListPopupWindow lpw;
//    private String[] list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        for (int i = 0; i < 4; i++) {
            languages[i] = "A"+i;
            i = i * i +1;
            languages[i] = "B"+i;
            i = i * i +2;
            languages[i] = "C"+i;
            i = i * i +3;
            languages[i] = "D"+i;
        }

//        etTest = (EditText) findViewById(R.id.input_id);
//        etTest.setOnTouchListener(this);
//
//        list = new String[] { "item1", "item2", "item3", "item4" };
//        lpw = new ListPopupWindow(this);
//        lpw.setAdapter(new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, list));
//        lpw.setAnchorView(etTest);
//        lpw.setModal(true);
//        lpw.setOnItemClickListener(this);

        //Create Array Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, languages);
        //Find TextView control
        AutoCompleteTextView acTextView = (AutoCompleteTextView) findViewById(R.id.input_position);
        //Set the number of characters the user must type before the drop down list is shown
        acTextView.setThreshold(1);
//        acTextView.setTextColor(Color.RED);
        //Set the adapter
        acTextView.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_startup, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
