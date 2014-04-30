package ece641.March11th.graph;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

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

public class GraphViewHelper implements GraphDisplayConstants{
	private View view;
	private GraphView graphView;
	private final Calendar todayDate;
	private GraphViewSeries graphViewSeries;
	private ODTDatabaseHelper dbHelper;
	private Calendar dateToChange;
	private int userID;
	private DateAndGL data;

	private TextView num, max, min, avg;

	public GraphViewHelper(View view, GraphView graphView, GraphViewSeries graphViewSeries, 
			ODTDatabaseHelper dbHelper, Calendar todayDate, int userID) {
		this.view = view;
		this.graphViewSeries = graphViewSeries;
		this.dbHelper = dbHelper;
		this.todayDate = todayDate;
		this.graphView = graphView;
		this.userID = userID;

		num = (TextView) view.findViewById(R.id.textViewNumDataPoints);
		max = (TextView) view.findViewById(R.id.textViewMax);
		min = (TextView) view.findViewById(R.id.textViewMin);
		avg = (TextView) view.findViewById(R.id.textViewAvg);
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
					data = dbHelper.getDayGL(dateToChange, userID);

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
			while(data == null) {}
			if(data != null) {
				setStatistics(data);
				data = null;
			}
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
					data = dbHelper.getWeekGL(dateToChange, userID);

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
			while(data == null) {}
			if(data != null) {
				setStatistics(data);
				data = null;
			}
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
					data = dbHelper.getMonthGL(dateToChange, userID);
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
			while(data == null) {}
			if(data != null) {
				setStatistics(data);
				data = null;
			}
		}
	}

	public void setThreshold() {

		GraphViewData[] very_high_data = new GraphViewData[2];
		very_high_data[0] = new GraphViewData(0, VERY_HIGH);
		very_high_data[1] = new GraphViewData(1, VERY_HIGH);
		GraphViewSeries very_high = new GraphViewSeries("Very high" , very_threshold, very_high_data);

		GraphViewData[] very_low_data = new GraphViewData[2];
		very_low_data[0] = new GraphViewData(0, VERY_LOW);
		very_low_data[1] = new GraphViewData(1, VERY_LOW);
		GraphViewSeries very_low = new GraphViewSeries("Very low" , very_threshold, very_low_data);

		GraphViewData[] high_data = new GraphViewData[2];
		high_data[0] = new GraphViewData(0, HIGH);
		high_data[1] = new GraphViewData(1, HIGH);
		GraphViewSeries high = new GraphViewSeries("High", threshold, high_data);

		GraphViewData[] low_data = new GraphViewData[2];
		low_data[0] = new GraphViewData(0, LOW);
		low_data[1] = new GraphViewData(1, LOW);
		GraphViewSeries low = new GraphViewSeries("Low", threshold, low_data);

		GraphViewData[] target_data = new GraphViewData[2];
		target_data[0] = new GraphViewData(0, TARGET);
		target_data[1] = new GraphViewData(1, TARGET);
		GraphViewSeries target = new GraphViewSeries("Target", target_style, target_data);

		graphView.addSeries(very_high);
		graphView.addSeries(very_low);
		graphView.addSeries(high);
		graphView.addSeries(low);
		graphView.addSeries(target);
		graphView.setManualYAxisBounds(350, 0);
		graphView.setShowLegend(true);
		graphView.setLegendWidth(180);

	}

	public void setStatistics(DateAndGL data) {
		ArrayList<Double> glucoseData = data.getGLList();
		if(glucoseData == null) {
			num.setText("");
			max.setText("");
			min.setText("");
			avg.setText("");
			return;
		} else {
			int dataSize = glucoseData.size();
			if(dataSize != 0) {
				num.setText(Integer.toString(dataSize));
				double maxValue = Collections.max(glucoseData);
				double minValue = Collections.min(glucoseData);
				double avgValue = 0;
				for(Double g : glucoseData) {
					avgValue += g;
				}
				avgValue /= dataSize;

				max.setText(String.format("%.2f", maxValue));
				min.setText(String.format("%.2f", minValue));
				avg.setText(String.format("%.2f", avgValue));
				return;
			}
			else {
				num.setText("");
				max.setText("");
				min.setText("");
				avg.setText("");
				return;
			}
		}
	}
}
