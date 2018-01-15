/*
Name: Anthony Frazier
Date: July 01, 2015
Program Name: Fighter
Description: Lab Assignment 10 - More Classes

Write two Java classes.

    An Investment class represents an investment, with interest compounded yearly, which means that each year,
    the APR is multiplied by the current investment value, and the result is added to the value of the investment.
        Include all of the usual accessor and mutator methods.
        Include a toString() method so you can print out the value of the investment in a nice format.
        There should be a wait(n) method that will add the interest for n years.
    Write a class with a main method that will input an APR, an investment, a number of years, and an iterval
    (such as 1 for every year or 2 for every 2 years, etc.), then print out a table showing the initial value of
    the investment and then its value after each interval up to the number of years, in a nice format.

Comment your code with your name, date, description and a comment for each method,
including a short description of the purpose of the method, the parameters and the return value,
place your program into a compressed archive and upload that to this dropbox.
*/

import java.util.Scanner;
import java.text.DecimalFormat;
public class InvestmentDriver
{
	public static void main(String[] args)
	{
			boolean check = true;												// Check flag condition for loop
			Scanner input = new Scanner(System.in);								// Accept input from user

			System.out.println("This program a total return on investment based on a principle amount, apr, and number of years.");

			while ( check )														// While check = true, perform loop
			{
				System.out.println("Would you like to calculate a loan? Type y or n");
				char cont = input.next().toLowerCase().charAt(0);				// Ask user to calculate a loan. If y, calculate.
																				// If anything else, check = false
				if (cont == 'y')
				{
					Investment i = new Investment();

					System.out.println("Enter the loan amount");				// Ask user for input
					i.setPrinciple( input.nextDouble() );						// Obtain input via method

					System.out.println("Enter the APR in percent.");			// Ask user for input
					i.setApr( input.nextDouble() );								// Obtain input via method

					System.out.println("How many years to calculate?.");			// Ask user for input
					i.setYears( input.nextInt() );									// Obtain input via method

					i.toCalc();

				}
				else
				{
					check = false;

				}
			}
			System.out.println("Thanks!");
	}

}

