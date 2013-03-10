package com.bharathi.preprocess;

import java.util.ArrayList;

import com.bharathi.commons.PointXY;
import com.bharathi.commons.Trace;
import com.bharathi.commons.TraceGroup;

public class PrepocessorUtil {
	
	public static TraceGroup removeDuplicatePoints(TraceGroup inTraceGroup){
		TraceGroup outTraceGroup = new TraceGroup();
		ArrayList<Trace> traceList = new  ArrayList<Trace>();
		ArrayList<PointXY> pointList;
		int numTrace = inTraceGroup.getNumTraces();
		for(int traceIndex = 0; traceIndex < numTrace; ++traceIndex){
			Trace t = inTraceGroup.getTraceAt(traceIndex);
			pointList = new ArrayList<PointXY>();
			pointList.add(t.getPointAt(0));
			ArrayList<PointXY> inPointList = t.getTracePoints();
			int numPoint = inPointList.size();
			for(int pointIndex = 1; pointIndex < numPoint; ++pointIndex){
				if((inPointList.get(pointIndex).getX() != inPointList.get(pointIndex - 1).getX())
					|| (inPointList.get(pointIndex).getY() != inPointList.get(pointIndex - 1).getY())){
					pointList.add(inPointList.get(pointIndex));
				}
			}
			outTraceGroup.addTrace(new Trace(pointList));
		}
		return outTraceGroup ;
	}

}
