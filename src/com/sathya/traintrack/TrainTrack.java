package com.sathya.traintrack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class TrainTrack extends Activity {
    
	public String source,destination;
	
	/**
	 * List of all the various train stations
	 */
	private final String[] STATIONS={
			"Velachery",
			"Perungudi",
			"Taramani",
			"Thiruvanmiyur",
			"Indra Nagar",
			"Kasturbai Nagar",
			"Kotturpuram",
			"Greenways Road",
			"Mandaveli",
			"Tirumailai",
			"Light House",
			"Triplicane",
			"Chepauk",
			"Chindatripet",
			"Park Town",
			"Fort",
			"Beach"			
	};
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        /**
         * Create Adapter for list of STATIONS
         */
        final ArrayAdapter<String> stationsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,STATIONS);
        
        
        final AutoCompleteTextView getSourceStation = (AutoCompleteTextView) findViewById (R.id.enterSource);
        final AutoCompleteTextView getDestinationStation= (AutoCompleteTextView) findViewById (R.id.enterDestination);

        getSourceStation.setThreshold(1);
        getSourceStation.setAdapter(stationsAdapter);
        
        getDestinationStation.setThreshold(1);
        getDestinationStation.setAdapter(stationsAdapter);
        

        getSourceStation.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				source = stationsAdapter.getItem(arg2);
			
			}
				
			});
       
        getDestinationStation.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
			
				destination = stationsAdapter.getItem(arg2);
				
			}
				
			});
        
        
        Button homeButton = (Button) findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				findFav();
				getSourceStation.setText(source);
				getDestinationStation.setText(destination);
			
			}
		});
        
        Button favButton = (Button) findViewById(R.id.favButton);
        favButton.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){
        		writeFav();
        		showToast();
        		}
        });
    	  
         
  
        Button findButton1 = (Button) findViewById(R.id.findButton);
        
        findButton1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				queryDb();
				
			}
		});		
    }
    
    public void findFav(){
    	SharedPreferences prefs = getSharedPreferences("locationPref", MODE_PRIVATE);
    	source = prefs.getString("source", ""); 
    	destination = prefs.getString("destination", "");
    }
    
    public void writeFav(){

    	SharedPreferences prefs = getSharedPreferences("locationPref", MODE_PRIVATE);
    	Editor mEditor = prefs.edit(); mEditor.putString("source",source); 
    	mEditor.putString("destination",destination);
    	mEditor.commit();

    }
    
    public void showToast() {
		Toast.makeText(this, "Favourites have been set", Toast.LENGTH_SHORT).show();
		
	}
    
    private void queryDb(){
	
    	Intent queryDatabase = new Intent(this,FindTime.class);
    	
    	Bundle bundle = new Bundle();
    	bundle.putString("source",source);
    	bundle.putString("destination",destination);
    	queryDatabase.putExtras(bundle);
    	startActivityForResult(queryDatabase,0);
    	
    }
}