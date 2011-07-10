package capsulemanager.arpia49.com;

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

public class EditCapsuleActivity extends Activity {
	final Random myRandom = new Random();
	SharedPreferences sp = null;
	Button buttonAdd;
	Button buttonDelete;
	TextView capsule;
	DataBaseHelper myDbHelper = new DataBaseHelper(this);
	int number = 0;
	String name = "";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.capsules_edition);
		sp = PreferenceManager.getDefaultSharedPreferences(this);
		buttonAdd = (Button) findViewById(R.id.add);
		buttonDelete = (Button) findViewById(R.id.delete);
		capsule = (TextView) findViewById(R.id.capsule_name);
		name = getIntent().getExtras().getString("Name");

		try {
			myDbHelper.updateDataBase();
			Cursor cursor = myDbHelper.coffeeInfo((String) getIntent().getExtras().getString("Name"));
			if (cursor.moveToFirst()) {
				do {
					number = cursor.getInt(cursor.getColumnIndex("total"));
					capsule.setText(name + ": "+ number);
				} while (cursor.moveToNext());
			}
			if (cursor != null && !cursor.isClosed()) {
				cursor.close();
			}
		} catch (SQLException sqle) {
			throw sqle;
		}
		
		buttonAdd.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				number++;
				myDbHelper.updateContent(name, number);
				capsule.setText(name + ": "+ number);
			}
		});
		
		buttonDelete.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				if(number >0){
					number--;
					myDbHelper.updateContent(name, number);
					capsule.setText(name + ": "+ number);
				}
			}
		});
	}
}