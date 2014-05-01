package ece641.March11th.IO;

import ece641.March11th.dblayout.ODTDatabaseHelper;
import ece641.March11th.entities.Contact;
import ece641.March11th.ui.R;
import ece641.March11th.ui.UserLoginActivity;
import ece641.March11th.ui.R.id;
import ece641.March11th.ui.R.layout;
import ece641.March11th.ui.R.menu;
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
import android.widget.AdapterView.OnItemSelectedListener;
import android.os.Build;

public class AddContactActivity extends Activity {
	
	String contactname;
	String contactnumber;
	String contactemail;
	String contacttype;
	int userid;
	ODTDatabaseHelper dbh=new ODTDatabaseHelper(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_contact);
		Intent intent=getIntent();
		userid=intent.getIntExtra("userid", 1);
		
		final Spinner contacttypeSpinner=(Spinner) findViewById(R.id.spinnerAddContactType);
	 	 
		 ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.contact_type_array, android.R.layout.simple_spinner_item);
	 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	 contacttypeSpinner.setAdapter(adapter);
		
	 contacttypeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent,
					View view, int position, long id) {
				// TODO Auto-generated method stub
				contacttype = (String) contacttypeSpinner.getSelectedItem();
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
      });
	}

public void	addContact(View view){
	EditText contactnameText=(EditText) findViewById(R.id.editTextAddContactName); 
	EditText contactnumberText=(EditText) findViewById(R.id.editTextAddContactNumber); 
	EditText contactemailText=(EditText) findViewById(R.id.editTextAddContactEmail); 
	
	if(contactnameText.getText().toString().trim().length()==0){
		
		AlertDialog.Builder builder = new AlertDialog.Builder(AddContactActivity .this);
		builder.setMessage("NO Contact Name!")
		.setTitle("NO Contact Name!");

		builder.setPositiveButton("Return", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {}
		});


		AlertDialog dialog = builder.create();
		dialog.show();	
		
	}
	else{
			contactname=contactnameText.getText().toString();
	
	
	if(contactnumberText.getText().toString().trim().length()==0){}
	
		else{
			contactnumber=contactnumberText.getText().toString();
	}
	
	if(contactemailText.getText().toString().trim().length()==0){}
	
	else{
		contactemail=contactemailText.getText().toString();
}
	
	}
	Contact contact=new Contact(contactname,contactnumber,contactemail,contacttype,userid);
	
	dbh.addContact(contact);
}
}
