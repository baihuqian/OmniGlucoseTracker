package ece641.March11th.IO;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import ece641.March11th.dblayout.ODTDatabaseHelper;
import ece641.March11th.entities.User;
import ece641.March11th.entities.UserInfoConstants;
import ece641.March11th.ui.R;
import ece641.March11th.ui.UIHelper;

public class ViewUserInfoActivity extends Activity
implements EditUserDialog.EditUserDialogListener, UserInfoConstants{
private int userid;
private ODTDatabaseHelper db=new ODTDatabaseHelper(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_view_user_info);
		UIHelper.setOrientation(this);
		//UIHelper.hideActionBar(this);
		Intent intent=getIntent();
		userid=intent.getIntExtra(USERID, 1);
	
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
		
		Bundle args = new Bundle();
		args.putInt(USERID, userid);
		EditUserDialog dialog2 = new EditUserDialog  ();
		dialog2 .setArguments(args);
		dialog2.show(getFragmentManager(), null);
		
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

	@Override
	public void onEditUserDialogPositiveClick(DialogFragment dialog,
			User user) {
		db.updateUser(user);
		// TODO Auto-generated method stub

		
		 Toast.makeText(this, "User Information is Updated!", Toast.LENGTH_LONG).show();
		View v=getWindow().getDecorView().findViewById(android.R.id.content);
		refreshUserInfo(v);
		
	}

	@Override
	public void onEditUserDialogNegativeClick(DialogFragment dialog) {
		// TODO Auto-generated method stub
		
	}
}
