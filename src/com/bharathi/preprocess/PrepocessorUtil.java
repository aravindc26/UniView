package com.bharathi.preprocess;

import java.util.ArrayList;

import com.bharathi.commons.PointXY;
import com.bharathi.commons.Trace;
import com.bharathi.commons.TraceGroup;

public class PrepocessorUtil {
	
	public static TraceGroup removeDuplicatePoints(TraceGroup inTraceGroup){
		TraceGroup outTraceGroup = new TraceGroup();
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
	
	public static TraceGroup smoothenTraceGroup(TraceGroup inTraceGroup, int smoothFilterLength){
		TraceGroup outTraceGroup = new TraceGroup();
		float sumX, sumY;
		for(Trace t : inTraceGroup.getTraceList()){
			int numPoints = t.getTracePoints().size();
			ArrayList<PointXY> pointList = new ArrayList<PointXY>();
			for(int pointIndex = 0; pointIndex < numPoints; ++ pointIndex){	
				sumX = sumY = 0;
				for(int loopIndex = 0; loopIndex < smoothFilterLength; ++loopIndex){
					int actualIndex = (pointIndex-loopIndex);

					//checking for bounding conditions
					if (actualIndex <0 )
					{
						actualIndex = 0;
					}
					else if (actualIndex >= numPoints )
					{
						actualIndex = numPoints -1;
					}

					//accumulating values
					sumX +=  t.getPointAt(actualIndex).getX();
					sumY +=  t.getPointAt(actualIndex).getY();
				}
				sumX /= (float) smoothFilterLength;
				sumY /= (float) smoothFilterLength;
				pointList.add(new PointXY(sumX, sumY));
			}
			outTraceGroup.addTrace(new Trace(pointList));
		}
		return outTraceGroup;
	}

}
