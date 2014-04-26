package ece641.March11th.test;

import java.util.Calendar;

import ece641.March11th.dblayout.ODTDatabaseHelper;
import ece641.March11th.entities.Data;
import ece641.March11th.entities.User;

public class BuildTestDatabase {
	private ODTDatabaseHelper db;
	public BuildTestDatabase(ODTDatabaseHelper db){this.db=db;}
	
	public void buildDatabase(){
		User user1=new User("admin", 26, "Male", "admin", "admin");
		User user2=new User("abc", 12, "Male", "abc11", "abc111");
		User user3=new User("abc2", 15, "Female", "abc22", "abc22");
		User user4=new User("abc3", 18, "Female", "abc33", "abc333");
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
		
		Calendar date21=Calendar.getInstance();
		Calendar date22=Calendar.getInstance();
		Calendar date23=Calendar.getInstance();
		Calendar date24=Calendar.getInstance();
		Calendar date25=Calendar.getInstance();
		
		//modify the dates to simulate the data base
		date11.set(Calendar.HOUR_OF_DAY, 8);
		date12.set(Calendar.HOUR_OF_DAY, 10);
		date13.set(Calendar.HOUR_OF_DAY, 12);
		date14.set(Calendar.HOUR_OF_DAY, 14);
		date15.set(Calendar.HOUR_OF_DAY, 16);
		
		date21.set(Calendar.HOUR_OF_DAY,8);
		date22.set(Calendar.HOUR_OF_DAY,10);
		date23.set(Calendar.HOUR_OF_DAY,12);
		date24.set(Calendar.HOUR_OF_DAY,14);
		date25.set(Calendar.HOUR_OF_DAY,16);	
		int temday=date11.get(Calendar.DAY_OF_MONTH)-1;
		date21.set(Calendar.DAY_OF_MONTH,temday);
		date22.set(Calendar.DAY_OF_MONTH,temday);
		date23.set(Calendar.DAY_OF_MONTH,temday);
		date24.set(Calendar.DAY_OF_MONTH,temday);
		date25.set(Calendar.DAY_OF_MONTH,temday);
		
		
		Data data11=new Data(date11,null,0.223,null,1,adminid);
		Data data12=new Data(date12,null,0.123,null,3,adminid);
		Data data13=new Data(date13,null,0.333,null,5,adminid);
		Data data14=new Data(date14,null,0.253,null,5,adminid);
		Data data15=new Data(date15,null,0.323,null,5,adminid);
		
		Data data21=new Data(date21,null,0.523,null,1,adminid);
		Data data22=new Data(date22,null,0.623,null,3,adminid);
		Data data23=new Data(date23,null,0.433,null,5,adminid);
		Data data24=new Data(date24,null,0.223,null,5,adminid);
		Data data25=new Data(date25,null,0.213,null,5,adminid);
		
		db.addData(data11);
		db.addData(data12);
		db.addData(data13);
		db.addData(data14);
		db.addData(data15);
		
		db.addData(data21);
		db.addData(data22);
		db.addData(data23);
		db.addData(data24);
		db.addData(data25);
		
		
		
	}
}
