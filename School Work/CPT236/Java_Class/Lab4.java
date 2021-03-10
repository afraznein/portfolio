/*
Name: Anthony Frazier
Date: September 29, 2014, September 30,2014
Program Name: Lab4
Description: Fourth Java Lab; Recieve user input for string of four positive numbers, then output each number individually.
Thought Process : I was able to get this to work very easily using type string from the start, but the lab seemed to want to use type int. I was unable to index the individual parts of an int type, so I forced it to type string in order to reach each char at index. Had to look up Integer.toString(variable) on the API.
*/


import java.util.Scanner;
public class Lab4
{

	public static void main(String[] args)
	{
		int num;								// Declare Variable
		Scanner input=new Scanner(System.in);	// Declare Variable

		System.out.println("Please input a positive four-digit integer");	// Request input from user

		num = input.nextInt();												// Request input from user
		String output = Integer.toString(num);								// Force int data type to string


		System.out.println("The first  digit entered was : "+output.charAt(0));	// Output char at index 0, location 1
		System.out.println("The second digit entered was : "+output.charAt(1));	// Output char at index 1, location 2
		System.out.println("The third  digit entered was : "+output.charAt(2));	// Output char at index 2, location 3
		System.out.println("The fourth digit entered was : "+output.charAt(3));	// Output char at index 3, location 4

	}
}