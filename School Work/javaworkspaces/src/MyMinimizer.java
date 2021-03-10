import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/* Name: Anthony Frazier
 * Class: CSCE355
 * Due Date: November 21, 2017
 * Assignment: Programming Assignment.
 * 
 * Minimizing a DFA:
 * The input DFA is read from a text file given as the first command line
 * argument, and the output DFA is written to standard output. You should use the table-of-distinguishable-states
 * method described in class to do the minimization. You do not need
 * to output the distinguishability table, but you may optionally do so to standard error if you
 * want. Both the input and output DFA descriptions adhere to the format described below.
 * The ordering of the states of the output DFA is not completely determined, so correct answers
 * may differ up to the ordering of states. That's okay, we'll take that into account using our
 * own script that determines whether two DFAs are the same up to reordering states.
*/
public class MyMinimizer {
	
	static final int THIRD_TIME_IS_A_CHARM = 10;

	
public static void main(String[] args) throws IOException
{	
	File inputFile1 = null;	
	DFA newDFA = new DFA();
	DFA finalDFA = new DFA();
	
	inputFile1 = new File(args[0]);
	
	newDFA = DFA.ScanDescription(inputFile1);
	int[] finalStates = Minimize(newDFA);
	
	printDFA(finalStates, newDFA);
	
} // end main

public static int[] Minimize(DFA dfa)
{	/* Two states are considered equivalent when:
	* If state1 reads string w and ends in an accepting state, and state2 reads same string w and ends in an accepting state
	* OR If state1 reads string w and ends in an non-accepting state, and state2 reads same string w and ends in an non-accepting state
	* 
	* Step One: Remove states that are unreachable from start state
	* Step Two: Set up two lists: one that represents equivalent states and one that represents distinguishable states
	*/
	
	// Create our reachable array
	boolean[] reachable = Reachable(dfa);
	
	// Initialize our arrays
	int[]one = new int[dfa.getStates()];
	int[]two = new int[dfa.getStates()];
	int[][] trans = dfa.getTransition();
	
	for (int i = 0; i < dfa.getStates(); i++)
	{
		one[i] = -1;
		two[i] = -1;
	}
	
	// After building our reachable array, for states that are not reachable, set all transitions = -1
	for (int i = 0; i < dfa.getStates(); i++)
	{
		if (reachable[i] == false)
		{
			System.err.println(i+" has been found to be unreachable. Setting all transitions to -1");
		}
		for (int j = 0; j < dfa.getAlphabet().length; j++)
		{
			if (reachable[i] == false)
			{
				trans[i][j] = -1;
				//unreachable[i] = i;
			}	
		}
	}
	
	System.err.println();
	// TODO: CLEAN IT
	// Populate one[] with every state that is reachable, remove states that are accepting. Add these removed states to two[]
	for(int i = 0; i < dfa.getStates(); i++)
	{
		if (reachable[i] == true)
		{
			one[i] = i;
			System.err.println(i+" has been added to reachable array one[]");
		
		}
		for (int j = 0; j < dfa.getStates(); j++)
		{
			if(dfa.getAccepting()[j] == i)
			{
				two[i] = one[i];
				one[i] = -1;
				System.err.println(two[i]+" has been removed from []one and added to []two");
			}
		}
	}
	
	System.err.println();
	// Now that we have our arrays of accepting and not accepting states that are reachable, we must test for equivalence.
	// If equivalent states are found, remove one of them.
	
	// TODO: I think I need to parse over everything again to recheck for equivalence
	
	for (int i = 0; i < dfa.getStates(); i++) // may need -1 here on getStates()
	{
		for (int j = 0; j < dfa.getStates(); j++)
		{
			if (one[i] != -1 && one[j] != -1)
			{
				boolean equiv = true;
				for (int k = 0; k < dfa.getAlphaString().length(); k++)
				{
					if (trans[i][k] != trans[j][k])
					{
						equiv = false;
					}
				}
				
				if (equiv && i != j) // Don't need to remove states that are the same (i.e. 0 and 0 will be equiv)
				{
					int x = Math.max(i,j);
					System.err.println("States "+i+" and "+j+" have been found to be equivalent in one[]");
					System.err.println("Removed duplicate state"+one[x]);
					one[x] = -1;
					
					for (int k = 0; k < dfa.getStates(); k++)
					{
						for (int m = 0; m < dfa.getAlphaString().length(); m++)
						if (trans[k][m] == x)
						{
							int y = Math.min(i,j);
							trans[k][m] = one[y];
							System.err.println("Since we removed state "+x+" we have adjusted transitions from all states to point to the smaller state");
						}
					}
					
				}
			}
			
			if (two[i] != -1 && two[j] != -1)
			{
				boolean equiv2 = true;
				for (int k = 0; k < dfa.getAlphaString().length(); k++)
				{
					if (trans[i][k] != trans[j][k])
					{
						equiv2 = false;
					}
				}
				if (equiv2 && i != j) // Don't need to remove states that are the same (i.e. 0 and 0 will be equiv)
				{
					System.err.println("States "+i+" and "+j+" have been found to be equivalent in two[]");
					System.err.println("Removed duplicate state"+one[j]);
					two[j] = -1;
					
					for (int k = 0; k < dfa.getStates(); k++)
					{
						for (int m = 0; m < dfa.getAlphaString().length(); m++)
						if (trans[k][m] == j)
						{
							trans[k][m] = two[i];
							System.err.println("Since we removed accepting state "+j+" we have adjusted transitions from all states to point to "+two[i]);
						}
					}
					
				}
			}
				
		}		
	}

	// Count the new set of final states
	int count = 0;
	for (int i = 0; i < dfa.getStates(); i++)
	{
		if(one[i] != -1)
		{
			count++;
		}
		if(two[i] != -1)
		{
			count++;
		}
	}

	System.err.println("Number of states is now:"+count);
	int[] newStates = new int[one.length + two.length];
	for (int i = 0; i < one.length; i++)
	{
		newStates[i] = one[i];
		System.err.println("newStates["+i+"] is now "+one[i]);
	}
	int x = 0;
	for (int i = two.length; i < newStates.length; i++)
	{
		newStates[i] = two[x];
		System.err.println("newStates["+i+"] is now "+two[x]);
		x++;
	}
	
	int[] finalStates = new int[count];
	int y = 0; // use this to keep track of where we are with j
	for (int i = 0; i < finalStates.length; i++)
	{
		for (int j = y; j < newStates.length; j++)
		{
			if (newStates[j] != -1)
			{
				finalStates[i] = newStates[j];
				System.err.println("finalStates["+i+"] is now "+newStates[j]);
				y = j+1;
				j = newStates.length;
			}
		}
	}

	dfa.setAccepting(two);
	dfa.setTransition(trans);

	return finalStates;
}


public static boolean[] Reachable(DFA dfa) {
	
	// Get the total number of states and create a local transition table from our DFA
	int states = dfa.getStates();
	int[][] trans = dfa.getTransition();

	// Initialize a bool array, and set all except start state (0) equal to false
	boolean[] reachable = new boolean[states];
	reachable[0] = true;
	

	// I am going to run this ~3 times (magic number right now). My concern is the following:
	//   What happens if I say a state, 2 for example, is not reachable. Do I don't bother
	//   parsing its transition table. Now, in state 3, I discover that 2 is reachable. How
	//   do I go back and re add those? My solution is to wrap my structure in a while < 5
	//   because any transition I avoided in the first pass will now have reachable[]=true, thus
	//   we can add the new transitions. 
	
	// TODO, maybe use a boolean here
	int count = 0;
	while (count < THIRD_TIME_IS_A_CHARM)
	{	// Parse over the states. If a state is listed in the transition table,
		//  and the state we are currently in has a reached status of true,
		//  set the new found state as reached = true
		for (int i = 0; i < states; i++)
		{
			for (int j = 0; j < dfa.getAlphabet().length; j++)
			{
				if (reachable[i] == true)
				{
					int index = trans[i][j];
					reachable[index] = true;
					System.err.println("Transition: "+index+" is NOW reachable.");
				}
			}
		}
		System.err.println("\n\n");
		count++;
	}
	
	return reachable;
}


public static void printDFA(int[] finalStates, DFA dfa) {
	
	String accept = "";
	for (int i = 0; i < dfa.getAccepting().length; i++)
	{
		if (dfa.getAccepting()[i] != -1)
		accept = accept + " " + dfa.getAccepting()[i];
	}
	if (accept.length() > 1)
	{
		accept = accept.substring(1);
	}
	
	// Catch the edge case where nothing is accepting
	//  If nothing is accepting, then none of this matters at all! I think...
	if (accept.equals("") || accept.equals(" "))
	{
		dfa.setStates(1);
		int[][] trans = new int[1][dfa.getAlphaString().length()];
		for (int i = 0; i < dfa.getAlphaString().length(); i++)
		{
			trans[0][i] = 0;
		}
		dfa.setTransition(trans);
		
		System.out.println("Number of states: 1");
		System.out.println("Accepting states: ");
		System.out.println("Alphabet: "+dfa.getAlphaString());
		for (int i = 0; i < finalStates.length; i++)
		{
			String transLine = "";
			for (int j = 0; j < dfa.getAlphaString().length(); j++)
			{
				transLine = transLine + " " +dfa.getTransition()[finalStates[i]][j];
			}
			transLine = transLine.substring(1); 
			System.out.println(transLine);
		}
		return;
	}
	
	System.out.println("Number of states: "+finalStates.length);
	System.out.println("Accepting states: "+accept);
	System.out.println("Alphabet: "+dfa.getAlphaString());
	
	// Finally, we need to rename our states somehow to match in our new total number of states scheme
	// I have all of my remaining final states in a finalStates[] that is only as long as my new total # of states
	// I need to rename all existing states to conform to a new naming scheme based on my new total # of states
/*	
	for (int i = 0; i < finalStates.length; i++)
	{
		String transLine = "";
		for (int j = 0; j < dfa.getAlphaString().length(); j++)
		{
			for (int x = 0; x < finalStates.length; x++)
			{
				if (finalStates[x] == dfa.getTransition()[finalStates[i]][j])
				{
					transLine = transLine + " " +finalStates[x];
					System.err.println("Changing transition "+dfa.getTransition()[finalStates[i]][j]+" to "+finalStates[x]);
				}
			}
		}
		transLine = transLine.substring(1); 
		System.err.println(transLine);
		System.out.println(transLine);
	}
*/
	
	for (int i = 0; i < finalStates.length; i++)
	{
		String transLine = "";
		for (int j = 0; j < dfa.getAlphaString().length(); j++)
		{
			for (int m = 0; m < finalStates.length; m++)
			{
				int x = dfa.getTransition()[finalStates[i]][j];
				System.err.println("The state I'm trying to change is "+dfa.getTransition()[finalStates[i]][j]);
				if (finalStates[m] == x)
				{
					transLine = transLine + " " +m;
					System.err.println("Changing transition "+dfa.getTransition()[finalStates[i]][j]+" to "+m);
				}
				
			}
			
		}
		transLine = transLine.substring(1); 
		System.err.println(transLine);
		System.out.println(transLine);
	}	
}

} // end class

