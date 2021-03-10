/*
Name: Anthony Frazier
Date: January 22, 2016
Program Name: Homework1
Description: Homework1
Objective:
Write a program where a user plays a games of Tic Tac Toe against the computer, and tries to remains undefeated for as long as possible.
The program should display the board before every turn
The user enters coordinates to place their piece
The game should keep prompting for coordinates if the space is already occupied or it is a non-negative, invalid input
If the user enters a negative number they quit and the computer wins
The computer then randomly selects an empty space and places their piece
If there are no available spaces (“CAT”) then the game is over but the player does not lose
If the player wins the computer demands a rematch.  This will happen until the player loses or quits.
Once the user loses they are asked to enter their name so it can be recorded to a high score file.  Then the file’s contents are displayed in game.
The user can then choose to enter the tournament again or quit.
*/

import java.util.*;
import java.lang.*;
public class Homework1
{
	public static void main(String[] args)
  	{
		Scanner input = new Scanner(System.in);																				// Declare new scanner object, ask user for input
		int wins = 0;
		char [][] tictac = new char[3][3];																					// Declare new tic-tac-toe array of size
		for (int i=0; i<3; i++)																								// Initialize Tic Tac Toe Grid, Probably need to make this a method
		{
			for (int j=0; j<3; j++)
			{
				tictac[i][j] = '?';
			}

		}
		int x = 0, y = 0;																									// Intialize X and Y values
		while ( x >= 0 )
		{
			System.out.println("Welcome to Tic Tac Toe Tourney! How many games can you win in a row?");						// Print Method probably needed
			System.out.println(" |    0     |    1     |    2     |				  ");
		    System.out.println("__________________________________________________");
			System.out.println("0|    "+tictac[0][0]+"     |    "+tictac[1][0]+"     |    "+tictac[2][0]+"     |    ");
			System.out.println("__________________________________________________");
			System.out.println("1|    "+tictac[0][1]+"     |    "+tictac[1][1]+"     |    "+tictac[2][1]+"     |    ");
			System.out.println("__________________________________________________");
			System.out.println("1|    "+tictac[0][2]+"     |    "+tictac[1][2]+"     |    "+tictac[2][2]+"     |    ");
			System.out.println("__________________________________________________");
			System.out.println("Enter the X coordinate to place your 'X'. Enter a negative value to quit.");
			x = input.nextInt();
			System.out.println("Enter the Y coordinate to place your 'X'. Enter a negative value to quit.");
			y = input.nextInt();
			tictac[x][y] = 'X';
			for (int i=0; i<3; i++)																								// Initialize Tic Tac Toe Grid, Probably need to make this a method
			{
				for (int j=0; j<3; j++)
				{
					if ( tictac[i][j] == 'X' && tictac[i][j+1] == 'X' && tictac[i][j+2] == 'X' )
					{
						System.out.println("");
						wins++;
					}

				}

			}
			if ( tictac[0][0] == 'X' && tictac[1][0] == 'X' && tictac[2][0] == 'X' )
			{
			}


		}
	}																								// End main method
}																									// End class