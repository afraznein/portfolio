/*
Name: Anthony Frazier
Date: June 2, 2015
Program Name: Homework 5
Description: Write a program to prompt for and input the answers to a series of questions to do the same thing as
the "program" at http://web2.uvcs.uvic.ca/courses/elc/studyzone/490/grammar/arttree1.htm,
except to have the user input answers rather than clicking on links.
For example, the first question could be asked as "Is the noun definite or indefinite?
Enter 1 for definite or 2 for indefinite: ".
The entire decision tree is as attached.

Comment your code with your name, date, description and good identifiers,
place your program into a zip archive and upload that to the dropbox.
*/

import java.util.Scanner;
import java.lang.Character;
public class Homework5
{
		public static void main(String[] args)
  		{

			Scanner input = new Scanner(System.in);
			String prompt = "";								// Single variable to take in all inputs.

			System.out.println("This program is designed to help you decide which article to use with a noun.");

			System.out.println("Is the noun definite or indefinite? \n Enter 'D' for definite or 'I' for indefinite.");
				prompt = input.nextLine();
				prompt = prompt.toUpperCase();
				if ( prompt.matches("D"))
				{
					System.out.println("Use the article 'the' for your noun.");
				}
				else //if ( prompt.matches("I"))
				{
					System.out.println("Is the noun countable or is it non countable? \n Enter 'C' for countable or 'N' for non-countable.");
					prompt = input.nextLine();
					prompt = prompt.toUpperCase();
					if ( prompt.matches("N"))
					{
						System.out.println("Do not use an article for your noun.");
					}
					else //if ( prompt.matches("C"))
					{
						System.out.println("Is the noun plural or singular? \n Enter 'P' for plural or 'S' for singular.");
						prompt = input.nextLine();
						prompt = prompt.toUpperCase();
						if ( prompt.matches("P"))
						{
							System.out.println("Do not use an article for your noun.");
						}
						else //if ( prompt.matches("S"))
						{
							System.out.println("Does the noun begin with a vowel or a consonant? \n Enter 'V' for vowel or 'C' for consonant.");
							prompt = input.nextLine();
							prompt = prompt.toUpperCase();
							if ( prompt.matches("C"))
							{
								System.out.println("Use the article 'a' for your noun.");
							}
							else //if ( prompt.matches("V"))
							{
								System.out.println("Does the noun begin with a 'U'? \n Enter 'Y' for yes it begins with a U or N for no it does not begin with a U.");
								prompt = input.nextLine();
								prompt = prompt.toUpperCase();
								if ( prompt.matches("N"))
								{
									System.out.println("Use the article 'an' for your noun.");
								}
								else //if ( prompt.matches("Y"))
								{
									System.out.println("Does the noun begin with a 'Y' sound? \n Enter 'Y' for yes it begins with a Y sound or 'N' for no it does not begin with a Y sound.");
									prompt = input.nextLine();
								    prompt = prompt.toUpperCase();
								    if ( prompt.matches("Y"))
								    {
										System.out.println("Use the article 'a' for your noun.");
									}
									else //if ( prompt.matches("N"))
									{
										System.out.println("Use the article 'an' for your noun.");
									}
								}
							}
						}
					}
				}

 		}

}