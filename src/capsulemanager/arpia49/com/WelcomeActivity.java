package capsulemanager.arpia49.com;

import java.io.IOException;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
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
	Button buttonList;
	Button buttonTake;
	TextView capsuleName;
	TextView capsuleTotal;
	TextView capsuleDescription;
	TextView capsuleIntensity;
	TextView capsuleSuggestions;
	String lastCapsuleStr;
    DataBaseHelper myDbHelper = new DataBaseHelper(this);
    String name;
    int number;
    
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
		buttonGenerate = (Button) findViewById(R.id.buttonRandom);
		buttonList = (Button) findViewById(R.id.buttonEditStock);
		buttonTake = (Button) findViewById(R.id.buttonTakeIt);

		capsuleName = (TextView) findViewById(R.id.capsuleName);
		capsuleTotal = (TextView) findViewById(R.id.capsuleTotal);
		capsuleDescription = (TextView) findViewById(R.id.capsuleDescription);
		capsuleIntensity = (TextView) findViewById(R.id.capsuleIntensity);
		capsuleSuggestions = (TextView) findViewById(R.id.capsuleSuggestions);
		lastCapsuleStr = sp.getString("lastCapsuleStr", "");
		Cursor cursor2 = myDbHelper.coffeeInfo(lastCapsuleStr);
		if (cursor2.getCount() != 0) {
			if (cursor2.moveToFirst()) {
				capsuleName.setText(this.getString(R.string.lastCoffeeText)
						+" "+ lastCapsuleStr);
				capsuleDescription.setText(this.getString(R.string.capsuleDescription)
						+" "+ cursor2.getString(cursor2.getColumnIndex("description")));
				capsuleIntensity.setText(this.getString(R.string.capsuleIntensity)
						+" "+ cursor2.getInt(cursor2.getColumnIndex("intensity")));
				capsuleTotal.setText(this.getString(R.string.capsuleTotal)
						+" "+ cursor2.getInt(cursor2.getColumnIndex("total")));
				String suggestions = "";
				if (cursor2.getInt(cursor2.getColumnIndex("milk")) == 1) {
					suggestions = "Milk";
				}
				if (cursor2.getInt(cursor2.getColumnIndex("ristretto")) == 1) {
					suggestions += " Ristretto";
				}
				if (cursor2.getInt(cursor2.getColumnIndex("espresso")) == 1) {
					suggestions += " Espresso";
				}
				if (cursor2.getInt(cursor2.getColumnIndex("lungo")) == 1) {
					suggestions += " Lungo";
				}
				capsuleSuggestions.setText(this.getString(R.string.capsuleSuggestions) 
						+ " "+suggestions);

				if (cursor2 != null && !cursor2.isClosed()) {
					cursor2.close();
				}
			}
		} else {
			capsuleName.setText(R.string.welcomeText);
		}

		buttonGenerate.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				buttonTake.setVisibility(0);
			      Cursor cursor = myDbHelper.randomCoffee();
			      String suggestions = "";
			      if (cursor.moveToFirst()) {
				      name = cursor.getString(cursor.getColumnIndex("name"));
				      number = cursor.getInt(cursor.getColumnIndex("total"));
			    	  capsuleName.setText(name);
			    	  capsuleTotal.setText(getString(R.string.capsuleTotal)+" "+number);
			    	  capsuleDescription.setText(getString(R.string.capsuleDescription)+" "+cursor.getString(cursor.getColumnIndex("description")));
			    	  capsuleIntensity.setText(getString(R.string.capsuleIntensity)+" "+cursor.getInt(cursor.getColumnIndex("intensity")));
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
			    	  capsuleSuggestions.setText(getString(R.string.capsuleSuggestions)+" "+suggestions);
						SharedPreferences.Editor editor = sp.edit();
						editor.putString("lastCapsuleStr", cursor.getString(cursor.getColumnIndex("name")));
						editor.commit();
		      	      if (cursor != null && !cursor.isClosed()) {
		      	    	  cursor.close();
			      	  }
			      }
			}
		});

		buttonList.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent myIntent = new Intent(WelcomeActivity.this,
						CapsulesListActivity.class);
				WelcomeActivity.this.startActivity(myIntent);
			}
		});

		buttonTake.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
		        try {
		        	myDbHelper.updateDataBase();
					number--;
					myDbHelper.updateContent(name, number);
			    	capsuleTotal.setText("Available: "+number);
					buttonTake.setVisibility(4);
		        }catch(SQLException sqle){
		        	throw sqle;
		        }
			}
		});
	}
}