/*
Name: Anthony Frazier
Date: January 12, 2016
Program Name: Lab1
Description: Lab Assignment 1
Write a program that prompts the user for a size of a 2D matrix.
Then it populates the matrix with random numbers from 0-9, and prints that out.
Next create a new matrix that is twice the size of the old one and is populated with the numbers from the old one expanded out in 2x2 increments.
*/

import java.util.*;
import java.lang.*;
public class Lab1
{
	public static void main(String[] args)
  	{
		Scanner input = new Scanner(System.in);														// Declare new scanner object, ask user for input
		String str, str1;																			// Declare strings for output
		System.out.println("Please input the size of the array");
		int x = input.nextInt();
		System.out.println("");

		int[][] list = new int[x][x];																// Create new 2-d array of size x
		for (int i=0; i<x; i++)																		// Outer loop
		{
			for (int j=0; j<=x; j++)																	// Inner loop
			{
				double rand = ( Math.random() * 10.0);												// Generate random numbers, *10 to make whole integers
				list[i][j] = (int)rand;																// Type-cast force
				str = (list[i][j] + " ");
				System.out.print(str);
			}

			System.out.println("");
		}


		int dub = x * 2;																			// New variable dub created to double the size of the original input, x
		System.out.println("");
		int[][] list2 = new int[dub][dub];															// Create new 2-d array of size twice the previous array

		for (int i=0; i<x; i++)																		// Outer loop
		{
			for (int j=0; j<x; j++)																	// Inner loop
			{
				int ex=i, why=j;																	// EX = x coordinates, Why = y coordinates
				list2[ex][why] = list[i][j];														// double size 0,0 = original 0,0
				System.out.print(list2[ex][why]);
				why++;
				list2[ex][why] = list[i][j];														// double size 0,1 = original 0,0
				System.out.print(list2[ex][why]);
				ex++;
				why--;
				list2[ex][why] = list[i][j];														// double size 1,0 = original 0,0
				System.out.print(list2[ex][why]);
				why++;
				list2[ex][why] = list[i][j];														// double size 1,1 = original 0,0
				System.out.print(list2[ex][why]);
			} 																						// y coordinate increments from 0 to 1, rinse repeat above

			System.out.println("");
		}
																									// End outer loop second array

		//for (int i=0; i<x*2; i++)																		// Outer loop
		//{
		//	for (int j=0; j<x*2; j++)																	// Inner loop
		//	{
		//		list2[i][j] = list[i/2][j/2];
		//		System.out.print(list2[i][j]+" ");
		//	}

		//	System.out.println("");
		//}

	}																								// End main method
}																									// End class