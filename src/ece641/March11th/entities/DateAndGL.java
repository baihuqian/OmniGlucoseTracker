package ece641.March11th.entities;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DateAndGL {
	private ArrayList<Calendar> datelist;
	private ArrayList<Double> gllist;
	
	public void setDateList(ArrayList<Calendar> datelist){
		this.datelist=datelist;
		
	}
	public void setGLList(ArrayList<Double> gllist){
		this.gllist=gllist;
		
	}
	
	public ArrayList<Calendar> getDateList(){
		return this.datelist;
	}

	
	public ArrayList<Double> getGLList(){
		return this.gllist;
	}
}
