package capsulemanager.arpia49.com;

import java.util.Random;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
	final String[] capsulesArray = { "Ristretto", "Livanto", "Cosi",
			"Arpeggio", "Capriccio", "Roma", "Volluto", "Indriya",
			"Rosabaya", "Dulsão", "Fortissio Lungo", "Vivalto Lungo",
			"Finezzo Lungo", "Decaffeinato Intenso", "Decaffeinato Lungo",
			"Decaffeinato" };
	TextView textGenerateNumber;
	String lastCapsule;

	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);

	  setListAdapter(new ArrayAdapter<String>(this, R.layout.capsules_list, capsulesArray));

	  ListView lv = getListView();
	  lv.setTextFilterEnabled(true);

	  lv.setOnItemClickListener(new OnItemClickListener() {
	    public void onItemClick(AdapterView<?> parent, View view,
	        int position, long id) {

			Intent myIntent = new Intent(CapsulesListActivity.this,
					EditCapsuleActivity.class);
			CapsulesListActivity.this.startActivity(myIntent);
	    }
	  });
	}
}