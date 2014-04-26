package ece641.March11th.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;






import ece641.March11th.map.MapHelper;

public class LocationFragment extends Fragment {
	private MapView mapView;
	//private MyLocationOverlay locOverlay;
	//private MapHelper helper;
	
	public LocationFragment() {
		// TODO Auto-generated constructor stub
	}
	/*
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_location);

        mapView = (MapView) findViewById(R.id.fragment_location);
        mapView.setBuiltInZoomControls(true);
	}
	*/
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
	//	super.onCreate(savedInstanceState);
        // Inflate the layout for this fragment

		  View view=inflater.inflate(R.layout.fragment_location, container, false);
       //  mapView = (MapView) view.findViewById(R.id.the_map);
    //   mapView.setBuiltInZoomControls(true);
		return view;
		
    }
    
    
    
}
