/*
Name: Anthony Frazier
Date: February 17, 2016
Program Name: Lab06
Description: Lab06
Objective:	Objective:

Write a program that does the following:

Create and populate an ArrayList of integers with 10 to 20 numbers where each number could be between 0-99.  Then print that out.
Sort the ArrayList in ascending order either by writing QuickSort or MergeSort.  You may NOT use the collections sort.  Write it yourself. Then print that out.
Populate a Queue with the ArrayList, and then dequeue each element and print it out until the queue is empty.
Populate a Stack with the ArrayList, and then pop each element and print it out until the stack is empty.
Do this process 3 times.
 
Notes:
Make sure to use import.util.*;
Queues have a different kind of constructor from the rest
Queue<Integer> q = new LinkedList<Integer>();


*/
import java.util.*;
public class Lab06 
{
	public static void main(String[] args) 
	{
		ArrayList<Integer> aList = new ArrayList<Integer>();	// Create new Array list
		double rand = 0.0;
		int value = 0, loopCheck = 0, count = 0;

		while ( count < 3)
		{
			while ( loopCheck < 10 || loopCheck > 20)		// Generate random numbers between 10 and 20
			{
				rand = Math.random() * 20.0;		
				loopCheck = (int)rand;
			}
			System.out.println("Populating the Array List of Size "+loopCheck);		// Generate array list of size ^ random
			for (int i = 0; i < loopCheck; i++)
			{
				rand = Math.random() * 100.0;		
				value = (int)rand;
				aList.add(value);
			}
			System.out.println("This array list contains :  ");		// Print out list
			for (int i: aList)
			{
				System.out.println(i);
			}
			System.out.println("Sorting...");
			mergeSort(aList);				// Call merge sort
			System.out.println("This sorted array list contains : ");
			for (int i: aList)
			{
				System.out.println(i);
			}
			
			Queue<Integer> que = new LinkedList<Integer>();		// Create a queue
			System.out.println("Generating a Queue with elements from the sorted Array List : ");
			for (int i = 0; i < aList.size(); i++)
			{
				que.add(aList.get(i) );	// enqueue
			}
			System.out.println("Dequeueing and printing each element from the Queue ");
			while ( que.isEmpty() == false )
			{
				System.out.println( que.remove() ); // dequeue
			}
			
			Stack<Integer> stack = new Stack<Integer>();		// Create a stack
			System.out.println("Generating a Stack with elements from the sorted Array List : ");
			for (int i = 0; i < aList.size(); i++)
			{
				stack.push(aList.get(i) );	// push
			}
			System.out.println("Popping and printing all elements from the Stack ");
			while ( stack.isEmpty() == false )
			{
				System.out.println( stack.pop() ); // pop
			}
			aList.clear();		// Clear the Array List 			
			count++;
		} // end outer while loop
	}
	
	public static void mergeSort(ArrayList<Integer> aList)
	{
		int size = aList.size();
		int mid = size/2;
		int leftSide = mid;
		int rightSide = size - mid;
		ArrayList<Integer> leftList = new ArrayList<Integer>();		// Need two new array lists, left and right side
		ArrayList<Integer> rightList = new ArrayList<Integer>();
		
		if ( size < 2)
		{
			return;
		}
		for (int i = 0; i < mid; i++)		// Fill left
		{
			leftList.add( aList.get(i) );
		}
		for ( int i = mid; i < size; i++)		// Fill right
		{
			rightList.add( aList.get(i) );
		}
		mergeSort(leftList);		// Recursive calls
		mergeSort(rightList);
		merge(leftList, rightList, aList);		// Merge
	}
	
	public static void merge(ArrayList<Integer> leftList, ArrayList<Integer> rightList, ArrayList<Integer> aList)
	{
		int leftSize = leftList.size();		// This code is from class, modified to work with arrayList
		int rightSize = rightList.size();
		int i = 0, j = 0, k = 0;
		
		while ( i < leftSize && j < rightSize)		// Make sure both i index and j index have not exceeded arrayList size
		{
			if (leftList.get(i) <= rightList.get(j) )		// Left side check
			{
				aList.set(k, leftList.get(i));
				i++;
				k++;
			}
			
			else 			// Else right side is less
			{
				aList.set(k, rightList.get(j));
				j++;
				k++;
			}
		}
		while ( i < leftSize )	// Fill in remaining elements
		{
			aList.set(k, leftList.get(i));
			i++;
			k++;
		}
		while ( j < rightSize )
		{
			aList.set(k, rightList.get(j));
			j++;
			k++;
		}
	}

}
