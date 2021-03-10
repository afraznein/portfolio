/* Name: Anthony Frazier
 * Class: CSCE355
 * Due Date: November 21, 2017
 * Assignment: Programming Assignment: 
 *
 * Simulating a DFA. First read the description of the DFA. The description is in a file named by
 * the first command line argument (see below for information about the format of this file).
 * Next read a series of zero or more strings from another text file named by the second command
 * line argument. These are inputs to the DFA. Each string will take up an entire line of text,
 * ending with a newline character (which is not included in the string). For each string, write
 * to standard output either "accept" or "reject" according to the behavior of the DFA on the
 * string. End each output word with a newline.
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.lang.*;

public final class MySimulator{
	
public static void main(String[] args) throws IOException
{	//Set up error output. (This code is directly copied from a stack overflow example)
	FileOutputStream f = new FileOutputStream("error_log.txt");	 
	System.setErr(new PrintStream(f));
	
	DFA newDFA = null;
	newDFA = Simulate(args);
	
} // end main

public static DFA Simulate(String[] args) throws IOException
{	/* Simulating a DFA:
	 * First read the description of the DFA. The description is in a file named by
		the first command line argument.
 	*/

	File inputFile1 = null;
	File inputFile2 = null;
	
	DFA newDFA = new DFA();
	// Reads the file from the first argument line only if there is an argument
	if (args.length > 0) inputFile1 = new File(args[0]); 
	
	// Reads the file from the second argument line only if there is an argument
	if (args.length > 1) inputFile2 = new File(args[1]); 
	
	newDFA = DFA.ScanDescription(inputFile1);
	Parse(inputFile2, newDFA);
	return newDFA;
} // end Simulate();

public static void Parse(File inputFile, DFA dfa) throws IOException
{	/* Simulating a DFA:
	 * Read a series of zero or more strings from another text file named by the second command
		line argument. These are inputs to the DFA. Each string will take up an entire line of text,
		ending with a newline character (which is not included in the string). For each string, write
		to standard output either "accept" or "reject" according to the behavior of the DFA on the
		string. End each output word with a newline.
 	*/

	// This scanner scans the second file (the one with strings)
	Scanner input= new Scanner(inputFile);
	boolean accepts = false;
	
	while(input.useDelimiter("\n").hasNext())
	{
		//Read new line
		String str = input.nextLine();
		
		//These two if statements cover our empty string edge case
		if (str.equals("") && dfa.getAccepting()[0] != 0)
		{
			System.out.println("reject");
			str = input.nextLine();
		}
		if (str.equals("") && dfa.getAccepting()[0] == 0)
		{
			System.out.println("accept");
			str = input.nextLine();
		}
		
		//This Traverse call and if else structure covers all
		//  other strings other than empty
		accepts = Traverse(str, dfa);
		
		if (accepts)
		{
			System.out.println("accept");
		}
		else
		{
			System.out.println("reject");
		}
	} // end while
} // end Parse();

public static boolean Traverse(String line, DFA dfa)
{	//Start at starting state, then follow string
	//Scanner input = new Scanner(line);
	int state = 0;
	int index = 0;
	boolean accepted = false;
	
		
	// We have String line which contains the token we scanned from input.
	//  We will iterate over line with charAt.
	for (int x = 0; x < line.length(); x++)
	{
		char alpha = line.charAt(x);
		System.err.println("We just read the token: "+line+" And we read the char: "+alpha+"\n");
	
		// Once we have the char, parse over the language of the DFA, from the []Alphabet
		//  to find the index of the specific letter we're looking for
		for (int i = 0; i < dfa.getAlphabet().length; i++)
		{
			char step = dfa.getAlphabet()[i];
			if (alpha == step)
			{
				index = i;
			}
			
		}			
		// Assign our state to the new state located at transition[state][index]
		state = dfa.getTransition()[state][index];
	} // end for
		
	// Finally, if the state we are now in is contained in the
	//   accepting list, then set accepted=true;
	for (int i = 0; i < dfa.getAccepting().length-1; i++)
	{
		if (state == dfa.getAccepting()[i])
		{
			accepted = true;
		}
	}
	// Else return default accepted = false
	return accepted;
} // end Traverse()
} // end MySimulate()