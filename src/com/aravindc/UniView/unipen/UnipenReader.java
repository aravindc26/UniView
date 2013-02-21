package com.aravindc.UniView.unipen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class UnipenReader {
	
	private ArrayList<PenPoint> points;
	
	
	ArrayList<PenPoint> readPointsFromFile(File f)	{
		BufferedReader br = null;
		points = new ArrayList<PenPoint>();
		try	{
			String sCurrentLine;
			br = new BufferedReader(new FileReader(f));
			boolean flagForPenState = false;
			while((sCurrentLine = br.readLine()) != null){
				if(flagForPenState){
					if(sCurrentLine.equals(".PEN_UP")){
						flagForPenState = false;
					}
					PenPoint p = new PenPoint();
					String splitted[] = sCurrentLine.split(" ");
					p.setX(Integer.parseInt(splitted[0]));
					p.setY(Integer.parseInt(splitted[1]));
					points.add(p);
				}
				else if(sCurrentLine.equals(".PEN_DOWN")){
					flagForPenState = true;
				}
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally {
			try {
				if (br != null)br.close();
		} 
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return points;
	}
}