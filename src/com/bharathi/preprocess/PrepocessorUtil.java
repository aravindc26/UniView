package com.bharathi.preprocess;

import java.util.ArrayList;

import com.bharathi.commons.BoundingBox;
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
	
	public static TraceGroup normalizeAndPreserveAspectRatio(TraceGroup inTraceGroup, float width, float height){
		TraceGroup outTraceGroup = new TraceGroup();
		BoundingBox box = inTraceGroup.getBoundingBox();
		float w = box.xMax - box.xMin;
		float h = box.yMax - box.yMin;
		float newWidth;
		float newHeight;
		float wRatio;
		float hRatio;
		float offsetX = 0f;
		float offsetY = 0f;
		if(w >= h){
			wRatio = width / w;
			hRatio = wRatio;
		}
		else {
			hRatio = height / h;
			wRatio = hRatio;
		}
		newWidth = w * wRatio;
		newHeight = h * hRatio;
		if(newWidth >= newHeight){
			offsetY = (height - newHeight) / 2;
		}
		else{
			offsetX = (width - newWidth) / 2;
		}
		for(Trace t : inTraceGroup.getTraceList()){
			ArrayList<PointXY> outPoints = new ArrayList<PointXY>();
			for(PointXY p : t.getTracePoints()){
				outPoints.add(new PointXY(
						map(p.getX(), box.xMin, box.xMax, 0, newWidth), 
						map(p.getY(), box.yMin, box.yMax, 0, newHeight)));
			}
			outTraceGroup.addTrace(new Trace(outPoints));
		}
		outTraceGroup.translateTo(offsetX, offsetY);
		return outTraceGroup ;
	}
	public static final float map(float value, float start1, float stop1,
            float start2, float stop2) {
		return start2 + (stop2 - start2) * 
				((value - start1) / (stop1 - start1));
	}

}
