/*
Name: Anthony Frazier
Date: January 29, 2016
Program Name: Homework2
Description: Homework2
Objective:	
Write a program which simulates a rock, paper, scissors double round robin tournament using a circular linked list. 
Each player in the tournament is controlled by the computer, and they randomly choose rock, paper, or scissors each round. 
The computer players have a win, lose, and tie record, which is displayed at the end. 
Notes:
Rock wins over scissors, scissors wins over paper, and paper wins over rock.
A circular linked list is just like a single linked list (which was demonstrated in class) except the last element links to the first element.
A double round robin tournament is when each contestant competes against all other contestants in one round. 
In the double tournament each contestant competes against the other twice.
For instance it let’s assume there are 5 contestants named 0-4. 
The first round would be 0 vs 1, 0 vs 2, 0 vs 3, 0 vs 4.  Then round two would be 1 vs 2, 1 vs 3, 1 vs 4, 1 vs 0.
This continues until everyone competes against everyone else twice.

Suggested Methodology
You can solve this in any number of ways, and here’s a way you may take to approach this problem.
Make a generic circular linked list
Make a class player which has a name, number of wins, number of losses, and number of ties
Use the inherit properties of a circular linked list to schedule the tournament
*/


import java.util.*;
import java.io.*;
public class Homework2 {

	public static void main(String[] args) 
	{
		GenericCircularLinkedList <Player> list = new GenericCircularLinkedList();
		Scanner input = new Scanner(System.in);
		
		System.out.println("Welcome to Rock, Paper, Scissors || Double Round Robin Tournament");
		System.out.println("How many players do you want to see play?");
		int inp = input.nextInt();
		int count = 0;
		while (count < inp)
		{
			Player newPlayer = new Player();
			newPlayer.setNum(count);
			list.create(newPlayer);	
			count++;
		}

		count = 0;
		int numPlayers = list.getNumPlayers();
		
		Player Player1 = new Player();
		Player Player2 = new Player();
		
		while ( count < numPlayers)
		{
			list.currentReset();		// Reset the current pointer to the head
			
			if ( count > 0)				// If we have already cycled through once, shift current to new location
			{
				int sent = 0;
				while ( sent < count)
				{
					list.currentShift();
					sent++;
				}
				
			}
			Player1 = list.getCurrent();
				
			int wins1 = Player1.getWins();
			int losses1 = Player1.getLosses();
			int ties1 = Player1.getTies();
				
			int x = 0;
			while (x < numPlayers-1)			// Pit Player 1 against all other players
			{
				list.currentShift();
				Player2 = list.getCurrent();
				
				int wins2 = Player2.getWins();
				int losses2 = Player2.getLosses();
				int ties2 = Player2.getTies();
						
				play(Player1,Player2, wins1, losses1, ties1, wins2, losses2, ties2);
				x++;
			}
			count++;		// Increase count, go back to top loop to change player 1
		}
		
		count = 0;
		list.currentReset();
		int winningPlayer = Player1.getPlayerNum();
		int winCheck = Player1.getWins();
		System.out.println("Here are the results of the tournament");
		while ( count < numPlayers)
		{
			Player1 = list.getCurrent();
			if (Player1.getWins() > winCheck)		// trying to do a player win condition check, this current has faults if players have the same number of wins
			{
				winningPlayer = Player1.getPlayerNum();
			}
			System.out.println( "Player "+Player1.getPlayerNum()+"| Wins: "+Player1.getWins()+" Losses: "+Player1.getLosses()+" Ties: "+Player1.getTies() );
			list.currentShift();
			count++;
		}
		System.out.println("Player "+winningPlayer+" wins!");
	}
	
	public static void play(Player Player1, Player Player2, int wins1, int losses1, int ties1, int wins2, int losses2, int ties2)		// This code is not optimal at all
	{
		double rand1 = Math.random() * 3.0;		// Generates 0, 1 or 2
		int value1 = (int)rand1;
		double rand2 = Math.random() * 3.0;
		int value2 = (int)rand2;
		
		String Rock = "Rock";
		String Paper = "Paper";
		String Scissors = "Scissors";
		
		String PlayerOne = "";
		String PlayerTwo = "";
		
		if (value1 == 0)				// 0 = rock, 1 = paper, 2 = scissors
		{
			PlayerOne = Rock;
		}
		else if (value1 == 1)
		{
			PlayerOne = Paper;
		}
		else if (value1 == 2)
		{
			PlayerOne = Scissors;
		}
		if (value2 == 0)
		{
			PlayerTwo = Rock;
		}
		else if (value2 == 1)
		{
			PlayerTwo = Paper;
		}
		else if (value2 == 2)
		{
			PlayerTwo = Scissors;
		}
		
									// 0 = rock, 1 = paper, 2 = scissors
		if (value1 == value2)		// Both played same. Tie.
		{
			ties1++;
			ties2++;
			Player1.setTies(ties1);
			Player2.setTies(ties2);
			System.out.println("Player "+Player1.getPlayerNum()+": uses "+PlayerOne+".");
			System.out.println("Player "+Player2.getPlayerNum()+": uses "+PlayerTwo+".");
			System.out.println("TIE!");
			System.out.println("");
		}
		else if (value1 == 0 && value2 == 1)  // Player 1 Rock, Player 2 Scissors
		{			
			wins1++;
			losses2++;
			Player1.setWins(wins1);
			Player2.setLosses(losses2);
			System.out.println("Player "+Player1.getPlayerNum()+": uses "+PlayerOne+".");
			System.out.println("Player "+Player2.getPlayerNum()+": uses "+PlayerTwo+".");
			System.out.println("Player "+Player1.getPlayerNum()+" WINS.");
			System.out.println("");
			
		}
		else if (value1 == 1 && value2 == 0) // Player 1 Paper, Player 2 Rock
		{
			wins1++;
			losses2++;
			Player1.setWins(wins1);
			Player2.setLosses(losses2);
			System.out.println("Player "+Player1.getPlayerNum()+": uses "+PlayerOne+".");
			System.out.println("Player "+Player2.getPlayerNum()+": uses "+PlayerTwo+".");
			System.out.println("Player "+Player1.getPlayerNum()+" WINS.");
			System.out.println("");
		}
		else if (value1 == 2 && value2 == 1) // Player 1 Scissors, Player 2 Paper
		{
			wins1++;
			losses2++;
			Player1.setWins(wins1);
			Player2.setLosses(losses2);
			System.out.println("Player "+Player1.getPlayerNum()+": uses "+PlayerOne+".");
			System.out.println("Player "+Player2.getPlayerNum()+": uses "+PlayerTwo+".");
			System.out.println("Player "+Player1.getPlayerNum()+" WINS.");
			System.out.println("");
		}
		else  // Player 2 Wins
		{
			losses1++;
			wins2++;
			Player1.setLosses(losses1);
			Player2.setWins(wins2);
			System.out.println("Player "+Player1.getPlayerNum()+": uses "+PlayerOne+".");
			System.out.println("Player "+Player2.getPlayerNum()+": uses "+PlayerTwo+".");
			System.out.println("Player "+Player2.getPlayerNum()+" WINS.");
			System.out.println("");
		}
		
		
	}	// end of play method
}
