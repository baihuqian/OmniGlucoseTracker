package ece641.March11th.graph;

public class GlucoseGraphData implements Comparable<GlucoseGraphData> {
	public double time;
	public double glucose;
	
	public GlucoseGraphData(double time, double glucose) {
		this.time = time;
		this.glucose = glucose;
	}
	@Override
	public int compareTo(GlucoseGraphData d) {
		// TODO Auto-generated method stub
		if(this.time > d.time) {
			return 1;
		}
		else if(this.time < d.time) {
			return -1;
		}
		else return 0;
	}

}
