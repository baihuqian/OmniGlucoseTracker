package ece641.March11th.IO;

import java.util.Calendar;

import ece641.March11th.ui.DatePickerFragment;
import ece641.March11th.ui.R;
import ece641.March11th.ui.R.id;
import ece641.March11th.ui.R.layout;
import ece641.March11th.ui.R.menu;
import ece641.March11th.ui.TimePickerFragment;
import android.app.Activity;
import android.app.ActionBar;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.AdapterView.OnItemSelectedListener;
import android.os.Build;

public class AddDataActivity extends Activity implements DatePickerFragment.OnDateSelectedListener,TimePickerDialog.OnTimeSetListener {

	private int year;
	private int month;
	private int dayofmonth;
	private int hourofday;
	private int minute;
	private String samplesituation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_data);
		

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
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
     });
	
	}
	
	public void chooseDate(View view){
	DialogFragment newFragment = new DatePickerFragment();
    newFragment.show(getFragmentManager(), "datePicker");
	}

	
	public void chooseTime(View view){
		DialogFragment newFragment = new TimePickerFragment();
	    newFragment.show(getFragmentManager(), "timePicker");
		
	}
	@Override
	public void onDateSelected(Calendar date) {
		
		year=date.get(Calendar.YEAR);
		month=date.get(Calendar.MONTH);
		dayofmonth=date.get(Calendar.DAY_OF_MONTH);
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		// TODO Auto-generated method stub
		this.hourofday=hourOfDay;
		this.minute=minute;
	}

	

	
}
