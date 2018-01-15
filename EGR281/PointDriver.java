/*
Name: Anthony Frazier
Date: July 20, 2015
Program Name: Point
Description: Lab Assignment 12: Arrays

Define two classes.

A Point class contains

x and y int fields
a two-int-parameter constructor
all accessor and mutator methods for x and y
a toString method
an equals method
The mutator methods and constructor should not allow values greater than 24 or less than 0 for any coordinate.
A driver class contains a main method that

inputs x and y coordinates for points until the user enters a negative x coordinate, saving points in an array of up to 625 points.
prints out 25 lines of 25 characters, printing a "*" if the current coordinates match a point in the array and a " " otherwise.
Hint: For each row and column, instantiate a new Point with those coordinates and test for equality with each Point in the array.
If it is not equal to any Point in the array, print a space.
An example run is attached.
*/

import java.util.*;
public class PointDriver
{
	public static void main(String[] args)
  	{
		Scanner input = new Scanner(System.in);
		Point[] list = new Point[625];
		int count = 0;
		System.out.println("Please input coordinates (x,y). To end, input a negative x coordinate");

		int x=0, y=0;
		while ( x >= 0 && y >= 0 )
		{
				System.out.print("Input x-coordinate: ");				// Request Input from user
				x = input.nextInt();
				System.out.print("Input y-coordinate: ");				// Request Input from user
				y = input.nextInt();
				list[count] = new Point(x, y);							// Write x, y as new object into array at index count
				count++;												// Increment count
		}
		count = 0;
		for (int i=0; i<=24; i++)										// Outer loop
		{
			count = 0;
			for (int j=0; j<=24; j++)									// Inner loop
			{
				Point p = new Point(i, j);								// Create new point at i, j
				while (count < 625)
				{
					if ( list[count].equals(p) )							// Test new point created (i, j) against array[index]; if true, print *, if false, loop
					{
						System.out.print("*");
						count = 999;
					}
					else
					{
						count++;

					}
				System.out.print("");

				}
			}
			System.out.println("");
		}


	}

}