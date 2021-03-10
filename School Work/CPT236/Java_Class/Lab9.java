/*
Name: Anthony Frazier
Date: November 23, 2014
Program Name: Lab9
Description: Lab Assignment 9: Methods

Write a program that prompts the user to input a sequence of characters and outputs the number of vowels.
Write a value returning method isVowel that returns the value true if a given character is a vowel and otherwise returns false.

Enter a sequence of characters: roopa
Number of vowels =3

Another run

Enter a sequence of characters: xyz
Number of vowels =0


*/

import java.util.Scanner;
public class Lab9
{
		public static void main(String[] args)
  		{
			Scanner input = new Scanner(System.in);
			String ans = "";
			boolean check = true;

			System.out.println("This program will count the vowels in whatever string you type.");

			while (check == true)
			{
				System.out.println("Would you like to input a string? Type yes or no");
				char cont = input.next().charAt(0);

				if (cont == 'y')
				{
					System.out.println("Enter a sequence of characters: ");		// Ask user for input
					ans = input.next();
					ans = ans.toLowerCase();									// Convert string to lowercase
					int vowels = isVowel(ans);									// Call method
					System.out.println("Number of vowels = "+vowels);			// Output vowel count
				}
				else
				{
					check = false;

				}
			}

			System.out.println("Thanks!");
		}
		// Method definition
		public static int isVowel(String ans)
		{
			int len = (ans.length() - 1);			// Set variable = to total index of string
			int x = 0;								// Set variable for counting index
			int count = 0;							// Set variable for vowel counter
			while (x <= len)
			{
				if (ans.charAt(x) == 'a')
				{
					count++;
				}
				if (ans.charAt(x) == 'e')
				{
					count++;
				}
				if (ans.charAt(x) == 'i')
				{
					count++;
				}
				if (ans.charAt(x) == 'o')
				{
					count++;
				}
				if (ans.charAt(x) == 'u')
				{
					count++;
				}
				x++;
			}
			return count;						// Return total vowel count
		}

}