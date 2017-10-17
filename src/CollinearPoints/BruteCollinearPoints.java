package CollinearPoints;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
	
	private LineSegment[] lineSegments;
	
	public BruteCollinearPoints(Point[] points) {    // finds all line segments containing 4 points
		
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
        Arrays.sort(copyOfPoints);
        
		for (int i = 0; i < copyOfPoints.length - 3; i++) {
			for (int j = i+1; j < copyOfPoints.length - 2; j++) {
				for (int k = j+1; k < copyOfPoints.length - 1; k++) {
					for (int l = k+1; l < copyOfPoints.length; l++) {
						if (copyOfPoints[i].slopeTo(copyOfPoints[j]) == copyOfPoints[i].slopeTo(copyOfPoints[k])){	
							if (copyOfPoints[i].slopeTo(copyOfPoints[j]) == copyOfPoints[i].slopeTo(copyOfPoints[l])){
											segments.add(new LineSegment(copyOfPoints[i], copyOfPoints[l]));
							}
						}
							
					}
				}
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
