package ece641.March11th.ui;

import ece641.March11th.IO.AddActivity;
import ece641.March11th.IO.AddActivityDialogFragment;
import android.R.color;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class ButtonFragment extends Fragment {
	private Button daily, monthly, weekly, location;
	private Drawable buttonDefaultBackground;
	private ImageButton add;

	public ButtonFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
		
        View view = inflater.inflate(R.layout.fragment_button, container, false);
        daily = (Button) view.findViewById(R.id.buttonDaily);
        weekly = (Button) view.findViewById(R.id.buttonWeekly);
        monthly = (Button) view.findViewById(R.id.buttonMonthly);
        location = (Button) view.findViewById(R.id.buttonLocation);
        add = (ImageButton) view.findViewById(R.id.buttonNewActivity);
        
        daily.setOnClickListener(dailyOnclick);
        weekly.setOnClickListener(weeklyOnclick);
        monthly.setOnClickListener(monthlyOnclick);
        location.setOnClickListener(locationOnclick);
        add.setOnClickListener(addOnclick);
        buttonDefaultBackground = daily.getBackground();
        // default fragment is location
        location.setBackgroundColor(color.holo_blue_bright);
        return view;
    }
	
	OnClickListener dailyOnclick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			daily.setBackgroundColor(color.holo_blue_bright);
			weekly.setBackground(buttonDefaultBackground);
			monthly.setBackground(buttonDefaultBackground);
			location.setBackground(buttonDefaultBackground);
			DailyFragment dailyFragment = new DailyFragment();
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.maininfoContainer, dailyFragment).commit();
		}
		
	};
	
	OnClickListener weeklyOnclick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// switch fragment
			// call getActivity().setInfo() and pass the R.layout.fragment value
			
			daily.setBackground(buttonDefaultBackground);
			weekly.setBackgroundColor(color.holo_blue_bright);
			monthly.setBackground(buttonDefaultBackground);
			location.setBackground(buttonDefaultBackground);
			WeeklyFragment weeklyFragment = new WeeklyFragment();
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.maininfoContainer, weeklyFragment).commit();
		}
		
	};
	
	OnClickListener monthlyOnclick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			daily.setBackground(buttonDefaultBackground);
			weekly.setBackground(buttonDefaultBackground);
			monthly.setBackgroundColor(color.holo_blue_bright);
			location.setBackground(buttonDefaultBackground);
			MonthlyFragment monthlyFragment = new MonthlyFragment();
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.maininfoContainer, monthlyFragment).commit();
		}
		
	};
	
	OnClickListener locationOnclick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			daily.setBackground(buttonDefaultBackground);
			weekly.setBackground(buttonDefaultBackground);
			monthly.setBackground(buttonDefaultBackground);
			location.setBackgroundColor(color.holo_blue_bright);
			LocationFragment locationFragment = new LocationFragment();
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.maininfoContainer, locationFragment).commit();
		}
		
	};
	
	OnClickListener addOnclick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intentoAdd= new Intent(getActivity(),AddActivity.class);
			startActivity(intentoAdd);
		}
		
	};
}
