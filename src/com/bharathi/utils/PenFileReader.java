package com.bharathi.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.bharathi.commons.PointXY;
import com.bharathi.commons.Trace;
import com.bharathi.commons.TraceGroup;


/*
 * contains methods for reading points from a Unipen file and getting 
 * the bounding box of the points.
 * 
 */
public class PenFileReader {

	public static ArrayList<PointXY> readPointListFromFile(File f)	{
		System.out.println("readPintsFromFile()");
		BufferedReader br = null;
		ArrayList<PointXY> points;
		points = new ArrayList<PointXY>();
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
					PointXY p = new PointXY();
					String splitted[] = sCurrentLine.split(" ");
					p.setX(Float.parseFloat(splitted[0]));
					p.setY(Float.parseFloat(splitted[1]));
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
	
	public static TraceGroup readTraceGroupFromFile(File file){
		BufferedReader br = null;
		boolean flagForPenState = false;
		ArrayList<PointXY> points = new ArrayList<PointXY>();
		ArrayList<Trace> outTrace = new ArrayList<Trace>();
		try{
			String sCurrentline;
			br = new BufferedReader(new FileReader(file));
			while((sCurrentline = br.readLine()) != null){
				if(flagForPenState){
					if(sCurrentline.equals(".PEN_UP")){
						flagForPenState = false;
						outTrace.add(new Trace(points));
						continue;
					}
					PointXY p = new PointXY();
					String splitted[] = sCurrentline.split(" ");
					p.setX(Float.parseFloat(splitted[0]));
					p.setY(Float.parseFloat(splitted[1]));
					System.out.println(p+ " " + flagForPenState);
					points.add(p);
				}
				if(sCurrentline.equals(".PEN_DOWN")){
					points = new ArrayList<PointXY>();
					flagForPenState = true;
				} 
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
		TraceGroup tg = new TraceGroup();
		System.out.println("Number of Traces Read : " + outTrace.size());
		tg.setTraceList(outTrace);
		return tg;
	}
}
