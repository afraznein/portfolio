/*
Name: Anthony Frazier
Date: February 17, 2016
Program Name: Homework05
Description: Homework05
Objective:	Objective:

Write a program that implements the following sorting algorithms:
Selection Sort
Bubble Sort
Merge Sort
Quick Sort
 
Next test the efficiency of each using the following steps:
Create an array of randomly selected whole numbers of 1,000 elements
Each value is selected from the range 0-999
Sort that array using each method
Make sure to copy the initial array into a new one so that the tests will be accurate and not be sorting sorted arrays
Show that each method successfully sorted the array
Count the number of checks each method had to perform before fully sorting the array
Keep track of the total number of checks for each algorithm
Perform this 20 times and keep track of the running average
 
Finally at the end give the average number of checks using each algorithm.

Have been unable to get quick sort working

*/
import java.util.*;
public class Homework5 
{
	public static int bubbleCount = 0, selectionCount = 0, mergeCount = 0, quickCount = 0;
	
	public static void main(String[] args) 
	{
		double rand = 0.0, bubbleAvg = 0.0, selectionAvg = 0.0, mergeAvg = 0.0, quickAvg = 0.0;
		int value = 0, generateCount = 0, count = 0;
		
		int[] arrInitial = new int[1000];
		int[] arrBubble = new int[1000];
		int[] arrSelection = new int[1000];
		int[] arrMerge = new int[1000];
		int[] arrQuick = new int[1000];
		
		while ( count < 20 )
			{
			System.out.println("Generating array of size 1000 with values 0-999.");
			while ( generateCount < 1000)		// Generate array of size 1000 with values from 0 - 999
			{
				rand = Math.random() * 1000.0;		
				value = (int)rand;
				
				arrInitial[generateCount] = value;
				generateCount++;
			}
			System.out.println("Array generated.");
			for (int i: arrInitial)
			{
				System.out.println(i);
			}
			System.out.println("Creating multiple copies of array for the purposes of sorting.");
			for ( int i = 0; i < 1000; i++)			// Create copies of unsorted array
			{
				arrBubble[i] = arrInitial[i];
			}
			for ( int i = 0; i < 1000; i++)			// Create copies of unsorted array
			{
				arrSelection[i] = arrInitial[i];
			}
			for ( int i = 0; i < 1000; i++)			// Create copies of unsorted array
			{
				arrMerge[i] = arrInitial[i];
			}
			for ( int i = 0; i < 1000; i++)			// Create copies of unsorted array
			{
				arrQuick[i] = arrInitial[i];
			}
			
			System.out.println("Sorting array with bubble sort.");
			bubbleSort(arrBubble);
			System.out.println("Bubble Sorted Array");
			for (int i: arrBubble)				// Print out the sorted array
			{
				System.out.println(i);
			}
			System.out.println("The bubble sort took : "+bubbleCount+" checks.");
			
			System.out.println("Sorting array with selection sort.");
			arrSelection = recursiveSelectionSort(arrSelection, 0, 0, 0);
			System.out.println("Selection Sorted Array");		// Print out the sorted array
			for (int i: arrSelection)
			{
				System.out.println(i);
			}
			System.out.println("The selection sort took : "+selectionCount+" checks.");
			
			System.out.println("Sorting array with merge sort");
			mergeSort(arrMerge);
			System.out.println("Merge Sorted Array");
			for (int i: arrMerge)
			{
				System.out.println(i);
			}
			System.out.println("The merge sort took : "+mergeCount+" checks.");
			
			System.out.println("Sorting array with quick sort");
			//quickSort(arrQuick, 0, 999);
			System.out.println("Quick Sorted Array");
			for (int i: arrMerge)
			{
				System.out.println(i);
			}
			System.out.println("The quick sort took : "+quickCount+" checks.");
			
			bubbleAvg = bubbleAvg + bubbleCount;
			selectionAvg = selectionAvg + selectionCount;
			mergeAvg = mergeAvg + mergeCount;
			quickAvg = quickAvg + quickCount;
			
			count++;
		}
		bubbleAvg = bubbleAvg / 20;
		selectionAvg = selectionAvg / 20 ;
		mergeAvg = mergeAvg / 20;
		quickAvg = quickAvg / 20;
		
		System.out.println("\n\n");
		System.out.println("The bubble sort average was : "+bubbleAvg);
		System.out.println("The selection sort average was : "+selectionAvg);
		System.out.println("The merge sort average was : "+mergeAvg);
		System.out.println("The quick sort average was : "+quickAvg);
	}
	public static void bubbleSort(int[]a )
	{
		int decreasing = a.length;
		while ( decreasing >= 1)
		{	
			for ( int i = 1; i < decreasing ; i++)		// Sort the array
			{
				if (a[i-1] < a[i])
				{
					int temp = a[i-1];
					a[i-1] = a[i];
					a[i] = temp;
					bubbleCount++;
				}
						
			}
			decreasing--;
		}
		
	}
	
