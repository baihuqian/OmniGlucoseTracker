package ece641.March11th.IO;

import ece641.March11th.IO.CreateAccountDialog.NoticeDialogListener;
import ece641.March11th.dblayout.ODTDatabaseHelper;
import ece641.March11th.dblayout.abstractODTDatabaseHelper;
import ece641.March11th.entities.Contact;
import ece641.March11th.ui.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;

public class ShowContactDialog extends DialogFragment {
	
	int contactid;
	
	Contact contact;
	
	public interface ShowContactDialogListener {
        public void onShowContactDialogPositiveClick(DialogFragment dialog);
        public void onShowContactDialogNeutralClick(DialogFragment dialog);
        
		public void onShowContactDialogNegativeClick(DialogFragment dialog);
    }
	
	
	ShowContactDialogListener mListener;
	
	
	public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (ShowContactDialogListener) activity;
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
	    
ODTDatabaseHelper dbh=new ODTDatabaseHelper(getActivity()) ;
contact=dbh.getContact(contactid);

AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
// Get the layout inflater
LayoutInflater inflater = getActivity().getLayoutInflater();


final View layout = inflater.inflate(R.layout.fragament_showcontact, null);

TextView name=(TextView) layout.findViewById(R.id.textViewContactName);
TextView number=(TextView)layout.findViewById(R.id.textViewContactNumber);   
TextView email=(TextView) layout.findViewById(R.id.textViewContactEmail);
TextView type=(TextView) layout.findViewById(R.id.textViewContactType);	

   	name.setText(contact.getContactName());
   	number.setText(contact.getContactNumber());
   	email.setText(contact.getContactEmail());
   type.setText(contact.getContactType());

	


	
builder.setView(layout)
.setPositiveButton("Delete", new DialogInterface.OnClickListener() {

    public void onClick(DialogInterface dialog, int id) {
   
       
    	
    	
    	mListener.onShowContactDialogPositiveClick(ShowContactDialog.this);
    	
}
})


.setNeutralButton("Back", new DialogInterface.OnClickListener(){

	@Override
	public void onClick(DialogInterface dialog, int which) {
		mListener.onShowContactDialogNeutralClick(ShowContactDialog.this);
		
	}   
	
	
})





.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
    public void onClick(DialogInterface dialog, int id) {
    	mListener.onShowContactDialogNegativeClick(ShowContactDialog.this);
    }
    
    
});
     
return builder.create();
}


 		
    	
	  

	
	
	
}


