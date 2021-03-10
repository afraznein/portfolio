/*
Name: Anthony Frazier
Date: May 31, 2015
Program Name: Homework 4
Description: Write a program to the following specifications:

Input the user's last name.
Greet the user by name.
Show an appointment for the user, based on the first letter of their last name, ignoring case:
Names starting with A to G should go to room A at 8 am.
Names starting with H to M should go to room B at 8 am.
Names starting with N to T should go to room A at 12 pm.
Names starting with U to Z should go to room B at 12 pm.
Names starting with anything else should be instructed to run the program again.
Use appropriate documentation and identifiers.
*/

import java.util.Scanner;
import java.lang.Character;
public class Homework4
{
		public static void main(String[] args)
  		{
				boolean check = true;


				while ( check == true )
				{
						Scanner input = new Scanner(System.in);
						String str, sort;
						char firstLetter;


						System.out.println("Please input your last name");		// Request input from user
						str = input.next();
						str = str.toUpperCase();								// Force string to UpperCase

						firstLetter =  str.charAt(0);							// Set str.index0 to firstLetter
						sort = Character.toString(firstLetter);					// Set string sort to firstLetter

					if ( sort.matches("[A-Z]") )								// If string sort = a valid letter, then
					{
						System.out.println("Hello " +str+ ".");

						if ( sort.matches("[A-G]") )							// Check input for appointment location
						{
							System.out.println("Go to room A at 8:00 AM");
							check = false;
						}

						else if ( sort.matches("[H-M]") )
						{
							System.out.println("Go to room B at 8:00 AM");
							check = false;
						}
						else if ( sort.matches("[N-T]") )
						{
							System.out.println("Go to room A at 12:00 PM");
							check = false;
						}
						else	// if U-Z
						{
							System.out.println("Go to room B at 12:00 PM");
							check = false;
						}
					}
					else														// If input invalid, request new input
					{
						System.out.println("Invalid User Name Entered, Please Try Again.");
						check = true;
					}
  				}
 		}

}