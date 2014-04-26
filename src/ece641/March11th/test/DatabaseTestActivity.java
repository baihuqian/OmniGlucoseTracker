package ece641.March11th.test;

import java.util.ArrayList;
import java.util.Calendar;

import ece641.March11th.dblayout.ODTDatabaseHelper;
import ece641.March11th.dblayout.abstractODTDatabaseHelper;
import ece641.March11th.entities.DateAndGL;
import ece641.March11th.ui.R;
import ece641.March11th.ui.R.id;
import ece641.March11th.ui.R.layout;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;
import android.os.Build;

public class DatabaseTestActivity extends Activity {
	int userid;
	ODTDatabaseHelper dbh1=new ODTDatabaseHelper(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_database_test);
		Intent intent = getIntent();
		userid=intent.getIntExtra("userID", 1);
		int tableRowID = 100;
		TableLayout	tableLayout = (TableLayout)findViewById(R.id.maintable);
		Calendar indate=Calendar.getInstance();
     DateAndGL dateandgl=dbh1.getWeekGL(indate,userid);

   ArrayList<Calendar> datelist= dateandgl.getDateList();
   ArrayList<Double> gllist=dateandgl.getGLList();
   
   int length=datelist.size();
   
   for(int i=0;i<length;i++){
	   
	 int tmphour=  datelist.get(i).get(Calendar.HOUR_OF_DAY);
	 double tmpgl=gllist.get(i);
	 TableRow row = new TableRow(this);
	 row.setPadding(1, 1, 1, 1);
     row.setId(tableRowID++);
     TextView col1 = new TextView(this);
	    TextView col2 = new TextView(this);
	 col1.setText(Integer.toString(tmphour));
	 col2.setText(Double.toString(tmpgl));
	 col1.setGravity(Gravity.LEFT);
	    col2.setGravity(Gravity.LEFT);
	    
	    LayoutParams layoutParams = new LayoutParams( 
	    		TableLayout.LayoutParams.WRAP_CONTENT, 
	    		TableLayout.LayoutParams.WRAP_CONTENT);
	    layoutParams.weight = 0.2f;
	    col1.setLayoutParams(layoutParams);
	    col2.setLayoutParams(layoutParams);
	 
	   row.addView(col1);
	    row.addView(col2);
	 
	   
	 tableLayout.addView(row); 
   }
   
   
   
   
   
   
	}

	
	
}
