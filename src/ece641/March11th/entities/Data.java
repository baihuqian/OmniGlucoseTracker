package ece641.March11th.entities;

import java.util.Date;
import java.util.Calendar;

import android.location.Location;

public class Data {
	
	private	int eventid;
	private	int year;
	private	int month;
	private	int dayofmonth;
	private	int dayofweek;
	private	int hour;
	private	int minute;
	private	int second;
	private	String longtitude;
	private	String latitude;
	private	String GL;
	private String note;
	private	String sampletype;
	private	int userid;
	private	int weekofyear;
	
    public Data(){}
    
    
    //constructor for input data,without eventid,must have the correct userid!!!
    public Data(Calendar date,Location location,String gl,String note,String sampletype,int userid){
    	this.year=date.get(Calendar.YEAR);
    	this.month=date.get(Calendar.MONTH);
    	this.dayofmonth=date.get(Calendar.DAY_OF_MONTH);
    	this.dayofweek=date.get(Calendar.DAY_OF_WEEK);
    	this.hour=date.get(Calendar.HOUR_OF_DAY);
    	this.minute=date.get(Calendar.MINUTE);
    	this.second=date.get(Calendar.SECOND);
    	this.weekofyear=date.get(Calendar.WEEK_OF_YEAR);
    	
    	if(location!=null){
    	this.longtitude=Double.toString(location.getLongitude());
    	this.latitude=Double.toString(location.getLatitude());}
    	else{this.longtitude="no";
    	this.latitude="no";
    	}
    	this.note=note;
    	this.GL=gl;
    	
    	this.sampletype=sampletype;
    	this.userid=userid;   	
    }
    
    
  
    //constructor for output data,with eventid
    public Data(int eventid,Calendar date,String longtitude,String latitude ,String gl,String note,String sampletype,int userid){
    	this.eventid=eventid;
    	this.year=date.get(Calendar.YEAR);
    	this.month=date.get(Calendar.MONTH);
    	this.dayofmonth=date.get(Calendar.DAY_OF_MONTH);
    	this.dayofweek=date.get(Calendar.DAY_OF_WEEK);
    	this.hour=date.get(Calendar.HOUR_OF_DAY);
    	this.minute=date.get(Calendar.MINUTE);
    	this.second=date.get(Calendar.SECOND);
    	this.longtitude=longtitude;
    	this.latitude=latitude;
    	this.GL=gl;
    	this.sampletype=sampletype;
    	this.userid=userid; 
    	this.note=note;
    	this.weekofyear=date.get(Calendar.WEEK_OF_YEAR);
      	
    }
    
    
    public Data(int dataid, Calendar date,Location location,String gl,String note,String sampletype,int userid) {
    	this.eventid=dataid;
    	this.year=date.get(Calendar.YEAR);
    	this.month=date.get(Calendar.MONTH);
    	this.dayofmonth=date.get(Calendar.DAY_OF_MONTH);
    	this.dayofweek=date.get(Calendar.DAY_OF_WEEK);
    	this.hour=date.get(Calendar.HOUR_OF_DAY);
    	this.minute=date.get(Calendar.MINUTE);
    	this.second=date.get(Calendar.SECOND);
    	this.weekofyear=date.get(Calendar.WEEK_OF_YEAR);
    	
    	if(location!=null){
    	this.longtitude=Double.toString(location.getLongitude());
    	this.latitude=Double.toString(location.getLatitude());}
    	else{this.longtitude="no";
    	this.latitude="no";
    	}
    	this.note=note;
    	this.GL=gl;
    	
    	this.sampletype=sampletype;
    	this.userid=userid;  
		// TODO Auto-generated constructor stub
	}


	public Calendar getDate(){
    	Calendar date=Calendar.getInstance();
    	
    	date.set(Calendar.YEAR, this.year);
    	date.set(Calendar.MONTH, this.month);
    	date.set(Calendar.DAY_OF_MONTH, this.dayofmonth);
    	date.set(Calendar.DAY_OF_WEEK, this.dayofweek);
    	date.set(Calendar.HOUR_OF_DAY, this.hour);
    	date.set(Calendar.MINUTE, this.minute);
    	date.set(Calendar.SECOND, this.second);
    	date.set(Calendar.WEEK_OF_YEAR, this.weekofyear);
    	return date;
    	
   
    }
    
    public String getGL(){
    		
   return this.GL;	
    }


	public int getUserID() {
		// TODO Auto-generated method stub
		
		return this.userid;
	}
	
	public int getDataID() {
		// TODO Auto-generated method stub
		
		return this.eventid;
	}
	public String getSampleType() {
		// TODO Auto-generated method stub
		
		return this.sampletype;
	}

public String getLongtitude() {
		// TODO Auto-generated method stub
		return this.longtitude;
	}
	public String getLatitude() {
		// TODO Auto-generated method stub
		return this.latitude;
	}


	public String getNote() {
		// TODO Auto-generated method stub
		return this.note;
	}
	

	
    
}
