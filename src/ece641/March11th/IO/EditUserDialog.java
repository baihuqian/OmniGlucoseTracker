package ece641.March11th.IO;

import ece641.March11th.IO.CreateAccountDialog.NoticeDialogListener;
import ece641.March11th.dblayout.ODTDatabaseHelper;
import ece641.March11th.entities.User;
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
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class EditUserDialog extends DialogFragment {
	
	private String updategender;
	private int updateage;
	private int userid;
	private String updatename;
	private String	updateloginname;
	private  String	updatepassword1;
	private  String	updatepassword2;
	private double updateheight;
	private double updateweight;
	
	  ODTDatabaseHelper dbh;
	
	public interface EditUserDialogListener {
        public void onEditUserDialogPositiveClick(DialogFragment dialog,User user);
      
        public void onEditUserDialogNegativeClick(DialogFragment dialog);

    }
	
	
	EditUserDialogListener mListener;
	
	
	public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (EditUserDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
			Bundle mArgs = getArguments();
			userid=mArgs.getInt("userid");
		
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    // Get the layout inflater
	    LayoutInflater inflater = getActivity().getLayoutInflater();
	    
	
       final View layout = inflater.inflate(R.layout.fragament_edituser, null);
       
 dbh=new ODTDatabaseHelper(getActivity());
		User user=dbh.getUser(userid);
		final Spinner genderSpinner=(Spinner) layout.findViewById(R.id.updateGender);
	 	 
		 ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
		        R.array.gender_array, android.R.layout.simple_spinner_item);
	 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		genderSpinner.setAdapter(adapter);
		int spinnerPosition = adapter.getPosition(user.getGender());

		//set the default according to value
		genderSpinner.setSelection(spinnerPosition);
  	genderSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent,
					View view, int position, long id) {
				// TODO Auto-generated method stub
				updategender = (String) genderSpinner.getSelectedItem();
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
      });

		
		
		EditText updateUsername = (EditText) layout.findViewById(R.id.editTextUpdateLoginName);
		EditText updatePassword1 = (EditText) layout.findViewById(R.id.editTextUpdatePassword);
		EditText updatePassword2 = (EditText) layout.findViewById(R.id.editTextCheckUpdatePassword);
		EditText updateName = (EditText) layout.findViewById(R.id.editTextUpdateName);
		EditText updateAge = (EditText) layout.findViewById(R.id.editTextUpdateAge);
		EditText updateHeight = (EditText) layout.findViewById(R.id.editTextUpdateHeight);
		EditText updateWeight = (EditText) layout.findViewById(R.id.editTextUpdateWeight);
		updateUsername.setHint(user.getLoginName());
	
		updateName.setHint(user.getUserName());
		updateAge.setHint(Integer.toString(user.getAge()));
		updateHeight.setHint(Double.toString(user.getHeight()));
		updateWeight.setHint(Double.toString(user.getWeight()));
 	
 		
    	
    	
 		
	    builder.setView(layout)
	    .setPositiveButton("Update", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {		
            	EditText updateUsername = (EditText) layout.findViewById(R.id.editTextUpdateLoginName);
    		EditText updatePassword1 = (EditText) layout.findViewById(R.id.editTextUpdatePassword);
    		EditText updatePassword2 = (EditText) layout.findViewById(R.id.editTextCheckUpdatePassword);
    		EditText updateName = (EditText) layout.findViewById(R.id.editTextUpdateName);
    		EditText updateAge = (EditText) layout.findViewById(R.id.editTextUpdateAge);
    		EditText updateHeight = (EditText) layout.findViewById(R.id.editTextUpdateHeight);
    		EditText updateWeight = (EditText) layout.findViewById(R.id.editTextUpdateWeight);
    		updateloginname=updateUsername.getText().toString().trim();
        	updatepassword1=updatePassword1.getText().toString().trim();
        	updatepassword2=updatePassword2.getText().toString().trim();
        	
        	
        	updatename=updateName.getText().toString().trim();
    		
        if(updateAge.getText().toString().trim().length()==0){}
    	else
    	{updateage=Integer.parseInt(updateAge.getText().toString().trim());}
        
        
        if(updateHeight.getText().toString().trim().length()==0){}
      	else
      	{updateheight=Double.parseDouble(updateHeight.getText().toString().trim());}
        
        
        if(updateWeight.getText().toString().trim().length()==0){}
      	else
      	{updateweight=Double.parseDouble(updateWeight.getText().toString().trim());}
        
        if(dbh.checkIfLoginNameExist(updateloginname)){
    		
    		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    		builder.setMessage("The Login Name Exists!" )
    	       .setTitle("Login Name Problem!");
    		
    		builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {}
    	       });

    		
    		AlertDialog dialog1 = builder.create();
    		dialog1.show();	
    		
    	
    		
    	}
        else if(updatepassword1.equals(updatepassword2)){
    		User usertba=new User(userid,updatename,updateage,updategender,updateloginname,updatepassword1,updateheight,updateweight);
    		
    		mListener.onEditUserDialogPositiveClick(EditUserDialog.this, usertba);
    		
    		}



    else{
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	builder.setMessage("Password Doesn't Match!" )
          .setTitle("Password Doesn't Match!");
    	
    	builder.setPositiveButton("Retry", new DialogInterface.OnClickListener(){
              public void onClick(DialogInterface dialog, int id) {}
          });

    	
    	AlertDialog dialog1 = builder.create();
    	dialog1.show();	
    }
            
        	
        	
        	
            	
        }
	    })
	    
	    
	
        
	    
	    
        
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            	EditUserDialog.this.getDialog().cancel();
            }
            
            
        });
	         
	    return builder.create();
	}

}


