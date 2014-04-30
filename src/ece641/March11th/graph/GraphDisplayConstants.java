package ece641.March11th.graph;

import java.text.SimpleDateFormat;

import android.graphics.Color;

import com.jjoe64.graphview.GraphViewSeries;

public interface GraphDisplayConstants {
	public final static int DISPLAY_DAILY = 1;
	public final static int DISPLAY_WEEKLY = 2;
	public final static int DISPLAY_MONTHLY = 3;
	public final static int DISPLAY_LOCATION = 4;
	public final static String LAUNCH_TYPE = "LAUNCH_TYPE";
	
	public final static int VERY_LOW = 60;
	public final static int LOW = 80;
	public final static int TARGET = 180;
	public final static int HIGH = 200;
	public final static int VERY_HIGH = 300;
	
	public static final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	
	GraphViewSeries.GraphViewSeriesStyle data_style = new GraphViewSeries.GraphViewSeriesStyle(Color.CYAN, 1);
	GraphViewSeries.GraphViewSeriesStyle very_threshold = new GraphViewSeries.GraphViewSeriesStyle(Color.RED, 1);
	GraphViewSeries.GraphViewSeriesStyle threshold = new GraphViewSeries.GraphViewSeriesStyle(Color.MAGENTA, 1);
	GraphViewSeries.GraphViewSeriesStyle target_style = new GraphViewSeries.GraphViewSeriesStyle(Color.GREEN, 1);
}
