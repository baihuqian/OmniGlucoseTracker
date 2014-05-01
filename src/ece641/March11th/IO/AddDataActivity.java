package ece641.March11th.IO;

import java.util.Calendar;

import ece641.March11th.dblayout.ODTDatabaseHelper;
import ece641.March11th.entities.Data;
import ece641.March11th.map.LocationHelper;
import ece641.March11th.ui.DatePickerFragment;
import ece641.March11th.ui.DatePickerFragment.OnDateSelectedListener;
import ece641.March11th.ui.NewDatePickerFragment;
import ece641.March11th.ui.R;
import ece641.March11th.ui.TimePickerFragment.OnTimeSelectedListener;
import ece641.March11th.ui.UserLoginActivity;
import ece641.March11th.ui.R.id;
import ece641.March11th.ui.R.layout;
import ece641.March11th.ui.R.menu;
import ece641.March11th.ui.TimePickerFragment;
import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.AdapterView.OnItemSelectedListener;
import android.os.Build;

public class AddDataActivity extends Activity implements NewDatePickerFragment.OnDateSelectedListener,TimePickerFragment.OnTimeSelectedListener {

	private int year;
	private int month;
	private int dayofmonth;
	private int hourofday;
	private int minute;
	private String samplesituation;
	private int sampletype;
	private double gllevel;
	private int userid;
	private String notes;
	ODTDatabaseHelper dbh=new ODTDatabaseHelper(this);
	
//private OnDateSelectedListener fd;
//private OnTimeSelectedListener ft;
	
	@Override
	public void onTimeSelected(int hourofday,int minute){
		this.hourofday=hourofday;
		this.minute=minute;
	}
	@Override
	public void onDateSelected(Calendar date) {
		
		year=date.get(Calendar.YEAR);
		month=date.get(Calendar.MONTH);
		dayofmonth=date.get(Calendar.DAY_OF_MONTH);
		
				
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_data);
		Intent intent=getIntent();
		userid=intent.getIntExtra("userid", 1);
		CheckBox checkBox = (CheckBox) findViewById(R.id.checkBoxAttachLocation);
        if (checkBox.isChecked()) {
            checkBox.setChecked(false);
        }
		
		

		final Spinner sapmlesituationSpinner=(Spinner) findViewById(R.id.spinnerAddData);
	 	 
		 ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.sample_situation_array, android.R.layout.simple_spinner_item);
	 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	 sapmlesituationSpinner.setAdapter(adapter);
		
	 sapmlesituationSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent,
					View view, int position, long id) {
				// TODO Auto-generated method stub
				samplesituation = (String) sapmlesituationSpinner.getSelectedItem();
				sampletype=sapmlesituationSpinner.getSelectedItemPosition();
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
     });
	
	}
	
	public void chooseDate(View view){
	DialogFragment newFragment = new NewDatePickerFragment();
    newFragment.show(getFragmentManager(), "datePicker");
	}

	
	public void chooseTime(View view){
		DialogFragment newFragment = new TimePickerFragment();
	    newFragment.show(getFragmentManager(), "timePicker");
		
	}
	


	
	public void addData(View view){
		Location location;
		Calendar calendar=Calendar.getInstance();
		calendar.set(Calendar.YEAR,year);
		calendar.set(Calendar.MONTH,month);
		calendar.set(Calendar.DAY_OF_MONTH, dayofmonth);
		calendar.set(Calendar.HOUR_OF_DAY, hourofday);
		calendar.set(Calendar.MINUTE, minute);
		
		CheckBox checkBox = (CheckBox) findViewById(R.id.checkBoxAttachLocation);
		
		EditText gl=(EditText) findViewById(R.id.editTextGL);
		EditText noteText=(EditText) findViewById(R.id.editNote);
		notes=noteText.getText().toString().trim();
		
		if((gl.getText().toString().trim().length()==0)&&(noteText.getText().toString().trim().length()==0)&&(!(checkBox.isChecked()))){
		
			AlertDialog.Builder builder = new AlertDialog.Builder(AddDataActivity.this);
			builder.setMessage("Nothing Valuable to be Recorded!")
			.setTitle("Nothing Valuable!");

			builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {}
			});


			AlertDialog dialog = builder.create();
			dialog.show();
			
			
		}
		else{
			if(notes.length()==0){notes=null;}
			else{}
		
		if(gl.getText().toString().trim().length()==0){gllevel=-1000;}
		else
		{gllevel=Double.parseDouble(gl.getText().toString().trim());}
		
		if (checkBox.isChecked()) {
			LocationHelper lh=new LocationHelper(this);
			location=lh.getBestCurrentLocation();
			
			Data data=new Data(calendar,location,gllevel,notes,sampletype,userid);
			dbh.addData(data);
		}
		
		else{Data data=new Data(calendar,null,gllevel,notes,sampletype,userid);
		dbh.addData(data);
		}
		
		}
	}

	

	

	

	
}
