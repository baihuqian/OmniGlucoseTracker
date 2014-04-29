package ece641.March11th.ui;

import ece641.March11th.dblayout.ODTDatabaseHelper;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.os.Build;

public class UpdateUserInfoActivity extends Activity {
	private String gender;
	private int age;
	ODTDatabaseHelper dbh=new ODTDatabaseHelper(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_update_user_info);
		
		  final Spinner genderSpinner=(Spinner)findViewById(R.id.chooseGender);
		 	 
	 		 ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
	 		        R.array.gender_array, android.R.layout.simple_spinner_item);
	 	 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	 		genderSpinner.setAdapter(adapter);
	 		
	 		genderSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
				@Override
				public void onItemSelected(AdapterView<?> parent,
						View view, int position, long id) {
					// TODO Auto-generated method stub
					gender = parent.getItemAtPosition(position).toString();
				}
				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					// TODO Auto-generated method stub
				}
            });
		    
	}
		
	
	public void submitUserUpdate(View view){
		
		EditText updateUsername = (EditText) findViewById(R.id.editTextUpdateUsername);
		EditText updatePassword1 = (EditText) findViewById(R.id.editTextUpdatePassword);
		EditText updatePassword2 = (EditText) findViewById(R.id.editTextCheckUpdatePassword);
		EditText updateName = (EditText) findViewById(R.id.editTextUpdateName);
		EditText updateAge = (EditText) findViewById(R.id.editTextUpdateAge);
		
	String	updateloginname=updateUsername.getText().toString();
    String	updatepassword1=updatePassword1.getText().toString();
    String	updatepassword2=updatePassword2.getText().toString();
    	
    String	updatename=updateName.getText().toString();
		
    if(updateAge.getText().length()==0){age=-1;}
	else
	{age=Integer.parseInt(updateAge.getText().toString());}
	 	   
	
    
    
    
    
    
    
    
    
		
	}
}
