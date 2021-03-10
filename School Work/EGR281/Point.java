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
Hint: For each row and column, instantiate a new Point with those coordinates and test for equality with each Point in the array.  If it is not equal to any Point in the array, print a space.
An example run is attached.
*/

public class Point
{
	private int ex, why;
	//Constructor
	Point(int x, int y)
	{
		this.set(x,y);
	}
	//Setters
	public void set(int x, int y)
	{
		if (x > 24 || y > 24 || y < 0)
		{
			throw new IllegalArgumentException("Invalid coordinate");
		}
		else
		{
			this.ex=x;
			this.why=y;
		}
	}
	public void setEx(int x)
	{
		if (x > 24)
			throw new IllegalArgumentException("Invalid coordinate");
		else
			this.ex = x;
	}
	public void setWhy(int y)
	{
		if (y > 24 || y < 0)
			throw new IllegalArgumentException("Invalid coordinate");
		else
			this.why = y;
	}

	//Getters
	public int getEx()
	{
		return ex;
	}
	public int getWhy()
	{
		return why;
	}
	public String toString()
	{
		String str = "";
		return str;
	}

	public boolean equals(Point other)
	{
		if( this.getEx() == other.getEx() && this.getWhy() == other.getWhy() )
			return true;
		else
			return false;
	}
}
