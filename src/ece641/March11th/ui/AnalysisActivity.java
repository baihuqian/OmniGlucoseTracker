package ece641.March11th.ui;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jjoe64.graphview.BarGraphView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;

import ece641.March11th.dblayout.ODTDatabaseHelper;
import ece641.March11th.entities.UserInfoConstants;
import ece641.March11th.graph.GlucoseDataConverter;
import ece641.March11th.graph.GraphDisplayConstants;

public class AnalysisActivity extends Activity implements UserInfoConstants, GraphDisplayConstants{
	private int userID;
	private GraphViewSeries graphViewSeries;
	private ODTDatabaseHelper dbHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_analysis);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		UIHelper.hideActionBar(this);
		
		Intent intent = getIntent();
		userID = intent.getIntExtra(USERID, -1);
		
		GraphViewData [] graphViewData = new GraphViewData[6];
		graphViewData[0] = new GraphViewData(0, 0); // very-low
		graphViewData[1] = new GraphViewData(1, 0); // low
		graphViewData[2] = new GraphViewData(2, 0); // low - target
		graphViewData[3] = new GraphViewData(3, 0); // target - high
		graphViewData[4] = new GraphViewData(4, 0); // high
		graphViewData[5] = new GraphViewData(5, 0); // very high
		
		graphViewSeries = new GraphViewSeries(graphViewData);
		GraphView graphView = new BarGraphView(this, "Monthly Glucose Analysis");
		graphView.addSeries(graphViewSeries);
		
		LinearLayout container = (LinearLayout) findViewById(R.id.analysisGraphContainer);
		container.addView(graphView);
		
		graphView.setHorizontalLabels(
				new String[] {
						"0 - " + Integer.toString(VERY_LOW),
						Integer.toString(VERY_LOW +1) + " - " + Integer.toString(LOW),
						Integer.toString(LOW +1) + " - " + Integer.toString(TARGET),
						Integer.toString(TARGET +1) + " - " + Integer.toString(HIGH),
						Integer.toString(HIGH +1) + " - " + Integer.toString(VERY_HIGH),
						"> " + Integer.toString(VERY_HIGH)
						});
		
		dbHelper = new ODTDatabaseHelper(this);
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		AsyncTask<Integer, Void, int [] > task = new AsyncTask<Integer, Void, int [] >() {

			@Override
			protected int[] doInBackground(Integer... userID) {
				// TODO Auto-generated method stub
				ArrayList<Double> GLList = dbHelper.getMonthGL(Calendar.getInstance(), userID[0]).getGLList();
				int [] returnValue = new int [6];
				for(int i : returnValue) {
					i = 0;
				}
				for(Double g : GLList) {
					if(g <= VERY_LOW) {
						returnValue[0]++;
					}
					else if(g <= LOW) {
						returnValue[1]++;
					}
					else if(g <= TARGET) {
						returnValue[2]++;
					}
					else if(g <= HIGH) {
						returnValue[3]++;
					}
					else if(g <= VERY_HIGH) {
						returnValue[4]++;
					}
					else {
						returnValue[5]++;
					}
				}
				
				
				return returnValue;
			}

			@Override
			protected void onPostExecute(int[] result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				GraphViewData [] graphViewData = new GraphViewData[6];
				graphViewData[0] = new GraphViewData(0, result[0]); // very-low
				graphViewData[1] = new GraphViewData(1, result[1]); // low
				graphViewData[2] = new GraphViewData(2, result[2]); // low - target
				graphViewData[3] = new GraphViewData(3, result[3]); // target - high
				graphViewData[4] = new GraphViewData(4, result[4]); // high
				graphViewData[5] = new GraphViewData(5, result[5]); // very high
				graphViewSeries.resetData(graphViewData);
			}

			

		};
		task.execute(userID);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.analysis, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	

}
