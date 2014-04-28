package ece641.March11th.dblayout;


import java.util.Calendar;

import ece641.March11th.entities.Contact;
import ece641.March11th.entities.DateAndGL;
import ece641.March11th.entities.User;
import ece641.March11th.entities.Data;

public interface DatabaseRead {
	public User getUser(String loginname);
	public User getUser(int userid);
	public Contact getContact(int contactid);
	

	public Data getData(int dataid);
	
	public DateAndGL getDayGL(Calendar indate,int userid);
	public DateAndGL getWeekGL(Calendar indate,int userid);
	public DateAndGL getMonthGL(Calendar indate,int userid);
	
}
