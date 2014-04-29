package ece641.March11th.dblayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import ece641.March11th.entities.Contact;
import ece641.March11th.entities.DateAndGL;
import ece641.March11th.entities.User;
import ece641.March11th.entities.Data;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public abstract class abstractODTDatabaseHelper extends SQLiteOpenHelper {
	public static final String DATABASE_NAME="ODTDB";
	public static final int DATABASE_VERSION = 1;
	//define tables
	public static final String TABLE_USER="User";
	public static final String TABLE_DATA="DATA";
	public static final String TABLE_CONTACT="Contact";
	//define table user 
	public static final String TABLE_USER_COL1="User_ID";
	public static final String TABLE_USER_COL2="User_Name";
	public static final String TABLE_USER_COL3="User_Age";
	public static final String TABLE_USER_COL4="User_Gender";
    public static final String TABLE_USER_COL5="User_LoginName";
    public static final String TABLE_USER_COL6="User_Password";
	
    //define table location
    public static final String TABLE_DATA_COL1="Event_ID";
    public static final String TABLE_DATA_COL2="Year";
    public static final String TABLE_DATA_COL3="Month";
    public static final String TABLE_DATA_COL4="DayofMonth";
    public static final String TABLE_DATA_COL5="DayofWeek";
    public static final String TABLE_DATA_COL6="Hour";
    public static final String TABLE_DATA_COL7="Minute";
    public static final String TABLE_DATA_COL8="Second";
    public static final String TABLE_DATA_COL9="Longtitude";
    public static final String TABLE_DATA_COL10="Latitude";
    public static final String TABLE_DATA_COL11="Activity";
    public static final String TABLE_DATA_COL12="GL_Value";
    public static final String TABLE_DATA_COL13="Step";
    public static final String TABLE_DATA_COL14="user_id";
    public static final String TABLE_DATA_COL15="WeekofYear";
   
    
    //define table contact
    public static final String TABLE_CONTACT_COL1="Contact_ID";
    public static final String TABLE_CONTACT_COL2="Contact_Name";
    public static final String TABLE_CONTACT_COL3="Contact_Number";
    public static final String TABLE_CONTACT_COL4="Contact_Type";
    public static final String TABLE_CONTACT_COL5="user_id";
    
    
    
	public abstractODTDatabaseHelper(Context context) {
		// super(context, "/mnt/sdcard/test.db", null, DATABASE_VERSION);
		 super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
		
		
	}
	

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_TABLE_USER="CREATE TABLE "+ TABLE_USER 
				+" ("
				+ TABLE_USER_COL1 +" INTEGER PRIMARY KEY AUTOINCREMENT, "
			    + TABLE_USER_COL2 +" TEXT, "
				+ TABLE_USER_COL3 +" INTEGER, " 
				+ TABLE_USER_COL4 +" TEXT, "
				+ TABLE_USER_COL5 +" TEXT, "
				+ TABLE_USER_COL6 +" TEXT "		
				+ " )";
	
		String CREATE_TABLE_DATA="CREATE TABLE "+TABLE_DATA
				+" ("
				+ TABLE_DATA +" INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ TABLE_DATA_COL2 +" INTEGER,"
				+ TABLE_DATA_COL3 +" INTEGER,"
				+ TABLE_DATA_COL4 +" INTEGER,"
				+ TABLE_DATA_COL5 +" INTEGER,"
				+ TABLE_DATA_COL6 +" INTEGER,"
				+ TABLE_DATA_COL7 +" INTEGER,"
				+ TABLE_DATA_COL8 +" INTEGER,"
				+ TABLE_DATA_COL9 +" REAL,"
				+ TABLE_DATA_COL10 +" REAL,"
				+ TABLE_DATA_COL11 +" TEXT,"
				+ TABLE_DATA_COL12 +" REAL,"
				+ TABLE_DATA_COL13 +" INTEGER,"
				+ TABLE_DATA_COL14 +" INTEGER,"
				+TABLE_DATA_COL15 +" INTEGER,"
				+" FOREIGN KEY ("+TABLE_DATA_COL14+") REFERENCES "+ TABLE_USER+"("+TABLE_USER_COL1+")"
				+" )"
				;
		

		
		
		String CREATE_TABLE_CONTACT="CREATE TABLE "+ TABLE_CONTACT
				+" ("
				+ TABLE_CONTACT_COL1 +" INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ TABLE_CONTACT_COL2 +" TEXT,"
				+ TABLE_CONTACT_COL3 +" TEXT,"
				+ TABLE_CONTACT_COL4 +" TEXT,"
				+ TABLE_CONTACT_COL5 +" INTEGER,"
				+" FOREIGN KEY("+TABLE_CONTACT_COL5+") REFERENCES "+TABLE_USER+"("+TABLE_USER_COL1+")"
				+" )"
				;

		db.execSQL(CREATE_TABLE_USER);
		db.execSQL(CREATE_TABLE_DATA);
	    db.execSQL(CREATE_TABLE_CONTACT);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATA);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT);
        // Create tables again
        onCreate(db);
		
	}
    
	// All the check methods
	
	//Check if the login name exist in database
    public boolean checkIfLoginNameExist(String loginName){
    	SQLiteDatabase db = this.getReadableDatabase();
    	
   	    String where=TABLE_USER_COL5+"='"+loginName+"';";
   	    Cursor cursor=null;
    	cursor = db.query(TABLE_USER, null,where,null, null, null, null);
    	if(cursor.moveToFirst()){return true;}
    	else{return false;}
    	
    }
	
    //check if the login name and the password matches  
    public boolean checkIfLoginNameMatchPassword(String loginName,String passWord){
    	//to use this method, you must make sure the loginname exist in the database  
    	SQLiteDatabase db = this.getReadableDatabase();
    	String returncol[]={TABLE_USER_COL6};
   	    String where=TABLE_USER_COL5+"='"+loginName+"'";
    	Cursor cursor = db.query(TABLE_USER, returncol,where,null, null, null, null);
    	
    	cursor.moveToFirst();
    	String passwordindb=cursor.getString(0);
    	if(passwordindb.equals(passWord)){return true;}
    	else{return false;}
    	
    }
    
    
    
    
  
    
    //get the user's ID by login name
    public int getUserID(String loginName){
    	int userID=0;
    	SQLiteDatabase db = this.getReadableDatabase();
    	String returncol[]={TABLE_USER_COL1};
    	String where=TABLE_USER_COL5+"='"+loginName+"'";
    	Cursor cursor = db.query(TABLE_USER, returncol,where,null, null, null, null);
    	if(cursor!=null){
    		cursor.moveToFirst();
    	userID=cursor.getInt(0);}
    	else{userID=0;}
    	return userID;
    }
   
    
    //return all the GL level in a Day,if no GL value exist, return null ArrayList
    public DateAndGL getDayGL(Calendar indate,int userid){
    	DateAndGL dateandgl=new DateAndGL();
    	int year=indate.get(Calendar.YEAR);
    	int month=indate.get(Calendar.MONTH);
    	int day=indate.get(Calendar.DAY_OF_MONTH);
    	
    	
    	
    	ArrayList<Double> gllist=new ArrayList<Double>();
    	ArrayList<Calendar> datelist=new ArrayList<Calendar>();
    	SQLiteDatabase db = this.getReadableDatabase();
    	
   	    String where=TABLE_DATA_COL2+"="+year
   	    		+" AND "+TABLE_DATA_COL3+"="+month
   	    		+" AND "+TABLE_DATA_COL4+"="+day
   	    		+" AND "+TABLE_DATA_COL14+"="+userid
   	    		+" AND "+TABLE_DATA_COL12 +" IS NOT NULL ;";
   	    Cursor cursor=null;
    	cursor = db.query(TABLE_DATA, null,where,null, null, null, null);
    	
    	
    	if(cursor.moveToFirst()){
    		
    		do{
    		int tmpyear=cursor.getInt(1);
    		int tmpmonth=cursor.getInt(2);
    		int tmpdayofmonth=cursor.getInt(3);
    		int tmpdayofweek=cursor.getInt(4);
    		int tmphour=cursor.getInt(5);
    		int tmpminute=cursor.getInt(6);
    		int tmpsecond=cursor.getInt(7);
    		int tmpweekofyear=cursor.getInt(14);
    		
    		
    		
    		Calendar tmpdate=Calendar.getInstance();
    		
    	tmpdate.set(Calendar.YEAR, tmpyear);
    	tmpdate.set(Calendar.MONTH, tmpmonth);
    	tmpdate.set(Calendar.DAY_OF_MONTH, tmpdayofmonth);
    	tmpdate.set(Calendar.DAY_OF_WEEK, tmpdayofweek);
    	tmpdate.set(Calendar.HOUR_OF_DAY, tmphour);
    	tmpdate.set(Calendar.MINUTE, tmpminute);
    	tmpdate.set(Calendar.SECOND, tmpsecond);
    	tmpdate.set(Calendar.WEEK_OF_YEAR,tmpweekofyear);
    		
    	double tempgl=cursor.getDouble(11);
    	gllist.add(tempgl);
    	datelist.add(tmpdate);
    		}
    	while (cursor.moveToNext());
    		
    		
    	}
    	
    	dateandgl.setDateList(datelist);
    	dateandgl.setGLList(gllist);
    	return dateandgl;
    	
    
    
    	
    }
    
    // return all the GL level in a Week,if no GL value exist, return null ArrayList
    public DateAndGL getWeekGL(Calendar indate,int userid){
    	DateAndGL dateandgl=new DateAndGL();
    	int year=indate.get(Calendar.YEAR);
    	
    	int weekofyear=indate.get(Calendar.WEEK_OF_YEAR);
    	
    	ArrayList<Double> gllist=new ArrayList<Double>();
    	ArrayList<Calendar> datelist=new ArrayList<Calendar>();
    	SQLiteDatabase db = this.getReadableDatabase();
    	
   	    String where=TABLE_DATA_COL2+"="+year
   	    		+" AND "+TABLE_DATA_COL15+"="+weekofyear
   	    		+" AND "+TABLE_DATA_COL14+"="+userid
   	    		+" AND "+TABLE_DATA_COL12 +" IS NOT NULL ;";
   	    Cursor cursor=null;
    	cursor = db.query(TABLE_DATA, null,where,null, null, null, null);
    	
    	

    	if(cursor.moveToFirst()){
    		
    		do{
    		int tmpyear=cursor.getInt(1);
    		int tmpmonth=cursor.getInt(2);
    		int tmpdayofmonth=cursor.getInt(3);
    		int tmpdayofweek=cursor.getInt(4);
    		int tmphour=cursor.getInt(5);
    		int tmpminute=cursor.getInt(6);
    		int tmpsecond=cursor.getInt(7);
    		int tmpweekofyear=cursor.getInt(14);
    		
    		
    		
    		Calendar tmpdate=Calendar.getInstance();
    		
    	tmpdate.set(Calendar.YEAR, tmpyear);
    	tmpdate.set(Calendar.MONTH, tmpmonth);
    	tmpdate.set(Calendar.DAY_OF_MONTH, tmpdayofmonth);
    	tmpdate.set(Calendar.DAY_OF_WEEK, tmpdayofweek);
    	tmpdate.set(Calendar.HOUR_OF_DAY, tmphour);
    	tmpdate.set(Calendar.MINUTE, tmpminute);
    	tmpdate.set(Calendar.SECOND, tmpsecond);
    	tmpdate.set(Calendar.WEEK_OF_YEAR,tmpweekofyear);
    		
    	double tempgl=cursor.getDouble(11);
    	gllist.add(tempgl);
    	datelist.add(tmpdate);
    		}
    	while (cursor.moveToNext());
    		
    		
    	}
    	
    	dateandgl.setDateList(datelist);
    	dateandgl.setGLList(gllist);
    	return dateandgl;
    	
    }
    // return all the GL level in a Month,if no GL value exist, return null ArrayList
    public DateAndGL getMonthGL(Calendar indate,int userid){
    	DateAndGL dateandgl=new DateAndGL();
    	int year=indate.get(Calendar.YEAR);
    	int month=indate.get(Calendar.MONTH);
    	
    	
    	ArrayList<Double> gllist=new ArrayList<Double>();
    	ArrayList<Calendar> datelist=new ArrayList<Calendar>();
    	SQLiteDatabase db = this.getReadableDatabase();
    	
   	    String where=TABLE_DATA_COL2+"="+year
   	    		+" AND "+TABLE_DATA_COL3+"="+month
   	    		+" AND "+TABLE_DATA_COL14+"="+userid
   	    		+" AND "+TABLE_DATA_COL12 +" IS NOT NULL ;";
   	    Cursor cursor=null;
    	cursor = db.query(TABLE_DATA, null,where,null, null, null, null);
    	
    	


    	if(cursor.moveToFirst()){
    		
    		do{
    		int tmpyear=cursor.getInt(1);
    		int tmpmonth=cursor.getInt(2);
    		int tmpdayofmonth=cursor.getInt(3);
    		int tmpdayofweek=cursor.getInt(4);
    		int tmphour=cursor.getInt(5);
    		int tmpminute=cursor.getInt(6);
    		int tmpsecond=cursor.getInt(7);
    		int tmpweekofyear=cursor.getInt(14);
    		
    		
    		
    		Calendar tmpdate=Calendar.getInstance();
    		
    	tmpdate.set(Calendar.YEAR, tmpyear);
    	tmpdate.set(Calendar.MONTH, tmpmonth);
    	tmpdate.set(Calendar.DAY_OF_MONTH, tmpdayofmonth);
    	tmpdate.set(Calendar.DAY_OF_WEEK, tmpdayofweek);
    	tmpdate.set(Calendar.HOUR_OF_DAY, tmphour);
    	tmpdate.set(Calendar.MINUTE, tmpminute);
    	tmpdate.set(Calendar.SECOND, tmpsecond);
    	tmpdate.set(Calendar.WEEK_OF_YEAR,tmpweekofyear);
    		
    	double tempgl=cursor.getDouble(11);
    	gllist.add(tempgl);
    	datelist.add(tmpdate);
    		}
    	while (cursor.moveToNext());
    		
    		
    	}
    	
    	dateandgl.setDateList(datelist);
    	dateandgl.setGLList(gllist);
    	return dateandgl;
    }
    
    
 
    
    
 //Add USER
