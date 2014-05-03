package ece641.March11th.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import ece641.March11th.entities.UserIDInterface;
import ece641.March11th.entities.UserInfoConstants;

public class WelcomeActivity extends Activity implements UserInfoConstants, UserIDInterface{
	private boolean isTablet;
	private int userID;
	//private int userid;
	
	public int getUserID() {
		return userID;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		// retrieve payloads in intent
		Intent intent = getIntent();
		userID = intent.getIntExtra(USERID, 1);
		
		
		UIHelper.hideActionBar(this);

		UIHelper.setOrientation(this);
		
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		
		NavigationFragment navigationFragment = new NavigationFragment();
		fragmentTransaction.add(R.id.navigationContainer, navigationFragment);
		if(isTablet) {
			UserInfoFragment userinfoFragment = new UserInfoFragment();
			fragmentTransaction.add(R.id.userinfoContainer, userinfoFragment);
		}
		fragmentTransaction.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.weicome, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	

}
