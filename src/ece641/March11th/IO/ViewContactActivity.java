package ece641.March11th.IO;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import ece641.March11th.dblayout.ODTDatabaseHelper;
import ece641.March11th.entities.Contact;
import ece641.March11th.entities.UserInfoConstants;
import ece641.March11th.ui.R;
import ece641.March11th.ui.UIHelper;

public class ViewContactActivity extends Activity implements 
ShowContactDialog.ShowContactDialogListener,
EditContactDialog.EditContactDialogListener,
AddContactDialog.AddContactDialogListener,
UserInfoConstants {
	int contactid;
	String contactname;
	String contactnumber;
	String contactemail;
	String contacttype;
	 int userid;
	ODTDatabaseHelper dbh=new ODTDatabaseHelper(this);
	ListView listview;
	List<String> contactnamelist=new ArrayList<String>();
	ArrayList<Contact> contactlist=new ArrayList<Contact>();
	ArrayAdapter<String> contactnamedapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_contact);
		Intent intent=getIntent();
		userid=intent.getIntExtra(USERID, 1);
		UIHelper.setOrientation(this);
		//UIHelper.hideActionBar(this);
		
		

		
		contactlist=dbh.getContactList(userid);

		
		
		int length=contactlist.size();
		if(contactlist.isEmpty()){
		
		}
		else{
		 for (int i = 0; i < length; i++) 
			 contactnamelist.add(contactlist.get(i).getContactName());
		    }
		 
		
		
		
		 contactnamedapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,contactnamelist );

		
		 listview = (ListView) this.findViewById(R.id.listView1);
		 listview.setAdapter(contactnamedapter);
		
		
		 listview.setOnItemClickListener(new OnItemClickListener() {
			 

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) { 
					
					contactid=contactlist.get(position).getContactID();
					
					Bundle args = new Bundle();
					args.putInt("contactid", contactid);
					args.putInt(USERID, userid);
					ShowContactDialog dialog = new ShowContactDialog ();
					dialog .setArguments(args);
					dialog.show(getFragmentManager(), null);
					
					
					
				}
					
				
				
		
		
		 });
		}
		
		
	
	
	public void addContact(View view){
		
		Bundle args = new Bundle();
		
		args.putInt(USERID, userid);
		
		AddContactDialog dialog = new AddContactDialog();
		dialog .setArguments(args);
		dialog.show(getFragmentManager(), null);
	}

	@Override
	public void onShowContactDialogPositiveClick(DialogFragment dialog) {
		 AlertDialog.Builder builder = new AlertDialog.Builder(ViewContactActivity.this);
			builder.setMessage("Are you sure to delete this contact?")
			.setTitle("Warning:deletedata!");

			builder.setPositiveButton("Yes!", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					
					dbh.deleteContact(contactid);
					View v=getWindow().getDecorView().findViewById(android.R.id.content);
					refreshList(v);

				}
			});

			builder.setNegativeButton("Cancel!", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					



				}
			});

			AlertDialog dialog1 = builder.create();
			dialog1.show();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onShowContactDialogNeutralClick(DialogFragment dialog) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onShowContactDialogNegativeClick(DialogFragment dialog) {
		
		Bundle args = new Bundle();
		args.putInt("contactid", contactid);
		args.putInt(USERID, userid);
		EditContactDialog dialog1 = new EditContactDialog ();
		dialog1 .setArguments(args);
		dialog1.show(getFragmentManager(), null);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEditContactDialogPositiveClick(DialogFragment dialog,Contact contact) {
		// TODO Auto-generated method stub
		dbh.updateContact(contact);
		 Toast.makeText(ViewContactActivity.this, "Contact Information is Updated!", Toast.LENGTH_LONG).show();	
	}

	

	@Override
	public void onEditContactDialogNegativeClick(DialogFragment dialog) {
		// TODO Auto-generated method stub
		
	}

	

public void refreshList(View view){

	contactlist=dbh.getContactList(userid);

	
	contactnamelist=new ArrayList<String>();
	int length=contactlist.size();
	if(contactlist.isEmpty()){
		contactnamedapter.clear();
	}
	else{
	 for (int i = 0; i < length; i++) {
		 contactnamelist.add(contactlist.get(i).getContactName());
		 contactnamedapter.clear();
		 contactnamedapter.addAll(contactnamelist);
	    }
	    
	    
	
	
	 
	 
	 contactnamedapter.notifyDataSetChanged();
	
}	
	
	
	
		
}

@Override
public void onAddContactDialogPositiveClick(DialogFragment dialog,
		Contact contact) {
	dbh.addContact(contact);
	View v=getWindow().getDecorView().findViewById(android.R.id.content);
	refreshList(v);
	
	// TODO Auto-generated method stub
	
}

@Override
public void onAddContactDialogNegativeClick(DialogFragment dialog) {
	// TODO Auto-generated method stub
	
}}


