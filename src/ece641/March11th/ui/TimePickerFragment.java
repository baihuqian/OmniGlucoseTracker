package ece641.March11th.ui;

import java.util.Calendar;

import ece641.March11th.ui.DatePickerFragment.OnDateSelectedListener;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
	
	public interface OnTimeSelectedListener{
		public void onTimeSelected(int hourofday,int minute);
	}
	
	OnTimeSelectedListener onTimeSelectedListener;
@Override
public Dialog onCreateDialog(Bundle savedInstanceState) {
// Use the current time as the default values for the picker
final Calendar c = Calendar.getInstance();
int hour = c.get(Calendar.HOUR_OF_DAY);
int minute = c.get(Calendar.MINUTE);

// Create a new instance of TimePickerDialog and return it

		TimePickerDialog tp=new TimePickerDialog(getActivity(), this, hour, minute,DateFormat.is24HourFormat(getActivity()));
		tp.setCanceledOnTouchOutside(false);

return tp;
}


@Override
public void onAttach(Activity activity) {
	// TODO Auto-generated method stub
	super.onAttach(activity);
	try {
		onTimeSelectedListener = (OnTimeSelectedListener) activity;
    } catch (ClassCastException e) {
        
    }
}
public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
// Do something with the time chosen by the user
	
	onTimeSelectedListener.onTimeSelected(hourOfDay, minute);
}



}
