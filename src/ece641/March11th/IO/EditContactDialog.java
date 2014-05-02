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
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class EditContactDialog extends DialogFragment {
	
	
	
	int userid;
	int contactid;
	String contactname;
	String contactnumber;
	String contactemail;
	String contacttype;
	
	
	Context context;
	
	ODTDatabaseHelper dbh;
	
	public interface EditContactDialogListener {
        public void onEditContactDialogPositiveClick(DialogFragment dialog,Contact contact);
      
        public void onEditContactDialogNegativeClick(DialogFragment dialog);

    }
	
	
	EditContactDialogListener mListener;
	
	
	public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (EditContactDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
			Bundle mArgs = getArguments();
			contactid = mArgs.getInt("contactid");
			userid=mArgs.getInt("userid", userid);
		 dbh=new ODTDatabaseHelper(getActivity());
		
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    // Get the layout inflater
	    LayoutInflater inflater = getActivity().getLayoutInflater();
	    
	
       final View layout = inflater.inflate(R.layout.fragament_editcontact, null);
       

       Contact oldcontact=dbh.getContact(contactid);
		
      
		EditText nameText=(EditText) layout.findViewById(R.id.editTextEditContactName); 
		EditText numberText=(EditText) layout.findViewById(R.id.editTextEditContactNumber); 
		EditText emailText=(EditText) layout.findViewById(R.id.editTextEditContactEmail); 	
	    final Spinner typeSpinner=(Spinner) layout.findViewById(R.id.spinnerEditContactType); 	
	    
	    nameText.setHint(oldcontact.getContactName());
	    numberText.setHint(oldcontact.getContactNumber());
	    emailText.setHint(oldcontact.getContactEmail());
	    
	 	
		 ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
		        R.array.contact_type_array, android.R.layout.simple_spinner_item);
	 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	 typeSpinner.setAdapter(adapter);
	 int spinnerPosition = adapter.getPosition(oldcontact.getContactType());

		//set the default according to value
	 typeSpinner.setSelection(spinnerPosition);
	 typeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent,
					View view, int position, long id) {
				// TODO Auto-generated method stub
				contacttype = (String) typeSpinner.getSelectedItem();
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
    });
 	
 		
    	
    	
 		
	    builder.setView(layout)
	    .setPositiveButton("Update", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {
            	EditText nameText=(EditText) layout.findViewById(R.id.editTextEditContactName); 
        		EditText numberText=(EditText) layout.findViewById(R.id.editTextEditContactNumber); 
        		EditText emailText=(EditText) layout.findViewById(R.id.editTextEditContactEmail); 	
        		
        	
        	
        		contactname=nameText.getText().toString().trim();
        	
        		 contactnumber=numberText.getText().toString().trim();
        		
        		 contactemail=emailText.getText().toString().trim();
        		 
        		Contact contact=new Contact(contactid,contactname,contactnumber,contactemail,contacttype,userid);
           
               
            	
            	
            	mListener.onEditContactDialogPositiveClick(EditContactDialog.this,contact);
            	
        }
	    })
	    
	    
	
        
	    
	    
        
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            	EditContactDialog.this.getDialog().cancel();
            }
            
            
        });
	         
	    return builder.create();
	}

}


