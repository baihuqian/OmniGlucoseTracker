package ece641.March11th.ui;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
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

import ece641.March11th.dblayout.ODTDatabaseHelper;
import ece641.March11th.graph.GraphDisplayConstants;
import ece641.March11th.graph.GraphViewHelper;

public class MonthlyFragment extends Fragment implements GraphDisplayConstants{
	private GraphView graphView;
	private GraphViewSeries graphViewSeries;
	private Calendar date, todayDate;
	private ImageButton previous, next, cal;
	private ODTDatabaseHelper dbHelper;
	public GraphViewHelper graphHelper;
	
	OnCalSelectedListener calSelectedListener;
	
	public void setDate(Calendar date) {
		this.date = date;
	}
	public MonthlyFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_monthly, container, false);
        int userID = ((DisplayActivity) getActivity()).getUserID();
     // initialize widgets
        previous = (ImageButton) view.findViewById(R.id.imageButtonPrevious);
        next = (ImageButton) view.findViewById(R.id.imageButtonNext);
        previous.setOnClickListener(previousListener);
        next.setOnClickListener(nextListener);
        cal = (ImageButton) view.findViewById(R.id.imageButtonChooseDay);
        cal.setOnClickListener(calListener);
        
        // fetch data
        Date d = new Date();
        todayDate = new GregorianCalendar();
        date = new GregorianCalendar();
        todayDate.setTime(d);
        date.setTime(d);// store date of today
        String strDate = null;
        if(date != null) {
        	strDate = sdf.format(date.getTime());
        	((TextView) view.findViewById(R.id.textViewMonthly)).setText(strDate);
        }
        
        
        
        // format data
        GraphViewData [] graphViewData = new GraphViewData[2]; // place holder
        graphViewData[0] = new GraphViewData(0.2, 0.5);
        graphViewData[1] = new GraphViewData(0.4, 0.7);
        graphViewSeries = new GraphViewSeries("Data", data_style, graphViewData);
        // set up graphView
        graphView = new LineGraphView(view.getContext(), "Monthly Glucose Level");
        graphView.addSeries(graphViewSeries);
        //graphView.addSeries(graphViewSeries);
        LinearLayout graphViewHolder = (LinearLayout) view.findViewById(R.id.monthlyGraphViewHolder);
        graphViewHolder.addView(graphView);
        //graphView.setScrollable(true);
		//graphView.setScalable(true);
        graphView.setViewPort(0, 1);
        
        
        dbHelper = new ODTDatabaseHelper(view.getContext());
        graphHelper = new GraphViewHelper(view, graphView, graphViewSeries, dbHelper, todayDate, userID);
        graphHelper.setThreshold();
        graphHelper.changeMonth(todayDate);
        
        return view;
    }
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		
		super.onAttach(activity);
		try {
			calSelectedListener = (OnCalSelectedListener) activity;
		} catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnArticleSelectedListener");
		}
	}
	
	private OnClickListener previousListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(date.get(Calendar.MONTH) != Calendar.JANUARY) {
				date.roll(Calendar.MONTH, false); 
			}
			else {
				date.roll(Calendar.YEAR, false);
				date.set(Calendar.MONTH, Calendar.DECEMBER);
			}
			graphHelper.changeMonth(date);
		}
		
	};
	private OnClickListener nextListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(date.get(Calendar.YEAR) > todayDate.get(Calendar.YEAR) ||
					(date.get(Calendar.YEAR) == todayDate.get(Calendar.YEAR) && 
					(date.get(Calendar.MONTH) > todayDate.get(Calendar.MONTH) ||
					(date.get(Calendar.MONTH) == todayDate.get(Calendar.MONTH) && 
					 date.get(Calendar.DATE) >= todayDate.get(Calendar.DATE))))) {
				Toast.makeText(getActivity(), "Most recent date!", Toast.LENGTH_LONG).show();
			}
			else {
				if(date.get(Calendar.MONTH) == Calendar.DECEMBER) {
					date.roll(Calendar.YEAR, true);
					date.set(Calendar.MONTH, Calendar.JANUARY);
				}
				else {
					date.roll(Calendar.MONTH, true);
					
				}
					
				
		        graphHelper.changeMonth(date);
			}
		}
		
	};
	
	private OnClickListener calListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			DialogFragment newFragment = new DatePickerFragment();
		    newFragment.show(getFragmentManager(), "datePicker");
		    if(calSelectedListener != null) {
		    	calSelectedListener.onCalSeleted(DISPLAY_MONTHLY); 
		    }
		}
		
	};
}