public void addUser(User user){
	
	
	
	SQLiteDatabase db = this.getWritableDatabase();
	ContentValues insertValues = new ContentValues();
	insertValues.put(TABLE_USER_COL2, user.getUserName());
	insertValues.put(TABLE_USER_COL3, user.getAge());
	insertValues.put(TABLE_USER_COL4, user.getGender());
	insertValues.put(TABLE_USER_COL5, user.getLoginName());
	insertValues.put(TABLE_USER_COL6, user.getPassword());
	db.insert(TABLE_USER, null, insertValues);
    }

//Add CONTCT
public void addContact(Contact contact){

	
	

	SQLiteDatabase db = this.getWritableDatabase();
	ContentValues insertValues = new ContentValues();
	insertValues.put(TABLE_CONTACT_COL2, contact.getContactName());
	insertValues.put(TABLE_CONTACT_COL3, contact.getContactNumber());
	insertValues.put(TABLE_CONTACT_COL4, contact.getContactType());
	insertValues.put(TABLE_CONTACT_COL5, contact.getUserID());
	
	db.insert(TABLE_CONTACT, null, insertValues);
	
	
}

//Add Date
public void addData(Data data){
	
	Calendar indate=data.getDate();
	

	SQLiteDatabase db = this.getWritableDatabase();
	ContentValues insertValues = new ContentValues();
	insertValues.put(TABLE_DATA_COL2, indate.get(Calendar.YEAR));
	insertValues.put(TABLE_DATA_COL3, indate.get(Calendar.MONTH));
	insertValues.put(TABLE_DATA_COL4, indate.get(Calendar.DAY_OF_MONTH));
	insertValues.put(TABLE_DATA_COL5, indate.get(Calendar.DAY_OF_WEEK));
	insertValues.put(TABLE_DATA_COL6, indate.get(Calendar.HOUR_OF_DAY));
	insertValues.put(TABLE_DATA_COL7, indate.get(Calendar.MINUTE));
	insertValues.put(TABLE_DATA_COL8, indate.get(Calendar.SECOND));
	if ((data.getLongtitude()!=1000)&&(data.getLatitude()!=1000)){
	insertValues.put(TABLE_DATA_COL9, data.getLongtitude());
	insertValues.put(TABLE_DATA_COL10, data.getLatitude());}
	else{}
	
	insertValues.put(TABLE_DATA_COL11, data.getActivity());
	insertValues.put(TABLE_DATA_COL12, data.getGL());
	insertValues.put(TABLE_DATA_COL13, data.getStep());
	insertValues.put(TABLE_DATA_COL14, data.getUserID());
	insertValues.put(TABLE_DATA_COL15, indate.get(Calendar.WEEK_OF_YEAR));
		
	
	
	
	db.insert(TABLE_DATA, null, insertValues);

	
}

