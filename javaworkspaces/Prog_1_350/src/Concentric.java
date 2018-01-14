// Modified with permission by Anthony Frazier
// CSCE350 Spring 17
// Project One: Concentric
// Objective: Find the center of a circle based on given points, then prove that all of the points in the matrix would be
//  contained within a circle with the discovered center and the radius.
import Jama.*;
import java.util.*;
import java.lang.Math;


public class Concentric {

	public final static double EPSILON = 1E-6;
	
	//rotates a vector 90 clockwise around the origin
	// rotates a point around another point
	public static Matrix cw90Rot2D(Matrix pt, Matrix origin){
		Matrix ptDiff = pt.minus(origin);
		
		double [][]Qarr = {{0,1},{-1,0}};
		Matrix Q = new Matrix(Qarr);
		Matrix ptRot =  Q.times(ptDiff);
		
		return origin.plus(ptRot);
	}
	
	
	//finds the 2D intersection of two lines (l0,l1), each defined by two points on the line
	//https://en.wikipedia.org/wiki/Line%E2%80%93line_intersection
	public static Matrix lineLineIntersection(Matrix l0a,Matrix l0b, Matrix l1a, Matrix l1b ) throws ParallelLinesException {
		double x1 = l0a.get(0, 0);
		double x2 = l0b.get(0, 0);
		double x3 = l1a.get(0, 0);
		double x4 = l1b.get(0, 0);
		double y1 = l0a.get(1, 0);
		double y2 = l0b.get(1, 0);
		double y3 = l1a.get(1, 0);
		double y4 = l1b.get(1, 0);
		
		double denom = (x1-x2)*(y3-y4)-(y1-y2)*(x3-x4);
		
		if(Math.abs(denom)<EPSILON) throw new ParallelLinesException();
		
		double numX = (x1*y2-y1*x2)*(x3-x4) - (x1-x2)*(x3*y4-y3*x4);
		double numY = (x1*y2-y1*x2)*(y3-y4) - (y1-y2)*(x3*y4-y3*x4);
		
		double [][] intArr = {{numX/denom},{numY/denom}};
		return new Matrix(intArr);
	}
	
	//Points are 2x1 matrices ( column vectors)
	//we're using JAMA ( http://math.nist.gov/javanumerics/jama/doc/ )... read the Matrix doc
	public static boolean areConcentric(List<Matrix> pts, double epsilon){
		
		// In order to determine the center, we will need 3 points, #A, #B, and #C. Using these
		//  three points, we can generate two midpoints midAB and midBC. Rotating #A and #C around
		//  these midpoints, we can generate two new points rotatedAB and rotatedBC. The intersection
		//  of these four generate points will be our proposed 'center' of our circle. We then need
		//  to generate a radius = distance from center to point A. Finally, we need to ensure that
		//  the distance between all points and the center is not greater than the radius, give or take
		//  epsilon.
		
		double[][] arraySizeDummy = new double[2][1];
		double kHalfItNow = 0.5;
		double radius = 0;
		int kTwoPoints = 2;
		int i = 0;
		int listSize = pts.size();
		boolean isConcentric = true;
		
		Matrix pointA = new Matrix(arraySizeDummy);
		Matrix pointB = new Matrix(arraySizeDummy);
		Matrix pointC = new Matrix(arraySizeDummy);
		Matrix midAB = new Matrix(arraySizeDummy);
		Matrix midBC = new Matrix(arraySizeDummy);
		Matrix rotatedAB = new Matrix(arraySizeDummy);
		Matrix rotatedBC = new Matrix(arraySizeDummy);
		Matrix center = new Matrix(arraySizeDummy);


		pointA = pts. get(i);
		pointB = pts. get(i+1);
		pointC = pts. get(i+2);
		midAB = (pointA.plus(pointB));
		midAB = midAB.times(kHalfItNow);
		midBC = (pointB.plus(pointC));
		midBC = midBC.times(kHalfItNow);
		
		rotatedAB = cw90Rot2D(pointA, midAB);
		rotatedBC = cw90Rot2D(pointC, midBC);
		
		try {
			center =  lineLineIntersection(midAB, rotatedAB, midBC, rotatedBC);
		} catch (ParallelLinesException e) {
			e.printStackTrace();
		}
		double x2 = center.get(i, i);
		double x1 = pointA.get(i, i);
		double y2 = center.get(i+1, i);
		double y1 = pointA.get(i+1, i);
		
		radius = Math.sqrt( (x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1) ); 
		
		for (int j = 0; j < listSize-1; j++)
		{
			Matrix temp = pts.get(j);
			x1 = center.get(i, i);
			x2 = temp.get(i, i);
			y1 = center.get(i+1, i);
			y2 = temp.get(i+1, i);
			
			double distance = Math.sqrt( (x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1) );
			double diff = radius - distance + epsilon;
			double diff1 = radius - distance - epsilon;
			if (diff > 0 && diff1 > 0)
			{
				isConcentric = false;
			}
		}
		
		return isConcentric;
	}
}


