import java.util.Arrays;

public class DFA {

	private int states;
	private int[] finalStates;
	private char[] alphabet;
	private int[][] transitions;
	
	//Base constructor
	public DFA() {
		this.states = 0;
		this.finalStates = null;
		this.alphabet = null;
		this.transitions = null;
	}

	//Standard Constructor
	public DFA(int states, int[] finalStates, char[] alphabet, int[][] transitions) {
		this.states = states;
		this.finalStates = finalStates;
		this.alphabet = alphabet;
		this.transitions = transitions;
	}

	//Make the Constructor directly from the File
	public DFA(String states, String finalStates, String alphabet, String[] transitions) {
		this.setStates(states);
		this.setFinalStates(finalStates);
		this.setAlphabet(alphabet);
		this.setTransitions(transitions);
	}
	
	
	//GETTERS
	public int getStates() {
		return states;
	}

	public int[] getFinalStates() {
		return finalStates;
	}

	public char[] getAlphabet() {
		return alphabet;
	}

	public int[][] getTransitions() {
		return transitions;
	}

	//STANDARD SETTERS
	public void setStates(int states) {
		this.states = states;
	}

	public void setFinalStates(int[] finalStates) {
		this.finalStates = finalStates;
	}

	public void setAlphabet(char[] alphabet) {
		this.alphabet = alphabet;
	}

	public void setTransitions(int[][] transitions) {
		this.transitions = transitions;
	}
	
	
	//SETTERS to set things directly from the file
	public void setStates(String s) {
		int index = s.indexOf(':');
		this.states = Integer.parseInt(s.substring(index + 2));
	}
	
	public void setFinalStates(String s) {
		int index = s.indexOf(":");
		String fs = s.substring(index + 2);
		String[] parts = fs.split(" ");
		this.finalStates = new int[parts.length];
		for(int i = 0; i < parts.length; i++)
		{
			this.finalStates[i] = Integer.parseInt(parts[i]);
		}
	}

	public void setAlphabet(String s) {
		int index = s.indexOf(':');
		String a = s.substring(index + 2);
		this.alphabet = new char[a.length()];
		for(int i = 0; i < this.alphabet.length; i++)
		{
			this.alphabet[i] = a.charAt(i);
		}
	}

	public void setTransitions(String[] lines) {
		String[] parts = null;
		this.transitions = new int[this.states][this.alphabet.length];
		for(int i = 0; i < this.states; i++)
		{
			parts = lines[(i+3)].split(" ");
			for(int j = 0; j < this.alphabet.length; j++)
			{
				this.transitions[i][j] = Integer.parseInt(parts[j]);
			}
		}
	}
	
	//Checks to see if an array contains an int
	public static boolean contains(final int[] array, final int key) {
		for (final int i : array) {
			if (i == key) {
				return true;
			}
		}
		return false;
	}
	
	//Prints out the DFA in the acceptable format
	public void printDFA()
	{
		//States
		System.out.println("Number of states: " + this.states);
		System.err.println("Number of states: " + this.states);
		//Final States
		System.out.print("Accepting states: ");
		System.err.print("Accepting states: ");
		for(int i = 0 ; i < this.finalStates.length; i++)
		{
			System.out.print(this.getFinalStates()[i] + " ");
			System.err.print(this.getFinalStates()[i] + " ");
		}
		System.out.println();
		System.err.println();
		//Alphabet
		System.out.print("Alphabet: ");
		System.err.print("Alphabet: ");
		for(int i = 0; i < this.alphabet.length; i++)
		{
			System.out.print(this.getAlphabet()[i]);
			System.err.print(this.getAlphabet()[i]);
		}
		System.out.println();
		System.err.println();
		//Transitions
		for(int i = 0; i < this.states; i++)
		{
			for(int j = 0; j < this.alphabet.length; j++)
			{
				System.out.print(this.transitions[i][j] + " ");
				System.err.print(this.transitions[i][j] + " ");
			}
			System.out.println();
			System.err.println();
		}
	}
	
	/*
	 *  CLOSURE PROPERTIES: COMPLMENT AND INTERSECTION
	 */
	
	public DFA complement()
	{
		int[] newFinalStates = new int[this.states - this.finalStates.length];
		int index = 0;
		for(int i = 0; i < this.states; i++)
		{
			if(contains(finalStates, i) == false)
			{
				newFinalStates[index] = i;
				index++;
			}
		}
		DFA dfa = new DFA(this.states, newFinalStates, this.alphabet, this.transitions);
		return dfa;
	}
	
	public static DFA intersection(DFA dfa, DFA dfa1)
	{
		
		int newStates = dfa.states * dfa1.states;
		int[] newFinalStates = new int[dfa.finalStates.length * dfa1.finalStates.length];
		char[] newAlphabet = dfa.alphabet;
		int[][] newTransitions = new int[newStates][newAlphabet.length];
		
		String[] concatenatedStates = new String[newStates];
		int index = 0;
		int index2 = 0;
		for(int i = 0; i < dfa.states; i++)
		{
			for(int j = 0; j < dfa1.states; j++)
			{
				concatenatedStates[index] = i + "" + j;
				if(contains(dfa.finalStates, i) && contains(dfa1.finalStates, j))
				{
					newFinalStates[index2] = index;
					index2++;
				}
				index++;
			}
			 
		}
		
		index = 0;
		for(int i = 0; i < dfa.states; i++)
		{
			for(int j = 0; j < dfa1.states; j++)
			{
				for(int k = 0; k < newAlphabet.length; k++)
				{
					for(int l = 0; l < concatenatedStates.length; l++)
					{
						if(concatenatedStates[l].equals(dfa.transitions[i][k] + "" + dfa1.transitions[j][k]))
						{
							newTransitions[index][k] = l;
						}
					}
				}
				index++;
			}
		}
		
		DFA dfa3 = new DFA(newStates, newFinalStates, newAlphabet, newTransitions);
		return dfa3;
	}
	
	
	
