package ece641.March11th.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import com.jjoe64.graphview.GraphView.GraphViewData;

import ece641.March11th.entities.DateAndGL;



public final class GlucoseDataConverter {
	public static GraphViewData [] convertDaily(DateAndGL data) {
		int size = data.getDateList().size();
		if(size == data.getGLList().size()) {
			GraphViewData [] graphViewData = new GraphViewData[size];
			GlucoseGraphData [] graphData = new GlucoseGraphData[size];
			for(int i = 0; i < size; i++) {
				Calendar date = data.getDateList().get(i);
				double convertedTime = date.get(Calendar.HOUR) / 24.0 + date.get(Calendar.MINUTE) / (24.0 * 60.0);
				if(date.get(Calendar.AM_PM) == Calendar.PM) {
					convertedTime += 0.5;
				}
				graphData[i] = new GlucoseGraphData(convertedTime, data.getGLList().get(i));
			}
			Arrays.sort(graphData);
			for(int i = 0; i < size; i++) {
				graphViewData[i] = new GraphViewData(graphData[i].time, graphData[i].glucose);
			}
			return graphViewData;
		}
		else {
			return null;
		}
	}
	
	public static GraphViewData [] convertWeekly(DateAndGL data) {
		int size = data.getDateList().size();
		if(size == data.getGLList().size()) {
			GraphViewData [] graphViewData = new GraphViewData[size];
			GlucoseGraphData [] graphData = new GlucoseGraphData[size];
			for(int i = 0; i < size; i++) {
				Calendar date = data.getDateList().get(i);
				double convertedTime = date.get(Calendar.DAY_OF_WEEK) / 7.0 + 
						date.get(Calendar.HOUR) / (7.0 * 24.0) + 
						date.get(Calendar.MINUTE) / (7.0 * 24.0 * 60.0);
				graphData[i] = new GlucoseGraphData(convertedTime, data.getGLList().get(i));
			}
			Arrays.sort(graphData);
			for(int i = 0; i < size; i++) {
				graphViewData[i] = new GraphViewData(graphData[i].time, graphData[i].glucose);
			}
			return graphViewData;
		}
		else {
			return null;
		}
	}
	
}
