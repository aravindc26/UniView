package com.bharathi.preprocess;

import java.util.ArrayList;

import com.bharathi.commons.PointXY;
import com.bharathi.commons.Trace;
import com.bharathi.commons.TraceGroup;

public class Smoother {
	private int filterLength;
	public Smoother(){
		
	}
	public Smoother(int n){
		this.filterLength = n;
	}
	public TraceGroup smooth(TraceGroup inTraceGroup){
		
		TraceGroup outTraceGroup;
		Trace t;
		ArrayList<Trace> outTraces;
		ArrayList<Trace> traceList = inTraceGroup.getTraceList();
		int size = traceList.size();
		for(int i = 0; i < size; ++i) {
			t = traceList.get(i);
			ArrayList<PointXY> p = t.getTracePoints();
			int numPoints = p.size();
			for(int pointIndex = 0; pointIndex < numPoints; ++i){
				float sumX, sumY;
				sumX = sumY = 0f;
				ArrayList<PointXY> outPoints = new ArrayList<PointXY>();
				for(int loopIndex = 0; loopIndex < filterLength; ++loopIndex){
					int actualIndex = pointIndex - loopIndex;
					if (actualIndex <0 ){
						actualIndex = 0;
					}
					else if (actualIndex >= numPoints )	{
						actualIndex = numPoints -1;
					}
					PointXY point = p.get(actualIndex);
					sumX += point.getX();
					sumY += point.getY();
				}
				sumX /= filterLength;
				sumY /= filterLength;
				
				outPoints.add(new PointXY(sumX, sumY));
			}
			
		}
		outTraceGroup = new TraceGroup();
		return outTraceGroup;
	}
}
