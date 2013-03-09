package com.bharathi.commons;

/*
 * A datastructure to hold a point input
 */
public class PointXY {
	
	private float x; //stores the x coordinate  
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	private float y; //stores the y coordinate
	
	//Default constructor
	public PointXY(){
		
	}
	
	public PointXY(float x, float y){
		this.x = x;
		this.y = y;
	}

	
	@Override
	public String toString() {
		return this.x + " " + this.y;
	}
}
