package ece641.March11th.ui;


import java.util.Calendar;



import ece641.March11th.dblayout.ODTDatabaseHelper;
import ece641.March11th.dblayout.abstractODTDatabaseHelper;
import ece641.March11th.entities.Data;
import ece641.March11th.entities.User;
import ece641.March11th.test.BuildTestDatabase;
import ece641.March11th.test.DatabaseTestActivity;
import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class UserLoginActivity extends FragmentActivity implements CreateAccountDialog.NoticeDialogListener {
	ODTDatabaseHelper dbh=new ODTDatabaseHelper(this);
	
private String nametba;
private int agetba;
private String gendertba;
private String loginnametba;
private String passwordtba1;
private String passwordtba2;
private Context context;
	
	public void logIn(View view){
		Intent intentToUserInfoActivity=new Intent(this,DisplayActivity.class);
		//get input loginname and password
		EditText inputUsername = (EditText) findViewById(R.id.inputUsername);
		EditText inputPassword = (EditText) findViewById(R.id.inputPassword);
		String userloginname=inputUsername.getText().toString();
		String password=inputPassword.getText().toString();
		boolean checkloginname=dbh.checkIfLoginNameExist(userloginname);
	if(checkloginname){ 
		boolean checkloginnameandpassword=dbh.checkIfLoginNameMatchPassword(userloginname, password);
		if(checkloginnameandpassword){
			int userID=dbh.getUserID(userloginname);
		intentToUserInfoActivity.putExtra("userID", userID);
		startActivity(intentToUserInfoActivity);}
		
		else{
			AlertDialog.Builder builder = new AlertDialog.Builder(UserLoginActivity.this);
			builder.setMessage("Wrong Password!Please Check your Password!")
		       .setTitle("Wrong Password!");
			
			builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {}
		       });

			
			AlertDialog dialog = builder.create();
			dialog.show();	
		}
		
		}
	
	else{
		
		AlertDialog.Builder builder = new AlertDialog.Builder(UserLoginActivity.this);
		builder.setMessage("User Name Doesn't Exist!Check your User Name!")
	       .setTitle("Wrong User Name!");
		
		builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	        	   
	           }
	       });

		builder.setNegativeButton("Creat Account", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	        	 View v=getWindow().getDecorView().findViewById(android.R.id.content);
	        	 createAccount(v);
	        	  
	        	
	       	    
	           }
	       });
		
		AlertDialog dialog = builder.create();
		dialog.show();
		
		
	}
		}
	
	
	public void createAccount(View view){
		
		
		CreateAccountDialog dialog = new CreateAccountDialog();
		dialog.show(getFragmentManager(), null);	
  
	}
	
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_log_in);
	// the following is hard code for database checking	
	if(	dbh.checkIfLoginNameExist("admin")){}
	else{
		
		BuildTestDatabase bd=new BuildTestDatabase(dbh);
		bd.buildDatabase();
		
	}
	
	
	
	
	
	//Hide the status bar.

    View decorView = getWindow().getDecorView();	
	int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
	decorView.setSystemUiVisibility(uiOptions);
	// Remember that you should never show the action bar if the
	// status bar is hidden, so hide that too if necessary.
	ActionBar actionBar = getActionBar();
	actionBar.hide();

	}


	


	@Override
	public void onDialogNegativeClick(DialogFragment dialog) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onDialogPositiveClick(DialogFragment dialog,String username,int age, String gender,String loginname,String password1,String password2) {
	/*
		nametba=username;
		agetba=age;
		 gendertba=gender;
		 loginnametba=loginname;
		 passwordtba1=password1;
		 passwordtba2=password2;
		 */
		 if(dbh.checkIfLoginNameExist(loginname)|loginname.equals(null)){
				
				AlertDialog.Builder builder = new AlertDialog.Builder(UserLoginActivity.this);
				builder.setMessage("The Login Name Exists! or Login Name is Null!" )
			       .setTitle("Login Name Exist!");
				
				builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {}
			       });

				
				AlertDialog dialog1 = builder.create();
				dialog1.show();	
				
			
				
			}
			else{
				
				if(password1.equals(password2)){
					User usertba=new User(username,age,gender, loginname,password1);
					 dbh.addUser(usertba);
					 Toast.makeText(UserLoginActivity.this, "New Account is Created!", Toast.LENGTH_LONG).show();
					}
			
			
			
			else{
				AlertDialog.Builder builder = new AlertDialog.Builder(UserLoginActivity.this);
				builder.setMessage("Password Doesn't Match!" )
			       .setTitle("Password Doesn't Match!");
				
				builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {}
			       });

				
				AlertDialog dialog1 = builder.create();
				dialog1.show();	
			}}
		
		
		// TODO Auto-generated method stub
		
	}
	
	
public void returnTest(View view){
	Intent intentToDatabaseTestActivity=new Intent(this,DatabaseTestActivity.class);
	startActivity(intentToDatabaseTestActivity);
	
}	
	
	
}