package ece641.March11th.IO;

import ece641.March11th.ui.R;
import ece641.March11th.ui.R.id;
import ece641.March11th.ui.R.layout;
import ece641.March11th.ui.R.menu;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.os.Build;

public class AddContactActivity extends Activity {
	String contacttype;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_contact);
		final Spinner contacttypeSpinner=(Spinner) findViewById(R.id.spinnerAddContact1);
	 	 
		 ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
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
	}


}