public User getUser(String loginname){
	User user=new User();
	
	SQLiteDatabase db = this.getReadableDatabase();
	
	    String where=TABLE_USER_COL5+"='"+loginname+"';";
	    Cursor cursor=null;
	cursor = db.query(TABLE_USER, null,where,null, null, null, null);
	if(cursor.moveToFirst()){
		user.setUserID(cursor.getInt(0));
		user.setUserName(cursor.getString(1));
		user.setAge(cursor.getInt(2));
		user.setGender(cursor.getString(3));
		user.setLoginname(cursor.getString(4));
		user.setPassword(cursor.getString(5));
		
		
		
	}
	return user;
	
}

public User getUser(int userid){
User user=new User();
	
	SQLiteDatabase db = this.getReadableDatabase();
	
	    String where=TABLE_USER_COL1+"="+userid+";";
	    Cursor cursor=null;
	cursor = db.query(TABLE_USER, null,where,null, null, null, null);
	if(cursor.moveToFirst()){
		user.setUserID(cursor.getInt(0));
		user.setUserName(cursor.getString(1));
		user.setAge(cursor.getInt(2));
		user.setGender(cursor.getString(3));
		user.setLoginname(cursor.getString(4));
		user.setPassword(cursor.getString(5));	
	}
	return user;
	
}

