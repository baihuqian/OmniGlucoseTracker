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
	private	double longtitude;
	private	double latitude;
	private	double GL;
	private	String activity;
	private	int sampletype;
	private	int userid;
	private	int weekofyear;
	
    public Data(){}
    
    
    //constructor for input data,without eventid,must have the correct userid!!!
    public Data(Calendar date,Location location,double gl,String activity,int sampletype,int userid){
    	this.year=date.get(Calendar.YEAR);
    	this.month=date.get(Calendar.MONTH);
    	this.dayofmonth=date.get(Calendar.DAY_OF_MONTH);
    	this.dayofweek=date.get(Calendar.DAY_OF_WEEK);
    	this.hour=date.get(Calendar.HOUR_OF_DAY);
    	this.minute=date.get(Calendar.MINUTE);
    	this.second=date.get(Calendar.SECOND);
    	this.weekofyear=date.get(Calendar.WEEK_OF_YEAR);
    	
    	if(location!=null){
    	this.longtitude=location.getLongitude();
    	this.latitude=location.getLatitude();}
    	else{this.longtitude=1000;
    	this.latitude=1000;
    	}
    	
    	this.GL=gl;
    	this.activity=activity;
    	this.sampletype=sampletype;
    	this.userid=userid;   	
    }
    
    
  
    //constructor for output data,with eventid
    public Data(int eventid,Calendar date,double longtitude,double latitude ,double gl,String activity,int sampletype,int userid){
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
    	this.sampletype=sampletype;
    	this.userid=userid; 
    	this.weekofyear=date.get(Calendar.WEEK_OF_YEAR);
      	
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
    
    public Double getGL(){
    		
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


	public double getLongtitude() {
		// TODO Auto-generated method stub
		return this.longtitude;
	}
	public double getLatitude() {
		// TODO Auto-generated method stub
		return this.latitude;
	}


	public String getActivity() {
		// TODO Auto-generated method stub
		return this.activity;
	}
	public int getSampleType() {
		// TODO Auto-generated method stub
		return this.sampletype;
	}

	
    
}
