package ece641.March11th.IO;

import java.util.Calendar;

import ece641.March11th.IO.CreateAccountDialog.NoticeDialogListener;
import ece641.March11th.dblayout.ODTDatabaseHelper;
import ece641.March11th.entities.Data;
import ece641.March11th.map.LocationHelper;
import ece641.March11th.ui.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.AdapterView.OnItemSelectedListener;

public class AddDataDialog extends DialogFragment {
	int userid;
	int dataid;
	int month;
	int year;
	int dayofmonth;
	int hourofday;
	int hour;
	int minute;
	String sampletype;
	String GL;
	String note;
	Location location;
	ODTDatabaseHelper dbh;
	Data newdata;
	
	public interface AddDataDialogListener {
        public void onAddDataDialogPositiveClick(DialogFragment dialog,Data data);
        
        public void onAddDataDialogNegativeClick(DialogFragment dialog);
    }
	
	
	AddDataDialogListener mListener;
	
	
	public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (AddDataDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    // Get the layout inflater
	    LayoutInflater inflater = getActivity().getLayoutInflater();
	    
	
       final View layout = inflater.inflate(R.layout.fragament_adddata, null);
       Bundle mArgs = getArguments();
		
		userid=mArgs.getInt("userid");
		CheckBox checkBox = (CheckBox) layout.findViewById(R.id.checkBoxAttachLocation);
        if (checkBox.isChecked()) {checkBox.setChecked(false);
            
        }
        else{}
		

		final Spinner sapmlesituationSpinner=(Spinner) layout.findViewById(R.id.spinnerAddData);
	 	 
		 ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
		        R.array.sample_situation_array, android.R.layout.simple_spinner_item);
	 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	 sapmlesituationSpinner.setAdapter(adapter);
		
	 sapmlesituationSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent,
					View view, int position, long id) {
				// TODO Auto-generated method stub
				sampletype = (String) sapmlesituationSpinner.getSelectedItem();
				
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
     });

 	   
 	
 		
    	
    	
 		
	    builder.setView(layout)
	    .setPositiveButton("Add", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {
            	DatePicker dp=(DatePicker)layout.findViewById(R.id.datePicker1);
        		TimePicker tp=(TimePicker)layout.findViewById(R.id.timePicker1);
            	Location location;
            	Calendar calendar= Calendar.getInstance();
        		calendar.set(Calendar.YEAR,dp.getYear());
        		calendar.set(Calendar.MONTH,dp.getMonth());
        		calendar.set(Calendar.DAY_OF_MONTH, dp.getDayOfMonth());
        		calendar.set(Calendar.HOUR_OF_DAY, tp.getCurrentHour());
        		calendar.set(Calendar.MINUTE, tp.getCurrentMinute());
        		
        		
        	
        		CheckBox checkBox = (CheckBox) layout.findViewById(R.id.checkBoxAttachLocation);
        		
        		EditText gl=(EditText) layout.findViewById(R.id.editTextGL);
        		EditText noteText=(EditText) layout.findViewById(R.id.editNote);
        		note=noteText.getText().toString().trim();
        		
        		if((gl.getText().toString().trim().length()==0)&&(noteText.getText().toString().trim().length()==0)&&(!(checkBox.isChecked()))){
        		
        			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        			builder.setMessage("Nothing Valuable to be Recorded!")
        			.setTitle("Nothing Valuable!");

        			builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
        				public void onClick(DialogInterface dialog, int id) {}
        			});


        			AlertDialog dialog1 = builder.create();
        			dialog1.show();
        			
        			
        		}
        		else{
        			
        			if(noteText.getText().toString().trim().length()==0){note="no";}
        			else{	note=noteText.getText().toString().trim();}
        		
        		if(gl.getText().toString().trim().length()==0){GL="no";}
        		else
        		{GL=gl.getText().toString().trim();}
        		
        		if (checkBox.isChecked()) {
        			LocationHelper lh=new LocationHelper(getActivity());
        			location=lh.getBestCurrentLocation();
        			
        			Data data=new Data(calendar,location,GL,note,sampletype,userid);

                	mListener.onAddDataDialogPositiveClick(AddDataDialog.this,data);
        		}
        		
        		else{Data data=new Data(calendar,null,GL,note,sampletype,userid);

            	mListener.onAddDataDialogPositiveClick(AddDataDialog.this,data);
        		}
        		
        		}
            	
            	
            	
        }
            })
	    
	    
	    

		
	    
        
	    
	    
        
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            	AddDataDialog.this.getDialog().cancel();
            }
            
            
        });
	         
	    return builder.create();
	}

}


