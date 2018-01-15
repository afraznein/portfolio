/*
Name: Anthony Frazier
Date: June 8, 2015
Program Name: Homework 6
Description: Write a program to guess a number from 0 to 999, to the following specifications.

	Tell the user to think of a number from 0 to 999. The program will guess the number.
Always start with a lower bound of 0 and an upper bound of 999.
	As long as the user inputs a string starting with 'l' or 'm',
	The next guess is the average of the upper bound and the lower bound, so the first guess is always 499.
Prompt the user in the following format (changing 499 to another guess each time) and then input a string.

Is it 499? (Enter y if it is, l if your number is less, or m if your number is more.)
	As long as the user does not enter 'y', 'l' or 'm', prompt them with Enter y, lowercase L, or m only! Try again: and input another string.
If the user inputs a string starting with 'l' (or 'L'), change the upper bound to one less than the guess.
	If the user inputs a string starting with 'm' (or 'M'), change the lower bound to one more thant the guess.
The next guess is the average of the upper bound and the lower bound.
	Keep count of the number of guesses.
Report the number of guesses made and the correct guess.
	Extra credit (5 points): Detect when the user is not answering correctly and there is no possible correct guess left. Output "Do not cheat!" and exit (System.exit(0);).


Is it 499? (Enter y if it is, l if your number is less, or m if your number is more.) n
Enter y, lowercase L, or m only! Try again: l
Is it 249? (Enter y if it is, l if your number is less, or m if your number is more.) m
Is it 374? (Enter y if it is, l if your number is less, or m if your number is more.) l
Is it 311? (Enter y if it is, l if your number is less, or m if your number is more.) y
I guessed your number, 311 in 4 guesses.
Be sure to add comments with your name, the date, a description of the program and good variable names.

Put your .java file into a compressed file and upload it to this dropbox.
*/

import java.util.Scanner;
public class Homework6
{
		public static void main(String[] args)
  		{

			Scanner input = new Scanner(System.in);
			int lower = 0, upper = 999, count = 1, guess = 0;
			boolean correct = false;
			String keyboard = "";
			char kbd;
			System.out.println("Think of a number from '0' to '999'.");

			while ( correct != true )
			{
				guess = (upper + lower) / 2;
				System.out.println("Is it "+guess+"? (Enter Y if it is, L if your number is less, or M if your number is more.)");
				keyboard = input.nextLine();
				keyboard = keyboard.toUpperCase();
				kbd = keyboard.charAt(0);
					if ( kbd != 'Y' && kbd != 'L' && kbd != 'M' )
					{
						System.out.println("Enter 'Y', 'L' or 'M' only! Please try again.");
							keyboard = input.nextLine();
							keyboard = keyboard.toUpperCase();
							kbd = keyboard.charAt(0);
					}
					if ( kbd == 'L' )
					{
						upper = guess - 1;
						count++;
					}
					if ( kbd == 'M' )
					{
						lower = guess + 1;
						count++;
					}
					if ( kbd == 'Y' )
					{
							System.out.println("I guessed your number, "+guess+" in "+count+" guesses.");
							correct = false;
							System.exit(0);
					}
				//	if ( upper == lower && kbd != 'Y')
				//	{
				//		System.out.println("Do not cheat!");
				//		System.exit(0);
				//	}

			}
		}
}