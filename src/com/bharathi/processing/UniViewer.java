package com.bharathi.processing;

import java.io.File;
import com.bharathi.commons.BoundingBox;
import com.bharathi.commons.PointXY;
import com.bharathi.commons.Trace;
import com.bharathi.commons.TraceGroup;
import com.bharathi.preprocess.PrepocessorUtil;
import com.bharathi.utils.PenFileReader;

import processing.core.*;

public class UniViewer extends PApplet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5697845850174349728L;
	boolean ready = false;
	 BoundingBox box;
	 TraceGroup tg;
	 int count = 0;
	public void setup(){
		 size(50, 50);
		 stroke(255, 0, 0);
		 
		 strokeWeight(3);
		  //smooth();
		 background(255);
		 if (frame != null) {
			    frame.setResizable(true);
		 }
		 selectInput("Select a file to Process", "fileSelected");
	}
	
	public void draw(){
		
		if(ready){
			if(count == 5){
				noLoop();
			}
			else
				++count;
			for(Trace t : tg.getTraceList())
				for(PointXY p : t.getTracePoints()){
					//System.out.println(p.getX() + " " + p.getY());
					point(p.getX(), p.getY());
				}
		}
	}
	
	public void resizeFrameForCharDisplay(){
		tg.translateTo(0, 0);
		box = tg.getBoundingBox();
		System.out.println("Bounding box after translation" + box);
		frame.setSize((int) box.xMax ,(int) box.yMax);
		frame.setLocationRelativeTo(null);
		
	}
	
	

	public void fileSelected(File file){
		 tg = PenFileReader.readTraceGroupFromFile(file);
		 System.out.println("number of points before preprocessing " + tg.getNumPoints());
		 System.out.println("number of traces before preprocessing " + tg.getNumTraces());
		 tg = PrepocessorUtil.removeDuplicatePoints(tg);
		 System.out.println("number of points after removing duplication " + tg.getNumPoints());
		 System.out.println("number of traces after removing duplication " + tg.getNumTraces());
		 tg = PrepocessorUtil.smoothenTraceGroup(tg, 10);
		 System.out.println("number of points after smoothing " + tg.getNumPoints());
		 System.out.println("number of traces after smoothing " + tg.getNumTraces());
		 resizeFrameForCharDisplay();
		 ready = true;
	}
	
	public static void main(String args[]) {
	    PApplet.main(new String[] {  "com.bharathi.processing.UniViewer" });
	  }
}
