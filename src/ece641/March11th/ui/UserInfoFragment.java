package ece641.March11th.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class UserInfoFragment extends Fragment {

	private TextView name, age, glucose;
	private ImageView portrait;
	
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
        glucose = (TextView) v.findViewById(R.id.userGlucose);
        portrait = (ImageView) v.findViewById(R.id.userImageView);
        
        // query db to get user info
        String userName = "John Doe";
        int userAge = 20;
        double userGlucose = 100;
        
        // set user info
        name.setText(userName);
        age.setText(Integer.toString(userAge));
        glucose.setText(Double.toString(userGlucose));
        return v;
    }
}
