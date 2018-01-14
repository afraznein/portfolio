/* Name: Anthony Frazier
 * Class: CSCE355
 * Due Date: November 21, 2017
 * Assignment: Programming Assignment.
 * Closure Properties: complement and intersection:
 * There will be either one or two command
 * line arguments. If one, then it is the name of a file containing the description of a DFA A,
 * and you are to output the complementary DFA ¬A (see Definition 7.4 of the course notes).
 * If two arguments, then they both are names of text files containing DFAs A and B, and you
 * are to output the product construction A ∧ B (see Definition 7.4 of the course notes). A and
 * B will have the same alphabet. In each case, write to standard output.
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.lang.*;

public class MyClosure {
	
	public static void main(String[] args) throws IOException
	{	//Set up error output. (This code is directly copied from a stack overflow example)
		FileOutputStream f = new FileOutputStream("error_log.txt");	 
		System.setErr(new PrintStream(f));
		
		DFA newDFA = null;
		DFA a_DFA = null;
		DFA b_DFA = null;
		File inputFile1 = null;
		File inputFile2 = null;
		
		
		// Reads the file from the first argument line only if there is an argument
		if (args.length > 0) inputFile1 = new File(args[0]); 
		
		// Reads the file from the second argument line only if there is an argument
		if (args.length > 1) inputFile2 = new File(args[1]); 
		
		if (inputFile2 == null) // Second input line doesn't exist, output complement
		{
			newDFA = DFA.ScanDescription(inputFile1);
			newDFA = makeComp(newDFA);
			printDFA(newDFA);
		}
		else
		{
			a_DFA = DFA.ScanDescription(inputFile1);
			b_DFA = DFA.ScanDescription(inputFile2);
			newDFA = makeProduct(a_DFA, b_DFA);
			printDFA(newDFA);
		}
	} // end main
	
	public static DFA makeComp(DFA dfa) throws IOException
	{
		int[] accept = new int[dfa.getStates()];
		boolean isAlreadyAccepting = false;
		
		System.err.println("The starting accepting list is: ");
		for (int i = 0; i < dfa.getStates(); i++)
		{
			accept[i] = -1;
			System.err.println(dfa.getAccepting()[i]);
		}
		int stateSave = 0;
		for (int i = 0; i < dfa.getStates(); i++)
		{
			isAlreadyAccepting = false;
			for(int j = 0; j < dfa.getStates(); j++)
			{
				if (i == dfa.getAccepting()[j])
				{
					isAlreadyAccepting = true;
					System.err.println("State "+j+" was found to be in the accepting list already. Not adding.");
				}
			}
			if (!isAlreadyAccepting)
			{
				accept[stateSave] = i;
				stateSave++;
			}
		}
		dfa.setAccepting(accept);	
		return dfa;
	}
	public static DFA makeProduct(DFA a_DFA, DFA b_DFA)
	{
		DFA newDFA = new DFA();
		int newStates = a_DFA.getStates() * b_DFA.getStates();
		System.err.println("\nOur new DFA should have "+newStates+" states.");
		int[][] trans = new int[newStates][a_DFA.getAlphaString().length()];
		int[] newAcceptingArr = new int[newStates];
		String[] concatStates = new String[newStates];
		
		for (int i = 0; i < newStates; i++)
		{
			newAcceptingArr[i] = -1;
		}
		
		// Build the new set states and new accepting states
		int nextIndex = 0;
		int tracker = 0;
		boolean a_accept = false;
		boolean b_accept = false;
		for(int i = 0; i < a_DFA.getStates(); i++)
		{
			for(int j = 0; j < b_DFA.getStates(); j++)
			{
				concatStates[tracker] = ""+i+"-"+""+j;
				System.err.println(concatStates[nextIndex]);
				a_accept = false;
				for (int k = 0; k < a_DFA.getAccepting().length; k++)
				{
					if (a_DFA.getAccepting()[k] == i)
					{
						a_accept = true;
					}
				}
				b_accept = false;
				for (int m = 0; m < b_DFA.getAccepting().length; m++)
				{
					if (b_DFA.getAccepting()[m] == j)
					{
						b_accept = true;
					}
				}
				if(a_accept && b_accept)
				{
					// add our new accepting product state into the array.
					newAcceptingArr[nextIndex] = tracker;
					nextIndex++;
				}
				tracker++;
			}
		}
		
		// Build the new trans table
		nextIndex = 0;
		for(int i = 0; i < a_DFA.getStates(); i++)
		{
			for(int j = 0; j < b_DFA.getStates(); j++)
			{
				for(int k = 0; k < a_DFA.getAlphaString().length(); k++)
				{
					for(int m = 0; m < newStates; m++)
					{
						String a = ""+a_DFA.getTransition()[i][k];
						String b = ""+b_DFA.getTransition()[j][k];
						String strang = a+"-"+b;
						if(concatStates[m].equals(strang))
						{
							trans[nextIndex][k] = m;
						}
					}
				}
				nextIndex++;
			}
		}
		
		// Build the new alpha string
		String alphaStr = "";
		for (int i = 0; i < a_DFA.getAlphabet().length; i++)
		{
			if (a_DFA.getAlphabet()[i] != -1)
			{
				alphaStr = alphaStr+""+a_DFA.getAlphabet()[i];
			}
		}
		
		newDFA.setTransition(trans);
		newDFA.setAccepting(newAcceptingArr);
		newDFA.setAlphabet(a_DFA.getAlphabet());
		newDFA.setStates(newStates);
		newDFA.setAlphaString(alphaStr);

/*(		
		// Parse over the transitions to build a set list named newStatesList.
		int temp = 0;
		// Set code is from a stack overflow example. Unable to get this working with just an array, going to use a 
		//  tree set to add all the values too, thus we have a sorted list of all of our new states.
		//  create a set of all the states we're collecting.
		Set<Integer> newStatesSet = new HashSet<Integer>();
		for(int j = 0; j < a_DFA.getStates(); j++)
		{
			for(int k = 0; k < b_DFA.getStates(); k++)
			{
				for(int a = 0; a < a_DFA.getAlphaString().length(); a++)
				{
					// We will have states 0,0 -> dfa_A.getStates() - 1, dfa_B.getStates() -1
					int smallerState = 0;
					if (a_DFA.getStates() < b_DFA.getStates())
					{
						smallerState = a_DFA.getStates();
					}
					else
					{
						smallerState = b_DFA.getStates();
					}
					temp = Integer.parseInt(""+a_DFA.getTransition()[j][a]+""+b_DFA.getTransition()[k][a]);
					newStatesSet.add(temp);
					System.err.println("The new state we just added to the set is : "+temp);
				}
			}
		}
	
		
		
		List newStatesList = new ArrayList<Integer>();
		newStatesList.addAll(newStatesSet);
		Collections.sort(newStatesList);
		
		// Convert the treeset to a string for error checking purposes
		//int[] finalStates = new int[newStates];
		//for (int i = 0; i < newStates; i++)
		//{
		//	finalStates[i] = -1;
		//}
		String str = newStatesList.toString();
		System.err.println(str);
		System.err.println(str);
		//for (int i = 0; i < newStates; i++)
		//{
		//	if (newStatesList.size >= newStates)
		//	{
		//		finalStates[i] = Integer.parseInt(""+newStatesList.get(i));
		//		System.err.print("FinalStates[i]"+i+" ");
		//		System.err.print(newStatesList.get(i)+ "\n");	
		//	}	
		//}
		//System.err.println();
		
		
		// Initialize an array named finalStates thats holds our final states in a sorted order, from 0 -> newStates -1
		//int[] finalStates = new int[newStates];
	//	for (int i = 0; i < newStates; i++)
	//	{
	//		finalStates[i] = newStatesList.headSet(i).size();
	//		System.err.print(newStatesList.headSet(i).size()+" ");
	//	}
	//	System.err.println();
		
		// Process our final transition tree.
		int temp2 = 0;
		for (int x = 0; x < newStates; x++)
		{
			for (int j = 0; j < a_DFA.getStates(); j++)
			{
				for (int k = 0; k < b_DFA.getStates(); k++)
				{
					for (int m = 0; m < a_DFA.getAlphaString().length(); m++)
					{
						for (int i = 0; i < newStatesList.size(); i++)
						{
							//if (newStatesList.get(i) != -1)
							//if (finalStates[i] != -1)
							//{
								temp2 = Integer.parseInt(""+a_DFA.getTransition()[j][m]+""+b_DFA.getTransition()[k][m]);
								
								if (Integer.parseInt(""+newStatesList.get(i)) == temp2)
								{
									System.err.println("State "+temp2+" has become state "+i);
									trans[x][m] = i;
									System.err.println("trans["+x+"]["+m+"] is now "+trans[x][m]);
									i = newStatesList.size();
								}
							//}
						}
					}
				}
			}
		}
		
		// Process our final accepting states
		int temp3 = 0;
		int nextAccept = 0;
		for (int i = 0; i < a_DFA.getStates(); i++)
		{
			for (int j = 0; j < b_DFA.getStates(); j++)
			{
				for (int k = 0; k < newStatesList.size(); k++)
				{
					if (a_DFA.getAccepting()[i] != -1 && b_DFA.getAccepting()[j] != -1)
					{
						System.err.println("DFA A @ "+i+" DFAB @ "+j+" is:");
						System.err.println(a_DFA.getAccepting()[i]+" "+b_DFA.getAccepting()[j]);
						temp3 = Integer.parseInt(""+a_DFA.getAccepting()[i]+""+b_DFA.getAccepting()[j]);
						System.err.println("The accepting product is "+temp3);
						if (temp3 == Integer.parseInt(""+newStatesList.get(k)))
						{
							accept[nextAccept] = k;
							System.err.println();
							System.err.println("The new accepting is "+k);
							System.err.println();
							nextAccept++;
						}
					}	
				}
			}
		}
		

		// Parse back over the transitions, and build trans and accepting lists.
/*
		int tempState = 0;
		int nextAccept = 0;
		for (int j = 0; j < a_DFA.getStates(); j++)
		{
			for (int k = 0; k < b_DFA.getStates(); k++)
			{
				for (int m = 0; m < a_DFA.getAlphaString().length(); m++)
				{
					tempState = Integer.parseInt(""+a_DFA.getTransition()[j][m]+""+b_DFA.getTransition()[k][m]);
					System.err.println("The old state is "+tempState);
					trans[nextAccept][m] = newStatesList.headSet(tempState).size();
					System.err.println("The new state is "+m+"\n\n");
	
				}
				// Build the new accepting list
				int temp3 = 0;
				int temp4 = 0;
				if (a_DFA.getAccepting()[j] != -1 && b_DFA.getAccepting()[k] != -1)
				{
					String a = Integer.toString(a_DFA.getAccepting()[j]);
					String b = Integer.toString(b_DFA.getAccepting()[k]);
					temp3 = Integer.parseInt(a+b);
					System.err.print("The old ACCEPTING state is "+temp3+" ");
					temp4 = newStatesList.headSet(temp3).size();
					accept[nextAccept] = temp4;
					System.err.print("The NEW ACCEPTING state is "+temp4+"\n\n");
					nextAccept++;
				}
			nextAccept++;
			} // end k for loop
		}
*/
		return newDFA;
	}
	public static void printDFA(DFA dfa) 
	{
		
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
		
		System.out.println("Number of states: "+dfa.getStates());
		System.out.println("Accepting states: "+accept);
		System.out.println("Alphabet: "+dfa.getAlphaString());
		
		for (int i = 0; i < dfa.getStates(); i++)
		{
			String transLine = "";
			for (int j = 0; j < dfa.getAlphaString().length(); j++)
			{
				transLine = transLine + " " +dfa.getTransition()[i][j];
			}
			//transLine = transLine.substring(1); 
			System.err.println(transLine);
			System.out.println(transLine);
		}
	}
}// end class