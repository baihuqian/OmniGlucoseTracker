package ece641.March11th.graph;

import java.util.Date;

import android.view.View;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;

public class GraphViewHelper {
	private View view;
	private GraphView graphView;
	private GraphViewSeries graphViewSeries;
	public GraphViewHelper(View view, GraphView graphView, GraphViewSeries graphViewSeries) {
		this.view = view;
		this.graphView = graphView;
		this.graphViewSeries = graphViewSeries;
	}
	
	public void changeDate(Date date) {
		
	}
	public void changeWeek(Date date) {
		
	}
	public void changeMonth(Date date) {
		
	}
}
