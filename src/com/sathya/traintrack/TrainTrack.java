package com.sathya.traintrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

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
			"Kasturba Nagar",
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
        
        
        AutoCompleteTextView getSourceStation = (AutoCompleteTextView) findViewById (R.id.enterSource);
        AutoCompleteTextView getDestinationStation= (AutoCompleteTextView) findViewById (R.id.enterDestination);

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
        
        /*
          Button homeButton = (Button) findViewById(R.id.homebutton);
         
        
        homeButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				insertData();
			}
		});
        */
        Button findButton1 = (Button) findViewById(R.id.findButton);
        
        findButton1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				queryDb();
				
			}
		});		
    }
    
    /*
      private void insertData(){
     
    	Intent insertDataEntry = new Intent(this,InsertData.class);
    	startActivity(insertDataEntry);
    }
    */
    
    private void queryDb(){
	
    	Intent queryDatabase = new Intent(this,FindTime.class);
    	
    	Bundle bundle = new Bundle();
    	bundle.putString("source",source);
    	bundle.putString("destination",destination);
    	queryDatabase.putExtras(bundle);
    	startActivityForResult(queryDatabase,0);
    	
    }
}