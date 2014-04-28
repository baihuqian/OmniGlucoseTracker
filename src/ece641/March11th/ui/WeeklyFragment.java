package ece641.March11th.ui;

import ece641.March11th.graph.GraphViewHelper;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WeeklyFragment extends Fragment {
	public GraphViewHelper graphHelper;
	public WeeklyFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weekly, container, false);
    }
}
