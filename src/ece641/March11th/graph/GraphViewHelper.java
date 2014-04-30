package ece641.March11th.graph;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;

import ece641.March11th.dblayout.ODTDatabaseHelper;
import ece641.March11th.entities.DateAndGL;
import ece641.March11th.ui.R;

public class GraphViewHelper {
	private View view;
	private GraphView graphView;
	private final Calendar todayDate;
	private GraphViewSeries graphViewSeries;
	private ODTDatabaseHelper dbHelper;
	private Calendar dateToChange;
	private int userID;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	public GraphViewHelper(View view, GraphView graphView, GraphViewSeries graphViewSeries, 
			ODTDatabaseHelper dbHelper, Calendar todayDate, int userID) {
		this.view = view;
		this.graphViewSeries = graphViewSeries;
		this.dbHelper = dbHelper;
		this.todayDate = todayDate;
		this.graphView = graphView;
		this.userID = userID;
	}

	public void changeDate(Calendar date) {
		if(date.get(Calendar.YEAR) > todayDate.get(Calendar.YEAR) || 
				(date.get(Calendar.YEAR) == todayDate.get(Calendar.YEAR) &&
				(date.get(Calendar.MONTH) > todayDate.get(Calendar.MONTH) ||
						(date.get(Calendar.MONTH) == todayDate.get(Calendar.MONTH) && 
						date.get(Calendar.DATE) > todayDate.get(Calendar.DATE))))) {
			Toast.makeText(view.getContext(), "Most recent date!", Toast.LENGTH_LONG).show();
		}
		else {
			AsyncTask<Calendar, Void, GraphViewData[] > task = new AsyncTask<Calendar, Void, GraphViewData[] >() {

				@Override
				protected GraphViewData [] doInBackground(Calendar... date) {
					// TODO Auto-generated method stub
					dateToChange = date[0];

					//userID = dbHelper.getUserID("admin");
					DateAndGL data = dbHelper.getDayGL(dateToChange, userID);

					return GlucoseDataConverter.convertDaily(data);
				}

				@Override
				protected void onPostExecute(GraphViewData[] result) {
					// TODO Auto-generated method stub
					super.onPostExecute(result);

					String strDate= sdf.format(dateToChange.getTime());
					((TextView) view.findViewById(R.id.textViewDate)).setText(strDate);

					graphViewSeries.resetData(result);
				}


			};
			task.execute(date);
		}
	}
	public void changeWeek(Calendar date) {
		if(date.get(Calendar.YEAR) > todayDate.get(Calendar.YEAR) || 
				(date.get(Calendar.YEAR) == todayDate.get(Calendar.YEAR) &&
				(date.get(Calendar.MONTH) > todayDate.get(Calendar.MONTH) ||
						(date.get(Calendar.MONTH) == todayDate.get(Calendar.MONTH) && 
						date.get(Calendar.DATE) > todayDate.get(Calendar.DATE))))) {
			Toast.makeText(view.getContext(), "Most recent date!", Toast.LENGTH_LONG).show();
		}
		else {
			AsyncTask<Calendar, Void, GraphViewData[] > task = new AsyncTask<Calendar, Void, GraphViewData[] >() {

				@Override
				protected GraphViewData [] doInBackground(Calendar... date) {
					// TODO Auto-generated method stub
					dateToChange = date[0];

					//userID = dbHelper.getUserID("admin");
					DateAndGL data = dbHelper.getWeekGL(dateToChange, userID);

					return GlucoseDataConverter.convertWeekly(data);
				}

				@Override
				protected void onPostExecute(GraphViewData[] result) {
					// TODO Auto-generated method stub
					super.onPostExecute(result);

					String strDate= sdf.format(dateToChange.getTime());
					((TextView) view.findViewById(R.id.textViewWeekly)).setText(strDate);

					graphViewSeries.resetData(result);
				}


			};
			task.execute(date);
		}


	}
	public void changeMonth(Calendar date) {
		// initialize labels
		int dayInMonth = date.getActualMaximum(Calendar.DAY_OF_MONTH);
		String [] label = new String [dayInMonth + 1];
		for(int i = 0; i < dayInMonth; i++) {
			label[i] = Integer.toString(i + 1);
		}
		label[dayInMonth] = "";
		graphView.setHorizontalLabels(label);
		
		
		if(date.get(Calendar.YEAR) > todayDate.get(Calendar.YEAR) || 
				(date.get(Calendar.YEAR) == todayDate.get(Calendar.YEAR) &&
				(date.get(Calendar.MONTH) > todayDate.get(Calendar.MONTH) ||
						(date.get(Calendar.MONTH) == todayDate.get(Calendar.MONTH) && 
						date.get(Calendar.DATE) > todayDate.get(Calendar.DATE))))) {
			Toast.makeText(view.getContext(), "Most recent date!", Toast.LENGTH_LONG).show();
		}
		else {
			AsyncTask<Calendar, Void, GraphViewData[] > task = new AsyncTask<Calendar, Void, GraphViewData[] >() {

				@Override
				protected GraphViewData [] doInBackground(Calendar... date) {
					// TODO Auto-generated method stub
					dateToChange = date[0];

					//userID = dbHelper.getUserID("admin");
					DateAndGL data = dbHelper.getMonthGL(dateToChange, userID);

					return GlucoseDataConverter.convertMonthly(data);
				}

				@Override
				protected void onPostExecute(GraphViewData[] result) {
					// TODO Auto-generated method stub
					super.onPostExecute(result);

					String strDate= sdf.format(dateToChange.getTime());
					((TextView) view.findViewById(R.id.textViewMonthly)).setText(strDate);

					graphViewSeries.resetData(result);
				}


			};
			task.execute(date);
		}
	}
}
