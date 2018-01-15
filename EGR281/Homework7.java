/*
Name: Anthony Frazier
Date: June 10, 2015
Program Name: Homework7
Description: An algorithm for determining prime numbers <= some integer i is as follows.
Start by assuming that i is prime. (Use a boolean variable.)
For each j from 2 to the square root of i (use Math.sqrt(i)), check to see if i is divisible by j
(What is i%j if it is?).  If i is divisible by j, it is not prime, so set your boolean variable to false.
If i is not divisible by any of those values of j, so the boolean variable is still true, then i is prime.

Use this idea to write a program that will output a menu like the following:
1. Check to see if a number is prime.
2. Find the next prime after a number.
3. Find the next prime before a number.
4. Find all primes between two numbers.
5. Quit

The menu choices should proceed as follows:

Input an integer i and output a message stating whether i is prime. If i is less than 2, state that numbers less than 2 cannot be prime.
Input an integer i and output the smallest prime number >= i.
Input an integer i and output the largest prime number <= i. If i is less than 2, state that numbers less than 2 cannot be prime.
Input two integers n1 and n2 and output all prime numbers >= n1 and <= n2
Quit the program.
Repeat printing the menu, inputting a choice and performing the chosen calculations as long as the choice is not 5.

Document your program with comments as usual, put it into a compressed file and upload it to this dropbox.
*/

import java.util.Scanner;
import java.lang.Math;
public class Homework7
{
	public static void main(String[] args)
  	{
		Scanner input = new Scanner(System.in);
		boolean prime = true;
		int menu = 0;
		int i = 0; // input
		int j = 2; // Counter for division

		System.out.println("All numbers entered for the purpose of this program must be greater than 2.");

		while ( menu != 5 )
		{
			System.out.println("1. Check to see if a number is prime.");
			System.out.println("2. Find the next prime after a number.");
			System.out.println("3. Find the next prime before a number.");
			System.out.println("4. Find all primes between two numbers.");
			System.out.println("5. Quit");
			menu = input.nextInt();
			switch (menu) {
				case 1:	// Check for if number is prime
				System.out.println("What number do you want to check to see if it is prime?");
				i = input.nextInt();
				if ( i <= 2 )
				{
					System.out.println("Cannot check numbers less than 2 for prime, Please try again.");
					i = input.nextInt();
				}

				while ( prime == true && j <= Math.sqrt(i) )
				 {
					if ( i%j == 0 )
					{
						prime = false;
					}
					else j++;
				}
				if ( prime == false )
					System.out.println("The number "+i+" is not prime.");
				else
					System.out.println("The number "+i+" is prime.");
				j=2;
				break;

				case 2:  // Check for first prime number after number
				System.out.println("What number do you want to check after until we find our first prime number?");
				 i = input.nextInt();
				 if ( i <= 2 )
				 {
				 	System.out.println("Cannot check numbers less than 2 for prime, Please try again.");
				 	i = input.nextInt();
				 }
				 i = i + 1;
				 j = 2;
				 prime = false;
				 while ( prime == false )
				 {
					while ( j <= Math.sqrt(i) )
					{
						if ( i%j == 0 )
						{
							i++;
							j=2;
						}
						else
							j++;
					}
					if ( i%j != 0 )
					{
						prime = true;
					}
				}
				System.out.println("The number "+i+" is prime.");
				j=2;
				break;

				case 3: // Check for first prime number before number
				System.out.println("What number do you want to check before until we find our first prime number?");
				 i = input.nextInt();
				 i = i - 1;
				 if ( i <= 2 )
				 {
				 	System.out.println("Cannot check numbers less than 2 for prime, Please try again.");
				 	i = input.nextInt();
				 }
				 j = 2;
				 prime = false;

				 while ( prime == false )
				 {
					while ( j <= Math.sqrt(i) )
					{
						if ( i%j == 0 )
						{
							i--;
							j=2;
						}
						else
							j++;
					}
					if ( i%j != 0 )
					{
						prime = true;
					}
				}
				System.out.println("The number "+i+" is prime.");

				break;
				case 4:	// Check for all prime numbers between two numbers?
				System.out.println("What numbers do you want check between for prime numbers? Enter numbers lowest to highest.");
				 i = input.nextInt();
				 if ( i <= 2 )
				 {
				 	System.out.println("Cannot check numbers less than 2 for prime, Please try again.");
				 	i = input.nextInt();
				 }
				 int l = input.nextInt();
				 if ( l <= 2 )
				 {
				 	System.out.println("Cannot check numbers less than 2 for prime, Please try again.");
				 	l = input.nextInt();
				 }
				 if ( l <= i )
				 {
				 	System.out.println("The second number must be higher than the first number. Neither number can be less than 2. Please reinput both numbers.");
				 	i = input.nextInt();
				 	l = input.nextInt();
				 }
				 //i++;
				 //l--;
				 j = 2;
				 prime = true;

				 while ( i < l )
				 {
					while ( prime = true && j <= Math.sqrt(i) && i < l )
					{
						if ( i%j != 0 )
						{
							j++;
						}
						else // ( i%j == 0 )
						{
							prime = false;
							j=2;
							if ( i < l )
								i++;
						}
					}
					if ( prime = true )
					{
						System.out.println("The number "+i+" is prime.");
						i++;
						j=2;
					}
					else
						i++;
				}
				j=2;
				break;

				case 5:
				break;									// Exit Program
				default:
				System.out.println("Invalid Input.");  // Exit Program
				break;
				}
		} // End Switch Menu
		System.exit(0);
	}  // End Main Method
}