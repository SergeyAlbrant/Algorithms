package CollinearPoints;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

private LineSegment[] lineSegments;
	
	public FastCollinearPoints(Point[] points) {    // finds all line segments containing 4 points
		
		if (points == null) throw new IllegalArgumentException();
		
		for (int i = 0; i < points.length; i++) {
			if (points[i] == null) throw new IllegalArgumentException();
		}
		
		for (int i = 0; i < points.length; i++) {
			for (int j = i+1; j < points.length; j++) {
				if (points[i].compareTo(points[j]) == 0) {
					throw new IllegalArgumentException();
				}
				
			}
		}
		
		ArrayList<LineSegment> segments = new ArrayList<>();
		 
		Point[] copyOfPoints = Arrays.copyOf(points, points.length);
        
        
        
		for (int i = 0; i < copyOfPoints.length - 3; i++) {
			Arrays.sort(copyOfPoints);
			
			Arrays.sort(copyOfPoints, copyOfPoints[i].slopeOrder());
			
			int p = 0;
			int firstPoint = 1;
			int lastPoint = 2;
			while (lastPoint < copyOfPoints.length) {
                
                while (lastPoint < copyOfPoints.length && 
                		Double.compare(copyOfPoints[p].slopeTo(copyOfPoints[firstPoint]), 
                				copyOfPoints[p].slopeTo(copyOfPoints[lastPoint])) == 0) {
                    lastPoint++;
                }

                if (lastPoint - firstPoint >= 3 && copyOfPoints[p].compareTo(copyOfPoints[firstPoint]) < 0) {
                    segments.add(new LineSegment(copyOfPoints[p], copyOfPoints[lastPoint - 1]));
                }

                firstPoint = lastPoint;
            }
			
			
		}
		
		lineSegments = segments.toArray(new LineSegment[segments.size()]);
	}
	
	public int numberOfSegments()  {      // the number of line segments
		return lineSegments.length;
	}
	
	public LineSegment[] segments() {               // the line segments
		return Arrays.copyOf(lineSegments, lineSegments.length);
	}
}
