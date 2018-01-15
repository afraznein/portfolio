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
import java.text.DecimalFormat;
public class Investment
{
	private double principle;    // Principle Investment Variable
	private double invest;	 	// Total value of investment (interest compounded yearly + principle)
	private double apr; 			// Interest Rate
	private double years;			// Number of years to compound interest
	DecimalFormat form = new DecimalFormat("#,###.00"); // Use to format money into readable format

	// Setters
		public void setPrinciple(double p)	// Set principle
		{
			this.principle = p;
		}

		public void setInvest(double i)		// Set investment
		{
			this.invest = i;
		}
		public void setApr(double a)		// Set APR
		{
			this.apr = a;
		}
		public void setYears(double y)			// Set Years
		{
			this.years = y;
		}
	// Getters

		public Double getPrinciple()			// Get principle
		{
			return principle;
		}

		public Double getInvest()				// Set investment
		{
			return invest;
		}
		public Double getApr()				// Set APR
		{
			return apr;
		}
		public Double getYears()				// Set Years
		{
			return years;
		}
	// Constructor
	public Investment()
	{
		this.principle = 0.0;
		this.apr = 0.0;
		this.years = 0.0;
		this.invest = 0.0;
	}


	// Performs calculations
 	public void toCalc()
 	{
		double p = getPrinciple();
		double a = getApr();
		double i = getInvest();
		double y = getYears();

		i = i + wait();

		String prin = toString(p);
		String ap = toString (a);
		String yea = toString(y);
		String inv = toString(i);

		toPrint(prin, ap, yea, inv);
	}

	// toString method (to format output)
		public String toString(double f)
		{
			String str = form.format(f);
			return str;
		}
	// Wait method to calculate
		public double wait()
		{
			double p = this.getPrinciple();
			double a = this.getApr();
			double y = this.getYears();
			double i = this.getInvest();

			i = p * a * y;
			return i;
		}
	// Print table method
		public void toPrint(String p, String a, String y, String i)
		{
			System.out.println("Principle:$"+p);
			System.out.println("APR      : "+a+"%");
			System.out.println("Years    : "+y);
			System.out.println("Total    :$"+i);
		}

}