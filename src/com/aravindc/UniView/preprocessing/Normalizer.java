package com.aravindc.UniView.preprocessing;

import java.util.ArrayList;

import com.aravindc.UniView.unipen.PenPoint;

public class Normalizer {
	
	private int w;
	private int h;
	private int W;
	private int H;
	private ArrayList<PenPoint> original;
	private ArrayList<PenPoint> normalized;
	
	public Normalizer(int w, int h, int W, int H, ArrayList<PenPoint> original) {
		this.w = w;
		this.h = h;
		this.W = W;
		this.H = H;
		this.original = original;
	}
	
	public ArrayList<PenPoint> normalize(){
		normalized = null;
		float aN = W/H;
		float aO = w/h;
		
		/*
		 * If aspect ratios of original stroke group are the same then copy the entire
		 * points to normalized points.
		 */
		if(aN == aO)	{
			// TODO copy the entire stroke group to the normalized stroke group.
		}
		
		else if(aN < aO)	{
			/* TODO then rescale the original stroke group to get normalizedImage such that
			 *   the minimum bounding rectangle of all connected components in normalized
			 *   stroke group become vertically centered and this rectangle spans the 
			 *   whole width of normalized stroke group
			 */
		}
		else {
			/*
			 *  TODO Shrink original stroke group  to fill horizontally  normalized
			 *  stroke group. 
			 */
		}
		return normalized;
	}
}
