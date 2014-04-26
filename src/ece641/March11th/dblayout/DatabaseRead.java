package ece641.March11th.dblayout;


import java.util.Calendar;

import ece641.March11th.entities.Contact;
import ece641.March11th.entities.DateAndGL;
import ece641.March11th.entities.User;
import ece641.March11th.entities.Data;

public interface DatabaseRead {
	public User getUser(String loginname);
		
	public Contact getContact();
	

	public Data getData();
	
	public DateAndGL getDayGL(Calendar indate);
	public DateAndGL getWeekGL(Calendar indate);
	public DateAndGL getMonthGL(Calendar indate);
	
}
