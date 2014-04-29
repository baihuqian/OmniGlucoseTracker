package ece641.March11th.graph;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
	private final Calendar todayDate;
	private GraphViewSeries graphViewSeries;
	private ODTDatabaseHelper dbHelper;
	private Calendar dateToChange;
	private int userid;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	public GraphViewHelper(View view, GraphViewSeries graphViewSeries, ODTDatabaseHelper dbHelper, Calendar todayDate) {
		this.view = view;
		this.graphViewSeries = graphViewSeries;
		this.dbHelper = dbHelper;
		this.todayDate = todayDate;
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

					userid = dbHelper.getUserID("admin");
					DateAndGL data = dbHelper.getDayGL(dateToChange, userid);

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

	}
	public void changeMonth(Calendar date) {

	}
}