public Contact getContact(int contactid){
	Contact contact=new Contact();
	
	SQLiteDatabase db = this.getReadableDatabase();
	
    String where=TABLE_CONTACT_COL1+"="+contactid+";";
    Cursor cursor=null;
cursor = db.query(TABLE_CONTACT, null,where,null, null, null, null);
if(cursor.moveToFirst()){
	contact.setContactID(cursor.getInt(0));
	contact.setContactName(cursor.getString(1));
	contact.setContactNumber(cursor.getString(2));
	contact.setContactType(cursor.getString(3));
	contact.setUserID(cursor.getInt(4));

}

	return contact;
}

public ArrayList<Contact> getContactList(int userid){

	ArrayList<Contact> contactlist=new ArrayList<Contact>();
	
	
	return contactlist;
}

public Data getData(int dataid){
	
	Data data =new Data();
	
	
	return data;
	
}
    

public void updateUser(User user) {
	
	int userid=user.getUserID();
	String username=user.getUserName();
	int age=user.getAge();
	String loginname=user.getLoginName();
	String password=user.getPassword();
	String gender=user.getGender();
	
	SQLiteDatabase db = this.getWritableDatabase();
	ContentValues insertValues = new ContentValues();
	if(username.equals(null)){}
	else{insertValues.put(TABLE_USER_COL2,username);	
	}
	if(age==-1){}
	else{insertValues.put(TABLE_USER_COL3,age);	
	}
	insertValues.put(TABLE_USER_COL4, gender);
	insertValues.put(TABLE_USER_COL5, loginname);
	insertValues.put(TABLE_USER_COL6, password);
	
	String where=TABLE_USER_COL1+"="+userid+";";
	db.update(TABLE_USER, insertValues, where, null);
	
	
	
}
}  

