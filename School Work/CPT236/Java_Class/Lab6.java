/*
Name: Anthony Frazier
Date: October 15, 2014
Program Name: Lab6
Description: Help a store clerk whether to sell beer to a customer based on age >=21; If old enough, make sure has enough money beer=$5 and return change. If not old enough, tell how many years must wait; If not enough cash, tell how much more is needed.
*/

import java.util.Scanner;
public class Lab6
{

	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);		// Declare scanner input
		int age, diff;								// Declare variables age for age of user, diff for more years required to reach 21 if needed, cash for input of cash, change if necessary
		double cash, change;

		System.out.println("Hello welcome to Frazier-Mart. If you're interested in buying beer, you'll have to tell me how old you are");	// Request input from user
		age = input.nextInt();

		if (age >= 21)
			{
				System.out.println("You are old enough to buy beer. How much cash do you have? Beer costs $5.00. You can only buy one, we haven't learned loops yet.");
				cash = input.nextDouble();

				if (cash >= 5.00)
					{
						System.out.println("You have enough cash to buy beer.");
						change = cash - 5.00;		// Perform calculation to see if we need to make change

						if (change >= 0.01)
							{
								System.out.println("Thank you for your patronage, here is your $"+change+" change back.");
							}
						else
							{
								System.out.println("Thank you for your patronage.");
							}
					}
				else
					{
						change = 5.00 - cash;		// Perform calculation to see how much more money user needs
						System.out.println("You need at least $"+change+" more to purchase beer");
					}
			}										// End of Age If Statement
		else
			{
				diff = 21 - age;					// Perform calculation to see how many years user must wait to buy beer
				System.out.println("You are not old enough to buy beer, please come back in "+diff+" years.");
			}
	}
}