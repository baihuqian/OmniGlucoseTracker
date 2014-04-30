package ece641.March11th.map;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import ece641.March11th.ui.R;
import ece641.March11th.ui.R.id;
import ece641.March11th.ui.R.layout;
import ece641.March11th.ui.R.menu;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class ViewOnMapActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_on_map);
		
		GoogleMap map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		GoogleMapOptions options = new GoogleMapOptions();
		options.mapType(GoogleMap.MAP_TYPE_SATELLITE)
		    .compassEnabled(true)
		    .rotateGesturesEnabled(true)
		    .tiltGesturesEnabled(false);
		MapFragment.newInstance(options);
		
		LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		/*
		Criteria crit = new Criteria();
		crit.setAccuracy(Criteria.ACCURACY_HIGH);

		String provider=locationManager.getBestProvider(crit,true);
		*/
		 Location currentlocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		double  latitude = currentlocation.getLatitude();
		 double   longitude = currentlocation.getLongitude();
		
		 map.addMarker(new MarkerOptions()
	        .position(new LatLng(latitude, longitude))
	        .title("Hello world"));
		 

	}

}
