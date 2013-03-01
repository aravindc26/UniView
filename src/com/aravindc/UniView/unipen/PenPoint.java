package com.aravindc.UniView.unipen;

public class PenPoint{
	private int x;
	private int y;
	
	public PenPoint(PenPoint p) {
		x = p.x;
		y = p.y;
	}
	
	public PenPoint(){
		
	}
	
	public void setXY(int x, int y){
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	@Override
		public String toString() {
			
			return "("+x+","+y+")";
		}	
}
