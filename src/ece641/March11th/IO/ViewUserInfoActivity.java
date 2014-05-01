package ece641.March11th.IO;

import ece641.March11th.dblayout.ODTDatabaseHelper;
import ece641.March11th.entities.User;
import ece641.March11th.ui.R;
import ece641.March11th.ui.R.id;
import ece641.March11th.ui.R.layout;
import ece641.March11th.ui.R.menu;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

public class ViewUserInfoActivity extends Activity {
	private int userid;
	private ODTDatabaseHelper db=new ODTDatabaseHelper(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_view_user_info);

		Intent intent=getIntent();
		userid=intent.getIntExtra("userid", 1);

		User user=db.getUser(userid);

		TextView name=(TextView) findViewById(R.id.textViewUserName);
		TextView age=(TextView) findViewById(R.id.textViewAge);
		TextView gender=(TextView) findViewById(R.id.textViewGender);
		TextView height=(TextView) findViewById(R.id.textViewHeight);
		TextView weight=(TextView) findViewById(R.id.textViewWeight);

		name.setText(user.getUserName());

		age.setText(Integer.toString(user.getAge()));
		gender.setText(user.getGender());
		height.setText(Double.toString(user.getHeight()));
		weight.setText(Double.toString(user.getWeight()));




	}

	public void updateUserInfo(View view){

		Intent intent=new Intent(this,UpdateUserInfoActivity.class);
		intent.putExtra("userid",userid);
		startActivity(intent);


	}

	public void	refreshUserInfo(View view){
		User user=db.getUser(userid);

		TextView name=(TextView) findViewById(R.id.textViewUserName);
		TextView age=(TextView) findViewById(R.id.textViewAge);
		TextView gender=(TextView) findViewById(R.id.textViewGender);
		TextView height=(TextView) findViewById(R.id.textViewHeight);
		TextView weight=(TextView) findViewById(R.id.textViewWeight);

		name.setText(user.getUserName());

		age.setText(Integer.toString(user.getAge()));
		gender.setText(user.getGender());
		height.setText(Double.toString(user.getHeight()));
		weight.setText(Double.toString(user.getWeight()));

	}
}
