package capsulemanager.arpia49.com;

import java.util.Random;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.TextView;

public class EditCapsuleActivity extends Activity {
	final Random myRandom = new Random();
	SharedPreferences sp = null;
	Button buttonAdd;
	Button buttonDelete;
	final String[] capsulesArray = { "Ristretto", "Livanto", "Cosi",
			"Arpeggio", "Capriccio", "Roma", "Volluto", "Indriya", "Rosabaya",
			"Dulsão", "Fortissio Lungo", "Vivalto Lungo", "Finezzo Lungo",
			"Decaffeinato Intenso", "Decaffeinato Lungo", "Decaffeinato" };
	TextView textGenerateNumber;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.capsules_edition);
		sp = PreferenceManager.getDefaultSharedPreferences(this);
		buttonAdd = (Button) findViewById(R.id.add);
		buttonDelete = (Button) findViewById(R.id.delete);

	}
}