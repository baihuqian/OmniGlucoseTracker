package ece641.March11th.ui;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

public class DailyFragment extends Fragment {
	private GraphView graphView;
	private GraphViewSeries graphViewSeries;
	private Calendar date, todayDate;
	private ImageButton previous, next;
	
	public DailyFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_daily, container, false);
        
        // initialize widgets
        previous = (ImageButton) view.findViewById(R.id.imageButtonPrevious);
        next = (ImageButton) view.findViewById(R.id.imageButtonNext);
        previous.setOnClickListener(previousListener);
        next.setOnClickListener(nextListener);
        
        // fetch data
        Date d = new Date();
        todayDate = new GregorianCalendar();
        date = new GregorianCalendar();
        todayDate.setTime(d);
        date.setTime(d);// store date of today
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String strDate = null;
        if(date != null) {
        	strDate = sdf.format(date.getTime());
        	((TextView) view.findViewById(R.id.textViewDate)).setText(strDate);
        }
        
        Cursor dataCursor = fetchData(date);
        
        // format data
        GraphViewData [] graphViewData = new GraphViewData[2]; // place holder
        
        graphViewSeries = new GraphViewSeries(graphViewData);
        // set up graphView
        graphView = new LineGraphView(view.getContext(), "Daily Glucose Level");
        //graphView.addSeries(graphViewSeries);
        LinearLayout graphViewHolder = (LinearLayout) view.findViewById(R.id.dailyGraphViewHolder);
        graphViewHolder.addView(graphView);
        graphView.setScrollable(true);
		graphView.setScalable(true);
        return view;
    }
	
	private Cursor fetchData(Calendar date) {
		return null;
	}
	
	private OnClickListener previousListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			date.roll(Calendar.DATE, false); // test
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	        String strDate = null;
	        if(date != null) {
	        	strDate = sdf.format(date.getTime());
	        	((TextView) getActivity().findViewById(R.id.textViewDate)).setText(strDate);
	        }
	        
			GraphViewData [] graphViewData = new GraphViewData[1];
			
			
			graphViewSeries.resetData(graphViewData); // feed new data to the data series
			graphView.scrollToEnd(); // scroll to end to animate updating
		}
		
	};
	private OnClickListener nextListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(date.get(Calendar.MONTH) > todayDate.get(Calendar.MONTH) ||
					(date.get(Calendar.MONTH) == todayDate.get(Calendar.MONTH) && 
							date.get(Calendar.DATE) >= todayDate.get(Calendar.DATE))) {
				Toast.makeText(getActivity(), "Most recent date!", Toast.LENGTH_LONG).show();
			}
			else {
				date.roll(Calendar.DATE, true);
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		        String strDate = null;
		        if(date != null) {
		        	strDate = sdf.format(date.getTime());
		        	((TextView) getActivity().findViewById(R.id.textViewDate)).setText(strDate);
		        }
				//
			}
		}
		
	};
}
