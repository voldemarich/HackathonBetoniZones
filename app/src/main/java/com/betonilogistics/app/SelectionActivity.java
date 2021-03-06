package com.betonilogistics.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.betonilogistics.app.locationtools.Coordinate;
import com.betonilogistics.app.locationtools.FriendlyLocationListener;
import com.betonilogistics.app.locationtools.LocationsXmlParser;
import com.betonilogistics.app.locationtools.MapsActivity;
import com.betonilogistics.app.locationtools.Util;
import com.betonilogistics.app.locationtools.Zone;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by voldemarich on 18.3.2016.
 */
public class SelectionActivity extends AppCompatActivity{


    String[] languages = new String[20];
    AutoCompleteTextView acTextView;
    EditText idEditView;
    TextInputLayout idInput;
    TextInputLayout posInput;
    Zone rootzone;
    ArrayList<Zone> arz;


    FriendlyLocationListener fll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_selection);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        ProgressBar progressBar = new ProgressBar(this);
        progressBar.setVisibility(View.GONE);
        progressBar.setIndeterminate(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(progressBar);

        idEditView = (EditText) findViewById(R.id.input_id);

        idInput = (TextInputLayout) findViewById(R.id.input_layout_id);
        idInput.setErrorEnabled(true);
        //idInput.setError(getString(R.string.error));
        posInput = (TextInputLayout) findViewById(R.id.input_layout_position);
        posInput.setErrorEnabled(true);
        //posInput.setError(getString(R.string.error));

        languages = readTxt();

        //Create Array Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, languages);
        //Find TextView control
        acTextView = (AutoCompleteTextView) findViewById(R.id.input_position);
        //Set the number of characters the user must type before the drop down list is shown
        acTextView.setThreshold(1);
//        acTextView.setTextColor(Color.RED);
        //Set the adapter
        acTextView.setAdapter(adapter);

        Button btnMap = (Button) findViewById(R.id.buttonmap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectionActivity.this, MapsActivity.class);
                startActivityForResult(intent, 1, null);
            }
        });


        fll = new FriendlyLocationListener(this);
        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 600, 3, fll);
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 500, 3, fll);
        onLocationSearch(true);




    }

    @Override
    public void setSupportProgressBarIndeterminateVisibility(boolean visible) {
        getSupportActionBar().getCustomView().setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    private void onLocationSearch(boolean isSearching) {
        if(isSearching)
            getSupportActionBar().setTitle("Searching location...");
        else getSupportActionBar().setTitle(R.string.activity_location);
        // Turn it on or off
        setSupportProgressBarIndeterminateVisibility(isSearching);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.selection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_done) {

            checkLocation();
            boolean cancel = false;
            // Reset errors.
            acTextView.setError(null);
            idEditView.setError(null);
            posInput.setError(null);
            idInput.setError(null);

            if(!isPositionValid()) {
                acTextView.setError(getString(R.string.error));
                posInput.setError(getString(R.string.error));
                cancel = true;
            }
            if(!isIdValid()) {
                idEditView.setError(getString(R.string.error));
                idInput.setError(getString(R.string.error));
                cancel = true;
            }
            if(cancel) return true;

            new AlertDialog.Builder(this)
                    .setTitle("Entry save")
                    .setMessage("Item with ID "+idEditView.getText()+" will be registered in zone "+acTextView.getText())
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with save
                            Toast.makeText(getBaseContext(), "Item "+idEditView.getText()+"is registered in zone "+acTextView.getText(),
                                    Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    })
                    .setNeutralButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean isIdValid() {
        if(idEditView.getText() == null || idEditView.getText().toString().isEmpty())
            return  false;
        return  true;
    }

    private boolean isPositionValid() {
        return Arrays.asList(languages).contains(acTextView.getText().toString().toUpperCase());
    }

    private String[] readTxt() {

        AssetManager am = getAssets();

        InputStream inputStream = null;
//     InputStream inputStream = getResources().openRawResource(R.raw.internals);
        System.out.println(inputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int i;
        try {
            inputStream = am.open("locations.txt");
            i = inputStream.read();
            while (i != -1) {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            Toast.makeText(getBaseContext(), "Error reading locations!", Toast.LENGTH_SHORT).show();
        }

        return byteArrayOutputStream.toString().split(",");
    }


    private void checkLocation() {
        try {
            LocationsXmlParser lxp = new LocationsXmlParser(getAssets().open("zones.xml"));
            rootzone = lxp.getRootZone();
            arz = lxp.getStorageZones();
                    try{
                        Location loc = fll.getMyposition();
                        Coordinate c = new Coordinate(loc.getLatitude(), loc.getLongitude());
                        if(!rootzone.cointains(c)){
                            String a = "You're";
                            Toast.makeText(SelectionActivity.this, a, Toast.LENGTH_LONG).show();
                        }
                        else {
                            FragmentManager manager = getSupportFragmentManager();

                            ListQueryFragment dialog = new ListQueryFragment();
                            Bundle args = new Bundle();
                            args.putStringArray("zones", Util.getZonesByLocation(arz, loc));
                            dialog.setArguments(args);
                            dialog.show(manager, "All items");
                        }
                    }
                    catch (Exception e){
                        Toast.makeText(SelectionActivity.this, "No gps connection", Toast.LENGTH_LONG).show();
                    }
        }
        catch (Exception e){
            Toast.makeText(this, "Smth wrong with xml with locations...", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }


}
