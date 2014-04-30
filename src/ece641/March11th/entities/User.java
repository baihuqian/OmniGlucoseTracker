package ece641.March11th.entities;

public class User {
	private int userid;
	private String username;
	private int age;
	private String gender;
	private String loginname;
	private String password;
	private double height;
	private double weight;
	


public User() {
	// TODO Auto-generated constructor stub
}
//constructor	for input user, without userid
public User(String username,int age,String gender,String loginname,String password,double height,double weight){
	
	this.username=username;
	this.age=age;
	this.gender=gender;
	this.loginname=loginname;
	this.password=password;	
	this.weight=weight;
	this.height=height;
}


public User(int userid,String username,int age,String gender,String loginname,String password,double height,double weight){
	this.userid=userid;
	this.username=username;
	this.age=age;
	this.gender=gender;
	this.loginname=loginname;
	this.password=password;	
	this.weight=weight;
	this.height=height;
}

//getters to get user information

public int getUserID(){
	return this.userid;
}

public String getUserName(){
	return this.username;
}

public int getAge(){
	return this.age;
}

public String getGender(){
	return this.gender;
}

public String getLoginName(){
	return this.loginname;
}

public String getPassword(){
	return this.password;
}
public double getWeight(){
	return this.weight;
}
public double getHeight(){
	return this.height;
}

//setters to set or update user information
public void setUserID(int userid){
	this.userid=userid;
}
public void setUserName(String username){
	this.username=username;
}

public void setAge(int age){
	this.age=age;
}

public void setGender(String gender){
	this.gender=gender;
}

public void setLoginname(String loginname){
	this.loginname=loginname;
}

public void setPassword(String password){
	this.password=password;
}

public void setHeight(double height){
	this.height=height;
}
public void setWeight(double weight){
	this.weight=weight;
}
}
