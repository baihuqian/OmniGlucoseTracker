package ece641.March11th.ui;

import java.util.Calendar;

import ece641.March11th.graph.GraphViewHelper;
import android.R.color;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;


public class DisplayActivity extends Activity 
			implements OnCalSelectedListener, DatePickerFragment.OnDateSelectedListener{
	
	private int resourceID;
	
	

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
		DailyFragment dailyFragment = new DailyFragment();
		fragmentTransaction.add(R.id.maininfoContainer, dailyFragment);
		UserInfoFragment userinfoFragment = new UserInfoFragment();
		fragmentTransaction.add(R.id.userinfoContainer, userinfoFragment);
		fragmentTransaction.commit();
		

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
		case 1: 
			helper = ((DailyFragment) getFragmentManager().findFragmentById(R.id.maininfoContainer)).graphHelper;
			helper.changeDate(date);
			break;
		case 2:
			helper = ((WeeklyFragment) getFragmentManager().findFragmentById(R.id.maininfoContainer)).graphHelper;
			helper.changeWeek(date);
			break;
		case 3:
			helper = ((MonthlyFragment) getFragmentManager().findFragmentById(R.id.maininfoContainer)).graphHelper;
			helper.changeMonth(date);
			break;
		}
		
		
	}


	
}
