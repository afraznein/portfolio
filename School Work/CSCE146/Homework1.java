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
This was complicated.
*/

import java.util.*;
import java.io.*;
public class Homework1
{
	public static int wins = 0;																								// Had to declare this globaly, when I made win checks methods, they needed access to this
	public static void main(String[] args)
  	{
		Scanner input = new Scanner(System.in);
		char [][] tictac = new char[3][3];
		SetBoard(tictac);																									// Set a new board with empty values
		int x = 0, y = 0;																									// Intialize X and Y coordinate values
		while ( x >= 0 )
		{
			PrintBoard (wins, tictac);																						// Send method # of wins and array, then Print Board

			x = getX();																										// Obtain X coordinate
			y = getY();																										// Obtain Y coordinate

			if (tictac [x][y] != '?')																						// Valid Player Move Check
			{
				System.out.println("There is already a move placed there. Try again.");
				x = getX();
				y = getY();
					if (tictac [x][y] != '?')
					{
						System.out.println("Seriously, no cheating. You lose.");											// Try to cheat, end program.
						System.exit(0);
					}
			}

			tictac[x][y] = 'X';																								// Place move
			wins = CheckWinConditions(wins, tictac);																		// Check Win Conditions

			boolean check = false;
			while ( !check )																								// Computer's Turn
			{
				double rand1 = Math.random() * 3.0;
				int value1 = (int)rand1;
				double rand2 = Math.random() * 3.0;
				int value2 = (int)rand2;

				if ( tictac[value1][value2] != 'X' && tictac[value1][value2] != 'O')										// Only place 'O' if value is ?
				{
					tictac[value1][value2] = 'O';
					check = true;
				}
			}																												// End AI Loop
			wins = CheckWinConditions(wins, tictac);																		// CHECK WIN CONDITIONS
		}																													// End main while loop
	}																														// End main method

	public static int CheckWinConditions(int wins, char [][] tictac)
	{
		Scanner input = new Scanner(System.in);
		boolean win = WinCheck('X', tictac);																				// Player Win Check
		if ( win )
		{
			System.out.println("\n\n");
			System.out.println("\n\nYou have won!!!1\n\n");
			wins++;
			SetBoard(tictac);
			return wins;
		}

		boolean lose = WinCheck('O', tictac);																				// Computer Win Check
		if ( lose )
		{
			System.out.println("You have lost");
			if ( wins > 0 )																									// Call high score board if wins > 0
			{
				System.out.println("What is your name?");
				String name = input.next();
				HighScores(wins, name);
			}
			wins = 0;
			SetBoard(tictac);
			return wins;
		}
		boolean stuck = CatCondition(tictac);																				// Check Cat Condition
		if ( stuck )																										// Door Stuck www.youtube.com/watch?v=VqB1uoDTdKM
		{
			System.out.println("No valid moves. Rematch.");
			SetBoard(tictac);
			return wins;
		}
		return wins;
	}
	public static void HighScores(int w, String n)																			// Majority of this code is attempting to study the API, have never done file i/o
	{
		n = (n + " " + w + "\n");
		String str = "";
		try
		{
			PrintWriter streamOut = new PrintWriter( new File("high.txt") );												// Declare new stream to write to file
			FileInputStream readFile = new FileInputStream ("high.txt");													// New stream to read form file
			DataInputStream streamIn = new DataInputStream (readFile);														// New stream to parse from stream
			streamOut.write("TIC TAC TOE TOURNAMENT. \n HIGH SCORE LIST\n");
			streamOut.write(n);																								// Write string to file
			streamOut.close();
			try
			{
				while ( streamIn.available() != 0)
				{
					System.out.println(streamIn.readLine() );
				}
				streamIn.close();
			}
			catch (final IOException exception)
			{
        			throw new RuntimeException(exception);
			}
		}
		catch (FileNotFoundException exception)
		{
			throw new RuntimeException(exception);
		}
	}																														// End File I/O

	public static int getX()
	{
			Scanner input = new Scanner(System.in);
			System.out.println("Enter the X coordinate to place your 'X'. Enter a negative value to quit.");
			int x = input.nextInt();
			if ( x < 0 )
			{
				System.exit(0);
			}
			if ( x > 2 )
			{
				System.out.println("Out of bounds. One more try. Enter X coordinate.");
				x = input.nextInt();
			}
			return x;
	}
	public static int getY()
	{
			Scanner input = new Scanner(System.in);
			System.out.println("Enter the Y coordinate to place your 'X'.");
			int y = input.nextInt();
			if ( y > 2 )
			{
				System.out.println("Out of bounds.. One more try. Enter Y coordinate.");
				y = input.nextInt();
			}
			return y;
	}
	public static void PrintBoard (int wins, char [][] tictac)
	{
		System.out.println("Welcome to Tic Tac Toe Tourney!");																// Print Method probably needed | Done
		System.out.println("You have won: "+wins+" times. How many games can you win in a row?");
		System.out.println(" |    0     |    1     |    2     |				  ");
	    System.out.println("__________________________________________________");
		System.out.println("0|    "+tictac[0][0]+"     |    "+tictac[1][0]+"     |    "+tictac[2][0]+"     |    ");
		System.out.println("__________________________________________________");
		System.out.println("1|    "+tictac[0][1]+"     |    "+tictac[1][1]+"     |    "+tictac[2][1]+"     |    ");
		System.out.println("__________________________________________________");
		System.out.println("2|    "+tictac[0][2]+"     |    "+tictac[1][2]+"     |    "+tictac[2][2]+"     |    ");
		System.out.println("__________________________________________________");
	}
	public static void SetBoard (char [][] tictac)
	{
		for (int i=0; i<3; i++)
		{
			for (int j=0; j<3; j++)
			{
				tictac[i][j] = '?';
			}

		}
	}
	public static boolean CatCondition (char [][] tictac)
	{
		for (int x=0; x<3; x++)
		{
			for (int y=0; y<3; y++)
			{
				if ( tictac[x][y] == '?' )
				{
					return false;
				}
			}
		}
		return true;
	}

	public static boolean WinCheck ( char x, char [][] tictac)
	{
		if ( tictac[0][0] == x && tictac[1][0] == x && tictac[2][0] == x )												// Top Row
				{
					return true;
				}
				else if ( tictac[0][1] == x && tictac[1][1] == x && tictac[2][1] == x )									// Middle Row
				{
					return true;
				}
				else if ( tictac[0][2] == x && tictac[1][2] == x && tictac[2][2] == x )									// Bottom Row
				{
					return true;
				}

					if ( tictac[0][0] == x && tictac[0][1] == x && tictac[0][2] == x )									// Left Column
					{
						return true;
					}
					else if ( tictac[1][0] == x && tictac[1][1] == x && tictac[1][2] == x )								// Middle Column
					{
						return true;
					}
					else if ( tictac[2][0] == x && tictac[2][1] == x && tictac[2][2] == x )								// Right Column
					{
						return true;
					}
						if ( tictac[0][0] == x && tictac[1][1] == x && tictac[2][2] == x )								// Diag Topleft
						{
							return true;
						}
						else if ( tictac[0][2] == x && tictac[1][1] == x && tictac[2][0] == x )							// Diag BotLeft
						{
							return true;
						}
		return false;
	}
}																														// End class