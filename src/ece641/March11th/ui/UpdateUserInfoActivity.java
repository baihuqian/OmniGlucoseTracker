package ece641.March11th.ui;

import ece641.March11th.dblayout.ODTDatabaseHelper;
import ece641.March11th.entities.User;
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
	ODTDatabaseHelper dbh=new ODTDatabaseHelper(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_update_user_info);
		Intent intent = getIntent();
		userid=intent.getIntExtra("userID", 1);
		
		User user=dbh.getUser(userid);

		
		
		EditText updateUsername = (EditText) findViewById(R.id.editTextUpdateLoginName);
		EditText updatePassword1 = (EditText) findViewById(R.id.editTextUpdatePassword);
		EditText updatePassword2 = (EditText) findViewById(R.id.editTextCheckUpdatePassword);
		EditText updateName = (EditText) findViewById(R.id.editTextUpdateName);
		EditText updateAge = (EditText) findViewById(R.id.editTextUpdateAge);
		updateUsername.setHint(user.getLoginName());
		updateAge.setHint(user.getAge());
		updateName.setHint(user.getUserName());
		
		   Spinner genderSpinner=(Spinner)findViewById(R.id.chooseGender);
		 	 
	 		 ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
	 		        R.array.gender_array, android.R.layout.simple_spinner_item);
	 	 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	 		genderSpinner.setAdapter(adapter);
	 		int spinnerPosition = adapter.getPosition(user.getGender());
	 		genderSpinner.setSelection(spinnerPosition);
	 		
	 		genderSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
				@Override
				public void onItemSelected(AdapterView<?> parent,
						View view, int position, long id) {
					// TODO Auto-generated method stub
					updategender = parent.getItemAtPosition(position).toString();
				}
				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					// TODO Auto-generated method stub
				}
            });
		    
	}
		
	
	public void submitUserUpdate(View view){
		
		EditText updateUsername = (EditText) findViewById(R.id.editTextUpdateLoginName);
		EditText updatePassword1 = (EditText) findViewById(R.id.editTextUpdatePassword);
		EditText updatePassword2 = (EditText) findViewById(R.id.editTextCheckUpdatePassword);
		EditText updateName = (EditText) findViewById(R.id.editTextUpdateName);
		EditText updateAge = (EditText) findViewById(R.id.editTextUpdateAge);
		
		updateloginname=updateUsername.getText().toString();
    	updatepassword1=updatePassword1.getText().toString();
    	updatepassword2=updatePassword2.getText().toString();
    	
    	updatename=updateName.getText().toString();
		
    if(updateAge.getText().length()==0){updateage=-1;}
	else
	{updateage=Integer.parseInt(updateAge.getText().toString());}
    
    if(dbh.checkIfLoginNameExist(updateloginname)|updateloginname.equals(null)){
		
		AlertDialog.Builder builder = new AlertDialog.Builder(UpdateUserInfoActivity.this);
		builder.setMessage("The Login Name Exists! or Login Name is Null!" )
	       .setTitle("Login Name Exist!");
		
		builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {}
	       });

		
		AlertDialog dialog1 = builder.create();
		dialog1.show();	
		
	
		
	}
    else if(updatepassword1.equals(updatepassword2)){
		User usertba=new User(userid,updatename,updateage,updategender,updateloginname,updatepassword1);
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
