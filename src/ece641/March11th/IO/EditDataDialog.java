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

public class EditDataDialog extends DialogFragment {
	int userid;
	int dataid;
	int month;
	int year;
	int dayofmonth;
	int hour;
	int minute;
	String sampletype;
	String GL;
	String note;
	Location location;
	ODTDatabaseHelper dbh;
	Data newdata;
	public interface EditDataDialogListener {
        public void onEditDataDialogPositiveClick(DialogFragment dialog,Data data);
        
        public void onEditDataDialogNegativeClick(DialogFragment dialog);
    }
	
	
	EditDataDialogListener mListener;
	
	
	public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (EditDataDialogListener) activity;
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
	    
	
       final View layout = inflater.inflate(R.layout.fragament_editdata, null);
       Bundle mArgs = getArguments();
		dataid = mArgs.getInt("dataid");
		userid=mArgs.getInt("userid");
	 dbh=new ODTDatabaseHelper(getActivity());

		Data olddata=dbh.getData(dataid);
	    DatePicker dp=(DatePicker) layout.findViewById(R.id.datePickerUpdateData);
		TimePicker tp=(TimePicker)layout.findViewById(R.id.timePickerUpdateData);
	//	tp.setIs24HourView(true);
	dp.init(olddata.getDate().get(Calendar.YEAR), olddata.getDate().get(Calendar.MONTH), olddata.getDate().get(Calendar.DAY_OF_MONTH), null);
 int tmphour=olddata.getDate().get(Calendar.HOUR);
 if(tmphour>12){tmphour=tmphour-12;}else{}
 
	tp.setCurrentHour(olddata.getDate().get(Calendar.HOUR));
   tp.setCurrentMinute(olddata.getDate().get(Calendar.MINUTE));
	   
   EditText GLText=(EditText) layout.findViewById(R.id.editTextUpdateGL);  
   EditText noteText=(EditText) layout.findViewById(R.id.updateNote); 
     
   GLText.setHint(olddata.getGL());
   noteText.setHint(olddata.getNote());
   
   final Spinner updatesampletypeSpinner=(Spinner) layout.findViewById(R.id.spinnerUpdateSituation);
	 
	 ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
	        R.array.sample_situation_array, android.R.layout.simple_spinner_item);
adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
int spinnerPosition = adapter.getPosition(olddata.getSampleType());
updatesampletypeSpinner.setSelection(spinnerPosition);
updatesampletypeSpinner.setAdapter(adapter);

updatesampletypeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent,
				View view, int position, long id) {
			// TODO Auto-generated method stub
			
			sampletype = (String) updatesampletypeSpinner.getSelectedItem();
		}
		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			
		}
});
 	   
 	
 		
    	
    	
 		
	    builder.setView(layout)
	    .setPositiveButton("Update", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {
            	Calendar calendar=Calendar.getInstance();
            	DatePicker dp=(DatePicker) layout.findViewById(R.id.datePickerUpdateData);
            	TimePicker tp=(TimePicker)layout.findViewById(R.id.timePickerUpdateData);
            	

            	 EditText GLText=(EditText) layout.findViewById(R.id.editTextUpdateGL);  
            	    EditText noteText=(EditText) layout.findViewById(R.id.updateNote); 
            	    
            	    CheckBox checkBox = (CheckBox) layout.findViewById(R.id.checkBoxUpdateLocation);
            	    
            	    calendar.set(Calendar.YEAR, dp.getYear());
            	    calendar.set(Calendar.MONTH, dp.getMonth());
            	    calendar.set(Calendar.DAY_OF_MONTH,dp.getDayOfMonth());
            	    calendar.set(Calendar.HOUR_OF_DAY,tp.getCurrentHour());
            	    calendar.set(Calendar.MINUTE,tp.getCurrentMinute());
            	    

            	
            			
            			if(noteText.getText().toString().trim().length()==0){note="no";}
            			else{	note=noteText.getText().toString().trim();}
            		
            		if(GLText.getText().toString().trim().length()==0){GL="no";}
            		else
            		{GL=GLText.getText().toString().trim();}
            		
            		if (checkBox.isChecked()) {
            			LocationHelper lh=new LocationHelper(getActivity());
            			location=lh.getBestCurrentLocation();
            			
            			newdata=new Data(dataid,calendar,location,GL,note,sampletype,userid);
            			
            		}
            		
            		else{newdata=new Data(dataid,calendar,null,GL,note,sampletype,userid);
            		
            		}
               
            	
            	
            	mListener.onEditDataDialogPositiveClick(EditDataDialog.this,newdata);
            	
        }
            })
	    
	    
	    

		
	    
        
	    
	    
        
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            	EditDataDialog.this.getDialog().cancel();
            }
            
            
        });
	         
	    return builder.create();
	}

}


