package ece641.March11th.IO;

import java.util.ArrayList;
import java.util.List;

import ece641.March11th.dblayout.ODTDatabaseHelper;
import ece641.March11th.entities.Contact;
import ece641.March11th.ui.R;
import ece641.March11th.ui.R.id;
import ece641.March11th.ui.R.layout;
import ece641.March11th.ui.R.menu;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.os.Build;

public class ViewContactActivity extends Activity {
	
	String contactname;
	String contactnumber;
	String contactemail;
	String contacttype;
	int userid;
	ODTDatabaseHelper dbh=new ODTDatabaseHelper(this);
	String row="row";
	String rowedit="rowedit";
	String rowdelete="rowdelete";
	List<String> contactnamelist=new ArrayList<String>();
	ArrayList<Contact> contactlist=new ArrayList<Contact>();
	ArrayAdapter<String> contactnamedapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_contact);
		Intent intent=getIntent();
		userid=intent.getIntExtra("userid", 1);
		
		
		

		
		contactlist=dbh.getContactList(userid);

		
		
		int length=contactlist.size();
		if(length==0){
		
		}
		else{
		 for (int i = 0; i < length; i++) {
			 contactnamelist.add(contactlist.get(i).getContactName());
		    }
		 
		
		 ListView listview = (ListView) findViewById(R.id.listViewContact);
		 contactnamedapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,contactnamelist );
//
		 listview.setAdapter(contactnamedapter);}
		
	}
	
	

		
	
	public void addContact(View view){
		
		Intent intent=new Intent(this,AddContactActivity.class);
		startActivity(intent);
	}
	
	
	
	public void refreshContact(View view){
		
		contactlist=dbh.getContactList(userid);

		
		int length=contactlist.size();
		contactnamelist=new ArrayList<String>();
		if(length==0){
		
			
		}
		else{
		 for (int i = 0; i < length; i++) {
			 contactnamelist.add(contactlist.get(i).getContactName());
		    }
		 
		
		 contactnamedapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,contactnamelist );
		 ListView listview = (ListView) findViewById(R.id.listViewContact);
		 listview.setAdapter(contactnamedapter);
		 dbh.close();		 
	}
		
}

}