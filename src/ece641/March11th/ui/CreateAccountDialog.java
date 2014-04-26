package ece641.March11th.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class CreateAccountDialog extends DialogFragment {

	public String name="df";
	public String gender="df";
	public int age=0;
	public String loginname="df";
	public String password1="df";
	public String password2="df";
	
	public interface NoticeDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }
	
	
	NoticeDialogListener mListener;
	
	
	public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (NoticeDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    // Get the layout inflater
	    LayoutInflater inflater = getActivity().getLayoutInflater();
	    
	
       final View layout = inflater.inflate(R.layout.dialog_create_account, null);
 	   
 	   final Spinner genderSpinner=(Spinner) layout.findViewById(R.id.chooseGender);
 	 
 		 ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
 		        R.array.gender_array, android.R.layout.simple_spinner_item);
 	 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 		genderSpinner.setAdapter(adapter);
 		
 		
	    builder.setView(layout)
	    .setPositiveButton("Create", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {
           
                EditText inusername=(EditText) layout.findViewById(R.id.editTextCreateUsername);
            	EditText inpassword1=(EditText) layout.findViewById(R.id.editTextCreatePassword);
            	EditText inpassword2=(EditText) layout.findViewById(R.id.editTextCheckCreatePassword);
            	EditText inname=(EditText) layout.findViewById(R.id.editTextCreateName);
            	EditText inage=(EditText) layout.findViewById(R.id.editTextCreateAge);
            	
            	
            	genderSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						// TODO Auto-generated method stub
						gender = parent.getItemAtPosition(position).toString();
					}
					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						// TODO Auto-generated method stub
					}
                });
            	
            	loginname=inusername.getText().toString();
            	password1=inpassword1.getText().toString();
            	password2=inpassword1.getText().toString();
            	
            	name=inname.getText().toString();
            	if(inage.getText().length()==0){age=0;}
            	else
            	{age=Integer.parseInt(inage.getText().toString());}
            	
        }
	    })
        
        
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
              
            }
        });
	         
	    return builder.create();
	}

}

