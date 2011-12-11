package capsulemanager.arpia49.com;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CreateTagsActivity extends Activity {
	Button buttonStartTag;
	TextView tv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.capsules_tags);
		buttonStartTag = (Button) findViewById(R.id.bt_tagStart);

		buttonStartTag.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), getString(R.string.tempCreateToast),
						Toast.LENGTH_SHORT).show();
			}
		});
	}
}