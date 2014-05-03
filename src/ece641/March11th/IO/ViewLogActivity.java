package ece641.March11th.IO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ece641.March11th.dblayout.ODTDatabaseHelper;
import ece641.March11th.entities.Contact;
import ece641.March11th.entities.Data;
import ece641.March11th.test.TestActivity;
import ece641.March11th.ui.UIHelper;
import ece641.March11th.ui.NewDatePickerFragment;
import ece641.March11th.ui.R;
import ece641.March11th.ui.UserLoginActivity;
import ece641.March11th.ui.R.id;
import ece641.March11th.ui.R.layout;
import ece641.March11th.ui.R.menu;
import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.DialogFragment;
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
import android.widget.Toast;
import android.os.Build;

public class ViewLogActivity extends Activity implements
ShowDataDialog.ShowDataDialogListener,
EditDataDialog.EditDataDialogListener,
AddDataDialog.AddDataDialogListener,
NewDatePickerFragment.OnDateSelectedListener {
	int userid;
	int dataid;
	Context context;
	ListView listview;

	Calendar date=Calendar.getInstance();
List<String>	 showlist=new ArrayList<String>();
	

	ArrayList<Data> datalist=new ArrayList<Data>();
	ArrayAdapter<String> showadapter ;
	
	ODTDatabaseHelper dbh=new ODTDatabaseHelper(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_log);
		UIHelper.setOrientation(this);
		//UIHelper.hideActionBar(this);
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
			 
			int year= datalist.get(i).getDate().get(Calendar.YEAR);
			int month= datalist.get(i).getDate().get(Calendar.MONTH)+1;
			int dayofmonth= datalist.get(i).getDate().get(Calendar.DAY_OF_MONTH);
			int hourofday= datalist.get(i).getDate().get(Calendar.HOUR_OF_DAY);
			int minute= datalist.get(i).getDate().get(Calendar.MINUTE);
			String ms;
			if(minute<10){ ms="0"+minute;}
			else{ms=Integer.toString(minute);}
			String show="DataTime:"+year+"/"+month+"/"+dayofmonth+"    "+hourofday+":"+ms;
		
			 showlist.add(show);
		    }
		}
		
		listview = (ListView) findViewById(R.id.listViewLog1);
		showadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,showlist);

		 listview.setAdapter(showadapter);
	
		 listview.setOnItemClickListener(new OnItemClickListener() {
			 

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				
			dataid=datalist.get(position).getDataID();
			
			
			Bundle args = new Bundle();
			args.putInt("dataid", dataid);
			args.putInt("userid", userid);
			args.putString("date", showlist.get(position));
			ShowDataDialog dialog = new ShowDataDialog ();
			dialog .setArguments(args);
			dialog.show(getFragmentManager(), null);
				
				
			}
			}); 
		
		
		 
		
	}


	public void addData(View view){
	
Bundle args = new Bundle();
		
		args.putInt("userid", userid);
		
		AddDataDialog dialog = new AddDataDialog();
		dialog .setArguments(args);
		dialog.show(getFragmentManager(), null);
	}


	@Override
	public void onEditDataDialogPositiveClick(DialogFragment dialog,Data newdata) {
		// TODO Auto-generated method stub
		dbh.updateData(newdata);
		Toast.makeText(ViewLogActivity.this, "Data is Updated!", Toast.LENGTH_LONG).show();
	}




	@Override
	public void onEditDataDialogNegativeClick(DialogFragment dialog) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onShowDataDialogPositiveClick(DialogFragment dialog) {
		 AlertDialog.Builder builder = new AlertDialog.Builder(ViewLogActivity.this);
			builder.setMessage("Are you sure to delete this data?")
			.setTitle("Warning:deletedata!");

			builder.setPositiveButton("Yes!", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					
					dbh.deleteData(dataid);
					View v=getWindow().getDecorView().findViewById(android.R.id.content);
					refreshList(v,date);

				}
			});

			builder.setNegativeButton("Cancel!", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					



				}
			});

			AlertDialog dialog1 = builder.create();
			dialog1.show();
		// TODO Auto-generated method stub
	
		
	}


	@Override
	public void onShowDataDialogNeutralClick(DialogFragment dialog) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onShowDataDialogNegativeClick(DialogFragment dialog) {
		// TODO Auto-generated method stub
		
		Bundle args = new Bundle();
		args.putInt("dataid", dataid);
		args.putInt("userid", userid);
		EditDataDialog dialog2 = new EditDataDialog ();
		dialog2 .setArguments(args);
		dialog2.show(getFragmentManager(), null);
		
	}
	
	public void refreshList(View view,Calendar calendar){
		
		
		datalist=dbh.getDataListSortByTimeForView(calendar,userid);
		
		 showlist=new ArrayList<String>();
		int length=datalist.size();
		if(datalist.isEmpty()){showadapter.clear();}
		else{
			 for (int i = 0; i < length; i++) {
			int year= datalist.get(i).getDate().get(Calendar.YEAR);
			int month= datalist.get(i).getDate().get(Calendar.MONTH)+1;
			int dayofmonth= datalist.get(i).getDate().get(Calendar.DAY_OF_MONTH);
			int hourofday= datalist.get(i).getDate().get(Calendar.HOUR_OF_DAY);
			int minute= datalist.get(i).getDate().get(Calendar.MINUTE);
			String ms;
			if(minute<10){ ms="0"+minute;}
			else{ms=Integer.toString(minute);}
			String show="DataTime:"+year+"/"+month+"/"+dayofmonth+"    "+hourofday+":"+ms;
		
		
			 showlist.add(show);

			 showadapter.clear();
			 showadapter.addAll(showlist);
		    }
		    
		    
		
		
		 showadapter.notifyDataSetChanged();
		
	}	
		
		
		
	
	
}
	
public void	changeDate(View view){
	DialogFragment newFragment = new NewDatePickerFragment();
    newFragment.show(getFragmentManager(), "datePicker");
	
	
}


@Override
public void onDateSelected(Calendar date) {
	
	datalist=dbh.getDataListSortByTimeForView(date,userid);
	
	 showlist=new ArrayList<String>();
	int length=datalist.size();
	if(datalist.isEmpty()){showadapter.clear();}
	else{
		 for (int i = 0; i < length; i++) {
		int year= datalist.get(i).getDate().get(Calendar.YEAR);
		int month= datalist.get(i).getDate().get(Calendar.MONTH)+1;
		int dayofmonth= datalist.get(i).getDate().get(Calendar.DAY_OF_MONTH);
		int hourofday= datalist.get(i).getDate().get(Calendar.HOUR_OF_DAY);
		int minute= datalist.get(i).getDate().get(Calendar.MINUTE);
		String ms;
		if(minute<10){ ms="0"+minute;}
		else{ms=Integer.toString(minute);}
		String show="DataTime:"+year+"/"+month+"/"+dayofmonth+"    "+hourofday+":"+ms;
	
	
		 showlist.add(show);

		 showadapter.clear();
		 showadapter.addAll(showlist);
	    }
	    
	    
	
	
	 showadapter.notifyDataSetChanged();
	
	
	// TODO Auto-generated method stub
	
}

}


@Override
public void onAddDataDialogPositiveClick(DialogFragment dialog, Data data) {
	// TODO Auto-generated method stub
	dbh.addData(data);
	View v=getWindow().getDecorView().findViewById(android.R.id.content);
	refreshList(v,date);
}


@Override
public void onAddDataDialogNegativeClick(DialogFragment dialog) {
	// TODO Auto-generated method stub
	
}
}