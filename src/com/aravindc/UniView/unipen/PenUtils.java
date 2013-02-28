package com.aravindc.UniView.unipen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
/*
 * contains methods for reading points from a Unipen file and getting 
 * the bounding box of the points.
 * 
 */
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
	public static ArrayList<PenPoint> readPointsFromFile(File f)	{
		System.out.println("readPintsFromFile()");
		BufferedReader br = null;
		ArrayList<PenPoint> points;
		points = new ArrayList<PenPoint>();
		try	{
			String sCurrentLine;
			br = new BufferedReader(new FileReader(f));
			boolean flagForPenState = false;
			while((sCurrentLine = br.readLine()) != null){
				//System.out.println(sCurrentLine + flagForPenState);
				if(flagForPenState){
					if(sCurrentLine.equals(".PEN_UP")){
						flagForPenState = false;
						continue;
					}
					PenPoint p = new PenPoint();
					String splitted[] = sCurrentLine.split(" ");
					p.setX(Integer.parseInt(splitted[0]));
					p.setY(Integer.parseInt(splitted[1]));
					System.out.println(p+ " " + flagForPenState);
					points.add(p);
				}
				if(sCurrentLine.equals(".PEN_DOWN")){
					flagForPenState = true;
				} 
			}
			System.out.println("Finished Reading");
		}
		catch(IOException e){
			e.printStackTrace();
		}
		/*finally {
			try {
				if (br != null)br.close();
		} 
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}*/
		System.out.println("finished readPintsFromFile()");
		return points;
	}
}
