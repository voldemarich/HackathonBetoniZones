package com.voldemarich.betonilogistics.app;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListPopupWindow;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * Created by voldemarich on 18.3.2016.
 */
public class SelectionActivity extends AppCompatActivity implements OnTouchListener,
        OnItemClickListener {


    String[] languages = { "C","C++","Java","C#","PHP","JavaScript","jQuery","AJAX","JSON" };;

//    private  EditText etTest;
//    private ListPopupWindow lpw;
//    private String[] list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        String item = list[position];
//        etTest.setText(item);
//        lpw.dismiss();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int DRAWABLE_RIGHT = 2;

//        if (event.getAction() == MotionEvent.ACTION_UP) {
//           // if (event.getX() >= (v.getWidth() - ((EditText) v)
//             //       .getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
//                lpw.show();
//                return true;
//            }
//     //   }
        return false;
    }


}
