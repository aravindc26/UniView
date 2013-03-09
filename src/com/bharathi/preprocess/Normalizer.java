package com.bharathi.preprocess;

import java.util.ArrayList;

import com.bharathi.commons.BoundingBox;
import com.bharathi.commons.CaptureDevice;
import com.bharathi.commons.PointXY;
import com.bharathi.commons.Trace;
import com.bharathi.commons.TraceGroup;

public class Normalizer {
	
	private float sizeThreshold;
	private float aspectRatioThreshold;
	private float dotThreshold;
	public static final float EPS = 0.00001f;
	private static final float PREPROC_DEF_NORMALIZEDSIZE = 0;
	private CaptureDevice captureDevice;
	
	public TraceGroup normalizeSize(TraceGroup inTraceGroup){
		float xScale;
		float yScale;
		float aspectRatio;
		ArrayList<PointXY> normalizedTraces;
		Trace trace;
		TraceGroup outTraceGroup;
		float xMin, yMin, xMax, yMax;
		BoundingBox box = inTraceGroup.getBoundingBox();
		xMin = box.xMin;
		yMin = box.yMin;
		xMax = box.xMax;
		yMax = box.yMax;
		
		xScale = Math.abs(xMax - xMin) / inTraceGroup.getxScaleFactor();
		yScale = Math.abs(yMax - yMin) / inTraceGroup.getyScaleFactor();
		
		if(yScale > xScale) {
			aspectRatio = (xScale > EPS) ? (yScale / xScale) : aspectRatioThreshold + EPS;
		}
		else{
			aspectRatio = (yScale > EPS) ? (xScale / yScale) : aspectRatioThreshold + EPS;
		}
		
		float offsetY = (yMin + yMax) / 2.0f;
		float offsetX;
		outTraceGroup = new TraceGroup();
		
		if(xScale <= (dotThreshold * captureDevice.getXDPI()) && yScale <= (dotThreshold * captureDevice.getYDPI())){
			offsetX = PREPROC_DEF_NORMALIZEDSIZE/2;
			offsetY += PREPROC_DEF_NORMALIZEDSIZE/2;

			outTraceGroup.emptyAllTraces();

			for(int traceIndex=0;traceIndex<inTraceGroup.getNumTraces();++traceIndex)
			{
				Trace tempTrace;

				tempTrace = inTraceGroup.getTraceAt(traceIndex);

				int size = tempTrace.getTracePoints().size();
				ArrayList<PointXY> p = new ArrayList<PointXY>(size);
				
				for(int i = 0; i < size; ++i)
					p.add(new PointXY(offsetX, offsetY));
				
				tempTrace.setTracePoints(p);

				outTraceGroup.addTrace(tempTrace);
			}
			return outTraceGroup;

		}
		
		outTraceGroup.affineTransform();
		return outTraceGroup;
		
	}

}
