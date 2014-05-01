package ece641.March11th.IO;

import java.util.ArrayList;
import java.util.Calendar;

import ece641.March11th.dblayout.ODTDatabaseHelper;
import ece641.March11th.entities.Data;
import ece641.March11th.ui.R;
import ece641.March11th.ui.UserLoginActivity;
import ece641.March11th.ui.R.id;
import ece641.March11th.ui.R.layout;
import ece641.March11th.ui.R.menu;
import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.os.Build;

public class ViewLogActivity extends Activity {
	int userid;
	int dataid;
	Context context;
	ArrayList<Integer> dataidlist=new ArrayList<Integer>();
	
	ArrayList<Data> datalist=new ArrayList<Data>();
	ArrayAdapter<Integer> dataiddapter ;
	
	ODTDatabaseHelper dbh=new ODTDatabaseHelper(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_log);
		
		Intent intent=getIntent();
		userid=intent.getIntExtra("userid", 1);
		Calendar calendar=Calendar.getInstance();
		datalist=dbh.getDataListSortByTimeForView(calendar,userid);
		context=getApplicationContext();
		
		int length=datalist.size();


		if(length==0){
			}
		else{
		 for (int i = 0; i < length; i++) {
			 dataidlist.add(datalist.get(i).getDataID());
		    }
		}
		
		
		 ListView listview = (ListView) findViewById(R.id.listViewLog);
		dataiddapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, dataidlist);
//
		 listview.setAdapter(dataiddapter);
		 
		 listview.setOnItemClickListener(new OnItemClickListener() {
			 

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				
				dataid=dataidlist.get(position);
			
				
				AlertDialog alertDialog = new AlertDialog.Builder(ViewLogActivity.this).create();

			    alertDialog.setTitle("What do you want to do?");

			    alertDialog.setMessage("What do you want to do with this data?");

			    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Edit", new DialogInterface.OnClickListener() {

			      public void onClick(DialogInterface dialog, int id) {

			        //...

			    } }); 

			    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Delete", new DialogInterface.OnClickListener() {

			      public void onClick(DialogInterface dialog, int id) {
			    	  
			    	  AlertDialog.Builder builder = new AlertDialog.Builder(ViewLogActivity.this);
						builder.setMessage("Are you sure to delete this data?")
						.setTitle("Warning:deletedata!");

						builder.setPositiveButton("Yes!", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {

							}
						});

						builder.setNegativeButton("Cancel!", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								



							}
						});

						AlertDialog dialog1 = builder.create();
						dialog1.show();

			     

			    }}); 

			    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Cancel", new DialogInterface.OnClickListener() {

			      public void onClick(DialogInterface dialog, int id) {

			        //...

			    }});
			    alertDialog.show();
				
			
				
			}
			}); 
		
		 
		 
		
	}

	
	
	
	public void addData(View view){
	
		Intent intent=new Intent(this,AddDataActivity.class);
		startActivity(intent);
	}
}
