package ece641.March11th.ui;

import java.util.Calendar;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;

public class NewDatePickerFragment extends DialogFragment 
	implements DatePickerDialog.OnDateSetListener{

	public interface OnDateSelectedListener {
		public void onDateSelected(Calendar date);

	
	}
	
	OnDateSelectedListener dateSelectedListener;
	
	
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
         DatePickerDialog dp=new DatePickerDialog(getActivity(), this, year, month, day);
        dp.setCanceledOnTouchOutside(false);
        return dp;
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		try {
			dateSelectedListener = (OnDateSelectedListener) activity;
        } catch (ClassCastException e) {
            
        }
	}

	@Override
	public void onDateSet(DatePicker view, int year, int month, int day) {
		// TODO Auto-generated method stub
		Calendar c = Calendar.getInstance();
		c.set(year, month, day);
		dateSelectedListener.onDateSelected(c);
	}

}
