/*
Name: Anthony Frazier
Date: May 23, 2015
Program Name: Homework 2
Description: Input the length, width and height of a right rectangular pyramid.
Output the area of the rectangle and the volume of the right rectangular pyramid.
The area of a rectangle is length times width.
The volume of a right rectangular pyramid is the product of the length, width and height, divided by 3.
Assume that all measurements are in inches.
The output must be clearly labeled, and the prompts for input must also be clear.
You must include your name, the date and a short description of the program as comments at the top of the file.
All variables must have descriptive identifiers.
*/


import java.util.Scanner;
public class Homework2
{

	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		double length, width, height, area, volume;																	// Declare Variables

		System.out.println("Please input the length of the pyramid in inches.");									// Request User Input
			length = input.nextDouble();
		System.out.println("Please input the width of the pyramid in inches.");
			width = input.nextDouble();
		System.out.println("Please input the height of the pyramid in inches.");
			height = input.nextDouble();

	area = length * width;																							// Perform Calculations
		volume = (length * width * height) / 3;

		System.out.println("The area of a rectangle in inches is length * width = area.");							// Output
		System.out.println("("+length+") * ("+width+") = ("+area+") inches².");
		System.out.println("The volume of a right rectangular pyramid in inches is ( length * width * height ) / 3 = volume.");
		System.out.println("("+length+") * ("+width+") * ("+height+") ) / 3 = ("+volume+") inches (cubed).");		// Alt code for cubed doesn't seem to work. :(
	}
}