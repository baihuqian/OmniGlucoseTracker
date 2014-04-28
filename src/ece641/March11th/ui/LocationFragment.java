package ece641.March11th.ui;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.maps.*;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

import ece641.March11th.map.MapHelper;

public class LocationFragment extends Fragment {
	private GoogleMap map;
	//private MyLocationOverlay locOverlay;
	//private MapHelper helper;
	
	protected LocationManager locationManager;
	protected LocationListener locationListener;
	protected Context context;
	private Location currentlocation;
	
	public LocationFragment() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
	//	super.onCreate(savedInstanceState);
        // Inflate the layout for this fragment

		  View view=inflater.inflate(R.layout.fragment_location, container, false);
       //  mapView = (MapView) view.findViewById(R.id.the_map);
    //   mapView.setBuiltInZoomControls(true);
		map=  ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		GoogleMapOptions options = new GoogleMapOptions();
		options.mapType(GoogleMap.MAP_TYPE_SATELLITE)
		    .compassEnabled(true)
		    .rotateGesturesEnabled(true)
		    .tiltGesturesEnabled(false);
		MapFragment.newInstance(options);
		
		locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
		/*
		Criteria crit = new Criteria();
		crit.setAccuracy(Criteria.ACCURACY_HIGH);

		String provider=locationManager.getBestProvider(crit,true);
		*/
		 currentlocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		double  latitude = currentlocation.getLatitude();
		 double   longitude = currentlocation.getLongitude();
		
		 map.addMarker(new MarkerOptions()
	        .position(new LatLng(latitude, longitude))
	        .title("Hello world"));
		 
		 
		return view;
		
    }
    
	public void onDestroyView() 
	{
	        super.onDestroyView(); 
	        Fragment fragment = (getFragmentManager().findFragmentById(R.id.map));  
	        FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
	        ft.remove(fragment);
	        ft.commit();
	}
    
}
