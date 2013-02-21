package com.aravindc.UniView.unipen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

public class PenUtils {
	public static final String TOP_LEFT = "TOP_LEFT";
	public static final String TOP_RIGHT = "TOP_RIGHT";
	public static final String BOTTOM_LEFT = "BOTTOM_LEFT";
	public static final String BOTTOM_RIGHT = "BOTTOM_RIGHT";
	public static Hashtable<String,PenPoint> getBoundingBox(ArrayList<PenPoint> points){
		Hashtable<String, PenPoint> hash = new Hashtable<String,PenPoint>();
		
		int bigX, bigY, smallX, smallY;
		ArrayList<Integer> x = new ArrayList<Integer>();
		ArrayList<Integer> y = new ArrayList<Integer>();
		
		for(PenPoint p: points){
			x.add(p.getX());
			y.add(p.getY());
		}
		bigX = Collections.max(x);
		bigY = Collections.max(y);
		smallX = Collections.min(x);
		smallY = Collections.min(y);
		
		PenPoint tl = new PenPoint();
		tl.setXY(smallX, smallY);
		
		PenPoint tr = new PenPoint();
		tr.setXY(bigX, smallY);
		
		PenPoint bl = new PenPoint();
		bl.setXY(smallX, bigY);
		
		PenPoint br = new PenPoint();
		br.setXY(bigX, bigY);
		
		hash.put(TOP_LEFT, tl);
		hash.put(TOP_RIGHT, tr);
		hash.put(BOTTOM_LEFT, bl);
		hash.put(BOTTOM_RIGHT, br);
		
		return hash;
	}
}
