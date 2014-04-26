package ece641.March11th.ui;


import java.util.Calendar;

import ece641.March11th.dblayout.ODTDatabaseHelper;
import ece641.March11th.dblayout.abstractODTDatabaseHelper;
import ece641.March11th.entities.Data;
import ece641.March11th.test.BuildTestDatabase;
import ece641.March11th.test.DatabaseTestActivity;
import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
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
import android.os.Build;

public class UserLoginActivity extends FragmentActivity implements CreateAccountDialog.NoticeDialogListener {
	ODTDatabaseHelper dbh=new ODTDatabaseHelper(this);
	

	
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
		
		
		//open the dialoge
		CreateAccountDialog dialog = new CreateAccountDialog();
		dialog.show(getFragmentManager(), null);	
     //   dbh.addUser(dialog.name, dialog.age, dialog.gender, dialog.loginname, dialog.password1);

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
		/*
		//add test database
			dbh.addUser("admin", 26, "Male", "admin", "admin");
			dbh.addUser("abc", 12, "Male", "abc11", "abc111");
			dbh.addUser("abc2", 15, "Female", "abc22", "abc22");
			dbh.addUser("abc3", 18, "Female", "abc33", "abc333");
			int adminid=dbh.getUserID("admin");
			Calendar date1=Calendar.getInstance();
			Calendar date2=Calendar.getInstance();
			Calendar date3=Calendar.getInstance();
			Calendar date4=Calendar.getInstance();
			Calendar date5=Calendar.getInstance();
			Calendar date6=Calendar.getInstance();
			Calendar date7=Calendar.getInstance();
			
			//modify the dates to simulate the data base
			date1.set(Calendar.HOUR_OF_DAY, 6);
			date2.set(Calendar.HOUR_OF_DAY, 8);
			date3.set(Calendar.HOUR_OF_DAY, 10);
			date4.set(Calendar.HOUR_OF_DAY,6);
			date5.set(Calendar.HOUR_OF_DAY,10);
			date6.set(Calendar.HOUR_OF_DAY,6);
			date7.set(Calendar.HOUR_OF_DAY,10);
			
			date4.set(Calendar.YEAR, 2013);
			date5.set(Calendar.YEAR, 2013);
			int temday=date1.get(Calendar.DAY_OF_MONTH)-1;
			date6.set(Calendar.DAY_OF_MONTH, temday);
			date7.set(Calendar.DAY_OF_MONTH, temday);
			
			Data data1=new Data(date1,null,0.223,null,1,adminid);
			Data data2=new Data(date2,null,0.123,null,3,adminid);
			Data data3=new Data(date3,null,0.333,null,5,adminid);
			Data data4=new Data(date4,null,0.253,null,5,adminid);
			Data data5=new Data(date5,null,0.323,null,5,adminid);
			Data data6=new Data(date6,null,0.133,null,5,adminid);
			Data data7=new Data(date7,null,0.532,null,5,adminid);
			dbh.addData(data1);
			dbh.addData(data2);
			dbh.addData(data3);
			dbh.addData(data4);
			dbh.addData(data5);
			dbh.addData(data6);
			dbh.addData(data7);
			dbh.getDayGL(date1);
			*/
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
	public void onDialogPositiveClick(DialogFragment dialog) {
		// TODO Auto-generated method stub
		
	}
	
	
public void returnTest(View view){
	Intent intentToDatabaseTestActivity=new Intent(this,DatabaseTestActivity.class);
	startActivity(intentToDatabaseTestActivity);
	
}	
	
	
}