package ece641.March11th.IO;

import ece641.March11th.dblayout.ODTDatabaseHelper;
import ece641.March11th.entities.User;
import ece641.March11th.ui.R;
import ece641.March11th.ui.R.array;
import ece641.March11th.ui.R.id;
import ece641.March11th.ui.R.layout;
import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.os.Build;

public class UpdateUserInfoActivity extends Activity {
	private String updategender;
	private int updateage;
	private int userid;
	private String updatename;
	private String	updateloginname;
	private  String	updatepassword1;
	private  String	updatepassword2;
	private double updateheight;
	private double updateweight;
	ODTDatabaseHelper dbh=new ODTDatabaseHelper(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_update_user_info);
		Intent intent = getIntent();
		userid=intent.getIntExtra("userid", 1);
		
		User user=dbh.getUser(userid);
		final Spinner genderSpinner=(Spinner) findViewById(R.id.updateGender);
	 	 
		 ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.gender_array, android.R.layout.simple_spinner_item);
	 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		genderSpinner.setAdapter(adapter);
		int spinnerPosition = adapter.getPosition(user.getGender());

		//set the default according to value
		genderSpinner.setSelection(spinnerPosition);
   	genderSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent,
					View view, int position, long id) {
				// TODO Auto-generated method stub
				updategender = (String) genderSpinner.getSelectedItem();
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
       });

		
		
		EditText updateUsername = (EditText) findViewById(R.id.editTextUpdateLoginName);
		EditText updatePassword1 = (EditText) findViewById(R.id.editTextUpdatePassword);
		EditText updatePassword2 = (EditText) findViewById(R.id.editTextCheckUpdatePassword);
		EditText updateName = (EditText) findViewById(R.id.editTextUpdateName);
		EditText updateAge = (EditText) findViewById(R.id.editTextUpdateAge);
		EditText updateHeight = (EditText) findViewById(R.id.editTextUpdateHeight);
		EditText updateWeight = (EditText) findViewById(R.id.editTextUpdateWeight);
		updateUsername.setHint(user.getLoginName());
	
		updateName.setHint(user.getUserName());
		updateAge.setHint(Integer.toString(user.getAge()));
		updateHeight.setHint(Double.toString(user.getHeight()));
		updateWeight.setHint(Double.toString(user.getWeight()));
		  
		    
	}
		
	
	public void submitUserUpdate(View view){
		
		EditText updateUsername = (EditText) findViewById(R.id.editTextUpdateLoginName);
		EditText updatePassword1 = (EditText) findViewById(R.id.editTextUpdatePassword);
		EditText updatePassword2 = (EditText) findViewById(R.id.editTextCheckUpdatePassword);
		EditText updateName = (EditText) findViewById(R.id.editTextUpdateName);
		EditText updateAge = (EditText) findViewById(R.id.editTextUpdateAge);
		EditText updateHeight = (EditText) findViewById(R.id.editTextUpdateHeight);
		EditText updateWeight = (EditText) findViewById(R.id.editTextUpdateWeight);
		updateloginname=updateUsername.getText().toString().trim();
    	updatepassword1=updatePassword1.getText().toString().trim();
    	updatepassword2=updatePassword2.getText().toString().trim();
    	
    	
    	updatename=updateName.getText().toString().trim();
		
    if(updateAge.getText().toString().trim().length()==0){}
	else
	{updateage=Integer.parseInt(updateAge.getText().toString().trim());}
    
    
    if(updateHeight.getText().toString().trim().length()==0){}
  	else
  	{updateheight=Double.parseDouble(updateHeight.getText().toString().trim());}
    
    
    if(updateWeight.getText().toString().trim().length()==0){}
  	else
  	{updateweight=Double.parseDouble(updateWeight.getText().toString().trim());}
    
    if(dbh.checkIfLoginNameExist(updateloginname)){
		
		AlertDialog.Builder builder = new AlertDialog.Builder(UpdateUserInfoActivity.this);
		builder.setMessage("The Login Name Exists!" )
	       .setTitle("Login Name Problem!");
		
		builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {}
	       });

		
		AlertDialog dialog1 = builder.create();
		dialog1.show();	
		
	
		
	}
    else if(updatepassword1.equals(updatepassword2)){
		User usertba=new User(userid,updatename,updateage,updategender,updateloginname,updatepassword1,updateheight,updateweight);
		 dbh.updateUser(usertba);
		 Toast.makeText(UpdateUserInfoActivity.this, "User Information is Updated!", Toast.LENGTH_LONG).show();
		}



else{
	AlertDialog.Builder builder = new AlertDialog.Builder(UpdateUserInfoActivity.this);
	builder.setMessage("Password Doesn't Match!" )
      .setTitle("Password Doesn't Match!");
	
	builder.setPositiveButton("Retry", new DialogInterface.OnClickListener(){
          public void onClick(DialogInterface dialog, int id) {}
      });

	
	AlertDialog dialog1 = builder.create();
	dialog1.show();	
}
	
}
	
	
}
