package capsulemanager.arpia49.com;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class CapsulesListActivity extends ListActivity {
	final Random myRandom = new Random();
	SharedPreferences sp = null;
	Button buttonGenerate;
	TextView textGenerateNumber;
	String lastCapsule;
	DataBaseHelper myDbHelper = new DataBaseHelper(this);
    
    //Option Menu
	public static final int ACT_ADD_CAPSULE = 1;
	static final private int ADD_CAPSULE = Menu.FIRST;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		populateList();
	}
	
    protected void onActivityResult(int requestCode, int resultCode,
            Intent data) {
    	populateList();
    }
	
	public void populateList(){
		try {
			myDbHelper.openDataBase();
			final List<String> list = new ArrayList<String>();

			Cursor cursor = myDbHelper.allCoffee();
			if (cursor.moveToFirst()) {

				do {
					list.add(cursor.getString(0));
				} while (cursor.moveToNext());
			}
			if (cursor != null && !cursor.isClosed()) {
				cursor.close();
			}

			setListAdapter(new ArrayAdapter<String>(this,
					R.layout.capsules_list, list));

			ListView lv = getListView();
			lv.setTextFilterEnabled(true);

			lv.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {

					Intent myIntent = new Intent(CapsulesListActivity.this,
							EditCapsuleActivity.class);
					myIntent.putExtra("Name", list.get(position));
					CapsulesListActivity.this.startActivity(myIntent);
				}
			});
		} catch (SQLException sqle) {
			throw sqle;
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);

		// Create and add new menu items.
		MenuItem itemAdd = menu.add(0, ADD_CAPSULE, Menu.NONE,
				R.string.actAdd);

		// Assign icons
		itemAdd.setIcon(R.drawable.add);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
			case (ADD_CAPSULE): {
				Intent myIntent = new Intent(CapsulesListActivity.this,
						AddCapsuleActivity.class);
				CapsulesListActivity.this.startActivityForResult(myIntent, 0);
				return true;
			}
		}
		return false;
	}
}