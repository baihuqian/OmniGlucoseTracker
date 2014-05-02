package ece641.March11th.IO;



import android.app.Activity;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

public class EmergencyContactHandler {
	private String [] phoneNumber;
	private String [] emailAddr;
	private Activity activity;
	private String text, subject, message;
	
	public EmergencyContactHandler(Activity activity, String [] phone, String [] email, String message, String text, String subject) {
		this.phoneNumber = phone;
		this.emailAddr = email;
		this.activity = activity;
		this.message = message;
		this.text = text;
		this.subject = subject;
		
	}
	
	private void sendSMSMessage(String message) { // helper method to deal with SMS sending
		SmsManager smsMgr = SmsManager.getDefault();
		for(String addr : phoneNumber) {
			smsMgr.sendTextMessage(addr, null, message, null, null);
		}

	}
	
	private void sendEmail(String subject, String text) {
		Intent intent = new Intent(Intent.ACTION_SEND);
	    intent.setType("text/plain");
	    
	    intent.putExtra(Intent.EXTRA_EMAIL, emailAddr);
	    intent.putExtra(Intent.EXTRA_SUBJECT, subject);
	    intent.putExtra(Intent.EXTRA_TEXT, text);
	    activity.startActivity(intent);
	}
	
	public void sendEmergency() {
		try {
			
			sendSMSMessage(message);
			Toast.makeText(activity, "SMS sent", Toast.LENGTH_LONG).show();
			
		} catch(Exception e) {
			sendEmail(subject, text);
			Toast.makeText(activity, "Email sent", Toast.LENGTH_LONG).show();
		}
			
			
	}
}
