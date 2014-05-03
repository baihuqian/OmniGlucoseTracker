package ece641.March11th.ui;


import java.util.Calendar;











import ece641.March11th.IO.AddActivity;
import ece641.March11th.IO.CreateAccountDialog;
import ece641.March11th.dblayout.ODTDatabaseHelper;
import ece641.March11th.dblayout.abstractODTDatabaseHelper;
import ece641.March11th.entities.Data;
import ece641.March11th.entities.User;
import ece641.March11th.entities.UserInfoConstants;
import ece641.March11th.map.LocationLoggerService;
import ece641.March11th.test.BuildTestDatabase;
import ece641.March11th.test.DatabaseTestActivity;
import ece641.March11th.test.TestActivity;
import android.app.Activity;
import android.app.ActionBar;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
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

public class UserLoginActivity extends FragmentActivity
implements UserInfoConstants, CreateAccountDialog.NoticeDialogListener {
	ODTDatabaseHelper dbh=new ODTDatabaseHelper(this);

	private String nametba;
	private int agetba;
	private String gendertba;
	private String loginnametba;
	private String passwordtba1;
	private String passwordtba2;
	private Context context;
	private double height;
	private double weight;


	public void logIn(View view){
		Intent intentToUserInfoActivity=new Intent(this,WelcomeActivity.class);
		//get input loginname and password
		EditText inputUsername = (EditText) findViewById(R.id.inputUsername);
		EditText inputPassword = (EditText) findViewById(R.id.inputPassword);
		String userloginname=inputUsername.getText().toString();
		String password=inputPassword.getText().toString();
		boolean checkloginname=dbh.checkIfLoginNameExist(userloginname);
		if(checkloginname){ 
			boolean checkloginnameandpassword=dbh.checkIfLoginNameMatchPassword(userloginname, password);
			if(checkloginnameandpassword){
				int userid=dbh.getUserID(userloginname);
				intentToUserInfoActivity.putExtra(USERID, userid);
				//intentToUserInfoActivity.putExtra("userid", userid);

				// Find and stop the gps logger service, and start a new service for current user!

				ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
				for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
					if (LocationLoggerService.class.getName().equals(service.service.getClassName())) {
						stopService(new Intent(UserLoginActivity.this, LocationLoggerService.class));

					}
				}

				Intent intentforservice=new Intent(this,LocationLoggerService.class);


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
		UIHelper.setOrientation(this);
		UIHelper.hideActionBar(this);
		context=getApplicationContext();
		if(	dbh.checkIfLoginNameExist("admin")){}
		else{

			BuildTestDatabase bd=new BuildTestDatabase(context,dbh);
			bd.buildDatabase();

		}
		// set default username and password for testing

		((EditText) findViewById(R.id.inputUsername)).setText("admin");
		((EditText) findViewById(R.id.inputPassword)).setText("admin");

	}





	@Override
	public void onDialogNegativeClick(DialogFragment dialog) {
		// TODO Auto-generated method stub

	}


	@Override
	public void onDialogPositiveClick(DialogFragment dialog,String username,int age, String gender,String loginname,String password1,String password2,double height,double weight) {
		/*
		nametba=username;
		agetba=age;
		 gendertba=gender;
		 loginnametba=loginname;
		 passwordtba1=password1;
		 passwordtba2=password2;
		 */
		if(dbh.checkIfLoginNameExist(loginname)|loginname.length()==0){

			AlertDialog.Builder builder = new AlertDialog.Builder(UserLoginActivity.this);
			builder.setMessage("The Login Name Exists! or Login Name is Null!" )
			.setTitle("Login Name Problem!");

			builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {}
			});


			AlertDialog dialog1 = builder.create();
			dialog1.show();	



		}
		else{

			if(password1.equals(password2)&&(password1.length()!=0)){
				if(username.equals("")){
					AlertDialog.Builder builder = new AlertDialog.Builder(UserLoginActivity.this);
					builder.setMessage("Username is Empty!" )
					.setTitle("Username is Empty!");

					builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {}
					});


					AlertDialog dialog1 = builder.create();
					dialog1.show();	
					
				}
				else{
				User usertba=new User(username,age,gender, loginname,password1,height,weight);
				dbh.addUser(usertba);
				Toast.makeText(UserLoginActivity.this, "New Account is Created!", Toast.LENGTH_LONG).show();}
			}



			else{
				AlertDialog.Builder builder = new AlertDialog.Builder(UserLoginActivity.this);
				builder.setMessage("Password Doesn't Match!Or PassWord is Null!" )
				.setTitle("Password Problem");

				builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {}
				});


				AlertDialog dialog1 = builder.create();
				dialog1.show();	
			}}


		// TODO Auto-generated method stub

	}


	public void returnTest(View view){
		dbh.deleteData(5);

	}	


}