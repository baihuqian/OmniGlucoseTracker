package ece641.March11th.ui;

import android.R.color;
import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;


public class DisplayActivity extends Activity {
	private int info = -1;
	
	public void setInfo(int id) {
		/**
		 *  info = -1: no display
		 *  info = 0 : daily
		 *  info = 1 : weekly
		 *  info = 2 : monthly
		 *  info = 3 : location
		 */
		
		switch (id) {
		case R.layout.fragment_location:
			info = 3;
			return;
			
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_display);
		
		View decorView = getWindow().getDecorView();
		// Hide the status bar.
		int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
		decorView.setSystemUiVisibility(uiOptions);
		// Remember that you should never show the action bar if the
		// status bar is hidden, so hide that too if necessary.
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		
		ButtonFragment buttonFragment = new ButtonFragment();
		fragmentTransaction.add(R.id.buttonContainer, buttonFragment);
		LocationFragment locationFragment = new LocationFragment();
		fragmentTransaction.add(R.id.maininfoContainer, locationFragment);
		UserInfoFragment userinfoFragment = new UserInfoFragment();
		fragmentTransaction.add(R.id.userinfoContainer, userinfoFragment);
		fragmentTransaction.commit();
		

	}


	
}
