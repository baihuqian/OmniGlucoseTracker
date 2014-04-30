package ece641.March11th.IO;

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
import android.os.Build;

public class ViewUserInfoActivity extends Activity {
private int userid;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_user_info);
		
		Intent intent=getIntent();
		userid=intent.getIntExtra("userid", 1);

	}

	public void updateUserInfo(View view){
		
		Intent intent=new Intent(this,UpdateUserInfoActivity.class);
		startActivity(intent);
	}
}
