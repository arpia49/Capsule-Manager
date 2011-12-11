package capsulemanager.arpia49.com;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;

public class CapsuleLauncherActivity extends Activity {
	private PendingIntent intent;
	private NfcAdapter adapter;

	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		adapter = NfcAdapter.getDefaultAdapter(this);
		intent = PendingIntent.getActivity(this, 0,
				new Intent(this, getClass())
						.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

		Intent myIntent = new Intent(CapsuleLauncherActivity.this,
				WelcomeActivity.class);
		CapsuleLauncherActivity.this.startActivity(myIntent);

	}

	@Override
	protected void onPause() {
		super.onPause();
		if (NfcAdapter.getDefaultAdapter(this) != null)
			NfcAdapter.getDefaultAdapter(this).disableForegroundDispatch(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		adapter.enableForegroundDispatch(this, intent, null, null);

	}
}