package ece641.March11th.entities;

public class Contact {
	private int contactid;
	private String contactname;
	private String contactnumber;
	private String contacttype;
	private String contactemail;
	private int userid;
	
public Contact(){}

//inout constructor
public Contact(String contactname,String contactnumber,String contactemail,String contacttype,int userid){
	
	this.contactname=contactname;
	this.contactnumber=contactnumber;
	this.contactemail=contactemail;
	this.contacttype=contacttype;
	this.userid=userid;
	
	
}

//output constructor
public Contact(int contactid,String contactname,String contactnumber,String contactemail,String contacttype,int userid){
	this.contactid=contactid;
	this.contactname=contactname;
	this.contactnumber=contactnumber;
	this.contactemail=contactemail;
	this.contacttype=contacttype;
	this.userid=userid;
	
	
}

//get method

public int getContactID(){
	return this.contactid;
}

public String getContactName(){
	return this.contactname;
}

public String getContactNumber(){
	return this.contactnumber;
}
public String getContactEmail(){
	return this.contactemail;
}

public String getContactType(){
	return this.contacttype;
}

public int getUserID(){
	return this.userid;
	
}


//set method
public void setContactID(int contactid){
	this.contactid=contactid;
	
}

public void setContactName(String contactname){
	this.contactname=contactname;
}

public void setContactNumber(String contactnumber){
	this.contactnumber=contactnumber;
}
public void setContactEmail(String contactemail){
	this.contactemail=contactemail;
}
public void setContactType(String contacttype){
	this.contacttype=contacttype;
}

public void setUserID(int userid){
	this.userid=userid;
}
}
