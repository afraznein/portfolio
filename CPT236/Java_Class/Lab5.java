/*
Name: Anthony Frazier
Date: October 7, 2014
Program Name: Lab5
Description: Output overtime pay, regular pay and total pay based on user input on hours worked and rate of pay. Do not print anything in regards to overtime pay if there is no overtime.
*/


import java.util.Scanner;
public class Lab5
{

	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);									// Declare scanner input
		double hours, rate, reg, overtime, total;								// Declare variables

		System.out.println("Please input the total number of hours worked");	// Request input from user
		hours = input.nextDouble();
		System.out.println("Please input the hourly wage");
		rate = input.nextDouble();

		reg = hours * rate;														// Perform basic calculations. Regular pay = Hours x Hourly Rate
		total = reg;
		overtime = 0;

		System.out.println("The number of hours worked was "+hours+" hours.");	// Output number of hours worked, rate of pay and regular pay regardless of overtime.
		System.out.println("The rate of pay was  $"+rate+".");
		System.out.println("The regular pay was  $"+reg+".");

		if (hours > 40)															// Check to see if user input indicates working more than 40 hours a week
			{
					overtime = (hours - 40) * 1.5 * rate;						// Perform calculation to obtain overtime pay
					total = overtime + reg;										// Add overtime to regular pay for total
					System.out.println("The overtime pay was $"+overtime+".");	// Output overtime pay and total pay
					System.out.println("The total pay was $"+total+".");
			}
			else																// If no overtime earned, just output total pay
				System.out.println("The total pay was $"+total+".");

	}
}