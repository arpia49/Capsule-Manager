package capsulemanager.arpia49.com;

import java.util.Random;

import android.app.Activity;
import android.content.SharedPreferences;
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
	// Button buttonList;
	final String[] capsulesArray = { "Ristretto", "Livanto", "Cosi",
			"Arpeggio", "Capriccio", "Roma", "Volluto", "Indriya", "Rosabaya",
			"Dulsão", "Fortissio Lungo", "Vivalto Lungo", "Finezzo Lungo",
			"Decaffeinato Intenso", "Decaffeinato Lungo", "Decaffeinato" };
	TextView randomCapsule;
	String lastCapsule;
	String showText;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		sp = PreferenceManager.getDefaultSharedPreferences(this);
		buttonGenerate = (Button) findViewById(R.id.generate);
		// buttonList = (Button) findViewById(R.id.listCapsules);

		final int[] anArray = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
				14, 15 };
		randomCapsule = (TextView) findViewById(R.id.randomText);
		lastCapsule = capsulesArray[anArray[sp.getInt("lastCapsule", 0)]];
		if (sp.getInt("lastCapsule", 0) != 0) {
			randomCapsule.setText("Your last coffee was - " + lastCapsule + " -");
		} else {
			randomCapsule
					.setText("Welcome, this is your first time, just press the button!");
		}

		buttonGenerate.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				int capsuleNumber = myRandom.nextInt(anArray.length);
				lastCapsule = capsulesArray[anArray[capsuleNumber]];
				switch (capsuleNumber) {
				case 3:
				case 7:
					showText = "Take an " + lastCapsule + "!";
					break;
				default:
					showText = "Take a " + lastCapsule + "!";
					break;
				}
				randomCapsule.setText(showText);
				SharedPreferences.Editor editor = sp.edit();
				editor.putInt("lastCapsule", capsuleNumber);
				editor.commit();
			}
		});

		// buttonList.setOnClickListener(new OnClickListener() {
		//
		// public void onClick(View v) {
		// Intent myIntent = new Intent(WelcomeActivity.this,
		// CapsulesListActivity.class);
		// WelcomeActivity.this.startActivity(myIntent);
		// }
		// });
	}
}