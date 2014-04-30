package ece641.March11th.ui;

import ece641.March11th.entities.UserInfoConstants;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class WelcomeActivity extends Activity implements UserInfoConstants{
	private boolean isTablet;
	private int userID;
	
	public int getUserID() {
		return userID;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);

		Intent intent = getIntent();
		userID = intent.getIntExtra(USERID, -1);
		
		View decorView = getWindow().getDecorView();
		// Hide the status bar.
		int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
		decorView.setSystemUiVisibility(uiOptions);
		// Remember that you should never show the action bar if the
		// status bar is hidden, so hide that too if necessary.
		ActionBar actionBar = getActionBar();
		actionBar.hide();

		View userInfoContainer = findViewById(R.id.userinfoContainer);
		isTablet = userInfoContainer != null && userInfoContainer.getVisibility() == View.VISIBLE;
		
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