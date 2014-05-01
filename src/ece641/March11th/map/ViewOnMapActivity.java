package ece641.March11th.map;

import java.util.ArrayList;
import java.util.Calendar;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import ece641.March11th.dblayout.ODTDatabaseHelper;
import ece641.March11th.entities.Data;
import ece641.March11th.ui.R;
import ece641.March11th.ui.R.id;
import ece641.March11th.ui.R.layout;
import ece641.March11th.ui.R.menu;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.os.Build;

public class ViewOnMapActivity extends Activity {
	int userid;
	ArrayList<Integer> dataidlist=new ArrayList<Integer>();
	ArrayList<Double> lonlist=new ArrayList<Double>();
	ArrayList<Double> latlist=new ArrayList<Double>();
	ArrayList<Data> datalist=new ArrayList<Data>();
	
	Calendar calendar;
	ODTDatabaseHelper dbh=new ODTDatabaseHelper(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_on_map);

		Intent intent=getIntent();
		userid=intent.getIntExtra("userid", 1);
		
		
		
		GoogleMap map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		map.setMyLocationEnabled(true);
		GoogleMapOptions options = new GoogleMapOptions();
		options.mapType(GoogleMap.MAP_TYPE_SATELLITE)
		    .compassEnabled(true)
		    .rotateGesturesEnabled(true)
		    .tiltGesturesEnabled(false);
		MapFragment.newInstance(options);
		calendar=Calendar.getInstance();
		datalist=dbh.getDataListSortByTimeForMap(calendar, userid);
		int length=datalist.size();
		
		if(length==0){}
		else{
			for(int i=0;i<length;i++){
			double lat=datalist.get(i).getLatitude();
			double lon=datalist.get(i).getLongtitude();
			map.addMarker(new MarkerOptions().position(new LatLng(lat,lon)));
			
					}
		}
		
		
		/*
		LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
	
		 Location currentlocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		double  latitude = currentlocation.getLatitude();
		 double   longitude = currentlocation.getLongitude();
		
		 map.addMarker(new MarkerOptions()
	        .position(new LatLng(latitude, longitude))
	        .title("Hello world"));
		 
*/
		
		
		
		
		
	}

}
