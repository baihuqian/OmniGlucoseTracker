package ece641.March11th.IO;

import ece641.March11th.ui.R;
import ece641.March11th.ui.R.layout;
import ece641.March11th.ui.R.string;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AddActivityDialogFragment extends DialogFragment {

	
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    // Get the layout inflater
	    LayoutInflater inflater = getActivity().getLayoutInflater();


	    builder.setView(inflater.inflate(R.layout.dialog_add_activity, null))
	    // Add action buttons
	           .setPositiveButton(R.string.submit, new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	            	   // dismiss
	                   dismiss();
	               }
	           })
	           .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	            	   dismiss();
	               }
	           });      
	    return builder.create();
	}

	public View OnCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.dialog_add_activity, container, false);
		
		
		return v;}
	
}
