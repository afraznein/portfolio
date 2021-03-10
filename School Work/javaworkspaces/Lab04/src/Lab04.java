import java.util.Scanner;
/*
Name: Anthony Frazier
Date: February 3, 2016
Program Name: Lab04
Description: Lab 04
Objective:	
Implement a program which uses a recursive method that determines if a string entered by the user is a palindrome.  
A string is palindrome if it is the same forward as it is backwards, such as “radar” or “Taco cat”.

Here are assumptions about the recursive method
The method should ignore all white space
The method should also ignore case
Assume if a string is less than two characters or is null that it is a palindrome
Do not use any loops or iteration that is not recursive
 
Hints:
Recursive methods generally attempt to solve a smaller problem then return the results of those in order to solve the larger one.
To recursively solve this always look at the first character and the last character. 
Only if they are equal do you recursively call the method again, but pass in a new string with the first and last character removed from the given string.
Some useful string methods but all may not be used in the solution
toUppercase(): makes all characters uppercased
toLowercase(): makes all characters lowercased
charAt(index): returns a character at the specified index
trim(): returns a string with leading and trailing whitespace removed
substring(startIndex,endIndex): returns a string from the starting index (inclusive or is included) to the ending index (exclusive or is not included).
*/
public class Lab04 
{

	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a word and I will determine if it is a palindrome");
		String  word = input.nextLine();
		word = word.toLowerCase();
		word = word.trim();
		
		boolean check = recursivePalindrome(word);
		if ( check )
		{
			System.out.println("The word '"+word+"' is a palindrome.");
		}
		else
		{
			System.out.println("The word '"+word+"' is NOT a palindrome.");
		}

	}
	
	public static boolean recursivePalindrome(String w)
	{
		if (w == null)	// Per directions, if w = null return is a palindrome
		{
			return true;
		}
		if (w.length()-1 < 2) // Per directions, if string < 2 return is a palindrome
		{
			return true;
		}
		if (w.charAt(0) != w.charAt(w.length()-1) )	// If the first character != the last character, is not a palindrome
		{
			return false;
		}
		else
		{
			return recursivePalindrome( w.substring(1,w.length()-1) );	// If it bypasses all of the above checks, take off index 0 and index length()-1 and call again
		}
	}
}
