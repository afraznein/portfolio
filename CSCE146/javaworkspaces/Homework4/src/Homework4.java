import java.util.*;
import java.applet.*;
import java.awt.*;


/*
Name: Anthony Frazier
Date: February 3, 2016
Program Name: Homework4
Description: Homework4
Objective:	Write a program in which draws (yes it actually makes a picture) a triangular fractal using recursion.  
This is best if done using a java applet. 
 
Suggested Methodology
The idea for it is this
	First draw a filled equilateral triangle
	Next draw another filled equilateral triangle of a different color that’s upside down in the middle of that triangle
	Using the other triangles formed repeat step 2 until a pixel limit of 4 is reached
	
The method fillPolygon(int[] xPoints, int[] yPoint, numberOfPoints) as called by the graphics device is important
The method setColor(Color aColor) is important for picking different colors to draw things.

// Not Complete, going to attempt to finish after work tommorrow :(
*/
public class Homework4 extends Applet
{
	private Image display;
	private Graphics drawingArea;
	
	public void init()
	{
		int[] xPoints = new int[3];
		int[] yPoints = new int[3];
		int side = 500;
		//int height = 0;
		display = createImage(side, side);
		drawingArea = display.getGraphics();
		drawInitialTriangles(xPoints, yPoints, drawingArea);
		
		side = 125;
		
		drawTriangle(xPoints, yPoints, side, drawingArea);
		
		
	}
	
	public void paint(Graphics g)
	{
		g.drawImage(display, 0, 0, null);
	}
	
	public static void drawInitialTriangles(int[] xPoints, int[] yPoints, Graphics g)
	{
		g.setColor(Color.black);
		xPoints[0] = 0;
		yPoints[0] = 500;
		
		xPoints[1] = 250;
		yPoints[1] = 0;
		
		xPoints[2] = 500;
		yPoints[2] = 500;
		g.fillPolygon(xPoints, yPoints, 3);
		
		g.setColor(Color.white);
		xPoints[0] = 125;
		yPoints[0] = 250;
		
		xPoints[1] = (500 /4)*3;
		yPoints[1] = 250;
		
		xPoints[2] = 250;
		yPoints[2] = 500;
		g.fillPolygon(xPoints, yPoints, 3);
	}
	public static void drawTriangle(int[] xPoints, int[] yPoints, int side, Graphics g)
	{
		
		int height = side/2 * (int)Math.sqrt(3);
		int[] tempTopX = new int[3];
		int[] tempTopY = new int[3];
		int[] tempLeftX = new int[3];
		int[] tempLeftY = new int[3];
		int[] tempRightX = new int[3];
		int[] tempRightY = new int[3];

		// Top Black Triangle
		xPoints[0] = xPoints[0];
		yPoints[0] = yPoints[0];
		
		xPoints[1] = xPoints[0]+side;
		yPoints[1] = yPoints[0]-(side*2);
		
		xPoints[2] = xPoints[0]+(side*2);
		yPoints[2] = yPoints[0];
		
		tempTopX[0] = xPoints[0];		// Set temp array
		tempTopX[1] = xPoints[1];
		tempTopX[2] = xPoints[2];
		
		tempTopY[0] = yPoints[0];
		tempTopY[1] = yPoints[1];
		tempTopY[2] = yPoints[2];
			
		// Top White Triangle
		xPoints[0] = xPoints[0]+(side/2);
		yPoints[0] = yPoints[0]-side;
		
		xPoints[1] = xPoints[2]-(side/2);
		yPoints[1] = yPoints[2]-side;
	
		xPoints[2] = xPoints[0]+(side/2);
		yPoints[2] = yPoints[2];
		g.setColor(Color.red);
		g.fillPolygon(xPoints, yPoints, 3);
		

		// Bot Left Black Triangle
		xPoints[0] = xPoints[2]-(side*2);
		yPoints[0] = yPoints[2]+(side*2);
		
		xPoints[1] = xPoints[0]+side;
		yPoints[1] = yPoints[0]-(side*2);
		
		xPoints[2] = xPoints[1]+(side);
		yPoints[2] = yPoints[1]+(side*2);
		
		tempLeftX[0] = xPoints[0];		// Set temp array
		tempLeftX[1] = xPoints[1];
		tempLeftX[2] = xPoints[2];
		
		tempLeftY[0] = yPoints[0];
		tempLeftY[1] = yPoints[1];
		tempLeftY[2] = yPoints[2];
		
		// BotLeft White Triangle
		xPoints[0] = xPoints[0]+(side/2);
		yPoints[0] = yPoints[0]-(side/2);
		
		xPoints[1] = xPoints[0]+(side/2);
		yPoints[1] = yPoints[0];
	
		xPoints[2] = xPoints[0]+(side/4);
		yPoints[2] = yPoints[0]+(side/2);
		g.setColor(Color.red);
		g.fillPolygon(xPoints, yPoints, 3);
		
/*		
		// Bot Right Black Triangle
		xPoints[0] = side*2;
		yPoints[0] = side*4;
		
		xPoints[1] = (side*2)+side;
		yPoints[1] = side*2;
		
		xPoints[2] = side*4;
		yPoints[2] = side*4;
		
		tempRightX[0] = xPoints[0];		// Set temparray
		tempRightX[1] = xPoints[1];
		tempRightX[2] = xPoints[2];
		
		tempRightY[0] = yPoints[0];
		tempRightY[1] = yPoints[1];
		tempRightY[2] = yPoints[2];
		
		// BotLeft White Triangle
		xPoints[0] = xPoints[0]+(side/2);
		yPoints[0] = yPoints[0]-side;
		
		xPoints[1] = xPoints[2]-(side/2);
		yPoints[1] = yPoints[2]-side;
	
		xPoints[2] = xPoints[0]+(side/2);
		yPoints[2] = yPoints[2];
		g.setColor(Color.red);
		g.fillPolygon(xPoints, yPoints, 3);
*/		
		if ( side < 4 )
		{
			return;
		}
		else
		{
			g.setColor(Color.red);
			drawTriangle(tempTopX, tempTopY,side/2,g);
			drawTriangle(tempLeftX, tempLeftY,side/2,g);
			drawTriangle(tempRightX, tempRightY,side/2,g);
		}
		
	}
}