package ece641.March11th.IO;

import ece641.March11th.IO.CreateAccountDialog.NoticeDialogListener;
import ece641.March11th.dblayout.ODTDatabaseHelper;
import ece641.March11th.entities.Data;
import ece641.March11th.ui.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;


public class ShowDataDialog extends DialogFragment {

String date;
int dataid;
String sampletype;
String Gl;
String note;
String locationstatus;


	public interface ShowDataDialogListener {
        public void onShowDataDialogPositiveClick(DialogFragment dialog);
        public void onShowDataDialogNeutralClick(DialogFragment dialog);
        public void onShowDataDialogNegativeClick(DialogFragment dialog);
    }
	
	
	ShowDataDialogListener mListener;
	
	
	public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (ShowDataDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		Bundle mArgs = getArguments();
		dataid = mArgs.getInt("dataid");
		date=mArgs.getString("date");
ODTDatabaseHelper dbh=new ODTDatabaseHelper(getActivity()) ;
Data data=dbh.getData(dataid);
		
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    // Get the layout inflater
	    LayoutInflater inflater = getActivity().getLayoutInflater();
	    
	
       final View layout = inflater.inflate(R.layout.fragament_showdata, null);
       


TextView dateview=(TextView) layout.findViewById(R.id.textViewDate);
TextView GL=(TextView)layout.findViewById(R.id.textViewGL);
TextView location=(TextView)layout.findViewById(R.id.textViewLocation);   
TextView sample=(TextView) layout.findViewById(R.id.textViewSample);
TextView note=(TextView) layout.findViewById(R.id.textViewNote);

    dateview.setText(date);
    
 	GL.setText(data.getGL());
 	if(data.getLatitude().equals("no")){location.setText("NO");}
 	else{location.setText("YES");
 		
 	}
 	
 	sample.setText(data.getSampleType());
 	note.setText(data.getNote());
    	
    
 		
	    builder.setView(layout)
	    .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {
           
               
            	
            	
            	mListener.onShowDataDialogPositiveClick(ShowDataDialog.this);
            	
        }
	    })
	    
	    
	    .setNeutralButton("Back", new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				mListener.onShowDataDialogNeutralClick(ShowDataDialog.this);
				
			}   
	    	
	    	
	    })
	    
        
	    
	    
        
        .setNegativeButton("Edit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            	mListener.onShowDataDialogNegativeClick(ShowDataDialog.this);
            }
            
            
        });
	         
	    return builder.create();
	}

	
	
	
}


