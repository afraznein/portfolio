/*
Name: Anthony Frazier
Date: September 17, 2014
Program Name: Lab3
Description: Third Java Lab; Recieve user input for capacity of miles per gallon of fuel tank, then compute total miles.
*/

import java.util.Scanner;							// Import scanner object to obtain keyboard input from user
public class Lab3
{


	public static void main(String[] args)
	{
		Scanner input=new Scanner(System.in);		// Declare new scanner object for input

		int capacity, mpg, total;					// Declare variables

		System.out.println("This program is designed to compute the total number of miles you can drive based on the capacity of the fuel tank and the miles per gallon.");
		System.out.println("Please input the capacity of the fuel tank in whole gallons.");

			capacity=input.nextInt();				// Request first input from user

		System.out.println("Please input the miles per gallon of the vehicle in whole miles.");

			mpg=input.nextInt();					// Request first input from user

			total = capacity * mpg;					// Perform calculations

		System.out.println("Based on the fuel capacity of ("+capacity+") total gallons and ("+mpg+") miles per gallon, the vehicle can travel ("+total+") miles on one tank of gas.");
	}
}													// End of program