package capsulemanager.arpia49.com;

import android.app.Activity;
import android.database.SQLException;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class AddCapsuleActivity extends Activity {

	Button btAdd;
	EditText etName;
	EditText etDescription;
	EditText etTotal;
	EditText etIntensity;
	CheckBox cbMilk;
	CheckBox cbRistretto;
	CheckBox cbEspresso;
	CheckBox cbLungo;
    DataBaseHelper myDbHelper = new DataBaseHelper(this);
    Toast toast;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.capsules_add);
		btAdd = (Button) findViewById(R.id.btAdd);
		etName = (EditText) findViewById(R.id.etName);
		etDescription = (EditText) findViewById(R.id.etDescription);
		etTotal = (EditText) findViewById(R.id.etTotal);
		etIntensity = (EditText) findViewById(R.id.etIntensity);
		cbMilk = (CheckBox) findViewById(R.id.cbMilk);
		cbRistretto = (CheckBox) findViewById(R.id.cbRistretto);
		cbEspresso = (CheckBox) findViewById(R.id.cbEspresso);
		cbLungo = (CheckBox) findViewById(R.id.cbLungo);
	
		btAdd.setOnClickListener(new OnClickListener() {
	
			public void onClick(View v) {
				if (checkForm()){
					if(checkDB()){
						String query = etTotal.getText()+","+cbMilk.isChecked()+", "
						+cbLungo.isChecked()+", "+cbEspresso.isChecked()+", "
						+cbRistretto.isChecked()+", "+etIntensity.getText()+", "
						+"\'#000000\'"+", "+"9"+", \'"
						+etDescription.getText()+"\', \'"+etName.getText()+"\'";
						query=query.replace("true", "1");
						query=query.replace("false", "0");
				        try {
				        	myDbHelper.updateDataBase();
				        	myDbHelper.addCoffee(query);
				        }catch(SQLException sqle){
				        	throw sqle;
				        }
						toast = Toast.makeText(getApplicationContext(),
								R.string.txtCapsuleAdded, Toast.LENGTH_SHORT);
						toast.show();
					}else{
						toast = Toast.makeText(getApplicationContext(),
								R.string.txtCapsuleExists, Toast.LENGTH_SHORT);
						toast.show();
					}
				}else{
					toast = Toast.makeText(getApplicationContext(),
							R.string.txtMissingData, Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		});
		
		
		etName.addTextChangedListener(new TextWatcher() { 
            public void  afterTextChanged (Editable s){ 
                if(checkForm()){
                	btAdd.setEnabled(true);
                }else{
                	btAdd.setEnabled(false);
                }
            }

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
			};
		});
		
		etDescription.addTextChangedListener(new TextWatcher() { 
            public void  afterTextChanged (Editable s){ 
                if(checkForm()){
                	btAdd.setEnabled(true);
                }else{
                	btAdd.setEnabled(false);
                }
            }

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
			};
		});
		
		etIntensity.addTextChangedListener(new TextWatcher() { 
            public void  afterTextChanged (Editable s){ 
                if(checkForm()){
                	btAdd.setEnabled(true);
                }else{
                	btAdd.setEnabled(false);
                }
            }

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
			};
		});
		
		etTotal.addTextChangedListener(new TextWatcher() { 
            public void  afterTextChanged (Editable s){ 
                if(checkForm()){
                	btAdd.setEnabled(true);
                }else{
                	btAdd.setEnabled(false);
                }
            }

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
			};
		});
	}
	
	boolean checkForm(){
		Boolean result = true;
		if(etName.getText().length()==0
				|| etDescription.getText().length()==0
				|| etTotal.getText().length()==0
				|| etIntensity.getText().length()==0){
			result = false;
		}
		return result;
	}
	

	boolean checkDB(){
		Boolean result = true;
		String query;
		query = "name = '"+etName.getText()+"'";
        try {
        	myDbHelper.openDataBase();
        	if(!myDbHelper.checkContent(query)){
        		result = false;
        	}
        }catch(SQLException sqle){
        	throw sqle;
        }
		return result;			
	}
}