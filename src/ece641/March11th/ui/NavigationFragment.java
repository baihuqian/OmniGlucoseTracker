package ece641.March11th.ui;



import java.util.ArrayList;

import ece641.March11th.IO.EmergencyContactHandler;
import ece641.March11th.IO.UpdateUserInfoActivity;
import ece641.March11th.IO.ViewContactActivity;
import ece641.March11th.IO.ViewLogActivity;
import ece641.March11th.IO.ViewUserInfoActivity;
import ece641.March11th.dblayout.ODTDatabaseHelper;
import ece641.March11th.entities.Contact;
import ece641.March11th.entities.UserInfoConstants;
import ece641.March11th.graph.GraphDisplayConstants;
import ece641.March11th.map.ViewOnMapActivity;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import ece641.March11th.IO.ViewContactActivity;
import ece641.March11th.IO.ViewLogActivity;
import ece641.March11th.IO.ViewUserInfoActivity;
import ece641.March11th.entities.UserInfoConstants;
import ece641.March11th.graph.GraphDisplayConstants;

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
			Context context=getActivity().getApplicationContext();
			// TODO Auto-generated method stub
			Intent intent=new Intent(context,ViewUserInfoActivity.class);
			intent.putExtra(USERID, userID);
			intent.putExtra("userid", userID);
			startActivity(intent);
			
		}
		
	};
	
	private OnClickListener contactsOnclickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			Context context=getActivity().getApplicationContext();
			// TODO Auto-generated method stub
			Intent intent=new Intent(context,ViewContactActivity.class);
			intent.putExtra("userid", userID);
			startActivity(intent);
		}
		
	};
	
	private OnClickListener locationOnclickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			Context context=getActivity().getApplicationContext();
			// TODO Auto-generated method stub
		Intent intent=new Intent(context,ViewOnMapActivity.class);
		intent.putExtra("userid", userID);
			
			startActivity(intent);
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
			Intent intent = new Intent(getActivity(), AnalysisActivity.class);
			intent.putExtra(USERID, userID);
			startActivity(intent);
		}
		
	};
	
	private OnClickListener messageOnclickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			Context context=getActivity().getApplicationContext();
			// TODO Auto-generated method stub
			
			Intent intent=new Intent(context,ViewLogActivity.class);
			intent.putExtra("userid", userID);
			startActivity(intent);
			
		}
		
	};
	
	private OnClickListener emergencyOnclickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			ODTDatabaseHelper dbHelper = new ODTDatabaseHelper(view.getContext());
			ArrayList<Contact> contactSet = dbHelper.getContactList(userID);
			String [] phone = new String [contactSet.size()];
			String [] email = new String [contactSet.size()];
			for(int i = 0; i < contactSet.size(); i++) {
				Contact c = contactSet.get(i);
				phone[i] = c.getContactNumber();
				email[i] = c.getContactEmail();
			}
			
			String text = "Name: " + dbHelper.getUser(userID).getUserName() + "\n" +
					"Glucose Level: " + dbHelper.getLatestGL(userID) + "\n";
			String subject = dbHelper.getUser(userID).getUserName() + " in emergency";
			EmergencyContactHandler handler = new EmergencyContactHandler(getActivity(), phone, email, text, text, subject);
			handler.sendEmergency();
		}
		
	};
}
