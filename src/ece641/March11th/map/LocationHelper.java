package ece641.March11th.map;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

public class LocationHelper {
	
private Location currentlocation;
	


private Context context;

public LocationHelper(Context context){
      this.context =context;
 }


	public Location getBestCurrentLocation(){
	
		
		LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
	
		 Location currentlocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		
		return currentlocation;
		
	}
	
	


}
