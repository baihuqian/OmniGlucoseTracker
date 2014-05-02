package ece641.March11th.test;

import java.util.Calendar;

import android.content.Context;
import android.location.Location;
import ece641.March11th.dblayout.ODTDatabaseHelper;
import ece641.March11th.entities.Contact;
import ece641.March11th.entities.Data;
import ece641.March11th.entities.User;
import ece641.March11th.map.LocationHelper;

public class BuildTestDatabase {
	private ODTDatabaseHelper db;
	Context context;
	 public  BuildTestDatabase(Context context,ODTDatabaseHelper db){
	       this.context = context;
	       this.db=db;
	  }
	
	public void buildDatabase(){
		User user1=new User("admin", 26, "Male", "admin", "admin",175,60);
		User user2=new User("abc", 12, "Male", "abc11", "abc111",175,60);
		User user3=new User("abc2", 15, "Female", "abc22", "abc22",175,60);
		User user4=new User("abc3", 18, "Female", "abc33", "abc333",175,60);
		//build USERtable
		db.addUser(user1);
		db.addUser(user2);
		db.addUser(user3);
		db.addUser(user4);
		
		int adminid=db.getUserID("admin");
		//build DATA Table
		Calendar date11=Calendar.getInstance();
		Calendar date12=Calendar.getInstance();
		Calendar date13=Calendar.getInstance();
		Calendar date14=Calendar.getInstance();
		Calendar date15=Calendar.getInstance();
		Calendar date16=Calendar.getInstance();
		Calendar date17=Calendar.getInstance();
		Calendar date18=Calendar.getInstance();
		
		Calendar date21=Calendar.getInstance();
		Calendar date22=Calendar.getInstance();
		Calendar date23=Calendar.getInstance();
		Calendar date24=Calendar.getInstance();
		Calendar date25=Calendar.getInstance();
		Calendar date26=Calendar.getInstance();
		Calendar date27=Calendar.getInstance();
		Calendar date28=Calendar.getInstance();
		
		//modify the dates to simulate the data base
		date11.set(Calendar.HOUR_OF_DAY, 8);
		date12.set(Calendar.HOUR_OF_DAY, 10);
		date13.set(Calendar.HOUR_OF_DAY, 12);
		date14.set(Calendar.HOUR_OF_DAY, 14);
		date15.set(Calendar.HOUR_OF_DAY, 16);
		date16.set(Calendar.HOUR_OF_DAY, 18);
		date17.set(Calendar.HOUR_OF_DAY, 20);
		date18.set(Calendar.HOUR_OF_DAY, 22);
		
		date21.set(Calendar.HOUR_OF_DAY,8);
		date22.set(Calendar.HOUR_OF_DAY,10);
		date23.set(Calendar.HOUR_OF_DAY,12);
		date24.set(Calendar.HOUR_OF_DAY,14);
		date25.set(Calendar.HOUR_OF_DAY,16);
		date26.set(Calendar.HOUR_OF_DAY,18);
		date27.set(Calendar.HOUR_OF_DAY,20);
		date28.set(Calendar.HOUR_OF_DAY,22);
		int temday=date11.get(Calendar.DAY_OF_MONTH)-1;
		
		date21.set(Calendar.DAY_OF_MONTH,temday);
		date22.set(Calendar.DAY_OF_MONTH,temday);
		date23.set(Calendar.DAY_OF_MONTH,temday);
		date24.set(Calendar.DAY_OF_MONTH,temday);
		date25.set(Calendar.DAY_OF_MONTH,temday);
		date26.set(Calendar.DAY_OF_MONTH,temday);
		date27.set(Calendar.DAY_OF_MONTH,temday);
		date28.set(Calendar.DAY_OF_MONTH,temday);
		
		
		
	
		LocationHelper lh=new LocationHelper(context);
		double tlo=lh.getBestCurrentLocation().getLongitude();
		double tla=lh.getBestCurrentLocation().getLatitude();
		
		Location l11=lh.getBestCurrentLocation();
		Location l12=lh.getBestCurrentLocation();
		Location l13=lh.getBestCurrentLocation();
		Location l14=lh.getBestCurrentLocation();
		Location l15=lh.getBestCurrentLocation();
		Location l16=lh.getBestCurrentLocation();
		Location l17=lh.getBestCurrentLocation();
		Location l18=lh.getBestCurrentLocation();
	
		
		Location l21=lh.getBestCurrentLocation();
		Location l22=lh.getBestCurrentLocation();
		Location l23=lh.getBestCurrentLocation();
		Location l24=lh.getBestCurrentLocation();
		Location l25=lh.getBestCurrentLocation();
		Location l26=lh.getBestCurrentLocation();
		Location l27=lh.getBestCurrentLocation();
		Location l28=lh.getBestCurrentLocation();
		
		
		l11.setLongitude(tlo-0.0001);
		l11.setLatitude(tla-0.0001);
		
		l12.setLongitude(tlo-0.0002);
		l12.setLatitude(tla-0.0002);
		
		l13.setLongitude(tlo+0.0001);
		l13.setLatitude(tla+0.0001);
		
		l14.setLongitude(tlo+0.0002);
		l14.setLatitude(tla+0.0002);
		
		l15.setLongitude(tlo-0.0003);
		l15.setLatitude(tla-0.0003);
		
		l16.setLongitude(tlo+0.0003);
		l16.setLatitude(tla+0.0003);
		
		l17.setLongitude(tlo-0.0004);
		l17.setLatitude(tla-0.0004);
		
		l18.setLongitude(tlo+0.0004);
		l18.setLatitude(tla+0.0004);
		
		l21.setLongitude(tlo-0.0021);
		l21.setLatitude(tla-0.0011);
		
		l22.setLongitude(tlo-0.0052);
		l22.setLatitude(tla-0.0032);
		
		l23.setLongitude(tlo+0.0031);
		l23.setLatitude(tla+0.0021);
		
		l24.setLongitude(tlo+0.0052);
		l24.setLatitude(tla+0.0002);
		
		l25.setLongitude(tlo-0.0023);
		l25.setLatitude(tla-0.0003);
		
		l26.setLongitude(tlo+0.0033);
		l26.setLatitude(tla+0.0003);
		
		l27.setLongitude(tlo-0.0024);
		l27.setLatitude(tla-0.0004);
		
		l28.setLongitude(tlo+0.0004);
		l28.setLatitude(tla+0.0054);
		
		

	
		
		Data data11=new Data(date11,null,"130","no","Wake Up",adminid);
		Data data12=new Data(date12,null,"125","no","Wake Up",adminid);
		Data data13=new Data(date13,l13,"167","no","Wake Up",adminid);
		Data data14=new Data(date14,l14,"98","no","Wake Up",adminid);
		Data data15=new Data(date15,l15,"130","no","Wake Up",adminid);
		Data data16=new Data(date16,l16,"158","no","Wake Up",adminid);
		Data data17=new Data(date17,l17,"88","no","Wake Up",adminid);
		Data data18=new Data(date18,l18,"189","no","Wake Up",adminid);
		
		Data data21=new Data(date21,l21,"110","no","Wake Up",adminid);
		Data data22=new Data(date22,l22,"152","no","Wake Up",adminid);
		Data data23=new Data(date23,l23,"148","no","Wake Up",adminid);
		Data data24=new Data(date24,l24,"122","no","Wake Up",adminid);
		Data data25=new Data(date25,l25,"105","no","Wake Up",adminid);
		Data data26=new Data(date26,l26,"148","no","Wake Up",adminid);
		Data data27=new Data(date27,l27,"112","no","Wake Up",adminid);
		Data data28=new Data(date28,l28,"79","no","Wake Up",adminid);
		
		
		
		
		db.addData(data11);
		db.addData(data12);
		db.addData(data13);
		db.addData(data14);
		db.addData(data15);
		db.addData(data16);
		db.addData(data17);
		db.addData(data18);
	
		db.addData(data21);
		db.addData(data22);
		db.addData(data23);
		db.addData(data24);
		db.addData(data25);
		db.addData(data26);
		db.addData(data27);
		db.addData(data28);
		
	
	
		
	
		
		Contact contact1=new Contact("Jim","4128025288","daizhengkun@gmail.com","Emergency",adminid);
		Contact contact2=new Contact("Tom","0000000000","daizhengkun@gmail.com","Doctor",adminid);
		db.addContact(contact1);
		db.addContact(contact2);
		
		
	}
}
