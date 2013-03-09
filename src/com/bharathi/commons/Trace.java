package com.bharathi.commons;

import java.util.ArrayList;

/*
 * A Trace class holds the points which are between a pen down and pen up event, 
 * i.e it holds the points which can be written by the user in one go. 
 */
public class Trace {
	
	private ArrayList<PointXY> tracePoints; //stores the points 
	
	public Trace() {
		tracePoints = new ArrayList<PointXY>();
	}
	
	public Trace(ArrayList<PointXY> tracePoints) {
		this.tracePoints = tracePoints;
	}
	
	public Trace(Trace trace){
		this.tracePoints = trace.getTracePoints();
	}
	/*
	 * getter and setters
	 */
	public ArrayList<PointXY> getTracePoints() {
		return tracePoints;
	}

	public void setTracePoints(ArrayList<PointXY> tracePoints) {
		this.tracePoints = tracePoints;
	}
	
	/*
	 * adds a point to the trace.
	 */
	public void addPointXY(PointXY point) {
		this.tracePoints.add(point);
	}
	/*
	 * empties the trace.
	 */
	public void clear(){
		this.tracePoints.clear();
	}
	/*
	 * checks if the trace points are empty.
	 */
	boolean isTraceEmpty(){
		return tracePoints.size() == 0;
	}
	
	public PointXY getPointAt(int index){
		return  tracePoints.get(index);
	}
}