	public static int[] recursiveSelectionSort(int[]a, int startIndex, int smallestIndex, int currentIndex)
	{
		if (startIndex >= a.length-1)	// check to see if we're done
		{
			return a;
		}
		if (currentIndex >= a.length-1) // check if current iteration is done
		{
			if (startIndex != smallestIndex)
			{
				int temp = a[startIndex];
				a[startIndex] = a[smallestIndex];
				a[smallestIndex] = temp;
			}
			startIndex++;
			selectionCount++;
			return recursiveSelectionSort(a,startIndex,startIndex,startIndex+1);
		}
		else	// still looking for that smaller value
		{
			if (a[smallestIndex] > a[currentIndex] )
			{
				smallestIndex=currentIndex;
			}
			selectionCount++;
			return recursiveSelectionSort(a,startIndex+1,smallestIndex,currentIndex);
		}
	}
	
	public static void quickSort(int[]a, int left, int right)
	{
		int index = partition(a, left, right);
		if ( left < index - 1)
		{
			quickCount++;
			quickSort(a,left,index-1);
		}
		if ( index < right )
		{
			quickCount++;
			quickSort(a,index+1,right);
		}
	}
	
	public static int partition(int[] a, int left, int right)
	{
		int i = left;
		int j = right;
		
		int pivot = a[(left+right)/2];
		while ( i <= j )
		{
			while ( a[i] < pivot )
			{
				i++;
			}
			while ( a[j] > pivot )
			{
				j--;
			}
			if ( i <= j )
			{
				int temp = a[i];
				a[i] = a[j];
				a[j] = temp;
				i++;
				j++;
			}
		}
		return i;
	}
	
	public static void mergeSort(int[] arrMerge)
	{
		mergeCount++;
		int size = arrMerge.length-1;
		int mid = size/2;
		int leftSide = mid;
		int rightSide = size - mid;
		int[] left = new int[leftSide];				// Need two arrays, left and right
		int[] right = new int[rightSide];				
		
		if ( size < 2)
		{
			return;
		}
		for (int i = 0; i < mid; i++)		// Fill left
		{
			left[i] = arrMerge[i];
		}
		for ( int i = mid; i < size; i++)		// Fill right
		{
			right[i-mid] = arrMerge[i];
		}
		mergeSort(left);		// Recursive calls
		mergeSort(right);
		merge(left, right, arrMerge);		// Merge
	}
	
	public static void merge(int[] left, int[] right, int[] arrMerge)
	{
		int leftSize = left.length;		// This code is from class, modified to work with arrayList
		int rightSize = right.length;
		int i = 0, j = 0, k = 0;
		
		while ( i < leftSize && j < rightSize)		// Make sure both i index and j index have not exceeded arrayList size
		{
			if (left[i] <= right[j] )		// Left side check
			{
				arrMerge[k] = left[i];
				i++;
				k++;
			}
			
			else 			// Else right side is less
			{
				arrMerge[k] = right[j];
				j++;
				k++;
			}
		}
		while ( i < leftSize )	// Fill in remaining elements
		{
			arrMerge[k] = left[i];
			i++;
			k++;
		}
		while ( j < rightSize )
		{
			arrMerge[k] = right[j];
			j++;
			k++;
		}
	}

}
