/*
Name: Anthony Frazier
Date: April 20, 2016
Program Name: Lab12
Description: Lab12
Objective:	Objective:

 It is always a good idea to organize your fears in a data structure. 
  Create a hash table of phobias where each entry the key is the name of the phobia, and the data is the explanation.
 
 
Create a class Phobia
Instance variables
Name
Description
Methods
toString: overrides the base toString and returns the name plus the description
equals: returns true only if both the name and the description match

Create a hash table PhobiaHashTable where each of the buckets corresponds to letters in the English alphabet.
Each index corresponds to a letter of the alphabet, and a hash method should be implement where a 
phobia given will return the index
Every time there is a phobia that starts with the same letter simply add it to the end of the bucket
It is a good idea to make an array of ArrayLists
Methods

add: Adds a new phobia to the hash table
remove: if the phobia�s name and description matches then remove it.  Get over that fear.
lookup: given the phobia�s name return the description
printHashTable: prints out all entry in the hash table

Create a front end where users can add, remove, look up, and print out their fears.


*/
import java.util.ArrayList;
public class PhobiaHashTable {
		private ArrayList<Phobia>[] table;
		public PhobiaHashTable()
		{
			table = new ArrayList[25];
			for (int i = 0; i < table.length; i++)
			{
				table[i] = new ArrayList<Phobia>();
			}
		}
		// Hash Function
		
		private int calculateIndex(Phobia aFear)
		{
			int index = 0;
			char firstChar = ' ';
			String str = aFear.getName();
			str = str.toLowerCase();
			firstChar = str.charAt(0); // Ascii values of a - z = 97 - 122
			index = calculate(firstChar, 0 , 25);
			return index;
			
		}
		
		//public static void quickSort(char firstChar, int left, int right)
		//{
		//	int index = partition(firstChar, left, right);
		//	if (left < index - 1)
		//	{
		//		quickSort(firstChar,left,index-1);
		//	}
		//	if (index < right)
		//	{
		//		quickSort(firstChar,index+1, right);
		//	}
		//		
		//}
		
		public static int calculate(char firstChar, int left, int right)
		{
			int i = left;
			int j = right;
			int pivot = ( 97 + 122 ) / 2;
			
			if (firstChar == pivot)
			{
				return pivot;
			}
			
			if (firstChar < pivot) // Go left
			{
				while ( i < pivot - firstChar)
				{
					i++;
				}
				return i;
			}
			
			if (firstChar > pivot)
			{
				while ( j > pivot - firstChar)
				{
					j--;
				}
				return j;
			}
			return -1;
		}
		public void add(Phobia aFear)
		{
			int index = calculateIndex(aFear);
			table[index].add(aFear);
		}
		
		public void remove(Phobia aFear)
		{
			int index = calculateIndex(aFear);
			if (table[index].contains(aFear))
			{
			table[index].remove(aFear);
			}
			else
			{
				System.out.println("Phobia not found");
			}
		}
		
		public void lookUp(Phobia aFear)
		{
			int index = calculateIndex(aFear);
			if (table[index].contains(aFear))
			{
			System.out.println(aFear.toString());
			}
			else
			{
				System.out.println("Phobia not found");
			}
		}
		
		public void printHashTable()
		{
			int i = 0;
			while ( i < 26)
			{
				int j = 0;
				while ( j < table[i].size() )
				{
					if (table[i] != null)
					{
						System.out.println(table[i].toString());
						j++;
					}
					return;
				}
				i++;
			}
		}
	}

