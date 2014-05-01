package ece641.March11th.ui;

import ece641.March11th.dblayout.ODTDatabaseHelper;
import ece641.March11th.entities.User;
import ece641.March11th.entities.UserIDInterface;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class UserInfoFragment extends Fragment {

	private TextView name, age, gender, height, weight;
	private ImageView portrait;
	private ODTDatabaseHelper db;
	public UserInfoFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_user_info, container, false);



		name = (TextView) v.findViewById(R.id.userName);
		age = (TextView) v.findViewById(R.id.userAge);
		gender = (TextView) v.findViewById(R.id.userGender);
		height = (TextView) v.findViewById(R.id.textViewHeight);
		weight = (TextView) v.findViewById(R.id.textViewWeight);
		portrait = (ImageView) v.findViewById(R.id.userImageView);

		// query db to get user info
		db = new ODTDatabaseHelper(v.getContext());
		
		int userid = ((UserIDInterface) getActivity()).getUserID();
		/*
		Activity activity = getActivity();
		if(activity instanceof WelcomeActivity) {
			userid = ((WelcomeActivity) activity).getUserID();
		}
		else if(activity instanceof DisplayActivity) {
			userid = ((DisplayActivity) activity).getUserID();
		}*/
		User user=db.getUser(userid);

		String userName = user.getUserName();
		int userAge = user.getAge();
		String userGender = user.getGender();
		double userHeight = user.getHeight();
		double userWeight = user.getWeight();

		// set user info
		name.setText(userName);
		age.setText(Integer.toString(userAge));
		gender.setText(userGender);
		height.setText(String.format("%.1f", userHeight));
		weight.setText(String.format("%.1f", userWeight));
		return v;
	}
}