	/*
	 *  SIMULATOR METHODS
	 */
	
	public void checkStrings(String[] lines)
	{
		//checks every lines of the file
		for(int i = 0; i < lines.length; i++)
		{
			int location = 0;
			int currentState = 0;
			boolean valid = false;
			boolean accepting = false;
			
			//checks every character in the respective line
			for(int j = 0; j < lines[i].length(); j++)
			{
				//checks the character to see if its in the alphabet
				for(int l = 0; l < this.alphabet.length; l++)
				{
					//if it is in the alphabet we get in location
					if(lines[i].charAt(j) == this.alphabet[l]) 
					{
						valid = true;
						location = l;
					}
				}
				//once we have checked it appears in the alphabet we update our current state
				if(valid)
				{
					currentState = this.transitions[currentState][location];
				}
			}
			//checks every final state to see if the current state we are in is a final state
			for(int j = 0; j < this.finalStates.length; j++)
			{
				if(currentState == this.finalStates[j])
				{
					accepting = true;
				}
			}
			if(accepting == true)
			{
				System.out.println("accept");
			}
			else System.out.println("reject");
		}
	}
	
	/*
	 *  PROPERTIES METHODS
	 */
	
	public void isEmptyAndIsFinite() {
		int[] reachable = new int[this.states];
		boolean valid = false;
		int index = 1;
		
		//Put the start state in the array
		//For any state in the array add the states that it can reach if they are not already in the array
		reachable[0] = 0;
		for (int i = 0; i < reachable.length; i++) {
			for (int j = 0; j < this.alphabet.length; j++) {
				if(contains(reachable, this.transitions[reachable[i]][j]) == false)
				{
					reachable[index] = this.transitions[reachable[i]][j];
					index++;
				}
			} 
		}
		
		for(int i = 0; i < reachable.length; i++)
		{
			if(contains(finalStates, reachable[i]) == true)
			{
				valid = true;
			}
		}
		
		if(valid) System.out.print("nonempty" + " ");
		else System.out.print("empty" + " ");
		
		int[] reachableFromFinal = new int[this.states + this.finalStates.length];
		index = 0;
		valid = false;
		Arrays.fill(reachableFromFinal, -1);
		//Put the final states that the start state could reach in an array
		for(int i = 0; i < reachable.length; i++)
		{
			if(contains(finalStates, reachable[i]) == true)
			{
				reachableFromFinal[index] = reachable[i];
				index++;
			}
		}
		
		//For every final state in the array add the states that it can reach if they are not already in the array
		int placeholder = index;
		for(int i = 0; i < this.states; i++)
		{
			for(int j = 0; j < this.alphabet.length; j++)
			{
				if(reachableFromFinal[i] != -1)
				{
					if(contains(Arrays.copyOfRange(reachableFromFinal, placeholder, reachableFromFinal.length), this.transitions[reachableFromFinal[i]][j]) == false)
					{
						reachableFromFinal[index] = this.transitions[reachableFromFinal[i]][j];
						System.err.print(reachableFromFinal[index] + " ");
						index++;
					}
				}
			}
		}

		//If the final states that the start state can reach are in the second half of the array, then they can loop back to themself 
		for(int i = 0; i < Arrays.copyOfRange(reachableFromFinal, 0, placeholder).length; i++)
		{
			if(contains(Arrays.copyOfRange(reachableFromFinal, placeholder, reachableFromFinal.length), Arrays.copyOfRange(reachableFromFinal, 0, placeholder)[i])) valid = true;
		}

		if(valid) System.out.println("infinite");
		else System.out.println("finite");
	}
	
	/*
	 *  MINIMIZER METHODS
	 */
	
	public void removeUnreachableStates() {
		int[] reachable = new int[this.states];
		boolean valid = false;
		int index = 1;
		
		//Put the start state in the array
		//For any state in the array add the states that it can reach if they are not already in the array
		reachable[0] = 0;
		for (int i = 0; i < this.states; i++) {
			for (int j = 0; j < this.alphabet.length; j++) {
				if(contains(reachable, this.transitions[reachable[i]][j]) == false)
				{
					reachable[index] = this.transitions[reachable[i]][j];
					index++;
				}
			} 
		}

		//Check to see if the states are in reachable
		for(int i = 0; i < this.states; i++)
		{
			for(int j = 0; j < reachable.length; j++)
			{
				if(i == reachable[j])
				{
					valid = true;
					System.err.print(reachable[j] + " ");
				}
			}
			//if the state isn't in reachable then delete it's edges
			if(valid == false)
			{
				for(int k = 0; k < this.alphabet.length; k++)
				{
					this.transitions[i][k] = -1;
				}
			}
		}
	}
	
	
	
	
	
}
