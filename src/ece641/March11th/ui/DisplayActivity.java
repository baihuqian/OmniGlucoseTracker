package ece641.March11th.ui;

import java.util.Calendar;

import ece641.March11th.entities.UserInfoConstants;
import ece641.March11th.graph.GraphDisplayConstants;
import ece641.March11th.graph.GraphViewHelper;
import android.R.color;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class DisplayActivity extends Activity 
implements OnCalSelectedListener, DatePickerFragment.OnDateSelectedListener, 
GraphDisplayConstants, UserInfoConstants{

	private int resourceID;
	private boolean isTablet;
	private int userID;
	private int launchType;
	public int getLaunchType() {
		return launchType;
	}

	public int getUserID() {
		return userID;
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

		View userInfoContainer = findViewById(R.id.userinfoContainer);
		isTablet = userInfoContainer != null && userInfoContainer.getVisibility() == View.VISIBLE;

		Intent intent = getIntent();
		launchType = intent.getIntExtra(LAUNCH_TYPE, -1);
		userID = intent.getIntExtra(USERID, -1);

		if(!isFinishing()) {
			FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
			/*
		ButtonFragment buttonFragment = new ButtonFragment();
		fragmentTransaction.add(R.id.buttonContainer, buttonFragment);
			 */
			switch(launchType) {
			case DISPLAY_DAILY:
				DailyFragment dailyFragment = new DailyFragment();
				fragmentTransaction.add(R.id.maininfoContainer, dailyFragment);
				break;
			case DISPLAY_WEEKLY:
				WeeklyFragment weeklyFragment = new WeeklyFragment();
				fragmentTransaction.add(R.id.maininfoContainer, weeklyFragment);
				break;
			case DISPLAY_MONTHLY:
				MonthlyFragment monthlyFragment = new MonthlyFragment();
				fragmentTransaction.add(R.id.maininfoContainer, monthlyFragment);
				break;
			case DISPLAY_LOCATION:
				LocationFragment locationFragment = new LocationFragment();
				fragmentTransaction.add(R.id.maininfoContainer, locationFragment);
				break;
			default:
				break;
			}

			if(isTablet) {
				UserInfoFragment userinfoFragment = new UserInfoFragment();
				fragmentTransaction.add(R.id.userinfoContainer, userinfoFragment);
				ButtonFragment buttonFragment = new ButtonFragment();
				fragmentTransaction.add(R.id.buttonContainer, buttonFragment);
			}
			fragmentTransaction.commit();

		}
	}



	@Override
	public void onCalSeleted(int frag) {
		// TODO Auto-generated method stub
		resourceID = frag;
	}



	@Override
	public void onDateSelected(Calendar date) {
		// TODO Auto-generated method stub
		GraphViewHelper helper;
		switch(resourceID) {
		case DISPLAY_DAILY: 
			((DailyFragment) getFragmentManager().findFragmentById(R.id.maininfoContainer)).setDate(date);
			helper = ((DailyFragment) getFragmentManager().findFragmentById(R.id.maininfoContainer)).graphHelper;
			helper.changeDate(date);
			break;
		case DISPLAY_WEEKLY:
			((WeeklyFragment) getFragmentManager().findFragmentById(R.id.maininfoContainer)).setDate(date);
			helper = ((WeeklyFragment) getFragmentManager().findFragmentById(R.id.maininfoContainer)).graphHelper;
			helper.changeWeek(date);
			break;
		case DISPLAY_MONTHLY:
			((MonthlyFragment) getFragmentManager().findFragmentById(R.id.maininfoContainer)).setDate(date);
			helper = ((MonthlyFragment) getFragmentManager().findFragmentById(R.id.maininfoContainer)).graphHelper;
			helper.changeMonth(date);
			break;
		}


	}



}
