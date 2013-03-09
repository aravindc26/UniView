package com.bharathi.commons;
/*
 * A data structure to hold bounding box for a trace group.
 */
public class BoundingBox {
	public final float xMin;
	public final float yMin;
	public final float xMax;
	public final float yMax;
	
	public BoundingBox(float xMin, float yMin, float xMax, float yMax) {
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
	}
	
	@Override
	public String toString() {
		return xMin + "," + yMin + "," + xMax + "," + yMax;
	}
}
