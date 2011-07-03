package capsulemanager.arpia49.com;

import java.io.IOException;
import java.util.Random;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeActivity extends Activity {
	final Random myRandom = new Random();
	SharedPreferences sp = null;
	Button buttonGenerate;
//	Button buttonList;
	TextView capsuleName;
	TextView capsuleDescription;
	TextView capsuleIntensity;
	TextView capsuleSuggestions;
	String lastCapsuleStr;
    DataBaseHelper myDbHelper = new DataBaseHelper(this);
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
        try {
        	myDbHelper.createDataBase();
        }
        catch (IOException ioe) {
        	throw new Error("Unable to create database");
        }
        try {
        	myDbHelper.openDataBase();
        }catch(SQLException sqle){
        	throw sqle;
        }
		sp = PreferenceManager.getDefaultSharedPreferences(this);
		buttonGenerate = (Button) findViewById(R.id.generate);
//		buttonList = (Button) findViewById(R.id.listCapsules);

		capsuleName = (TextView) findViewById(R.id.capsuleName);
		capsuleDescription = (TextView) findViewById(R.id.capsuleDescription);
		capsuleIntensity = (TextView) findViewById(R.id.capsuleIntensity);
		capsuleSuggestions = (TextView) findViewById(R.id.capsuleSuggestions);
		lastCapsuleStr = sp.getString("lastCapsuleStr", "");
		Cursor cursor2 = myDbHelper.coffeeInfo(lastCapsuleStr);
		if (cursor2.getCount() !=0) {
		      if (cursor2.moveToFirst()) {

					capsuleName.setText("Your last coffee was - " + lastCapsuleStr + " -");
					  capsuleDescription.setText("Description: "+cursor2.getString(cursor2.getColumnIndex("description")));
					  capsuleIntensity.setText("Intensity: "+cursor2.getInt(cursor2.getColumnIndex("intensity")));
					  String suggestions = "";
					if(cursor2.getInt(cursor2.getColumnIndex("milk"))==1){
					  suggestions = "Milk";
					  }
					  if(cursor2.getInt(cursor2.getColumnIndex("ristretto"))==1){
					  suggestions += " Ristretto";
					  }
					  if(cursor2.getInt(cursor2.getColumnIndex("espresso"))==1){
					  suggestions += " Espresso";
					  }
					  if(cursor2.getInt(cursor2.getColumnIndex("lungo"))==1){
					  suggestions += " Lungo";
					  }
					  capsuleSuggestions.setText("Suggestions: "+suggestions);		    	  
		    	  
		    	  
		    	  
		    	  
		    	  
	      	      if (cursor2 != null && !cursor2.isClosed()) {
	      	    	  cursor2.close();
		      	  }
		      }
				} else {
			capsuleName
					.setText("Welcome, this is your first time, just press the button!");
		}

		buttonGenerate.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
			      Cursor cursor = myDbHelper.randomCoffee();
			      String suggestions = "";
			      if (cursor.moveToFirst()) {
			    	  capsuleName.setText(cursor.getString(cursor.getColumnIndex("name")));
			    	  capsuleDescription.setText("Description: "+cursor.getString(cursor.getColumnIndex("description")));
			    	  capsuleIntensity.setText("Intensity: "+cursor.getInt(cursor.getColumnIndex("intensity")));
			    	  if(cursor.getInt(cursor.getColumnIndex("milk"))==1){
			    		  suggestions = "Milk";
			    	  }
			    	  if(cursor.getInt(cursor.getColumnIndex("ristretto"))==1){
			    		  suggestions += " Ristretto";
			    	  }
			    	  if(cursor.getInt(cursor.getColumnIndex("espresso"))==1){
			    		  suggestions += " Espresso";
			    	  }
			    	  if(cursor.getInt(cursor.getColumnIndex("lungo"))==1){
			    		  suggestions += " Lungo";
			    	  }
			    	  capsuleSuggestions.setText("Suggestions: "+suggestions);
						SharedPreferences.Editor editor = sp.edit();
						editor.putString("lastCapsuleStr", cursor.getString(cursor.getColumnIndex("name")));
						editor.commit();
		      	      if (cursor != null && !cursor.isClosed()) {
		      	    	  cursor.close();
			      	  }
			      }
			}
		});

//		buttonList.setOnClickListener(new OnClickListener() {
//
//			public void onClick(View v) {
//				Intent myIntent = new Intent(WelcomeActivity.this,
//						CapsulesListActivity.class);
//				WelcomeActivity.this.startActivity(myIntent);
//			}
//		});
	}
}