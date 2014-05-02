package ece641.March11th.IO;

import ece641.March11th.IO.CreateAccountDialog.NoticeDialogListener;
import ece641.March11th.dblayout.ODTDatabaseHelper;
import ece641.March11th.entities.Contact;
import ece641.March11th.ui.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class AddContactDialog extends DialogFragment {
	
	
	
	int userid;
	int contactid;
	String contactname;
	String contactnumber;
	String contactemail;
	String contacttype;
	
	
	Context context;
	

	
	public interface AddContactDialogListener {
        public void onAddContactDialogPositiveClick(DialogFragment dialog,Contact contact);
      
        public void onAddContactDialogNegativeClick(DialogFragment dialog);

    }
	
	
	AddContactDialogListener mListener;
	
	
	public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (AddContactDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
			Bundle mArgs = getArguments();
			userid=mArgs.getInt("userid", userid);
		

		    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		    // Get the layout inflater
		    LayoutInflater inflater = getActivity().getLayoutInflater();
		    
		
	       final View layout = inflater.inflate(R.layout.fragament_addcontact, null);
	       

			
			final Spinner contacttypeSpinner=(Spinner) layout.findViewById(R.id.spinnerAddContactType);
		 	 
			 ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
			        R.array.contact_type_array, android.R.layout.simple_spinner_item);
		 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		 contacttypeSpinner.setAdapter(adapter);
			
		 contacttypeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
				@Override
				public void onItemSelected(AdapterView<?> parent,
						View view, int position, long id) {
					// TODO Auto-generated method stub
					contacttype = (String) contacttypeSpinner.getSelectedItem();
				}
				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					
				}
	      });
	   
      
    	
    	
 		
	    builder.setView(layout)
	    .setPositiveButton("Add", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {
            	EditText contactnameText=(EditText) layout.findViewById(R.id.editTextAddContactName); 
            	EditText contactnumberText=(EditText) layout.findViewById(R.id.editTextEditContactNumber); 
            	EditText contactemailText=(EditText) layout.findViewById(R.id.editTextEditContactEmail); 
            	
            	if((contactnameText.getText().toString().trim().length()==0)
            			|(contactnumberText.getText().toString().trim().length()==0)
            			|(contactemailText.getText().toString().trim().length()==0)
            			){
            		
            		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            		builder.setMessage("Not Valid Contact!")
            		.setTitle("Not Valid Contact!");

            		builder.setPositiveButton("Return", new DialogInterface.OnClickListener() {
            			public void onClick(DialogInterface dialog, int id) {}
            		});


            		AlertDialog dialog1 = builder.create();
            		dialog1.show();	
            		
            	}
            	else{
            			contactname=contactnameText.getText().toString();
            	
            	
            	if(contactnumberText.getText().toString().trim().length()==0){}
            	
            		else{
            			contactnumber=contactnumberText.getText().toString();
            	}
            	
            	if(contactemailText.getText().toString().trim().length()==0){}
            	
            	else{
            		contactemail=contactemailText.getText().toString();
            }
Contact contact=new Contact(contactname,contactnumber,contactemail,contacttype,userid);
            	
            	mListener.onAddContactDialogPositiveClick(AddContactDialog.this,contact);
            	}
            	
            	
        }
	    })
	    
	    
	
        
	    
	    
        
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            	AddContactDialog.this.getDialog().cancel();
            }
            
            
        });
	         
	    return builder.create();
	}

}


