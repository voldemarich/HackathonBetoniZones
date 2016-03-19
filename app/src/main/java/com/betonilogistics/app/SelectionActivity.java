package com.betonilogistics.app;

import android.content.res.AssetManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.betonilogistics.app.locationtools.FriendlyLocationListener;
import com.betonilogistics.app.locationtools.Util;

/**
 * Created by voldemarich on 18.3.2016.
 */
public class SelectionActivity extends AppCompatActivity{


    String[] languages = { "C","C++","Java","C#","PHP","JavaScript","jQuery","AJAX","JSON" };

//    private  EditText etTest;
//    private ListPopupWindow lpw;
//    private String[] list;

    FriendlyLocationListener fll;

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
        fll = new FriendlyLocationListener(this);
        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 600, 3, fll);
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 500, 3, fll);
        Button chkgps = (Button) findViewById(R.id.checker);

        chkgps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String a = "1";
//                    if(Util.isCoordInPredefArea(LocationConstants.getPredefCoordinates(), fll.getMyposition())){
//                        a = "GOT TO THE AREA!!!";
//                    }
//                    else {
//                        a = "DIDNT GET TO THE AREA!!!";
//                    }
                    Toast.makeText(SelectionActivity.this, a, Toast.LENGTH_LONG).show();
                }
                catch (Exception e){
                    Toast.makeText(SelectionActivity.this, "No gps connection", Toast.LENGTH_LONG).show();
                }
            }
        });

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
