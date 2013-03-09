package com.bharathi.commons;

import java.util.ArrayList;

public class TraceGroup {
	
	private float xScaleFactor; // scale factor for x axis.
	private float yScaleFactor; // scale factor for y axis.
	private ArrayList<Trace> traceList;
	
	//constructors
	public TraceGroup(){
		this.xScaleFactor = 1;
		this.yScaleFactor = 1;
	}
	
	public TraceGroup(TraceGroup tg){
		this.xScaleFactor = tg.getxScaleFactor();
		this.yScaleFactor = tg.getyScaleFactor();
		this.traceList = tg.getTraceList();
	}
	
	public TraceGroup(float xScale, float yScale, ArrayList<Trace> t){
		this.xScaleFactor = xScale;
		this.yScaleFactor = yScale;
		this.traceList = t;
	}
	
	// getters and setters
	public float getxScaleFactor() {
		return xScaleFactor;
	}

	public void setxScaleFactor(float xScaleFactor) {
		this.xScaleFactor = xScaleFactor;
	}

	public float getyScaleFactor() {
		return yScaleFactor;
	}

	public void setyScaleFactor(float yScaleFactor) {
		this.yScaleFactor = yScaleFactor;
	}

	public ArrayList<Trace> getTraceList() {
		return traceList;
	}

	public void setTraceList(ArrayList<Trace> traceList) {
		this.traceList = traceList;
	}
	
	//gets the trace at specified index.
	public  Trace getTraceAt(int index){
		return traceList.get(index);
	}
	
	// returns the number of traces.
	public int getNumTraces(){
		return traceList.size();
	}
	
	/*
	 * Returns the bounding box for the trace group.
	 */
	public BoundingBox getBoundingBox(){
		BoundingBox bounds;
		float xMin = Float.MAX_VALUE, yMin = Float.MAX_VALUE;
		float xMax = Float.MIN_VALUE, yMax = Float.MIN_VALUE;
		
		for(Trace t: traceList){
			for(PointXY p: t.getTracePoints()){
				float x = p.getX();
				float y = p.getY();
				if ( x < xMin )
				{
					xMin = x;
				}

				if ( x > xMax )
				{
					xMax = x;
				}

				if ( y < yMin )
				{
					yMin = y;
				}

				if ( y > yMax )
				{
					yMax = y;
				}
			}
		}
		bounds = new BoundingBox(xMin, yMin, xMax, yMax);
		return bounds;
	}

	public void emptyAllTraces() {
		traceList.clear();
	}

	public void addTrace(Trace tempTrace) {
		traceList.add(tempTrace);
		
	}

	public void affineTransform() {

	}

	public void translateTo(float x, float y){
		ArrayList<Trace> outTraceList = new ArrayList<Trace>();
		BoundingBox box = getBoundingBox();
				System.out.println("Bounding box befor translation" + box);
		for(Trace t : traceList){
			ArrayList<PointXY> pointlist = new ArrayList<PointXY>();
			for(PointXY p : t.getTracePoints()){
				float xvalue = p.getX() + x - box.xMin;
				float yvalue = p.getY() + y - box.yMin;
				pointlist.add(new PointXY(xvalue, yvalue));
			}
			outTraceList.add(new Trace(pointlist));
		}
		setTraceList(outTraceList);
	}
}
