/*
Name: Anthony Frazier
Date: June 22, 2015
Program Name: Homework8
Description: Lab Assignment 8: Methods

Write a program to calculate a loan payment, but use methods to do the work.

Write a program to prompt for and input a loan amount, an APR, and a number of years (possibly fractional), and to output the monthly payment and the total amount including interest, then repeat as desired.

The APR divided by 1200 (which is 12 months times 100 percent) is the monthly_interest, as used in the following formula.
The monthly payment is (amount*monthly_interest)/(1-(1/((1+monthly_interest) ^(years*12)))) (Note that (years*12) is a superscript)
The total amount including interest is the monthly payment times the number of months.
You must use a method to calculate and return the car payment.
You must use a method to input each value, and to check to make sure it is reasonable. For example, APR's and loan amounts must be positive, years of the loan must be 1-5, etc.
Each input value must be of the correct type before continuing. In other words, if the user inputs a String or a floating-point number when prompted for an integer,
give them another chance until they input the correct type.
The user should be able to repeat as long as they answer "y" or "Y" when asked if they want to continue.
Turn in your well-documented .java file in a compressed file to this dropbox. In addition to the documentation specified before,
each method must have comments for the method purpose, each parameter, each local variable, and the return value, if any.

*/
import java.util.Scanner;
public class Homework8
{
		public static void main(String[] args)
  		{
			Scanner input = new Scanner(System.in);
			boolean check = true;
			double loan = 0.0, apr = 0.0, years = 0.0, monthly_interest = 0.0, monthly_payment = 0.0, total_amount = 0.0;


			System.out.println("This program calculates loan payments based on loan amount, APR, and number of years.");

			while ( check )
			{
				System.out.println("Would you like to calculate a loan? Type y or n");
				char cont = input.next().charAt(0);

				if (cont == 'y')
				{
					System.out.println("Enter the loan amount");				// Ask user for input
					loan = inputAnswer(loan);

					System.out.println("Enter the APR in percent.");			// Ask user for input
					apr = input.nextDouble();
					monthly_interest = interestCalc(apr);						// Call method (monthly_interest = returned interest value)

					System.out.println("Enter the number of years.");			// Ask user for input
					years = input.nextDouble();

					monthly_payment = paymentCalc(loan, monthly_interest, years);	// Call method (monthly_payment = returned payment value)
					total_amount = totalCalc(monthly_payment, years);	// Call method (monthly_payment = returned payment value)

					System.out.println("A loan principle of $"+loan+" with an APR of "+apr+"% over "+years+" years will result in making a monthly payment of $"+monthly_payment+".");				// Output

					System.out.println("The total amount is $"+total_amount);
				}
				else
				{
					check = false;

				}
			}

			System.out.println("Thanks!");
		}


		// Method definition

		public static double inputAnswer(double inp)
		{
			Scanner input = new Scanner(System.in);
			inp = input.nextDouble();
			if ( inp.hasNextDouble() )
			{
				while ( inp < 0 )
				{
					System.out.println("That is not a valid entry. Please try again.");
					inp = input.nextDouble();
				}
			}
			else
			{
				while ( inp < 0 || inp.hasNextDouble() = false )
				{
					System.out.println("That is not a valid entry. Please try again.");
					inp = input.nextDouble();
				}
			}
			return inp;


		}


		public static double interestCalc(double apr)
		{
			double interest = apr / 100;
			interest = apr / 1200;
			return interest;						// Return monthly interest rate
		}

		public static double paymentCalc(double loan, double interest, double years)
		{
			double payment = (loan * interest) / ( 1 - ( 1 / ( Math.pow( 1 + interest, years * 12 ) ) ) );

			return payment;						// Return monthly payment
		}

		public static double totalCalc(double payment, double years)
		{
			double total = payment * years;
			return total;						// Return total amount
		}


}