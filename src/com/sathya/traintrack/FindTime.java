package com.sathya.traintrack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import android.app.Activity;
import android.database.SQLException;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class FindTime extends Activity {
	public String source,destination;
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.findtime);
	        
	        Bundle bundle = getIntent().getExtras();
	         
	        source = bundle.getString("source");
	        destination = bundle.getString("destination");
	        
	        TextView dispSource = (TextView) findViewById(R.id.dispsource);
	        TextView dispDest = (TextView) findViewById(R.id.dispdest);
	        
	        dispSource.setText(source);
	        dispDest.setText(destination);
	        
	        /*
	         * Find the directory for the SD Card using the API
	         */
	        File sdcard = Environment.getExternalStorageDirectory();

	        /*
	         * Get the text file
	         */
	        File file = new File(sdcard,source+".txt");

	        /*
	         * Read text from file
	         */
	        StringBuilder text = new StringBuilder();

	         try {
	        	 BufferedReader br = new BufferedReader(new FileReader(file));
	        	 String line;
	        	 

	        	 while ((line = br.readLine()) != null) {
	        		 text.append(line);
	        		 text.append('\n');
	        	 }
	         }
	         catch (IOException e) {
	        	 Log.w("ErrorReadFromFile","Error while reading from text file : " + e.getMessage());
	         }

	       //Find the view by its id
	       TextView tv = (TextView)findViewById(R.id.showTime);

	       //Set the text
	       tv.setText(text);

      
  }
	 
	 
}