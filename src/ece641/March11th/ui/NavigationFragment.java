package ece641.March11th.ui;

import ece641.March11th.entities.UserInfoConstants;
import ece641.March11th.graph.GraphDisplayConstants;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

public class NavigationFragment extends Fragment 
		implements GraphDisplayConstants, UserInfoConstants{
	private ImageView userInfo, contacts, location, daily, weekly, monthly, analysis, message, emergency;
	private View view;
	private int userID;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_navigation, container, false);
		
		userID = ((WelcomeActivity) getActivity()).getUserID();
		
		// initialize all buttons
		userInfo = (ImageView) view.findViewById(R.id.imageViewNavigationUserInfo);
		contacts = (ImageView) view.findViewById(R.id.imageViewNavigationContacts);
		location = (ImageView) view.findViewById(R.id.imageViewNavigationLocation);
		daily	 = (ImageView) view.findViewById(R.id.imageViewNavigationDaily);
		weekly	 = (ImageView) view.findViewById(R.id.imageViewNavigationWeekly);
		monthly	 = (ImageView) view.findViewById(R.id.imageViewNavigationMonthly);
		analysis = (ImageView) view.findViewById(R.id.imageViewNavigationAnalysis);
		message  = (ImageView) view.findViewById(R.id.imageViewNavigationMessage);
		emergency= (ImageView) view.findViewById(R.id.imageViewNavigationEmergency);
		
		
		//set up onClickListeners
		userInfo.setOnClickListener(userInfoOnclickListener);
		contacts.setOnClickListener(contactsOnclickListener);
		location.setOnClickListener(locationOnclickListener);
		daily.setOnClickListener(dailyOnclickListener);
		weekly.setOnClickListener(weeklyOnclickListener);
		monthly.setOnClickListener(monthlyOnclickListener);
		analysis.setOnClickListener(analysisOnclickListener);
		message.setOnClickListener(messageOnclickListener);
		emergency.setOnClickListener(emergencyOnclickListener);
		
		
		return view;
	}

	private OnClickListener userInfoOnclickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	private OnClickListener contactsOnclickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	private OnClickListener locationOnclickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	private OnClickListener dailyOnclickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(getActivity(), DisplayActivity.class);
			intent.putExtra(LAUNCH_TYPE, DISPLAY_DAILY);
			intent.putExtra(USERID, userID);
			startActivity(intent);
		}
		
	};
	
	private OnClickListener weeklyOnclickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(getActivity(), DisplayActivity.class);
			intent.putExtra(LAUNCH_TYPE, DISPLAY_WEEKLY);
			intent.putExtra(USERID, userID);
			startActivity(intent);
		}
		
	};
	
	private OnClickListener monthlyOnclickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(getActivity(), DisplayActivity.class);
			intent.putExtra(LAUNCH_TYPE, DISPLAY_MONTHLY);
			intent.putExtra(USERID, userID);
			startActivity(intent);
		}
		
	};
	
	private OnClickListener analysisOnclickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	private OnClickListener messageOnclickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	private OnClickListener emergencyOnclickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			
		}
		
	};
}